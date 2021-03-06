package org.openmrs.reference;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.reference.page.HeaderPage;
import org.openmrs.reference.page.HomePage;
import org.openmrs.uitestframework.test.TestBase;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class LoginTest extends TestBase {
    private HeaderPage headerPage;
    private HomePage homePage;

    @Before
    public void setUp() {
        headerPage = new HeaderPage(driver);
        homePage = new HomePage(driver);
    }
    
    @After
    public void logout() {
    	headerPage.logOut();
    	assertPage(loginPage);
    }
    
    @Test
    public void verifyModulesAvailableOnHomePage() throws Exception {
    	login();
        assertPage(homePage);
        assertTrue(homePage.isFindAPatientAppPresent());
        assertTrue(homePage.isActiveVisitsAppPresent());
        assertTrue(homePage.isRegisterPatientCustomizedForRefAppPresent());
        assertTrue(homePage.isCaptureVitalsAppPresent());
        assertTrue(homePage.isConfigureMetadataAppPresent());
        assertTrue(homePage.isSystemAdministrationAppPresent());
        assertThat(homePage.numberOfAppsPresent(), is(8));
    }

    @Test
    public void verifyClerkModulesAvailableOnHomePage() throws Exception {
    	assertPage(loginPage);
    	loginPage.loginAsClerk();
    	assertPage(homePage);
    	assertTrue(homePage.isActiveVisitsAppPresent());
    	assertTrue(homePage.isRegisterPatientCustomizedForRefAppPresent());
        assertThat(homePage.numberOfAppsPresent(), is(2));
    }
    
    @Test
    public void verifyDoctorModulesAvailableOnHomePage() throws Exception {
    	assertPage(loginPage);
    	loginPage.login("doctor", "Doctor123");
    	assertPage(homePage);
        assertTrue(homePage.isFindAPatientAppPresent());
        assertTrue(homePage.isActiveVisitsAppPresent());
        assertThat(homePage.numberOfAppsPresent(), is(2));
    }
    
    @Test
    public void verifyNurseModulesAvailableOnHomePage() throws Exception {
    	assertPage(loginPage);
    	loginPage.loginAsNurse();
    	assertPage(homePage);
    	assertTrue(homePage.isFindAPatientAppPresent());
    	assertTrue(homePage.isActiveVisitsAppPresent());
    	assertTrue(homePage.isCaptureVitalsAppPresent());
        assertThat(homePage.numberOfAppsPresent(), is(3));
    }

    @Test
    public void verifySysadminModulesAvailableOnHomePage() throws Exception {
        assertPage(loginPage);
        loginPage.loginAsSysadmin();
        assertPage(homePage);
        assertTrue(homePage.isConfigureMetadataAppPresent());
        assertTrue(homePage.isSystemAdministrationAppPresent());
        assertThat(homePage.numberOfAppsPresent(), is(2));
    }

}
