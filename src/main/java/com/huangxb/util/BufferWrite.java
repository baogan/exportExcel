package com.huangxb.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BufferWrite {
  public static void UseBufferedWriter(String fileContent, String filePath) throws IOException {

    BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
    try{
      writer.write(fileContent);
    }catch (IOException e){
      e.printStackTrace();
      System.out.println("IOException error:" + e);
    }finally {
      writer.close();
    }
  }

}
