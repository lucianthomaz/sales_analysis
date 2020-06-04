package com.lucianthomaz.sales.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Item {
   private int id;
   private int quantity;
   private double price;
}
