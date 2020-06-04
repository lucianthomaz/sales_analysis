package com.lucianthomaz.sales.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Salesman {
   private String cpf;
   private String name;
   private String salary;
}
