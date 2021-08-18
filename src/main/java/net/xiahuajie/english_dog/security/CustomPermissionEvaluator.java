package net.xiahuajie.english_dog.security;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {
    /**
     * 自定义验证方法
     *
     * @param authentication     登录的时候存储的用户信息
     * @param targetDomainObject @PreAuthorize("hasPermission('/hello/**','r')")
     *                           中hasPermission的第一个参数
     * @param permission         @PreAuthorize("hasPermission('/hello/**','r')")
     *                           中hasPermission的第二个参数
     * @return
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        // 获得loadUserByUsername()方法的结果
        SysUser user = (SysUser) authentication.getPrincipal();
        if (null != user) {
            // TODO 鉴权待实现
            return true;
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
                                 Object permission) {
        return false;
    }
}
