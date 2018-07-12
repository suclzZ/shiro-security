package com.sucl.shirosecurity.security.ext;

/**
 * 代理获取用户认证 授权相关信息
 */
public interface UserService {

    public abstract IAuthInfo getRealmUserInfo(IUser user);

    public abstract IAuthInfo getRealmUserInfo(IAuthToken authToken);

    public abstract IUserAdapter getUserAdapter(String adapter);

}
