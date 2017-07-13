package com.stayrascal.cloud.common.lib.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.stayrascal.cloud.common.auth.Authorization;
import com.stayrascal.cloud.common.auth.Identity;
import com.stayrascal.cloud.common.auth.IdentityType;
import com.stayrascal.cloud.common.lib.constant.DefaultValues;
import com.stayrascal.cloud.common.lib.constant.ErrorCode;
import com.stayrascal.cloud.common.lib.jersey.exception.ForbiddenException;
import com.stayrascal.cloud.common.lib.jersey.exception.InternalErrorException;
import org.joda.time.DateTime;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JwtTokenCodec {
    private String secret;
    private int expireMinute;

    public JwtTokenCodec(String secret, int expireMinute) {
        this.secret = secret;
        this.expireMinute = expireMinute;
    }

    public Identity decodeToken(String token) {
        try {
            JWTVerifier build = JWT.require(Algorithm.HMAC256(this.secret)).build();
            DecodedJWT decodedJWT = build.verify(token);
            return this.identityFromClaim(decodedJWT);
        } catch (UnsupportedEncodingException var4) {
            throw new InternalErrorException(ErrorCode.INTERNAL_ERROR, "Unsupported Encoding");
        } catch (JWTVerificationException var5) {
            throw new ForbiddenException(ErrorCode.INTERNAL_ERROR, "Forbidden Token");
        }
    }

    public String encodeToken(Identity identity) {
        try {
            Builder builder = this.identityToClaim(identity);
            Date expiresAt = DateTime.now().plusMinutes(this.expireMinute).toDate();
            return builder.withExpiresAt(expiresAt).sign(Algorithm.HMAC256(this.secret));
        } catch (UnsupportedEncodingException var4) {
            throw new InternalErrorException(ErrorCode.INTERNAL_ERROR, "Unsupported Encoding");
        }
    }

    private Identity identityFromClaim(DecodedJWT decodedJWT) {
        IdentityType identityType = IdentityType.valueOf(decodedJWT.getClaim("identityType").asString());
        String identityId = decodedJWT.getClaim("identityId").asString();
        Integer authorizationSize = decodedJWT.getClaim("authorizationSize").asInt();
        List<Authorization> authorizations = new ArrayList(authorizationSize.intValue());

        for (int i = 0; i < authorizationSize.intValue(); ++i) {
            String orgIdKey = String.format("authorization[%d].organizationId", new Object[]{Integer.valueOf(i)});
            String roleIdKey = String.format("authorization[%d].roleId", new Object[]{Integer.valueOf(i)});
            String organizationId = decodedJWT.getClaim(orgIdKey).asString();
            String roleId = decodedJWT.getClaim(roleIdKey).asString();
            authorizations.add(new Authorization(organizationId, roleId));
        }

        return new Identity(identityType, identityId, authorizations);
    }

    private Builder identityToClaim(Identity identity) {
        List<Authorization> authorizations = identity.getAuthorizations();
        int authorizationSize = authorizations.size();
        Builder builder = JWT.create().withClaim("identityType", identity.getIdentityType().name()).withClaim("identityId", identity.getIdentityId()).withClaim("authorizationSize", Integer.valueOf(authorizationSize));

        for (int i = 0; i < authorizationSize; ++i) {
            String orgIdKey = String.format("authorization[%d].organizationId", new Object[]{Integer.valueOf(i)});
            String roleIdKey = String.format("authorization[%d].roleId", new Object[]{Integer.valueOf(i)});
            Authorization auth = (Authorization) authorizations.get(i);
            builder.withClaim(orgIdKey, auth.getOrganizationId());
            builder.withClaim(roleIdKey, auth.getRoleId());
        }

        return builder;
    }

    public static void main(String[] args) {
        JwtTokenCodec jwtTokenCodec = new JwtTokenCodec(DefaultValues.Auth.tokenSecret(), -1000);
        String token = jwtTokenCodec.encodeToken(new Identity(IdentityType.MEMBER, "member", new ArrayList(0)));
        System.out.println(token);
    }
}
