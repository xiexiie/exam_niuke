package com.exam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class KaptchaController2 {

    @ResponseBody
    @RequestMapping("/getVerify")
    public String getVerifyCode(HttpServletRequest request){
        String verifyCode = (String)request.getSession().getAttribute("verifyCode");
        System.out.println("---------------------"+verifyCode);
        return verifyCode;
    }
}
