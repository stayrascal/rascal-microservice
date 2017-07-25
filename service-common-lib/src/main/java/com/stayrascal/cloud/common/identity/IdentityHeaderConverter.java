package com.stayrascal.cloud.common.identity;

import com.stayrascal.cloud.common.contract.auth.Authorization;
import com.stayrascal.cloud.common.contract.auth.Identity;
import com.stayrascal.cloud.common.contract.auth.IdentityType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

public class IdentityHeaderConverter {
    private static final Logger LOGGER = LoggerFactory.getLogger(IdentityHeaderConverter.class);
    private static final String IDENTITY_TYPE = "cloud.identityType";
    private static final String IDENTITY_ID = "cloud.identityId";
    private static final String AUTHORIZATION_SIZE = "cloud.authorizations.size";
    private static final String AUTHORIZATION_ORGANIZATION_ID = "cloud.authorizations.%d.organizationId";
    private static final String AUTHORIZATION_ROLE_ID = "cloud.authorizations.%d.roleId";

    public IdentityHeaderConverter() {
    }

    public static Identity headerToIdentity(MultivaluedMap<String, String> headers) {
        LOGGER.info("try get identity from headers: {}", headers);
        if(headers.containsKey("cloud.identityType") && headers.containsKey("cloud.identityId") && headers.containsKey("cloud.authorizations.size")) {
            Identity identity = new Identity();
            identity.setIdentityType(IdentityType.valueOf((String)headers.getFirst("cloud.identityType")));
            identity.setIdentityId((String)headers.getFirst("cloud.identityId"));
            int size = Integer.parseInt((String)headers.getFirst("cloud.authorizations.size"));
            List<Authorization> authorizations = new ArrayList(size);

            for(int i = 0; i < size; ++i) {
                String orgIdHeader = String.format("cloud.authorizations.%d.organizationId", new Object[]{Integer.valueOf(i)});
                String orgId = (String)headers.getFirst(orgIdHeader);
                String roleIdHeader = String.format("cloud.authorizations.%d.roleId", new Object[]{Integer.valueOf(i)});
                String roleId = (String)headers.getFirst(roleIdHeader);
                authorizations.add(new Authorization(orgId, roleId));
            }

            identity.setAuthorizations(authorizations);
            return identity;
        } else {
            return null;
        }
    }

    public static MultivaluedMap<String, String> identityToHeaders(Identity identity) {
        MultivaluedMap<String, String> headers = new MultivaluedHashMap();
        headers.putSingle("cloud.identityType", identity.getIdentityType().name());
        headers.putSingle("cloud.identityId", identity.getIdentityId());
        int authSize = identity.getAuthorizations().size();
        headers.putSingle("cloud.authorizations.size", String.valueOf(authSize));

        for(int i = 0; i < authSize; ++i) {
            Authorization auth = (Authorization)identity.getAuthorizations().get(i);
            String orgHeader = String.format("cloud.authorizations.%d.organizationId", new Object[]{Integer.valueOf(i)});
            String roleHeader = String.format("cloud.authorizations.%d.roleId", new Object[]{Integer.valueOf(i)});
            headers.putSingle(orgHeader, auth.getOrganizationId());
            headers.putSingle(roleHeader, auth.getRoleId());
        }

        return headers;
    }
}
