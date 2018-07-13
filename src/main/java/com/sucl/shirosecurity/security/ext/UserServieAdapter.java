package com.sucl.shirosecurity.security.ext;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;

public interface UserServieAdapter {
    
    boolean support(AuthenticationToken token);

    AuthenticationInfo getAuthenticationInfo(AuthenticationToken token);
}
