package com.stayrascal.cloud.order.functional.resource;

import com.github.springtestdbunit.bean.DatabaseConfigBean;
import com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DbUnitConfig {
    public static final Logger LOGGER = LoggerFactory.getLogger(DbUnitConfig.class);

    @Bean
    public DatabaseConfigBean dbUnitDatabaseConfig() {
        DatabaseConfigBean configBean = new DatabaseConfigBean();
        configBean.setEscapePattern("`?`");
        return configBean;
    }

    @Bean(name = "dbUnitDatabaseConnection")
    public DatabaseDataSourceConnection dbUnitDatabaseConnection(DataSource dataSource, DatabaseConfigBean configBean) {
        DatabaseDataSourceConnectionFactoryBean factoryBean = new DatabaseDataSourceConnectionFactoryBean(dataSource);
        factoryBean.setDatabaseConfig(configBean);
        try {
            return factoryBean.getObject();
        } catch (Exception e) {
            LOGGER.error("Failed to create database connection dur to exception", e);
            throw new RuntimeException(e);
        }
    }
}
