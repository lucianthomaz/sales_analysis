package com.lucianthomaz.sales.enums;

public enum DelimiterEnum {

   COLUMN_DELIMITER("ç"),
   SALE_DELIMITER(","),
   SALEDATA_DELIMITER("-");

   private String delimiter;

   DelimiterEnum(String delimiter) {
      this.delimiter = delimiter;
   }

   public String getDelimiter() {
      return delimiter;
   }
}
