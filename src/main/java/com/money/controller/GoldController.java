package com.money.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.money.models.Gold;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;


@RestController
public class GoldController {
    @RequestMapping("/api/gold-price/average")
    public List<Gold> getPrice(){
        String url = "http://api.nbp.pl/api/cenyzlota/last/14";
        RestTemplate restTemplate = new RestTemplate();
        Gold [] zloto = restTemplate.getForObject(url, Gold[].class);
        return Arrays.asList(zloto);
    }
}
