package com.sucl.shirosecurity.security.ext;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.SaltedAuthenticationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.subject.MutablePrincipalCollection;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 认证 授权对应的AuthenticationInfo、AuthorizationInfo进行装饰与统一
 *
 */
public abstract class AbstractAuthInfo implements IAuthInfo{
    protected IUser user;
    private PrincipalCollection principals;
    private Object credentials;
    private ByteSource credentialsSalt;

    public AbstractAuthInfo() {
    }

    public AbstractAuthInfo(IUser user, Object credentials)
    {
        this.principals = new SimplePrincipalCollection(user, FormLoginRealm.class.getName());
        this.credentials = credentials;
        this.user = user;
    }

    public AbstractAuthInfo(IUser user, Object hashedCredentials, ByteSource credentialsSalt, String realmName)
    {
        this.principals = new SimplePrincipalCollection(user, realmName);
        this.credentials = hashedCredentials;
        this.credentialsSalt = credentialsSalt;
        this.user = user;
    }

    public PrincipalCollection getPrincipals() {
        return this.principals;
    }

    public void setPrincipals(PrincipalCollection principals)
    {
        this.principals = principals;
    }

    public Object getCredentials() {
        return this.credentials;
    }

    public void setCredentials(Object credentials)
    {
        this.credentials = credentials;
    }

    public ByteSource getCredentialsSalt()
    {
        return this.credentialsSalt;
    }

    public void setCredentialsSalt(ByteSource salt)
    {
        this.credentialsSalt = salt;
    }

    public void merge(AuthenticationInfo info)
    {
        if ((info == null) || (info.getPrincipals() == null) || (info.getPrincipals().isEmpty())) {
            return;
        }

        if (this.principals == null) {
            this.principals = info.getPrincipals();
        } else {
            if (!(this.principals instanceof MutablePrincipalCollection)) {
                this.principals = new SimplePrincipalCollection(this.principals);
            }
            ((MutablePrincipalCollection)this.principals).addAll(info.getPrincipals());
        }

        if ((this.credentialsSalt == null) && ((info instanceof SaltedAuthenticationInfo))) {
            this.credentialsSalt = ((SaltedAuthenticationInfo)info).getCredentialsSalt();
        }

        Object thisCredentials = getCredentials();
        Object otherCredentials = info.getCredentials();

        if (otherCredentials == null) {
            return;
        }

        if (thisCredentials == null) {
            this.credentials = otherCredentials;
            return;
        }

        if (!(thisCredentials instanceof Collection)) {
            Set newSet = new HashSet();
            newSet.add(thisCredentials);
            setCredentials(newSet);
        }

        Collection credentialCollection = (Collection)getCredentials();
        if ((otherCredentials instanceof Collection))
            credentialCollection.addAll((Collection)otherCredentials);
        else
            credentialCollection.add(otherCredentials);
    }

    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof AbstractAuthInfo)) return false;

        AbstractAuthInfo that = (AbstractAuthInfo)o;

        return this.principals != null ? this.principals.equals(that.principals) : that.principals == null;
    }

    public int hashCode()
    {
        return this.principals != null ? this.principals.hashCode() : 0;
    }

    public String toString()
    {
        return this.principals.toString();
    }

    public Collection<String> getRoles()
    {
        return this.user.roleIds();
    }

    public Collection<String> getStringPermissions()
    {
        return null;
    }

    public Collection<Permission> getObjectPermissions()
    {
        return null;
    }
    public IUser getUser() {
        return this.user;
    }
    public void setUser(IUser user) {
        this.user = user;
    }
}
