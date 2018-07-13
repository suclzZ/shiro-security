package com.sucl.shirosecurity.security.ext;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class FormLoginRealm extends AuthorizingRealm implements ApplicationContextAware {

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Object principal = principals.getPrimaryPrincipal();

        if ((principal instanceof IUser)) {
            return userService.getRealmUserInfo((IUser)principal);
        }
        return new SimpleAuthorizationInfo();
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //token 从 form提交过来
        return userService.getRealmUserInfo((FormLoginToken)token);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        setAuthenticationTokenClass(FormLoginToken.class);
    }
}
