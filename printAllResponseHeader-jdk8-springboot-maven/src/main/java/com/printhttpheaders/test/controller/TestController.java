package com.printhttpheaders.test.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    
    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(HttpServletRequest httpRequest) {
        Enumeration<String> enumeration = httpRequest.getHeaderNames(); 
        String ret = "";
        while(enumeration.hasMoreElements()) { 
            String name = (String) enumeration.nextElement(); 
            String value = httpRequest.getHeader(name);
            
            ret += name + " : " + value + "<p>";
        }
        ret += "remoteIP" + " : " + httpRequest.getRemoteAddr();
        return ret;
    }
}
