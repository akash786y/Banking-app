package com.example.demo.Service;

import com.example.demo.DTO.StockDTO;
import com.example.demo.Model.Stock;

public interface StockService {

    public Stock buyStock(Long id, int quantity);
    public Stock sellStock(Long id, int quantity);

}
