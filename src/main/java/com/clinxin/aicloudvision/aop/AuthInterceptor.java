package com.clinxin.aicloudvision.aop;

import com.clinxin.aicloudvision.annotation.AuthCheck;
import com.clinxin.aicloudvision.exception.BusinessException;
import com.clinxin.aicloudvision.exception.ErrorCode;
import com.clinxin.aicloudvision.model.entity.User;
import com.clinxin.aicloudvision.model.enums.UserRoleEnum;
import com.clinxin.aicloudvision.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author clinxin
 */
@Aspect
@Component
public class AuthInterceptor {

    @Resource
    private UserService userService;

    /**
     * 权限拦截器
     * @param joinPoint  切入点
     * @param authCheck  权限注解
     * @return  方法返回值
     * @throws Throwable  异常
     */
    @Around("@annotation(authCheck)")
    public Object doInterceptor(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
        String mustRole = authCheck.mustRole();
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        // 获取当前登录用户
        User loginUser = userService.getLoginUser(request);
        UserRoleEnum mustRoleEnum = UserRoleEnum.getEnumByValue(mustRole);
        // 不需要权限，直接放行
        if (mustRoleEnum == null) {
            return joinPoint.proceed();
        }
        // 校验当前登录的用户是否有权限
        UserRoleEnum userRoleEnum = UserRoleEnum.getEnumByValue(loginUser.getUserRole());
        // 没有权限，直接拒绝
        if (userRoleEnum == null) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR, "用户没有权限");
        }
        // 要求必须有管理员权限
        // TODO 如果角色枚举值 > 2，需要调整这段逻辑
        if (UserRoleEnum.ADMIN.equals(mustRoleEnum) && !UserRoleEnum.ADMIN.equals(userRoleEnum)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR, "用户权限不足");
        }
        // 权限校验通过，放行
        return joinPoint.proceed();
    }
}
