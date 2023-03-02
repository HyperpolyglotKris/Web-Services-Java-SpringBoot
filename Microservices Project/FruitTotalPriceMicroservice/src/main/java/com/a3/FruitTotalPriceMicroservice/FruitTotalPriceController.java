package com.a3.FruitTotalPriceMicroservice;

import java.text.DecimalFormat;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class FruitTotalPriceController {

    @Autowired
    private Environment environment;

    @GetMapping("/fruit-total-price/fruit/{fruit}/month/{month}/amount/{amount}")
    public FruitTotalPrice retrieveFruitMonthPrice(@PathVariable String fruit, @PathVariable String month,
            @PathVariable int amount) {

        HashMap<String, String> uriVariables = new HashMap<>();
        uriVariables.put("fruit", fruit);
        uriVariables.put("month", month);

        ResponseEntity<FruitTotalPrice> responseEntity = new RestTemplate().getForEntity(
                "http://localhost:8000/fruit-monthly-price/fruit/{fruit}/month/{month}", FruitTotalPrice.class,
                uriVariables);

        FruitTotalPrice fruitTotalPrice = responseEntity.getBody();
        fruitTotalPrice.setAmount(amount);
        fruitTotalPrice.setTotalPrice(fruitTotalPrice.getAmount() * fruitTotalPrice.getPrice());

        // Change to this microservice's port
        String port = environment.getProperty("local.server.port");
        fruitTotalPrice.setEnvironment(port);

        // Reupdate the total price with a rounded double (removed floating decimals)
        double roundedNumber = fruitTotalPrice.getTotalPrice();
        DecimalFormat df = new DecimalFormat("#.##");
        roundedNumber = Double.valueOf(df.format(roundedNumber));
        fruitTotalPrice.setTotalPrice(roundedNumber);

        return fruitTotalPrice;
    }
}
