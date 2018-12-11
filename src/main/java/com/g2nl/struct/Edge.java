package com.g2nl.struct;

public class Edge {
  private long id;
  private long src;
  private long dst;
  protected String label;
  protected String data;

  public Edge(final long id, final long src, final long dst) {
    this.id = id;
    this.src = src;
    this.dst = dst;
    this.label = "";
    this.data = "";
  }

  public Edge(final long id, final long src, final long dst, final String label, final String data) {
    this.id = id;
    this.src = src;
    this.dst = dst;
    this.label = label;
    this.data = data;
  }

  public long id() {
    return this.id;
  }

  public long src() {
    return this.src;
  }

  public long dst() {
    return this.dst;
  }

  public String label() {
    return this.label;
  }

  public String data() {
    return this.data;
  }

  public String toString() {
    return String.valueOf(this.id) + ", " + String.valueOf(this.src)
    + ", " + String.valueOf(this.dst) + ", " + this.label + ", " + this.data;
  }

}
