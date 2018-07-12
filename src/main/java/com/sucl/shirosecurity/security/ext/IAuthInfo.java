package com.sucl.shirosecurity.security.ext;

import org.apache.shiro.authc.Account;
import org.apache.shiro.authc.MergableAuthenticationInfo;
import org.apache.shiro.authc.SaltedAuthenticationInfo;

/**
 * 自定义 认证、授权接口
 * 实现接口 {@link AbstractAuthInfo}
 */
public interface IAuthInfo extends Account,MergableAuthenticationInfo,SaltedAuthenticationInfo {

}
