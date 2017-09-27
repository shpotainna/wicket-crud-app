package com.shpota.users;

import com.shpota.users.pages.HomePage;
import com.shpota.users.pages.LoginPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.giffing.wicket.spring.boot.starter.configuration.extensions.external.spring.security.SecureWebSession;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@EnableWebSecurity
public class LoginPageTest {

	private static final String USERNAME = "admin";
	private static final String PASSWORD = "admin";
	
	private WicketTester tester;

	@Autowired
	private WebApplication wicketApplication;

	@Autowired
	private ApplicationContext applicationContextMock;
	
	
	@Before
	public void setUp() {
		ReflectionTestUtils.setField(wicketApplication, "applicationContext", applicationContextMock);
		tester = new WicketTester(wicketApplication);
		login(USERNAME, PASSWORD);
	}
	
	@Test
	public void test_something() {
		System.out.println("test");
	}
	
	private void login(String username, String password) {
		SecureWebSession session = (SecureWebSession) tester.getSession();
		session.signOut();
		tester.startPage(LoginPage.class);
		FormTester formTester = tester.newFormTester("loginForm");
		formTester.setValue("username", username);
		formTester.setValue("password", password);
		formTester.submit();
		tester.assertNoErrorMessage();
		tester.assertNoInfoMessage();
		//tester.assertRenderedPage(HomePage.class);
	}
}
