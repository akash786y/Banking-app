package com.example.demo.Service;

import com.example.demo.Model.Stock;
import com.example.demo.Repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class StockServiceImpl implements StockService{

    @Autowired
    private StockRepository stockRepository;

    @Override
    public Stock buyStock(Long id, int quantity) {
        return null;
    }

    @Override
    public Stock sellStock(Long id, int quantity) {
        return null;
    }
}
