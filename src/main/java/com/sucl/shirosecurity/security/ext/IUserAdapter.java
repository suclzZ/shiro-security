package com.sucl.shirosecurity.security.ext;

/**
 * 认证授权是获取相关信息的适配器
 * 需要实现的接口
 * @param <K>
 * @param <T>
 */
public interface IUserAdapter<K extends IUser,T extends IAuthToken> {

    public abstract boolean supports(IAuthToken authToken);

    public abstract boolean supports(IUser user);

    public abstract IAuthInfo getRealmUserInfo(K k);

    public abstract IAuthInfo getRealmUserInfo(T t);
}
