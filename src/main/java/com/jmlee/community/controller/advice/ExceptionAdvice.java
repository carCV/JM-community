package com.jmlee.community.controller.advice;

import com.jmlee.community.util.CommunityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 全局异常通知类，后面的参数可以限定要扫描的类 --> 在表现层统一处理异常
 * @author jmLee
 */
@ControllerAdvice(annotations = Controller.class)
public class ExceptionAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler(Exception.class)
    public void handleException(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {

        logger.error("服务器异常",e.getMessage());
        for (StackTraceElement element : e.getStackTrace()) {
            logger.error(element.toString());
        }

        String xRequestWith = request.getHeader("x-requested-with");

        //以 XMLHttpRequest 这种方式进行请求说明是一个异步请求
        if ("XMLHttpRequest".equals(xRequestWith)) {
            response.setContentType("application/plain;charset=utf-8");
            PrintWriter printWriter = response.getWriter();
            printWriter.write(CommunityUtil.getJSONString(1,"服务器异常"));
        } else {
            // 非异步请求直接重定向
            response.sendRedirect(request.getContextPath() + "/error");
        }
    }

}
