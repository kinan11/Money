package com.money.controller;
import com.money.models.Exchange;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RestController
public class ExchangeController {
    @RequestMapping("/api/exchange-rates/{currencyCode}")
    public List<Exchange> getPrice(@PathVariable String currencyCode){
        Exchange [] e = new Exchange[5];
        try{
            String url = "http://api.nbp.pl/api/exchangerates/rates/a/"+currencyCode+"/last/5";
            URL url1 = new URL(url);
            Scanner s = new Scanner(url1.openStream());
            String str = s.nextLine();
            String [] tab= str.split("\\[");
            tab[1]= tab[1].replace(",","");
            String regex = "\\{\"no\":\"([0-9\\/A-Z\\/A-Z\\/0-9]+)\"\"effectiveDate\":\"([0-9\\-]+)\"\"mid\":(\\d+\\.+\\d+)\\}";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(tab[1]);
            StringBuilder result = new StringBuilder();

            int i=0;
            while (matcher.find()) {
                e[i]=new Exchange(matcher.group(2),Double.parseDouble(matcher.group(3)));
                i++;
            }
        }catch (Exception ex)
        {
            System.out.println("Error");
        }
        return Arrays.asList(e);
    }
}
