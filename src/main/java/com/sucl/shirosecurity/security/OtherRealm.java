package com.sucl.shirosecurity.security;

import com.sucl.shirosecurity.entity.Account;
import com.sucl.shirosecurity.service.AccountService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class OtherRealm extends AuthorizingRealm {

    @Autowired
    private AccountService accountService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        Account account = accountService.getAcountByTelephone(username);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(account,account.getPassword(),getName());
        return authenticationInfo;
    }
}
