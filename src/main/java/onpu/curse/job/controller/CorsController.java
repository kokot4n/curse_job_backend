package onpu.curse.job.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;

@RestController
public class CorsController {
    @GetMapping("/user/weather")
    public ResponseEntity weather(@RequestParam String lat, @RequestParam String lon){
        final RestTemplate restTemplate = new RestTemplate();
        String uri = "https://api.openweathermap.org/data/2.5/onecall?lat="+lat+"&lon="+lon+"&exclude=hourly,minutely&appid=11f6c7832b5096ea219b19ec5d65f8b2&units=metric";
        return ResponseEntity.ok()
                .body(restTemplate.getForEntity(uri, String.class));
    }
}
