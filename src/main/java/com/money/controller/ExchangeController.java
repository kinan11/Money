package com.money.controller;
import com.money.models.Exchange;
import com.money.models.Gold;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RestController
public class ExchangeController {
    @RequestMapping("/api/exchange-rates/{currencyCode}")
    /*public List<Exchange> getPrice(){
       // String url = "http://api.nbp.pl/api/exchangerates/rates/a/chf/";
        //RestTemplate restTemplate = new RestTemplate();
        //Exchange [] money= restTemplate.getForObject(url, Exchange[].class);
        Exchange []money = new Exchange[2];
        money[0]=new Exchange("11","11","11","11","11","11");
        money[1]=new Exchange("11","11","11","11","11","11");
        return Arrays.asList(money);
    }*/
    public List<Gold> getPrice(@PathVariable String currencyCode){
        String url = "http://api.nbp.pl/api/cenyzlota/last/14";
        RestTemplate restTemplate = new RestTemplate();
        Gold [] zloto = restTemplate.getForObject(url, Gold[].class);

        Logger logger = LoggerFactory.getLogger(ExchangeController.class);
        try{
            //url = "http://www.mocky.io/v2/5c8bdd5c360000cd198f831e";
            url = "http://api.nbp.pl/api/exchangerates/rates/a/chf/last/5";
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            File f= new File(url);
            InputStream inputStream = null;
            URL url1 = new URL(url);
            Scanner s = new Scanner(url1.openStream());
            String str = s.nextLine();
            System.out.println(str);

            //str = "City: \"effectiveDate\":\"2022-03-03\"\"distance\":\"47028\" \"effectiveDate\":\"2022-03-03\"\"distance\":\"4.7028\" \"effectiveDate\":\"2022-03-03\"\"distance\":\"4.7028\" Price:";
            String regex = "\"effectiveDate\":\"([0-9\\-]+)\"\"mid\":(\\d+\\.+\\d+)"
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(str);
            StringBuilder result = new StringBuilder();
            while (matcher.find()) {
                result.append("[");
                result.append(matcher.group(1));
                result.append(":");
                result.append(matcher.group(2));
                result.append("]");
            }
            System.out.println(result.toString());

            //inputStream = new FileInputStream(f);
            // Document doc = builder.parse(url);
            //doc.getDocumentElement().normalize();
            //String s = doc.getElementsByTagName("price").item(0).getTextContent();
            //String s = doc.getElementsByTagName("No").item(0).getTextContent();
            //System.out.println(builder);

        }catch (Exception ex)
        {
            logger.error(ex.getMessage());
        }
        return Arrays.asList(zloto);
    }
}
