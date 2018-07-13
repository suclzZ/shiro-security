package com.sucl.shirosecurity.security.ext;

import java.util.List;

/**
 * 实体需要实现的接口
 */
public interface IUser {

    public abstract List<String> roleIds();

    public abstract String getLoginName();

    public abstract String getPassword();
}
