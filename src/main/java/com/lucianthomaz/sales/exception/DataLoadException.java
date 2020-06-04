package com.lucianthomaz.sales.exception;

public class DataLoadException extends RuntimeException{
   public DataLoadException(String errorMessage) {
      super(errorMessage);
   }
}
