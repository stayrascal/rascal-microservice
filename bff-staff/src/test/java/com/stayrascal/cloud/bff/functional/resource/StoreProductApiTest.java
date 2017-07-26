package com.stayrascal.cloud.bff.functional.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import com.stayrascal.cloud.bff.product.request.BatchUpdateStoreProductCommand;
import com.stayrascal.cloud.bff.product.request.UpdateStoreProductWithIdCommand;

import com.exmertec.dummie.Dummie;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

public class StoreProductApiTest extends BaseFunctionalTest {

    @Test
    public void shouldBatchUpdateStoreProductsStatus() throws Exception {
        UpdateStoreProductWithIdCommand command = Dummie
                .prepare(UpdateStoreProductWithIdCommand.class)
                .override(BigDecimal.class, new BigDecimal(0))
                .build();
        BatchUpdateStoreProductCommand batchCommand = new BatchUpdateStoreProductCommand();
        batchCommand.setCommands(Arrays.asList(command));

        given()
                .header("Content-Type", "application/json")
                .body(batchCommand)
                .when()
                .put("store-products/batch")
                .then()
                .statusCode(is(200));
    }
}
