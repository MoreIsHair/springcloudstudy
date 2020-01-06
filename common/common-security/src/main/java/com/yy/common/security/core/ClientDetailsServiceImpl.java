package com.yy.common.security.core;

import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;

/**
 * @author YY
 * @date 2020/1/3
 * @description
 */
public class ClientDetailsServiceImpl extends JdbcClientDetailsService {

    public ClientDetailsServiceImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
        return super.loadClientByClientId(clientId);
    }
}
