package com.example.demo.interceptor;


import com.example.demo.utils.BusinessErrorException;
import com.example.demo.utils.JSONResult;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseBody
@ControllerAdvice
public class MyInterceptor {

    /*
    * TODO:一般是会有一个自设的返回结果来处理异常的返回
    *  Result类，不想自己造轮子的话可以上网找一个
    * */

    @ExceptionHandler(Exception.class)
    public void myHandler(Exception e){
        e.printStackTrace();
        System.out.println("这是全局异常处理");
    }

    @ExceptionHandler(BusinessErrorException.class)
    public JSONResult handleBusinessError(BusinessErrorException e){
        String code = e.getCode();
        String message = e.getMessage();
        return new JSONResult(Integer.parseInt(code),message);
    }


}
