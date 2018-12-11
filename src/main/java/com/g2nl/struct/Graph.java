package com.g2nl.struct;

import java.util.ArrayList;
import java.util.Iterator;

public class Graph {
  private ArrayList<Vertex> vertices = new  ArrayList<Vertex>();
  private ArrayList<Edge> edges = new  ArrayList<Edge>();

  public Graph() {}

  public boolean addVertex(final long id, final String label, final String data) {
    vertices.add(new Vertex(id, label, data));
    // TODO: check whether exist
    return true;
  }

  public boolean addVertex(final long id) {
    vertices.add(new Vertex(id));
    // TODO: check whether exist
    return true;
  }

  public boolean addEdge(final long id, final long src, final long dst) {
    Iterator<Vertex> vIter = vertices.iterator();
    boolean srcExist = false;
    boolean dstExist = false;
    while (vIter.hasNext()){
      Vertex tmp = vIter.next();
      long tmpId = tmp.id();
      if (tmpId == src) {srcExist = true;}
      if (tmpId == dst) {dstExist = true;}
      if (srcExist && dstExist) {break;}
    }
    if (srcExist && dstExist) {
      edges.add(new Edge(id, src, dst));
      return true;
    } else {
      return false;
    }
  }

  public boolean addEdge(final long id, final long src, final long dst, final String label, final String data) {
    Iterator<Vertex> vIter = vertices.iterator();
    boolean srcExist = false;
    boolean dstExist = false;
    while (vIter.hasNext()){
      Vertex tmp = vIter.next();
      long tmpId = tmp.id();
      if (tmpId == src) {srcExist = true;}
      if (tmpId == dst) {dstExist = true;}
      if (srcExist && dstExist) {break;}
    }
    if (srcExist && dstExist) {
      edges.add(new Edge(id, src, dst, label, data));
      return true;
    } else {
      return false;
    }
  }

  public ArrayList<Vertex> vList() {
    return this.vertices;
  }

  public ArrayList<Edge> eList() {
    return this.edges;
  }

  public String toString() {
    String vStr = "Vertices:(ID, LABEL, DATA)";
    String eStr = "Edges:(ID, SRC, DST, LABEL, DATA)";
    Iterator<Vertex> vIter = vertices.iterator();
    while (vIter.hasNext()) {
      Vertex vTmp = vIter.next();
      vStr += "\n" + vTmp.toString();
    }
    Iterator<Edge> eIter = edges.iterator();
    while (eIter.hasNext()) {
      Edge eTmp = eIter.next();
      eStr += "\n" + eTmp.toString();
    }
    return vStr + "\n" + eStr;
  }

}
