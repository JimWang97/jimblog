package com.jimwang.jimblog.config;

import com.jimwang.jimblog.filter.StatelessAuthcFilter;
import com.jimwang.jimblog.realm.MyRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.web.servlet.DispatcherType;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    /*
     * 配置一个securityManager 安全管理器
     * */
    @Bean
    public SecurityManager securityManager(Realm myRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(myRealm);
        return defaultWebSecurityManager;
    }

    // 配置一个自定义的Realm的bean，最终将这个bean返回的对象来完成我们的认证和授权
    @Bean
    public MyRealm myRealm(){
        MyRealm myRealm = new MyRealm();
        return myRealm;
    }

    // 配置一个shrio的过滤器bean，这个bean将配置shiro相关的一个规则的拦截
    // 比如什么样的请求可以访问什么样的请求不可以访问
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/login"); // 配置用户登录请求 如果需要进行登录时，shiro就会转到这个请求进入登录页面
//        shiroFilterFactoryBean.setSuccessUrl("/admin/index"); // 配置登录成功以后转向的请求地址
        shiroFilterFactoryBean.setUnauthorizedUrl("/login"); //配置没有权限时转向的请求地址

        // 配置过滤器
        Map<String, Filter> filters = new LinkedHashMap<>();
        filters.put("authc", new StatelessAuthcFilter());
        shiroFilterFactoryBean.setFilters(filters);

        // 配置权限拦截规则
        Map<String,String> filterChainMap = new LinkedHashMap<>();
//        filterChainMap.put("/api/logout", "logout"); // 配置登出的请求，会清空内存

        filterChainMap.put("/api/login", "anon"); // 配置登录请求不需要认证。
        filterChainMap.put("/api/admin/**", "authc"); // 表示admin开头的请求需要登录
//        filterChainMap.put("/admin/**", "authc"); // 表示admin开头的请求需要登录
        filterChainMap.put("/**", "anon"); // 配置剩余的所有请求全部需要进行登录，必须写在最后
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap); // 设置权限拦截规则
        return shiroFilterFactoryBean;
    }

}