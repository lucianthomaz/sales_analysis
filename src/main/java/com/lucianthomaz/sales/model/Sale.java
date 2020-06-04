package com.lucianthomaz.sales.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Sale {
   private int id;
   private List<Item> items;
   private Salesman salesman;
}
