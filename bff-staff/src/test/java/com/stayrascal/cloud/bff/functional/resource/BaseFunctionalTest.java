package com.stayrascal.cloud.bff.functional.resource;

import com.stayrascal.cloud.Application;
import com.stayrascal.cloud.common.constant.EnvProfile;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
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

import java.io.IOException;
import java.net.URL;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
@Rollback
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class
})
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
        RestAssured.basePath = "/desktop/";
    }

    protected String jsonResource(String resourceName) throws IOException {
        URL url = BaseFunctionalTest.class.getResource(resourceName);
        return Resources.toString(url, Charsets.UTF_8);
    }
}
