package com.sucl.shirosecurity.security.ext;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 对UsernamePasswordToken的扩展
 * 比如手机号+验证码、动态码、 token等登录
 */
public class FormLoginToken extends UsernamePasswordToken implements IAuthToken{
    private String dynamicCode;
    private boolean isDynamicTrue;
    private String loginType;

    public FormLoginToken(String username, String password, boolean rememberMe, String host){
        super( username, password,  rememberMe, host);
    }

    public String getDynamicCode() {
        return dynamicCode;
    }

    public void setDynamicCode(String dynamicCode) {
        this.dynamicCode = dynamicCode;
    }

    public boolean isDynamicTrue() {
        return isDynamicTrue;
    }

    public void setDynamicTrue(boolean dynamicTrue) {
        isDynamicTrue = dynamicTrue;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }
}
