package com.example.boardserver.aop;


import com.example.boardserver.util.SessionUtil;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Slf4j
@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class LoginCheckAspect {

    @Around("@annotation(com.example.boardserver.aop.LoginCheck) && @ annotation(loginCheck)")
    public Object adminLoginCheck(ProceedingJoinPoint proceedingJoinPoint, LoginCheck loginCheck) throws Throwable{
        HttpSession session = (HttpSession)((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes())).getRequest().getSession();
        String id = null;
        int idIndex = 0;

        String userType = loginCheck.type().toString();
        switch (userType){
            case "ADMIN" : {
                id = SessionUtil.getLoginAdminId(session);
                break;
            }
            case "USER" : {
                id = SessionUtil.getLoginMemberId(session);
                break;
            }
        }
        if (id == null){
            log.error(proceedingJoinPoint.toString() + "accountName : " + id );
            throw new HttpStatusCodeException(HttpStatus.UNAUTHORIZED,"Check the ID "){};
        }

        Object[] modifiedArgs = proceedingJoinPoint.getArgs();

        if(proceedingJoinPoint != null){
            modifiedArgs[idIndex] = id;
        }

        return proceedingJoinPoint.proceed(modifiedArgs);
    }

}
