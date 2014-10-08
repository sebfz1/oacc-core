/*
 * Copyright 2009-2014, Acciente LLC
 *
 * Acciente LLC licenses this file to you under the
 * Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in
 * writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.acciente.rsf.sql.internal.persister;

import com.acciente.rsf.AccessControlException;
import com.acciente.rsf.Resource;
import com.acciente.rsf.ResourceCreatePermission;
import com.acciente.rsf.ResourcePermission;
import com.acciente.rsf.sql.internal.persister.id.DomainId;
import com.acciente.rsf.sql.internal.persister.id.Id;
import com.acciente.rsf.sql.internal.persister.id.ResourceClassId;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GrantResourceCreatePermissionPostCreatePersister extends Persister {
   private final SQLStrings sqlStrings;

   public GrantResourceCreatePermissionPostCreatePersister(SQLStrings sqlStrings) {
      this.sqlStrings = sqlStrings;
   }

   public Set<ResourceCreatePermission> getPostCreatePermissions(SQLConnection connection,
                                                                 Resource accessorResource,
                                                                 Id<ResourceClassId> resourceClassId,
                                                                 Id<DomainId> resourceDomainId) throws AccessControlException {
      SQLStatement statement = null;
      try {
         SQLResult resultSet;
         Set<ResourceCreatePermission> resourceCreatePermissions = new HashSet<>();

         // collect the non-system permissions the accessor has to the specified resource class
         statement = connection.prepareStatement(sqlStrings.SQL_findInGrantResourceCreatePermissionPostCreate_ResourceClassName_PostCreatePermissionName_PostCreateIsWithGrant_IsWithGrant_InheritLevel_DomainLevel_BY_AccessorID_AccessedDomainID_ResourceClassID);
         statement.setResourceId(1, accessorResource);
         statement.setResourceDomainId(2, resourceDomainId);
         statement.setResourceClassId(3, resourceClassId);
         resultSet = statement.executeQuery();

         while (resultSet.next()) {
            ResourcePermission resourcePermission;

            resourcePermission = ResourcePermission.getInstance(
                  resultSet.getString("PostCreatePermissionName"),
                  resultSet.getBoolean("PostCreateIsWithGrant"),
                  resultSet.getInteger("InheritLevel"),
                  resultSet.getInteger("DomainLevel"));

            resourceCreatePermissions
                  .add(ResourceCreatePermission.getInstance(resourcePermission, resultSet.getBoolean("IsWithGrant")));
         }
         resultSet.close();

         return resourceCreatePermissions;
      }
      catch (SQLException e) {
         throw new AccessControlException(e);
      }
      finally {
         closeStatement(statement);
      }
   }

   public Map<String, Map<String, Set<ResourceCreatePermission>>> getPostCreatePermissions(SQLConnection connection,
                                                                                           Resource accessorResource)
         throws AccessControlException {
      SQLStatement statement = null;

      try {
         Map<String, Map<String, Set<ResourceCreatePermission>>> createPermissionsMap = new HashMap<>();
         SQLResult resultSet;

         // collect the non-system permissions that the accessor has and add it to createALLPermissionsMap
         statement = connection.prepareStatement(sqlStrings.SQL_findInGrantResourceCreatePermissionPostCreate_ResourceDomainName_ResourceClassName_PostCreatePermissionName_PostCreateIsWithGrant_IsWithGrant_InheritLevel_DomainLevel_BY_AccessorID);
         statement.setResourceId(1, accessorResource);
         resultSet = statement.executeQuery();

         while (resultSet.next()) {
            final String resourceDomainName;
            final String resourceClassName;
            Map<String, Set<ResourceCreatePermission>> permissionsForResourceDomain;
            Set<ResourceCreatePermission> permissionsForResourceClass;

            resourceDomainName = resultSet.getString("DomainName");
            resourceClassName = resultSet.getString("ResourceClassName");

            if ((permissionsForResourceDomain = createPermissionsMap.get(resourceDomainName)) == null) {
               createPermissionsMap.put(resourceDomainName,
                                        permissionsForResourceDomain = new HashMap<>());
            }

            if ((permissionsForResourceClass = permissionsForResourceDomain.get(resourceClassName)) == null) {
               permissionsForResourceDomain.put(resourceClassName,
                                                permissionsForResourceClass = new HashSet<>());
            }

            ResourcePermission
                  resourcePermission
                  = ResourcePermission.getInstance(resultSet.getString("PostCreatePermissionName"),
                                                   resultSet.getBoolean("PostCreateIsWithGrant"),
                                                   resultSet.getInteger("InheritLevel"),
                                                   resultSet.getInteger("DomainLevel"));

            permissionsForResourceClass
                  .add(ResourceCreatePermission.getInstance(resourcePermission, resultSet.getBoolean("IsWithGrant")));
         }
         resultSet.close();

         return createPermissionsMap;
      }
      catch (SQLException e) {
         throw new AccessControlException(e);
      }
      finally {
         closeStatement(statement);
      }
   }

   public void addPostCreatePermissions(SQLConnection connection,
                                        Resource accessorResource,
                                        Id<ResourceClassId> accessedResourceClassId,
                                        Id<DomainId> accessedResourceDomainId,
                                        Set<ResourceCreatePermission> requestedResourceCreatePermissions,
                                        Resource grantorResource) throws AccessControlException {
      SQLStatement statement = null;
      try {
         // add the new create non-system permissions
         statement = connection.prepareStatement(sqlStrings.SQL_createInGrantResourceCreatePermissionPostCreate_WITH_AccessorID_GrantorID_AccessedDomainID_IsWithGrant_PostCreateIsWithGrant_ResourceClassID_PostCreatePermissionName);
         for (ResourceCreatePermission resourceCreatePermission : requestedResourceCreatePermissions) {
            if (!(resourceCreatePermission.isSystemPermission()
                  || resourceCreatePermission.getPostCreateResourcePermission().isSystemPermission())) {
               statement.setResourceId(1, accessorResource);
               statement.setResourceId(2, grantorResource);
               statement.setResourceDomainId(3, accessedResourceDomainId);
               statement.setBoolean(4, resourceCreatePermission.isWithGrant());
               statement.setBoolean(5, resourceCreatePermission.getPostCreateResourcePermission().isWithGrant());
               statement.setResourceClassId(6, accessedResourceClassId);
               statement.setString(7, resourceCreatePermission.getPostCreateResourcePermission().getPermissionName());

               assertOneRowInserted(statement.executeUpdate());
            }
         }
      }
      catch (SQLException e) {
         throw new AccessControlException(e);
      }
      finally {
         closeStatement(statement);
      }
   }

   public void removePostCreatePermissions(SQLConnection connection,
                                           Resource accessorResource,
                                           Id<ResourceClassId> accessedResourceClassId,
                                           Id<DomainId> accessedResourceDomainId) throws AccessControlException {
      SQLStatement statement = null;
      try {
         // revoke any existing create non-system permissions this accessor has to this resource domain + resource class
         statement = connection.prepareStatement(sqlStrings.SQL_removeInGrantResourceCreatePermissionPostCreate_BY_AccessorID_AccessedDomainID_ResourceClassID);
         statement.setResourceId(1, accessorResource);
         statement.setResourceDomainId(2, accessedResourceDomainId);
         statement.setResourceClassId(3, accessedResourceClassId);
         statement.executeUpdate();
      }
      catch (SQLException e) {
         throw new AccessControlException(e);
      }
      finally {
         closeStatement(statement);
      }
   }
}