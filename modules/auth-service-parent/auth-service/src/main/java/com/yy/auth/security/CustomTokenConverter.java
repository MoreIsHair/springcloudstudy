package com.yy.auth.security;

import com.yy.common.core.constant.CommonConstant;
import com.yy.common.core.enums.LoginType;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author YY
 * @date 2020/1/3
 * @description 扩展JwtAccessTokenConverter,加入自定义
 */
public class CustomTokenConverter extends JwtAccessTokenConverter {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        final Map<String, Object> additionalInfo = new HashMap<>(16);
        String grantType = authentication.getOAuth2Request().getGrantType();
        // 加入登录类型，用户名/手机号
        if (grantType.equalsIgnoreCase(CommonConstant.GRANT_TYPE_PASSWORD)) {
            additionalInfo.put("loginType", LoginType.PWD.getType());
        } else if (grantType.equalsIgnoreCase(CommonConstant.GRANT_TYPE_MOBILE)) {
            additionalInfo.put("loginType", LoginType.SMS.getType());
        } else if (grantType.equalsIgnoreCase(LoginType.WECHAT.getType())) {
            additionalInfo.put("loginType", LoginType.WECHAT.getType());
        }
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return super.enhance(accessToken, authentication);
    }
}
