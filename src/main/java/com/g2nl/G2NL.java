package com.g2nl;

import java.util.ArrayList;
import java.util.Iterator;

import com.g2nl.struct.Graph;
import com.g2nl.struct.NL;
import com.g2nl.io.IOFactory;

public class G2NL {

  public static void main(String[] args) {
    String location = System.getProperty("user.dir") + "/data/test";
    if (args.length > 0) {
      location = args[0].trim();
    }
    Graph testGraph = new Graph();
    if (!IOFactory.LoadGraph(testGraph, location)) {
      System.exit(0);
    }

    // print graph
    System.out.println("--------------Graph-------------");
    System.out.println(testGraph.toString());

    NL testNL = new NL();
    testNL.addVRDF(testGraph.vList());
    testNL.addERDF(testGraph.eList());

    // print RDF
    System.out.println("---------------RDF--------------");
    testNL.printRDF();

    // do something for NLGraph
    testNL.filterActive();
    System.out.println("---------------NL---------------");
    testNL.printNL();

    System.out.println("-------------NL Opt-------------");
    String rlt = testNL.optNL();
    System.out.println(rlt);
  }

}
