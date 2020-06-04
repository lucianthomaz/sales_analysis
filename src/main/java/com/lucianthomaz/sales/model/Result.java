package com.lucianthomaz.sales.model;

import lombok.Data;

@Data
public class Result {
   private int clientsTotal;
   private int salesmenTotal;
   private int greatestSaleId;
   private String worstSalesmanName;
}
