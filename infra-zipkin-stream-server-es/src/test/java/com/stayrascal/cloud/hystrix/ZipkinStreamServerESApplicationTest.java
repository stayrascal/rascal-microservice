package com.stayrascal.cloud.hystrix;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "eureka.client.enabled:false")
@ActiveProfiles("test")
public class ZipkinStreamServerESApplicationTest {

    @Test
    public void contextLoad() {
    }
}