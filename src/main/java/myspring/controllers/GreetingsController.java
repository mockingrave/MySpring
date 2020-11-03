package myspring.controllers;

import myspring.DTO.DateTimeDTO;
import myspring.DTO.IpLangBodyDTO;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
public class GreetingsController {

    @GetMapping("/hello")
    public String helloPage(){

       return "Hello, World!";

    }

    @PostMapping("/simpledata")
    public IpLangBodyDTO dataCheck (HttpServletRequest request) {
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

    @GetMapping("/datetime")
    public DateTimeDTO timeCheck(@RequestParam (required = false) long inputDateTime ){

        long currentServerDate = System.currentTimeMillis();
        long difference = Math.abs(currentServerDate-inputDateTime);

        SimpleDateFormat dateFormat= new SimpleDateFormat("HH:mm:ss (z) dd.MM.yyyy");
        DateTimeDTO dateTimeDTO = new DateTimeDTO();

        dateTimeDTO.setDateTime(dateFormat.format(currentServerDate));

        dateTimeDTO.setDifference("");
        String differenceStr =
                String.format("%02d",(difference/1000/60/60)%24) +":"+
                String.format("%02d",(difference/1000/60)%60)+":"+
                String.format("%02d",(difference/1000)%60)+":"+
                String.format("%03d",(difference%1000))+"  "+
                        String.format("%02d",(difference/1000/60/60/24)%30) +"."+
                        String.format("%02d",(difference/1000/60/60/24/30)%12) +"."+
                        String.format("%04d",(difference/1000/60/60/24/30/12));
        dateTimeDTO.setDifference(differenceStr);
        return dateTimeDTO;
    }
}
