package com.stayrascal.cloud.store.functional.resource;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.junit.Test;

@DatabaseSetup("classpath:StoreResourceFunctionalTest.xml")
@DatabaseTearDown("classpath:StoreResourceFunctionalTest.xml")
public class StoreResourceFunctionalTest extends BaseFunctionalTest {

    @Test
    public void name() throws Exception {

    }
}
