package com.stayrascal.cloud.loaddata.service;

import com.stayrascal.cloud.common.lib.constant.ErrorCode;
import com.stayrascal.cloud.common.lib.jersey.exception.ServerErrorException;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.AmbiguousTableNameException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.internal.SessionImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Component
@Transactional
public class ReloadTestDataService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReloadTestDataService.class);
    private static final List<String> EXCLUDED_TABLE_NAMES = Arrays.asList(new String[]{"schema_version"});
    private static final String TEST_DATA_RESOURCE = "test_datas.xml";
    @Autowired
    private EntityManager entityManager;

    public ReloadTestDataService() {
    }

    public void reloadData() throws DatabaseUnitException, SQLException, IOException {
        ClassPathResource classPathResource = new ClassPathResource("test_datas.xml");
        if (!classPathResource.exists()) {
            LOGGER.error("could not got test data: {}", "test_datas.xml");
            throw new ServerErrorException(ErrorCode.INTERNAL_ERROR, "could not got test data: {}", new Object[]{"test_datas.xml"});
        } else {
            DatabaseConnection connection = new DatabaseConnection(((SessionImpl) this.entityManager.unwrap(SessionImpl.class)).connection());
            connection.getConfig().setProperty("http://www.dbunit.org/properties/escapePattern", "`");
            QueryDataSet queryDataSet = new QueryDataSet(connection);
            Arrays.asList(connection.createDataSet().getTableNames()).stream().filter((name) -> {
                return !EXCLUDED_TABLE_NAMES.contains(name);
            }).forEach((name) -> {
                try {
                    queryDataSet.addTable(name);
                } catch (AmbiguousTableNameException var3) {
                    LOGGER.warn("add table error: {}", var3.getCause());
                }

            });
            DatabaseOperation.DELETE_ALL.execute(connection, queryDataSet);

            try {
                FlatXmlDataSet xmlDataSet = (new FlatXmlDataSetBuilder()).build(classPathResource.getInputStream());
                DatabaseOperation.INSERT.execute(connection, xmlDataSet);
            } catch (Exception var5) {
                throw new ServerErrorException(ErrorCode.INTERNAL_ERROR, "insert data failed: {}", new Object[]{var5.getCause()});
            }
        }
    }
}
