package com.lucianthomaz.sales.dataprovider;

import com.lucianthomaz.sales.model.Result;

public class OutDataProvider {

   public static Result getResult() {

      Result result = new Result();
      result.setClientsTotal(2);
      result.setSalesmenTotal(2);
      result.setGreatestSaleId(10);
      result.setWorstSalesmanName("Paulo");
      return result;
   }

}
