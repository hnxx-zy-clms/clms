/**
 * @FileName: RequestAspect
 * @Author: code-fusheng
 * @Date: 2020/3/18 10:47
 * Description: AOP
 */
package com.hnxx.zy.clms.common.aspect;

import com.alibaba.fastjson.JSON;
import com.hnxx.zy.clms.common.enums.StateEnum;
import com.hnxx.zy.clms.common.utils.StringUtils;
import com.hnxx.zy.clms.common.utils.ThreadLocalContext;
import com.hnxx.zy.clms.core.entity.Log;
import com.hnxx.zy.clms.core.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 切面输出基本信息
 * 以及记录日志
 *
 * @author code-fusheng
 */
@Aspect
@Component
@Slf4j
public class RequestAspect {

    @Autowired
    private LogService logService;

    /**
     * @Pointcut 切点 指定那些文件需要 AOP
     * 两个..代表所有子目录，最后括号里的两个..代表所有参数
     */
    @Pointcut("execution( * com.hnxx.zy.*.controller..*(..))")
    public void logPointCut() {
    }

    /**
     * 前置通知：方法执行之前调用
     * 在目标方法执行前后实施增强，可以应用于权限管理等功能
     * 此处用于记录请求的参数和请求内容
     */
    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint) throws Exception {
        Log logger = ThreadLocalContext.get().getLogger();
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        String uri = request.getRequestURI();
        // 记录下请求内容
        printRequestLog(joinPoint, request, uri);
    }

    /**
     * @Around 环绕通知
     * pjp 是 JoinPoint 的子接口，表示可以执行目标方法
     * 1。必须是Object类型的返回值
     * 2. 必须要接收一个参数
     * 3. 必须使用 throw Throwable
     */
    @Around("logPointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        // System.currentTimeMillis 获取系统当前时间
        long startTime = System.currentTimeMillis();
        // pjp.proceed() 执行目标方法 可以理解为对业务方法的模拟
        Object ob = pjp.proceed();
        // 计算目标方法运行时间
        long time = System.currentTimeMillis() - startTime;
        log.info("耗时 : {}", time);
        Log logger = ThreadLocalContext.get().getLogger();
        logger.setLogTime(time);
        return ob;
    }

    /**
     * 后置通知 没有发生异常 （在发生异常的情况下不执行此通知）
     * @AfterReturning 会直接获取对应切面方法的返回值，可以对这个返回值进行进一步处理（不能改变）
     * 此处用于返回日志请求结果 LogResult
     * @param ret
     */
    @AfterReturning(returning = "ret", pointcut = "logPointCut()")
    public void doAfterReturning(Object ret) {
        String result = JSON.toJSONString(ret);
        log.info("返回值:{}", JSON.toJSONString(ret));
        Log logger = ThreadLocalContext.get().getLogger();
        logger.setLogResult(result);
        logService.save(logger);
    }

    /**
     * 异常通知 处理程序中未处理的异常
     * 发生异常
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "logPointCut()", throwing = "e")
    public void saveExceptionLog(JoinPoint joinPoint, Throwable e) {
        Log logger = ThreadLocalContext.get().getLogger();
        logger.setLogStatus(StateEnum.REQUEST_ERROR.getCode());
        String exception = StringUtils.getPackageException(e, "com.hnxx.zy");
        logger.setLogMessage(exception);
        logger.setLogTime(0L);
        // 发生异常走异常通知
        logService.save(logger);
    }

    /**
     * 打印请求日志
     * @param joinPoint
     * @param request
     * @param uri
     */
    private void printRequestLog(JoinPoint joinPoint, HttpServletRequest request, String uri) {
        // 拿到切面方法
        log.info("请求地址 : {}", uri);
        log.info("请求方式 : {}", request.getMethod());
        // 获取真实的ip地址
        String ip = StringUtils.getRemoteIp(request);
        log.info("IP : {}", ip);
        String controllerName = joinPoint.getSignature().getDeclaringTypeName();
        log.info("方法 : {}.{}", controllerName, joinPoint.getSignature().getName());
        String params = Arrays.toString(joinPoint.getArgs());
        log.info("请求参数：{}", params);

        // 获取日志实体
        Log logger = ThreadLocalContext.get().getLogger();
        logger.setLogUrl(uri);
        logger.setLogParams(params);
        logger.setLogStatus(StateEnum.REQUEST_SUCCESS.getCode());
        logger.setLogMethod(request.getMethod());
        logger.setLogIp(ip);
    }
}

