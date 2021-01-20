package com.jimwang.jimblog.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;

public class MyRealm extends AuthenticatingRealm {

    /**
     * 用户认证的方法 这个方法不能手动调用 shiro会自动调用
     *
     * @param authenticationToken 用户身份 这里存放着用户的账号和密码
     * @return 返回用户登录成功后的身份证明
     * @throws AuthenticationException 如果认证失败 shiro会抛出异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();// 获取页面中的用户账号
        // 认证账号，从数据库中获取数据
        if (!"jimwang".equals(username)) {
            throw new UnknownAccountException();
        }
        // 设置当前登录用户中的密码数据进行加密
        // 建议在前端加密了再传给后端
        // 这里的加密一般用于注册的时候 加密了往数据库里写
//        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
//        credentialsMatcher.setHashAlgorithmName("MD5");
//        credentialsMatcher.setHashIterations(2);
//        this.setCredentialsMatcher(credentialsMatcher);
        // 创建密码认证对象，由shiro自动认证密码。
        // 参数1 数据库中的账号（或页面账号）
        // 参数2 数据库中读取出来的密码
        // 参数3 当前realm的名字
        // 如果密码认证成功则返回一个用户身份对象，如果失败抛出异常
        return new SimpleAuthenticationInfo(username, "Whoyouare48.", getName());
    }
}
