package com.lucianthomaz.sales;

import com.lucianthomaz.sales.file.DirectoryMonitoring;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SalesAnalysisApplication {

   public static void main(String[] args) {
      SpringApplication.run(SalesAnalysisApplication.class, args);
   }

   @Bean
   public void run() {
      DirectoryMonitoring directoryMonitoring = new DirectoryMonitoring();
      directoryMonitoring.monitorDirectory();
   }

}
