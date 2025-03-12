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

        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String value = httpRequest.getHeader(name);
            ret += name + " : " + value + "<p>";
        }

        String[] headerTypes = {"X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP",
                                "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR"};
        String ip = null;
        String header = null;

        for (String headerType : headerTypes) {
            ip = httpRequest.getHeader(headerType);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                header = headerType;
                ret += "remoteIP(" + header + ") : " + ip + "<p>";
                break;
            }
        }

        ret += "remoteIP(getRemoteAddr) : " + httpRequest.getRemoteAddr();

        // 여러 개의 IP가 있을 경우, 첫 번째 IP만 가져오기
        //if (ip != null && ip.contains(",")) {
        //    ip = ip.split(",")[0].trim();
        //}


        return ret;
    }
}
