package com.demo.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Aspect
@Component
public class Log {
    private static final Logger logger = LoggerFactory.getLogger(Log.class);

    //private Logger logger = LoggerFactory.getLogger(getClass());
    @Pointcut("execution(public * com.demo.controller..*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String name = (String) enu.nextElement();
            logger.info("name:{},value:{}", name, request.getParameter(name));
        }
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("RESPONSE : " + ret);
    }

    @Pointcut("execution(public * com.demo.service..*.*(..))")
    public void serviceLog() {
    }

    @Before("serviceLog()")
    public void doBeforeService(JoinPoint joinPoint) throws Throwable {
//        String class_name = joinPoint.getTarget().getClass().getName();
//        String method_name = joinPoint.getSignature().getName();
//        logger.info("class_name = {}", class_name);
//        logger.info("method_name = {}", method_name);
    }

    @AfterReturning(returning = "ret", pointcut = "serviceLog()")
    public void doAfterServiceReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
//        logger.info("RESPONSE : " + ret);
    }
}