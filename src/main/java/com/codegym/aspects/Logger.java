package com.codegym.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;

@Aspect
public class Logger {


    @Before(value = "execution(* com.codegym.controllers.*.*(..))")
    public void logControllers(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String funcName = joinPoint.getSignature().getName();
        this.log( className + ":" +  funcName, "");
    }

    @Before(value = "execution(* org.springframework.validation.*.*(..))")
    public void logValidations(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String funcName = joinPoint.getSignature().getName();
        this.log( className + ":" +  funcName, "");
    }



    public void log(String scope, String content) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String currentDateTime = ">>> " + timestamp.toString();


        System.out.println(currentDateTime);
        System.out.println(scope);
        System.out.println(content);

        File logFile = new File("log.txt");
        if (!logFile.exists()) {
            try {
                logFile.createNewFile();

                FileOutputStream os = new FileOutputStream(logFile);

                os.write(currentDateTime.getBytes());
                os.write(scope.getBytes());
                os.write(content.getBytes());
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
