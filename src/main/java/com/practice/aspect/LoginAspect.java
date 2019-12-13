package com.practice.aspect;

import com.alibaba.fastjson.JSON;
import com.practice.annotation.Access;
import com.practice.dto.BaseResp;
import com.practice.entity.Member;
import com.practice.service.RedisService;
import com.practice.status.AccessPeople;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author hzq
 * @date 2019/12/13
 */
@Component
@Aspect
public class LoginAspect {

    @Autowired
    private RedisService redisService;

    @Value("${unLoginApi}")
    private String unLoginApi;

    @Around("execution(public * com.practice.controller.*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        for(String path : unLoginApi.split(",")){
            if(request.getServletPath().equals(path)){
                return joinPoint.proceed();
            }
        }

        String token = request.getHeader("x-auth-token");
        if(token == null){
            return ResponseEntity.ok(BaseResp.unlogin());
        }
        Member member = (Member) redisService.existKey(token);
        if (member == null) {
            return ResponseEntity.ok(BaseResp.unlogin());
        }

        String methodName = joinPoint.getSignature().getName();
        Class clazz = joinPoint.getTarget().getClass();
        Class<?>[] paramClass = ((MethodSignature) joinPoint.getSignature()).getParameterTypes();

        Method method = clazz.getMethod(methodName, paramClass);
        if(method.isAnnotationPresent(Access.class)){
            Access access = method.getAnnotation(Access.class);
            for(AccessPeople accessPeople : access.value()){
                if(accessPeople.getType() == member.getType()){
                    return joinPoint.proceed();
                }
            }
            return ResponseEntity.ok(BaseResp.forbidden());
        }
        return joinPoint.proceed();
    }

}
