package com.sucl.shirosecurity.security;

import com.sucl.shirosecurity.entity.Account;
import com.sucl.shirosecurity.service.AccountService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 登录名登录
 */
public class DefaultRealm extends AuthorizingRealm {

    @Autowired
    private AccountService accountService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
<<<<<<< HEAD
        Object principal = principalCollection.getPrimaryPrincipal();
        //此时是account
        System.out.println(principal);//构建SimpleAuthenticationInfo的第一个参数
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRole("admin");
        return authorizationInfo;
=======
        //必须根据登录名等唯一字段增加权限，不然多realm都会执行此方法，若有一个有权限，另一个默认也会加上
        AuthorizationInfo authorizationInfo = super.getAuthorizationInfo(principalCollection);

        org.apache.shiro.authc.Account principal = (org.apache.shiro.authc.Account) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
        List<String> roles = new ArrayList<String>();
        roles.add("admin");
        simpleAuthorInfo.addRoles(roles);
    //    simpleAuthorInfo.addStringPermissions(permissionList);
        return simpleAuthorInfo;
>>>>>>> origin
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
<<<<<<< HEAD
        Account account = accountService.getAcountByLoginname(username);//用户名、邮箱、手机号 分别查询
=======
        Account account = accountService.getAcountByLoginname(username);
        if(account == null) return null;
>>>>>>> origin
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(account,account.getPassword(),account.getUsername());
        return authenticationInfo;
    }
}
