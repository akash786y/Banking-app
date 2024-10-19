package com.example.demo.Model.mongo;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.ValueGenerationType;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

@Data
@Builder
@Document(collection = "exchange_rate_mongo")
public class ExchangeRateMongo {

    @Id
    @Field(name="_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String result;
    @Field("time_next_update_unix")
    private String timeNextUpdateUnix;
    @Field("base_code")
    private String baseCode;
    @Field("conversion_rate")
    private Map<String, Double> conversionRate;
}
