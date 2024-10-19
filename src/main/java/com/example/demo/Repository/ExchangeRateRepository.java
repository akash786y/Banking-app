package com.example.demo.Repository;

import com.example.demo.Model.mongo.ExchangeRateMongo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRateRepository extends MongoRepository<ExchangeRateMongo, Long> {
}
