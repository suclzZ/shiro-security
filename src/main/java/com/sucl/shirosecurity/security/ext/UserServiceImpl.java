package com.sucl.shirosecurity.security.ext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.OrderComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService ,ApplicationContextAware {

    private List<IUserAdapter> userAdapters;
    private Map<String, IUserAdapter> userAdapterBeans;

    @Override//鉴权
    public IAuthInfo getRealmUserInfo(IUser user) {
        if (this.userAdapters != null) {
            for (IUserAdapter adapter : this.userAdapters) {
                if (adapter.supports(user)) {
                    IAuthInfo info = adapter.getRealmUserInfo(user);
                    if (info != null) {
                        return info;
                    }
                }
            }
        }
        return null;
    }

    @Override//认证
    public IAuthInfo getRealmUserInfo(IAuthToken token) {
        if (this.userAdapters != null) {
            for (IUserAdapter adapter : this.userAdapters) {
                if (adapter.supports(token)) {
                    IAuthInfo userInfo = adapter.getRealmUserInfo(token);
                    if (userInfo != null) {
                        return userInfo;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public IUserAdapter getUserAdapter(String adapter) {
        if (userAdapterBeans!=null){
            return userAdapterBeans.get(adapter);
        }
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        initUserAdapter(applicationContext);
    }

    private void initUserAdapter(ApplicationContext applicationContext){
        if (this.userAdapters == null) {
            this.userAdapterBeans = BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext, IUserAdapter.class, true, false);
            if (this.userAdapterBeans != null) {
                this.userAdapters = new ArrayList(this.userAdapterBeans.values());
                Collections.sort(this.userAdapters, new OrderComparator());
            }
        }
    }
}
