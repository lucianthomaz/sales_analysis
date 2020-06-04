package com.lucianthomaz.sales.enums;

import java.nio.file.Path;

public enum PathEnum {
   INPUT_PATH(Path.of(System.getProperty("user.home") + "/data/in/" )),
   OUTPUT_PATH(Path.of(System.getProperty("user.home") + "/data/out/" ));

   private Path path;

   PathEnum(Path path) {
      this.path = path;
   }

   public Path getPath() {
      return path;
   }
}
