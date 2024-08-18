package com.example.demo.Service;

import com.example.demo.Model.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class QuoteService {

    private static final String API = "https://api.quotable.io/random";

    @Autowired
    private RestTemplate restTemplate;

    public Quote getQuote(){
        ResponseEntity<Quote> res = restTemplate.exchange(API, HttpMethod.GET, null, Quote.class);
        return res.getBody();
    }
}
