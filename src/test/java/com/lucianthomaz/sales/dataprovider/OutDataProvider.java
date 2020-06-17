package com.lucianthomaz.sales.dataprovider;

import com.lucianthomaz.sales.model.Result;

import java.util.ArrayList;
import java.util.List;

public class OutDataProvider {

   public static Result getResult() {

      Result result = new Result();
      result.setClientsTotal(2);
      result.setSalesmenTotal(2);
      result.setGreatestSaleId(10);
      result.setWorstSalesmanName("Paulo");
      return result;
   }

   public static List<String> getFileResult() {

      List<String> output = new ArrayList<>();

      output.add("Total of Customers: 2");
      output.add("Total of Sellers: 2");
      output.add("Greatest Sale Id: 10");
      output.add("Worst Salesman Name: Paulo");

      return output;
   }

}
