/*
 * Copyright 2009-2015, Acciente LLC
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
package com.acciente.oacc;

import junit.framework.JUnit4TestAdapter;
import junit.framework.TestSuite;

public class TestAccessControlSuite  {

   public static TestSuite suite() {
      TestSuite suite = new TestSuite();

      suite.addTest(new JUnit4TestAdapter(TestAccessControl_assertDomainCreatePermissions.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_assertDomainPermissions.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_assertGlobalResourcePermissions.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_assertPostCreateDomainPermissions.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_assertPostCreateResourcePermissions.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_assertResourceCreatePermissions.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_assertResourcePermissions.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_authenticate.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_createAuthenticatableResource.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_createDomain.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_createResource.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_createResourceClass.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_createResourcePermission.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_customAuthenticationProvider.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_deleteDomain.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_deleteResource.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_getAccessorResourcesByResourcePermissions.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_getAuthenticatedResource.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_getDomainCreatePermissions.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_getDomainDescendants.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_getDomainNameByResource.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_getDomainPermissions.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_getEffectiveDomainCreatePermissions.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_getEffectiveDomainPermissions.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_getEffectiveGlobalResourcePermissions.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_getEffectiveResourceCreatePermissions.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_getEffectiveResourcePermissions.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_getGlobalResourcePermissions.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_getResourceClassInfo.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_getResourceClassInfoByResource.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_getResourceClassNames.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_getResourceCreatePermissions.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_getResourcePermissionNames.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_getResourcePermissions.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_getResourcesByResourcePermissions.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_getResourcesByResourcePermissionsAndDomain.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_getSessionResource.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_grantDomainCreatePermissions.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_grantDomainPermissions.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_grantGlobalResourcePermissions.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_grantResourceCreatePermissions.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_grantResourcePermissions.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_hasDomainCreatePermissions.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_hasDomainPermissions.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_hasGlobalResourcePermissions.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_hasPostCreateDomainPermissions.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_hasPostCreateResourcePermissions.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_hasResourceCreatePermissions.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_hasResourcePermissions.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_impersonate.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_revokeDomainCreatePermissions.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_revokeDomainPermissions.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_revokeResourceCreatePermissions.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_revokeResourcePermissions.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_revokeGlobalResourcePermissions.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_serialize.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_setCredentials.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_setDomainCreatePermissions.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_setDomainPermissions.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_setGlobalResourcePermissions.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_setResourceCreatePermissions.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_setResourcePermissions.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_unauthenticate.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_unauthenticatedApiCalls.class));
      suite.addTest(new JUnit4TestAdapter(TestAccessControl_unimpersonate.class));

      return suite;
   }
}
