package com.g2nl.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

import com.g2nl.struct.Graph;
import com.g2nl.struct.NL;

public class IOFactory {

  IOFactory() {}

  public static boolean LoadGraph(Graph g, final String location) {
    String vFile = location + ".v";
    String eFile = location + ".e";
    try {
      // load vfile
			FileInputStream fileInput = new FileInputStream(vFile);
			Scanner sc = new Scanner(fileInput, "UTF-8");
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
        String[] elements = line.split(",");
        if (elements.length == 1 && g.addVertex(Long.valueOf(elements[0]))) {
        } else if (elements.length == 2 && g.addVertex(Long.valueOf(elements[0]), elements[1], "")) {
        } else if (elements.length == 3 && g.addVertex(Long.valueOf(elements[0]), elements[1], elements[2])) {
        } else {
          System.out.println("vfile format error!");
          return false;
        }
			}
			if (fileInput != null) { fileInput.close();}
			if (sc != null) { sc.close();}

      // load efile
      fileInput = new FileInputStream(eFile);
			sc = new Scanner(fileInput, "UTF-8");
      while (sc.hasNextLine()) {
				String line = sc.nextLine();
        String[] elements = line.split(",");
        if (elements.length == 3 && g.addEdge(Long.valueOf(elements[0]), Long.valueOf(elements[1]), Long.valueOf(elements[2]))) {
        } else if (elements.length == 4 && g.addEdge(Long.valueOf(elements[0]), Long.valueOf(elements[1]), Long.valueOf(elements[2]), elements[3], "")) {
        } else if (elements.length == 5 && g.addEdge(Long.valueOf(elements[0]), Long.valueOf(elements[1]), Long.valueOf(elements[2]), elements[3], elements[4])){
        } else {
          System.out.println("efile format error!");
          return false;
        }
			}
			if (fileInput != null) { fileInput.close();}
			if (sc != null) { sc.close();}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    return true;
  }

}
