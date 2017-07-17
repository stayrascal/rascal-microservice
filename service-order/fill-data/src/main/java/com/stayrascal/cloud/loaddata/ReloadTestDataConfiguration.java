package com.stayrascal.cloud.loaddata;

import com.stayrascal.cloud.common.lib.constant.ErrorCode;
import com.stayrascal.cloud.common.lib.jersey.exception.ServerErrorException;
import com.stayrascal.cloud.loaddata.service.ReloadTestDataService;
import org.dbunit.DatabaseUnitException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.sql.SQLException;

@Configuration
public class ReloadTestDataConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReloadTestDataConfiguration.class);
    @Value("${cloud.allowReloadTestData}")
    private Boolean allowReloadTestData;
    @Autowired
    private ReloadTestDataService reloadTestDataService;

    public ReloadTestDataConfiguration() {
    }

    @Bean
    public Endpoint reloadTestDataEndpoint() {
        return new Endpoint() {
            public String getId() {
                return "reload-data";
            }

            public boolean isEnabled() {
                return ReloadTestDataConfiguration.this.allowReloadTestData.booleanValue();
            }

            public boolean isSensitive() {
                return false;
            }

            public Object invoke() {
                try {
                    ReloadTestDataConfiguration.this.reloadTestDataService.reloadData();
                    return "";
                } catch (SQLException | IOException | DatabaseUnitException var2) {
                    ReloadTestDataConfiguration.LOGGER.error("reload test data failed: {}", var2);
                    throw new ServerErrorException(ErrorCode.INTERNAL_ERROR, "reload test data failed: {}", new Object[]{var2});
                }
            }
        };
    }
}
