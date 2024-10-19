package com.example.demo.Service;

import com.example.demo.Model.mongo.ExchangeRate;
import com.example.demo.Model.mongo.ExchangeRateMongo;
import com.example.demo.Repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExchangeRateService {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${exchangeRate.api.key}")
    private String apiKey;

    public ExchangeRate getExchangeRate() {
        String API = "https://v6.exchangerate-api.com/v6/"+apiKey+"/latest/usd";
        ResponseEntity<ExchangeRate> res = restTemplate.exchange(API, HttpMethod.GET, null, ExchangeRate.class);
        ExchangeRate exchangeRate = res.getBody();

        ExchangeRateMongo exchangeRateMongo = ExchangeRateMongo.builder()
                .result(exchangeRate.getResult())
                .baseCode(exchangeRate.getBaseCode())
                .conversionRate(exchangeRate.getConversionRates())
                .timeNextUpdateUnix(exchangeRate.getTimeNextUpdateUtc())
                .build();

        exchangeRateRepository.save(exchangeRateMongo);
        return exchangeRate;
    }
}
