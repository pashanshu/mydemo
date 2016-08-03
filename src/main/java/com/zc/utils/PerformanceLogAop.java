package com.zc.utils;

import java.lang.reflect.Method;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.zc.annotation.PerformanceLog;


@Aspect
@Component
public class PerformanceLogAop {
	
	 private static Logger logger = Logger.getLogger(PerformanceLogAop.class);
	
    ThreadLocal<Long> time=new ThreadLocal<Long>();
    ThreadLocal<String> tag=new ThreadLocal<String>();
     
 //   @Pointcut("@annotation(com.zc.annotation.PerformanceLog)")
    @Pointcut("execution(* com.zc.controller.TestController.*(..))")
    public void anyMethod() {
    }
    
    /**
     * 在所有标注@Log的地方切入
     * @param joinPoint
     */
//    @Before("anyMethod()")
    public void beforeExec(JoinPoint joinPoint){
         
        time.set(System.currentTimeMillis());
        tag.set(UUID.randomUUID().toString());
         
        info(joinPoint);
         
        MethodSignature ms=(MethodSignature) joinPoint.getSignature();
        Method method=ms.getMethod();
        System.out.println(method.getAnnotation(PerformanceLog.class).ResponseClass()+"标记"+tag.get());
    }
     
//    @After("anyMethod()")
    public void afterExec(JoinPoint joinPoint){
        MethodSignature ms=(MethodSignature) joinPoint.getSignature();
        Method method=ms.getMethod();
        System.out.println("标记为"+tag.get()+"的方法"+method.getName()+"运行消耗"+(System.currentTimeMillis()-time.get())+"ms");
    }
     
    @Around(value="anyMethod() && @annotation(log)")
    public Object aroundExec(ProceedingJoinPoint pjp,PerformanceLog log) throws Throwable{
        logger.info("=========================around is begin ===========================");
        String requestClass="";
        String requestMethod="";
        String responseClass="";
        String responseMethod="";
        long beginTime=System.currentTimeMillis();
        Object result=pjp.proceed();
        long endTime= System.currentTimeMillis();
        if(true){
        	requestClass=log.RequestClass();
        	requestMethod=log.RequestMethod();
        	responseClass=pjp.getSignature().getDeclaringTypeName();
        	responseMethod=pjp.getSignature().getName();
        }
          logger.info("requestClass==>"+requestClass+"\t"+"requestMethod==>"+requestMethod+"\t"+"responseClass==>"+responseClass+"\t"+"responseMethod==>"+responseMethod+
        		  "\t"+"beginTime==>"+beginTime+"\t"+"endTime==>"+endTime+"\t"+"total==>"+(endTime-beginTime));
          return result;
    }
     
    private void info(JoinPoint joinPoint){
        System.out.println("--------------------------------------------------");
        System.out.println("King:\t"+joinPoint.getKind());
        System.out.println("Target:\t"+joinPoint.getTarget().toString());
        Object[] os=joinPoint.getArgs();
        System.out.println("Args:");
        for(int i=0;i<os.length;i++){
            System.out.println("\t==>参数["+i+"]:\t"+os[i].toString());
        }
        System.out.println("Signature:\t"+joinPoint.getSignature());
        System.out.println("SourceLocation:\t"+joinPoint.getSourceLocation());
        System.out.println("StaticPart:\t"+joinPoint.getStaticPart());
        System.out.println("--------------------------------------------------");
    }

}
