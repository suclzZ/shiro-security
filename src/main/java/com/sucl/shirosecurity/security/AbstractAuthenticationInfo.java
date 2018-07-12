package com.sucl.shirosecurity.security;

import org.apache.shiro.authc.Account;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.MergableAuthenticationInfo;
import org.apache.shiro.authc.SaltedAuthenticationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.Collection;

public class AbstractAuthenticationInfo implements Account,MergableAuthenticationInfo,SaltedAuthenticationInfo {

    @Override
    public void merge(AuthenticationInfo authenticationInfo) {

    }

    @Override
    public ByteSource getCredentialsSalt() {
        return null;
    }

    @Override
    public PrincipalCollection getPrincipals() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Collection<String> getRoles() {
        return null;
    }

    @Override
    public Collection<String> getStringPermissions() {
        return null;
    }

    @Override
    public Collection<Permission> getObjectPermissions() {
        return null;
    }
}
