package com.lucianthomaz.sales.service;

import com.lucianthomaz.sales.enums.DataIdentifierEnum;
import com.lucianthomaz.sales.enums.DelimiterEnum;
import com.lucianthomaz.sales.exception.DataLoadException;
import com.lucianthomaz.sales.model.*;

import java.util.*;
import java.util.stream.Collectors;

public class DataProcessing {

   public Result processFile(List<String> linesOfFile) {
      DataProcessing dataProcessing = new DataProcessing();
      Result result = new Result();

      if (!linesOfFile.isEmpty()) {
         List<Salesman> salesmen = dataProcessing.getSalesmen(linesOfFile);
         List<Client> clients = dataProcessing.getClients(linesOfFile);
         List<Sale> sales = dataProcessing.getSales(linesOfFile, salesmen);
         Sale greatestSale = dataProcessing.getGreatestSale(sales);
         Salesman worstSalesman = dataProcessing.getWorstSalesman(sales, salesmen);

         result.setClientsTotal(clients.size());
         result.setSalesmenTotal(salesmen.size());
         result.setGreatestSaleId(greatestSale.getId());
         result.setWorstSalesmanName(worstSalesman.getName());

         return result;
      }

      return result;
   }

   private List<Salesman> getSalesmen(List<String> lines) {

      List<Salesman> salesmen = lines.stream().filter(x-> x.startsWith(DataIdentifierEnum.SALESMAN_ID.getId()))
            .map(x -> x.split(DelimiterEnum.COLUMN_DELIMITER.getDelimiter()))
            .map(x -> new Salesman(x[1], x[2], x[3])).collect(Collectors.toList());

      if (salesmen.isEmpty()) {
         throw new DataLoadException("Salesmen not found in the file!");
      }
      return salesmen;

   }

   private List<Client> getClients(List<String> lines) {
      List<Client> clients = lines.stream().filter(x-> x.startsWith(DataIdentifierEnum.CLIENT_ID.getId()))
            .map(x -> x.split(DelimiterEnum.COLUMN_DELIMITER.getDelimiter()))
            .map(x -> new Client(x[1], x[2], x[3])).collect(Collectors.toList());

      if (clients.isEmpty()) {
         throw new DataLoadException("Clients not found in the file!");
      }
      return clients;
   }

   private List<Sale> getSales(List<String> lines, List<Salesman> salesmen) {
      List<Sale> sales = lines.stream().filter(x-> x.startsWith(DataIdentifierEnum.SALE_ID.getId()))
            .map(x -> x.split(DelimiterEnum.COLUMN_DELIMITER.getDelimiter()))
            .map(x -> new Sale(Integer.parseInt(x[1]),
                  getItems(x[2]).get(),
                  salesmen.stream().filter(s -> s.getName().equals(x[3])).findFirst().get())).collect(Collectors.toList());

      if (sales.isEmpty()) {
         throw new DataLoadException("Sales not found in the file!");
      }
      return sales;
   }

   private Sale getGreatestSale(List<Sale> sales) {
      Sale maiorVenda = sales.stream().findFirst().get();
      Double maior = 0.0;

      for (Sale s : sales) {
         maior = maiorVenda.getItems()
               .stream().map(x -> x.getQuantity() * x.getPrice()).reduce(0.0, Double::sum);
         Double vendaAtual = s.getItems().stream()
               .map(x -> x.getPrice() * x.getQuantity())
               .reduce(0.0, Double::sum);
         maiorVenda = vendaAtual > maior ? s : maiorVenda;
      }

      return maiorVenda;
   }

   private Salesman getWorstSalesman(List<Sale> sales, List<Salesman> salesmen) {
      Map<Salesman, Double> vendedores = new HashMap<>();
      List<List<Item>> items;
      Double total = 0.0;

      for (Salesman s: salesmen) {
         items = sales.stream().
               filter(x -> x.getSalesman().getName().equals(s.getName()))
               .map(Sale::getItems)
               .collect(Collectors.toList());

         total = items.stream()
               .map(l -> l.stream()
                     .map(v -> v.getQuantity() * v.getPrice())
                     .reduce(0.0, Double::sum))
               .reduce(0.0, Double::sum);

         vendedores.put(s, total);
      }

      return vendedores.entrySet().stream().min(Map.Entry.comparingByValue(Double::compareTo)).get().getKey();

   }

   private Optional<List<Item>> getItems(String line) {
      List<Item> items = new ArrayList<>();
      String[] sItems = line.substring(1, line.length()-1).split(DelimiterEnum.SALE_DELIMITER.getDelimiter());
      for (String s : sItems) {
         String[] temp = s.split(DelimiterEnum.SALEDATA_DELIMITER.getDelimiter());
         items.add(new Item(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Double.parseDouble(temp[2])));
      }
      return Optional.of(items);
   }

}
