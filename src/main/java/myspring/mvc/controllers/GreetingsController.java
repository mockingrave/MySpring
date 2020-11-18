package myspring.mvc.controllers;

import myspring.mvc.DTO.DateTimeDTO;
import myspring.mvc.DTO.IpLangBodyDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
public class GreetingsController {

    @GetMapping("/hello")
    public String helloPage() {

        return "Hello, World!";

    }

    @PostMapping("/simpledata")
    public IpLangBodyDTO dataCheck(HttpServletRequest request) {

        String ipAddress = request.getRemoteAddr();

        Locale currentLocale = request.getLocale();
        String language = currentLocale.getLanguage();

        String body;
        try {
            BufferedReader reader = request.getReader();
            body = reader.lines().collect(Collectors.joining());
        } catch (IOException e) {
            return new IpLangBodyDTO(ipAddress, "failure", "invalid body");
        }

        return new IpLangBodyDTO(ipAddress, language, body);
    }

    @GetMapping("/datetime")
    public DateTimeDTO timeCheck(@RequestParam(required = false) long inputDateTime) {

        long currentServerDate = System.currentTimeMillis();
        long differenceInMS = Math.abs(currentServerDate - inputDateTime);

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss (z) dd.MM.yyyy");

        String differenceInNormalForm =
                String.format("%02d", (differenceInMS / 1000 / 60 / 60) % 24) + ":" +
                        String.format("%02d", (differenceInMS / 1000 / 60) % 60) + ":" +
                        String.format("%02d", (differenceInMS / 1000) % 60) + ":" +
                        String.format("%03d", (differenceInMS % 1000)) + "  " +
                        String.format("%02d", (differenceInMS / 1000 / 60 / 60 / 24) % 30) + "." +
                        String.format("%02d", (differenceInMS / 1000 / 60 / 60 / 24 / 30) % 12) + "." +
                        String.format("%04d", (differenceInMS / 1000 / 60 / 60 / 24 / 30 / 12));

        return new DateTimeDTO(dateFormat.format(currentServerDate), differenceInNormalForm, differenceInMS);
    }

}
