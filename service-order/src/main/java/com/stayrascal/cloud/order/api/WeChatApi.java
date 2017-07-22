package com.stayrascal.cloud.order.api;

import com.stayrascal.cloud.common.constant.ErrorCode;
import com.stayrascal.cloud.common.jersey.exception.ServerErrorException;
import com.stayrascal.cloud.order.contract.enumeration.TransactionProvider;
import com.stayrascal.cloud.order.contract.result.WeChatPurchaseResult;
import com.stayrascal.cloud.order.facade.TransactionFacade;
import com.stayrascal.cloud.order.service.WeChatService;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.bean.pay.WxPayOrderNotifyResponse;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Component
@Path("we-chat")
public class WeChatApi {
    public static final Logger LOGGER = LoggerFactory.getLogger(WeChatApi.class);

    private final WeChatService weChatService;

    private final TransactionFacade transactionFacade;

    @Value("${cloud.weChat.redirectUrl}")
    private String weChatRedirectUrl;

    @Autowired
    public WeChatApi(WeChatService weChatService, TransactionFacade transactionFacade) {
        this.weChatService = weChatService;
        this.transactionFacade = transactionFacade;
    }

    @GET
    @Path("authorize-redirect")
    public Response userAuthorize(@QueryParam("code") String code,
                                  @QueryParam("state") String state) {
        try {
            final WxMpOAuth2AccessToken userInfo = weChatService.getUserInfo(code);
            return Response.temporaryRedirect(UriBuilder.fromUri(weChatRedirectUrl)
                    .queryParam("state", state)
                    .queryParam("open_id", userInfo.getOpenId())
                    .build()).build();
        } catch (WxErrorException e) {
            LOGGER.error("Receive wechat authorize failed, {}", e.getMessage());
            throw new ServerErrorException(ErrorCode.INTERNAL_ERROR, "Receive wechat authorize failed, {}", e.getMessage());
        }
    }

    @POST
    @Path("notify")
    public Response purchaseNotify(@Context HttpServletRequest request) {
        String response;
        try {
            final WeChatPurchaseResult result = weChatService.purchaseResultNotify(request);
            transactionFacade.confirmTransaction(result.getTransactionId(), TransactionProvider.WECHAT,
                    result.getWeChatTradeId(), result.getTotalAmount());
            response = WxPayOrderNotifyResponse.success("success");
        } catch (Exception e) {
            LOGGER.error("receive wechat notify failed, {}", e.getMessage());
            response = WxPayOrderNotifyResponse.fail(e.getMessage());
        }
        return Response.ok().entity(response).build();
    }
}
