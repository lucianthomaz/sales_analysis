package com.lucianthomaz.sales.enums;

public enum DataIdentifierEnum {
   SALESMAN_ID("001"),
   CLIENT_ID("002"),
   SALE_ID("003");

   private String id;

   DataIdentifierEnum(String id) {
      this.id = id;
   }

   public String getId() {
      return id;
   }
}
