package com.demo.component.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ValidExceptionHandler
{
    private final static Logger logger= LoggerFactory.getLogger(ValidExceptionHandler.class);
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleValidException(MethodArgumentNotValidException e)
    {

        //将错误信息返回给前台
        return e.getBindingResult().getFieldError().getDefaultMessage();
    }
}
