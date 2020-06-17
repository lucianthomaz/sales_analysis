package com.lucianthomaz.sales.service;

import com.lucianthomaz.sales.dataprovider.InDataProvider;
import com.lucianthomaz.sales.dataprovider.OutDataProvider;
import com.lucianthomaz.sales.model.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataProcessingTest {

   DataProcessing dataProcessing = new DataProcessing();
   private List<String> linesOfFile;

   @BeforeEach
   void init() {
      linesOfFile = InDataProvider.getEntrada();
   }

   @Test
   public void resultMustHaveEmptyValuesIfSalesmanNotFound() {
      List<String> linesWithoutSalesman = linesOfFile.stream()
            .filter(x -> !x.startsWith("001")).collect(Collectors.toList());
      Result result = dataProcessing.processFile(linesWithoutSalesman);
      assertEquals(0, result.getClientsTotal());
      assertEquals(0, result.getSalesmenTotal());
      assertEquals(0, result.getGreatestSaleId());
   }

   @Test
   public void resultMustHaveEmptyValuesIfClientsNotFound() {
      List<String> linesWithoutClients = linesOfFile.stream()
            .filter(x -> !x.startsWith("002")).collect(Collectors.toList());
      Result result = dataProcessing.processFile(linesWithoutClients);
      assertEquals(0, result.getClientsTotal());
      assertEquals(0, result.getSalesmenTotal());
      assertEquals(0, result.getGreatestSaleId());
   }

   @Test
   public void resultMustHaveEmptyValuesIfSlesNotFound() {
      List<String> linesWithoutSales = linesOfFile.stream()
            .filter(x -> !x.startsWith("003")).collect(Collectors.toList());
      Result result = dataProcessing.processFile(linesWithoutSales);
      assertEquals(0, result.getClientsTotal());
      assertEquals(0, result.getSalesmenTotal());
      assertEquals(0, result.getGreatestSaleId());
   }

   @Test
   public void shouldProcessInputAndReturnFinalResult() {
      Result result = dataProcessing.processFile(linesOfFile);

      assertEquals(OutDataProvider.getResult().getClientsTotal(), result.getClientsTotal());
      assertEquals(OutDataProvider.getResult().getSalesmenTotal(), result.getSalesmenTotal());
      assertEquals(OutDataProvider.getResult().getGreatestSaleId(), result.getGreatestSaleId());
      assertEquals(OutDataProvider.getResult().getWorstSalesmanName(), result.getWorstSalesmanName());
   }

}
