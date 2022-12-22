package cz.cvut.fit.tomanma9.tjvhospital.dao;

// HEAVY inspiration from https://ajayiyengar.com/2020/05/18/integration-testing-using-testcontainers-in-a-spring-boot-2-jpa-application/

import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.MountableFile;

public abstract class AbstractPostgresContainerBaseTest {

    protected static final PostgreSQLContainer postgresContainer;

    static {

        postgresContainer = new PostgreSQLContainer("postgres:15.1")
                .withDatabaseName("hospital")
                .withUsername("hospital")
                .withPassword("hospital");
        postgresContainer.withInitScript("create-script.sql");
                //.withLogConsumer();
                //.withCopyFileToContainer(MountableFile.forClasspathResource("create-script.sql"), "/docker-entrypoint-initdb.d/");
        System.out.println(postgresContainer.getLogs());
        postgresContainer.start();
        System.out.println("\n\nAFTERSTAERT\n\n");
        System.out.println(postgresContainer.getLogs());
    }

    static class PropertiesInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        // applicationContextInitializer is capable of setting ApplicationContext for our tests
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues
                    .of("spring.datasource.driver-class.name=" + postgresContainer.getDriverClassName(),
                            "spring.datasource.rul=" + postgresContainer.getJdbcUrl(),
                            "spring.datasource.username=" + postgresContainer.getUsername(),
                            "spring.datasource.password=" + postgresContainer.getPassword())
                    .applyTo(configurableApplicationContext.getEnvironment());
        }
    }
}
