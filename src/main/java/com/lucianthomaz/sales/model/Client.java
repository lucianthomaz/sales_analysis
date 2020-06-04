package com.lucianthomaz.sales.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Client {
   private String cnpj;
   private String name;
   private String businessArea;
}
