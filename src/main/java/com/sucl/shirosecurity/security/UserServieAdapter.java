package com.sucl.shirosecurity.security;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;

public interface UserServieAdapter {
    
    boolean support(AuthenticationToken token);

    AuthenticationInfo getAuthenticationInfo(AuthenticationToken token);
}
