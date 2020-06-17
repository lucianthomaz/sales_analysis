package com.lucianthomaz.sales.file;

import com.lucianthomaz.sales.service.DataProcessing;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

import static com.lucianthomaz.sales.enums.PathEnum.INPUT_PATH;
import static com.lucianthomaz.sales.enums.PathEnum.OUTPUT_PATH;
import static com.lucianthomaz.sales.file.FileIO.readFile;
import static com.lucianthomaz.sales.file.FileIO.writeReport;

public class DirectoryMonitoring {

   DataProcessing dataProcessing = new DataProcessing();

   public void monitorDirectory() {

      directoryCreation();

      try {
         Path inputPath = INPUT_PATH.getPath();
         WatchService watchService = FileSystems.getDefault().newWatchService();
         inputPath.register( watchService, StandardWatchEventKinds.ENTRY_CREATE);
         WatchKey key;
         while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
               Path newInPath = inputPath.resolve(event.context().toString());
               Path newOutPath = OUTPUT_PATH.getPath().resolve(event.context().toString());
               boolean isGrowing;
               long initialWeight;
               long finalWeight;
               do {
                  initialWeight = inputPath.toFile().length();
                  Thread.sleep(200);
                  finalWeight = inputPath.toFile().length();
                  isGrowing = initialWeight < finalWeight;

               } while (isGrowing);
               writeReport( newOutPath, dataProcessing.processFile(readFile(newInPath)));
            }
            key.reset();
         }
      } catch (InterruptedException e) {
         e.printStackTrace();
      } catch (IOException e) {
         throw new RuntimeException("An error occurred while trying to monitor the folder", e);
      }
   }

   private void directoryCreation() {
      File inputDirectory = new File(INPUT_PATH.getPath().toString());
      File outputDirectory = new File(OUTPUT_PATH.getPath().toString());
      inputDirectory.mkdirs();
      outputDirectory.mkdirs();
   }

}
