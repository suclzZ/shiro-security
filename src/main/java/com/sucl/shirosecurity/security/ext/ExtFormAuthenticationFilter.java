package com.sucl.shirosecurity.security.ext;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *  结合FormLoginToken实现各种token的登录
 */
public class ExtFormAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        FormLoginToken formLoginToken =
                new FormLoginToken(getUsername(request),getPassword(request),isRememberMe(request),getHost(request));
        return formLoginToken;
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        //实现ajax登录
        return super.onLoginSuccess(token, subject, request, response);
    }

    @Override
    protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {
        //1 异常转换成业务异常
        //2 通过ajax登录时返回
        super.setFailureAttribute(request, ae);
    }
}
