package com.stayrascal.cloud.order.service;

import com.stayrascal.cloud.common.lib.constant.ErrorCode;
import com.stayrascal.cloud.common.lib.jersey.exception.ServerErrorException;
import com.stayrascal.cloud.common.lib.jpa.UniqueKeyGenerator;
import com.stayrascal.cloud.order.contract.enumeration.WeChatTradeType;
import com.stayrascal.cloud.order.contract.result.WeChatPurchaseResult;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpPayService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.pay.request.WxPayUnifiedOrderRequest;
import me.chanjar.weixin.mp.bean.pay.result.WxPayOrderNotifyResult;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Map;

@Component
public class WeChatService {
    private static final Logger LOG = LoggerFactory.getLogger(WeChatService.class);

    @Value("${cloud.transaction.weChat.appId}")
    private String appId;

    @Value("${cloud.transaction.weChat.partnerKey}")
    private String partnerKey;

    @Value("${cloud.transaction.weChat.secret}")
    private String secret;

    @Value("${cloud.transaction.weChat.token}")
    private String token;

    @Value("${cloud.transaction.weChat.encodingAesKey}")
    private String encodingAesKey;

    @Value("${cloud.transaction.weChat.mchId}")
    private String mchId;

    @Value("${cloud.transaction.weChat.notifyUrl}")
    private String notifyUrl;

    @Autowired
    private UniqueKeyGenerator generator;

    private WxMpService getWeChatService() {
        WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
        config.setAppId(appId);
        config.setSecret(secret);
        config.setToken(token);
        config.setPartnerKey(partnerKey);
        config.setAesKey(encodingAesKey);
        config.setNotifyURL(notifyUrl);

        WxMpService wxService = new WxMpServiceImpl();
        wxService.setWxMpConfigStorage(config);

        return wxService;
    }


    public Map<String, String> purchase(String openId, WeChatTradeType tradeType, String ip,
                                        BigDecimal totalAmount, String transactionId) {
        WxMpPayService payService = getWeChatService().getPayService();
        try {
            return payService.getPayInfo(generateWeChatPaymentRequest(openId, tradeType, ip, totalAmount,
                    transactionId));
        } catch (WxErrorException e) {
            throw new ServerErrorException(ErrorCode.INTERNAL_ERROR,
                    "WeChat purchase failed, transactionId: {}, reason:{}", tradeType, e.getMessage());
        }
    }

    private WxPayUnifiedOrderRequest generateWeChatPaymentRequest(String openId, WeChatTradeType tradeType,
                                                                  String ip, BigDecimal totalAmount,
                                                                  String transactionId) {
        return WxPayUnifiedOrderRequest.builder()
                .appid(appId)
                .mchId(mchId)
                .nonceStr(generator.generateKey().replaceAll("-", ""))
                .openid(openId)
                .spbillCreateIp(ip)
                .tradeType(tradeType.name())
                .outTradeNo(transactionId)
                .totalFee(1)//totalAmount.multiply(new BigDecimal(100)).intValue())
                .body("Cloud Purchase")
                .build();
    }

    public WeChatPurchaseResult purchaseResultNotify(HttpServletRequest request) throws Exception {
        WxMpPayService payService = getWeChatService().getPayService();
        String xmlResult = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
        WxPayOrderNotifyResult result = payService.getOrderNotifyResult(xmlResult);
        String transactionId = result.getOutTradeNo();
        String tradeNo = result.getTransactionId();
        Integer totalFee = result.getTotalFee();
        return new WeChatPurchaseResult(transactionId, tradeNo, totalFee.doubleValue() / 100.0D,
                WeChatTradeType.valueOf(result.getTradeType()));
    }

    public WxMpOAuth2AccessToken getUserInfo(String code) throws WxErrorException {
        return getWeChatService().oauth2getAccessToken(code);
    }
}
