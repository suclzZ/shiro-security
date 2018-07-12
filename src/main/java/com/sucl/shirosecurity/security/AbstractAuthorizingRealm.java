package com.sucl.shirosecurity.security;

import com.sun.corba.se.impl.oa.toa.TOA;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public abstract class AbstractAuthorizingRealm extends AuthorizingRealm {

    private AuthcInfoService authcInfoService;

    public AuthcInfoService getAuthcInfoService() {
        return authcInfoService;
    }

    public void setAuthcInfoService(AuthcInfoService authcInfoService) {
        this.authcInfoService = authcInfoService;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return authcInfoService.getAuthorizationInfo(principals);
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        return authcInfoService.getAuthenticationInfo(token);
    }
}
