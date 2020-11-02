package myspring.controllers;

import myspring.DTO.IpLangBodyDTO;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class FirstController {

    @GetMapping("/hello")
    public String helloPage(){

       return "first/hello";
    }

    @PostMapping("/simpledata")
    @ResponseBody
    public IpLangBodyDTO hell(HttpServletRequest request) {
        IpLangBodyDTO ipLangBody= new IpLangBodyDTO();

        String ipAddress = request.getRemoteAddr();

        Locale currentLocale = request.getLocale();
        String language = currentLocale.getLanguage();


        String body = "";
        try {
            BufferedReader reader = request.getReader();
            body = reader.lines().collect(Collectors.joining());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("look at body!");
        }

        ipLangBody.setIp(ipAddress);
        ipLangBody.setLanguage(language);
        ipLangBody.setBody(body);

        return ipLangBody;
    }
}
