package com.stayrascal.cloud.order.functional.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.stayrascal.cloud.order.contract.command.CreateOrderCommand;
import com.stayrascal.cloud.order.contract.command.UpdateOrderCommand;
import com.stayrascal.cloud.order.contract.enumeration.OrderStatus;
import com.stayrascal.cloud.product.contract.dto.StoreProductDto;

import com.exmertec.dummie.Dummie;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@DatabaseSetup("classpath:OrderResourceFunctionalTest.xml")
@DatabaseTearDown("classpath:OrderResourceFunctionalTest.xml")
public class OrderResourceFunctionalTest extends BaseFunctionalTest {
    public static final String OPEN_ORDER_ID = "openOrderId";

    @Autowired
    private MockStoreProductServiceClient mockStoreProductServiceClient;

    @Test
    public void shouldSuccessGetOrderByOrderId() throws Exception {
        given()
                .header("Content-Type", "application/json")
                .when()
                .get("orders/" + OPEN_ORDER_ID)
                .then()
                .statusCode(is(200))
                .body("id", equalTo(OPEN_ORDER_ID));
    }

    @Test
    public void shouldThrow404WhenGetOrderWithInvalidOrderId() throws Exception {
        String invalidOrderId = "invalidOrderId";
        given()
                .header("Content-Type", "application/json")
                .when()
                .get("orders/" + invalidOrderId)
                .then()
                .statusCode(is(404));
    }

    @Test
    public void shouldListOrdersForGivenStore() throws Exception {
        given()
                .header("Content-Type", "application/json")
                .param("store_id", "storeId1")
                .param("page_size", "20")
                .param("page_index", "0")
                .param("sort_type", "ASC")
                .param("sort_by", "timeCreated")
                .when()
                .get("orders")
                .then()
                .statusCode(is(200))
                .body("totalCount", equalTo(1))
                .body("items[0].storeId", equalTo("storeId1"));
    }

    @Test
    public void shouldCreateOrderSuccessfully() throws Exception {
        CreateOrderCommand createOrderCommand = Dummie.prepare(CreateOrderCommand.class)
                .override(BigDecimal.class, BigDecimal.ONE).build();
        createOrderCommand.setUserId("test-user");
        createOrderCommand.getItems().forEach(item -> {
            item.setQuantity(5);
            item.setStoreProductId("storeProductId");
            item.setStoreItemId("storeItemId");
            item.setStoreProductName(null);
        });

        StoreProductDto storeProduct = Dummie.prepare(StoreProductDto.class)
                .override(BigDecimal.class, BigDecimal.ONE).build();
        storeProduct.setId("storeProductId");
        storeProduct.getItems().forEach(item -> {
            item.setId("storeItemId");
            item.setQuantity(10);
        });
        mockStoreProductServiceClient.setStoreProduct(storeProduct);

        given()
                .header("Content-Type", "application/json")
                .body(createOrderCommand)
                .when()
                .post("orders")
                .then()
                .log().all()
                .statusCode(is(201));
    }

    @Test
    public void shouldUpdateOrderNoteSuccessfully() throws Exception {
        UpdateOrderCommand updateOrderComman = new UpdateOrderCommand();
        updateOrderComman.setNote("test note");

        given()
                .header("Content-Type", "application/json")
                .body(updateOrderComman)
                .when()
                .put("orders/" + OPEN_ORDER_ID)
                .then()
                .log().all()
                .statusCode(is(200))
                .body("note", equalTo("test note"));
    }

    @Test
    public void shouldCloseOrderSuccessfully() throws Exception {
        UpdateOrderCommand updateOrderCommand = new UpdateOrderCommand();
        updateOrderCommand.setStatus(OrderStatus.CLOSED);

        given()
                .header("Content-Type", "application/json")
                .body(updateOrderCommand)
                .when()
                .put("orders/" + OPEN_ORDER_ID)
                .then()
                .log().all()
                .statusCode(is(200))
                .body("status", equalTo("CLOSED"));
    }
}
