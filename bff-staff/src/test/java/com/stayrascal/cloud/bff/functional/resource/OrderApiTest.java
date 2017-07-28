package com.stayrascal.cloud.bff.functional.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.stayrascal.cloud.bff.functional.resource.mock.MockMemberServiceClient;
import com.stayrascal.cloud.bff.functional.resource.mock.MockOrderServiceClient;
import com.stayrascal.cloud.bff.functional.resource.mock.MockStoreProductServiceClient;
import com.stayrascal.cloud.bff.functional.resource.mock.MockTransactionServiceClient;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderApiTest extends BaseFunctionalTest {

    @Autowired
    private MockOrderServiceClient mockOrderClient;

    @Autowired
    private MockMemberServiceClient mockMemberClient;

    @Autowired
    private MockTransactionServiceClient mockTransactionClient;

    @Autowired
    private MockStoreProductServiceClient mockStoreProductClient;

    @Test
    public void shouldGetOpenOrderDetailSuccessfully() throws Exception {
        mockOrderClient.setOrderJson(jsonResource("/dummy/orders/open.json"));
        mockMemberClient.setJson(jsonResource("/dummy/users/normal.json"));
        mockStoreProductClient.setStoreProductJson(jsonResource("/dummy/products/store-products/normal.json"));

        String orderId = "orderId";

        given()
                .header("Content-Type", "application/json")
                .when()
                .get("orders/" + orderId)
                .then()
                .statusCode(is(200))
                .body("id", equalTo(orderId));
    }

    @Test
    @Ignore
    public void shouldGetProcessingOrderSuccessfully() throws Exception {
        mockOrderClient.setOrderJson(jsonResource("/dummy/orders/processing.json"));
        mockMemberClient.setJson(jsonResource("/dummy/users/normal.json"));
        mockTransactionClient.setJson(jsonResource("/dummy/transactions/normal.json"));

        String orderId = "orderId";

        given()
                .header("Content-Type", "application/json")
                .when()
                .get("orders/" + orderId)
                .then()
                .statusCode(is(200))
                .body("id", equalTo(orderId))
                .body("transactionProvider", equalTo("ALIPAY"));
    }

    @Test
    public void shouldListOrdersSuccessfully() throws Exception {
        mockOrderClient.setOrderListJson(jsonResource("/dummy/orders/list.json"));
        mockMemberClient.setJson(jsonResource("/dummy/users/normal.json"));
        mockTransactionClient.setJson(jsonResource("/dummy/transactions/normal.json"));

        String orderId = "orderId";

        given()
                .header("Content-Type", "application/json")
                .param("store_id", "storeId")
                .param("page_size", "20")
                .param("page_index", "0")
                .param("sort_by", "timeCreated")
                .param("sort_type", "ASC")
                .when()
                .get("orders")
                .then()
                .log().all()
                .statusCode(is(200))
                .body("totalCount", equalTo(1))
                .body("items[0].id", equalTo(orderId))
                .body("items[0].totalItems", equalTo(5));
    }
}
