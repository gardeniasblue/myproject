package com.blogmang.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class Shiroconfig {
    @Bean(name="securityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    @Bean
    public UserRealm userRealm() {

        return new UserRealm();
    }

    //Filter工厂，设置对应的过滤条件和跳转条件
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        //添加shiro的内置过滤器
        /*
            anon:无需认证就可以访问
            authc:必须认证才能访问
            user：必须拥有记住我功能才能访问
            perms:拥有对某个资源的权限才能访问
            role:拥有某个角色权限才能访问

        */
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/statics/**", "anon");

        //v1表示admin下对User的操作
        filterChainDefinitionMap.put("/admin/v1/*", "authc");
        //v2:博客相关
        filterChainDefinitionMap.put("/admin/v2/*", "authc");
        //until:工具
        filterChainDefinitionMap.put("/admin/until/*", "authc");
        //登录
        shiroFilterFactoryBean.setLoginUrl("/admin/login");
        //首页
        //shiroFilterFactoryBean.setSuccessUrl("/admin/login");
        //错误页面，认证不通过跳转
        shiroFilterFactoryBean.setUnauthorizedUrl("/error");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
}
