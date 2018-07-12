package com.sucl.shirosecurity.security;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AuthcInfoService implements ApplicationContextAware {
    private List<UserServieAdapter> userServieAdapters;


    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token){
        if(userServieAdapters!=null){
            for(UserServieAdapter userServieAdapter : userServieAdapters){
                if(userServieAdapter.support(token)){
                    return userServieAdapter.getAuthenticationInfo(token);
                }
            }
        }
        return null;
    }

    public AuthorizationInfo getAuthorizationInfo(PrincipalCollection principals) {
        if(userServieAdapters!=null){
            for(UserServieAdapter userServieAdapter : userServieAdapters){
//                if(userServieAdapter.support(token)){
//                    return userServieAdapter.getAuthorizationInfos(token);
//                }
            }
        }
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        inituserServieAdapter(applicationContext);
    }

    private void inituserServieAdapter(ApplicationContext applicationContext) {
        Map<String, UserServieAdapter> adapterMap = BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext, UserServieAdapter.class, true, false);
        if(adapterMap!=null){
            userServieAdapters = new ArrayList<UserServieAdapter>(adapterMap.values());
        }
    }

}
