package com.college.upgradetest;

import com.college.Application;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Base for the upgrade safety-net tests. Starts the real application (embedded Tomcat, JSPs, Spring Security)
 * on a random port against the LOCAL MySQL copy ({@code local} profile, {@code application-local.properties}).
 *
 * <p>Most tables are MyISAM (no transactions), so tests cannot roll back. Every row a test creates is marked
 * with {@link TestData#MARK} and removed by {@link TestData#cleanup}. The schema is never touched
 * ({@code ddl-auto=none}).
 *
 * <p>All Spring-Boot-version-specific test wiring is in this class only.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class, TestDoublesConfig.class})
@WebIntegrationTest(randomPort = true, value = {
        "spring.jpa.hibernate.ddl-auto=none",
        "logging.level.org.hibernate.SQL=WARN"
})
@ActiveProfiles("local")
public abstract class AbstractAppTest {

    @Value("${local.server.port}")
    protected int port;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private Environment env;

    @Autowired
    protected TestDoublesConfig.RecordingMailSender mail;

    @Autowired
    protected TestDoublesConfig.RecordingMessageSender sms;

    protected JdbcTemplate jdbc;
    protected TestData data;

    private static boolean prepared;

    @Before
    public void prepareTestData() throws Exception {
        jdbc = new JdbcTemplate(dataSource);
        data = new TestData(jdbc);
        synchronized (AbstractAppTest.class) {
            if (!prepared) {
                assertLocalDatabase();
                data.cleanup();          // leftovers of an aborted run
                data.createUsers();
                // Own connection: the application's pool may already be closed when the JVM shuts down.
                final DriverManagerDataSource own = new DriverManagerDataSource(env.getProperty("spring.datasource.url"),
                        env.getProperty("spring.datasource.username"), env.getProperty("spring.datasource.password"));
                Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                    @Override
                    public void run() {
                        new TestData(new JdbcTemplate(own)).cleanup();
                    }
                }));
                prepared = true;
            }
        }
        mail.clear();
        sms.sent.clear();
    }

    /** Refuses to run against anything but a database on this machine. */
    private void assertLocalDatabase() throws Exception {
        try (Connection c = dataSource.getConnection()) {
            String url = c.getMetaData().getURL();
            if (!(url.contains("//localhost") || url.contains("//127.0.0.1"))) {
                fail("Upgrade tests write test rows and must only run against a local database, not " + url.replaceAll("\\?.*", ""));
            }
        }
    }

    protected WebSession anonymous() {
        return new WebSession(port);
    }

    /** Logs in as the given test user and checks the role-specific landing redirect. */
    protected WebSession loginAs(TestData.TestUser user) throws Exception {
        WebSession s = new WebSession(port);
        WebSession.Response r = s.login(user.username, TestData.PASSWORD);
        assertEquals("login of " + user.username + " should redirect: " + r, 302, r.status);
        assertTrue("login of " + user.username + " failed: " + r, !r.redirectPath().startsWith("/login"));
        return s;
    }

    protected static void assertCompletePage(String what, WebSession.Response r) {
        assertEquals(what + " status: " + r, 200, r.status);
        assertTrue(what + " is not a completely rendered page: " + r, r.body().contains("</html>"));
    }

    protected static void assertContains(String what, WebSession.Response r, String expected) {
        assertTrue(what + " should contain '" + expected + "': " + r, r.body().contains(expected));
    }
}
