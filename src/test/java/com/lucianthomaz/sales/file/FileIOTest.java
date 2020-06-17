package com.lucianthomaz.sales.file;

import com.lucianthomaz.sales.dataprovider.InDataProvider;
import com.lucianthomaz.sales.dataprovider.OutDataProvider;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import static com.lucianthomaz.sales.enums.PathEnum.INPUT_PATH;
import static com.lucianthomaz.sales.enums.PathEnum.OUTPUT_PATH;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FileIOTest {

   static final Path inputPath = INPUT_PATH.getPath().resolve("test.txt");
   static final Path outputPath = OUTPUT_PATH.getPath().resolve("test.txt");

   @BeforeAll
   public static void setup() throws IOException {
      File inputDirectory = new File(INPUT_PATH.getPath().toString());
      File outputDirectory = new File(OUTPUT_PATH.getPath().toString());
      inputDirectory.mkdirs();
      outputDirectory.mkdirs();
      Files.write(inputPath, InDataProvider.getEntrada());
   }

   @Test
   public void shouldReadContentOfInputFile() {
      List<String> input = FileIO.readFile(inputPath);
      assertEquals(InDataProvider.getEntrada(), input);
   }

   @Test
   public void shouldWriteReportInTheOutputFile() throws IOException {
      FileIO.writeReport(outputPath, OutDataProvider.getResult());
      List<String> output = Files.lines(outputPath).collect(Collectors.toList());
      assertEquals(output, OutDataProvider.getFileResult());
   }

   @AfterAll
   public static void tearDown() throws IOException {
      Files.delete(inputPath);
      Files.delete(outputPath);
   }

}