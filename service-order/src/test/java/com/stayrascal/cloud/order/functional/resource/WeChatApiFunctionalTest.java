package com.stayrascal.cloud.order.functional.resource;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.stayrascal.cloud.order.contract.dto.OrderDto;
import com.stayrascal.cloud.order.contract.dto.TransactionDto;
import com.stayrascal.cloud.order.contract.enumeration.TransactionStatus;
import com.stayrascal.cloud.order.facade.OrderFacade;
import com.stayrascal.cloud.order.facade.TransactionFacade;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;

@DatabaseSetup("classpath:WeChatApiFunctionalTest.xml")
@DatabaseTearDown("classpath:WeChatApiFunctionalTest.xml")
public class WeChatApiFunctionalTest extends BaseFunctionalTest {

    @Autowired
    private OrderFacade orderFacade;

    @Autowired
    private TransactionFacade transactionFacade;

    @Test
    public void shouldSuccessCreateTransaction() throws Exception {
        String weChatNotifyBody = "<xml>\n"
                + "  <appid><![CDATA[wxcf0848f15d297fb3]]></appid>\n"
                + "  <attach><![CDATA[支付测试]]></attach>\n"
                + "  <bank_type><![CDATA[CFT]]></bank_type>\n"
                + "  <fee_type><![CDATA[CNY]]></fee_type>\n"
                + "  <is_subscribe><![CDATA[Y]]></is_subscribe>\n"
                + "  <mch_id><![CDATA[10014392]]></mch_id>\n"
                + "  <nonce_str><![CDATA[5d2b6c2a8db53831f7eda20af46e531c]]></nonce_str>\n"
                + "  <openid><![CDATA[oUpF8uMEb4qRXf22hE3X68TekukE]]></openid>\n"
                + "  <out_trade_no><![CDATA[transactionId]]></out_trade_no>\n"
                + "  <result_code><![CDATA[SUCCESS]]></result_code>\n"
                + "  <return_code><![CDATA[SUCCESS]]></return_code>\n"
                + "  <sign><![CDATA[DE73EB470AC0EB87A1B4EEB2DE6B01C8]]></sign>\n"
                + "  <sub_mch_id><![CDATA[10000100]]></sub_mch_id>\n"
                + "  <time_end><![CDATA[20140903131540]]></time_end>\n"
                + "  <total_fee>100</total_fee>\n"
                + "  <trade_type><![CDATA[JSAPI]]></trade_type>\n"
                + "  <transaction_id><![CDATA[1004400740201409030005092168]]></transaction_id>\n"
                + "</xml>\n";
        given()
                .body(weChatNotifyBody)
                .when()
                .post("we-chat/notify")
                .then()
                .statusCode(is(200));

        TransactionDto transactionDto = transactionFacade.getTransaction("transactionId");
        assertThat(transactionDto.getStatus()).isEqualTo(TransactionStatus.CONFIRMED);

        final OrderDto orderDto = orderFacade.getOrder("openOrderId");
        assertThat(orderDto.getTransactionId()).isNotNull();
        assertThat(orderDto.getPickupCode()).isEqualTo("0");
    }
}
