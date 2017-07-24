package com.stayrascal.cloud.product.functional.resource;

import com.stayrascal.cloud.common.constant.EnvProfile;
import com.stayrascal.cloud.product.Application;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = "eureka.client.enabled:false")
@ContextConfiguration
@Rollback
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionDbUnitTestExecutionListener.class })
@ActiveProfiles({EnvProfile.TEST})
public abstract class BaseFunctionalTest {
    @LocalServerPort
    private int port;

    @Before
    public void setUp() throws Exception {
        initAssured(port);
    }

    private static void initAssured(int port) {
        RestAssured.port = port;
        RestAssured.basePath = "/rest/";
    }
}
