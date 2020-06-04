package com.lucianthomaz.sales.file;

import com.lucianthomaz.sales.model.Result;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class FileIO {

   public static List<String> readFile(Path path) {
      try (Stream<String> stream = Files.lines(path)) {
         return stream.filter(x -> !x.isEmpty()).collect(Collectors.toList());

      } catch (IOException e) {
         throw new RuntimeException("There was a problem during file reading!", e);
      }
   }


   public static void writeReport(Path path, Result result) {

      List<String> lines = new ArrayList<>();
      lines.add(String.format("%s: %s", "Total of Customers", result.getClientsTotal()));
      lines.add(String.format("%s: %s", "Total of Sellers", result.getSalesmenTotal()));
      lines.add(String.format("%s: %s", "Greatest Sale Id", result.getGreatestSaleId()));
      lines.add(String.format("%s: %s", "Worst Salesman Name", result.getWorstSalesmanName()));

      try {
         Files.write(path, lines);
      } catch (IOException e) {
         throw new RuntimeException("There was a problem during file writing", e);
      }
   }

}
