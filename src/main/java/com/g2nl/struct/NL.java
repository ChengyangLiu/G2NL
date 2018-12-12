package com.g2nl.struct;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.aksw.triple2nl.TripleConverter;
import org.dllearner.kb.sparql.SparqlEndpoint;
import com.hp.hpl.jena.graph.Triple;
import com.hp.hpl.jena.graph.NodeFactory;

public class NL {
  private long vNum;
  private long eNum;
  private ArrayList<RDF> vRDF;
  private ArrayList<RDF> eRDF;
  private ArrayList<Boolean> activeList;

  public NL() {
    this.vNum = 0;
    this.eNum = 0;
    this.vRDF = new ArrayList<RDF>();
    this.eRDF = new ArrayList<RDF>();
    this.activeList = new ArrayList<Boolean>();
  }

  public void addVRDF(ArrayList<Vertex> vList) {
    this.vNum = vList.size();
    Iterator<Vertex> vIter = vList.iterator();
    while (vIter.hasNext()) {
      Vertex v = vIter.next();
      this.vRDF.add(new RDF(v.id(), v.label().trim().replace(" ", "_"), v.data().trim().replace(" ", "_")));
    }
  }

  public void addERDF(ArrayList<Edge> eList) {
    this.eNum = eList.size();
    Iterator<Edge> eIter = eList.iterator();
    while (eIter.hasNext()) {
      Edge e = eIter.next();
      this.eRDF.add(new RDF(e.src(), e.label().trim().replace(" ", "_"), String.valueOf(e.dst()).trim().replace(" ", "_")));
    }
  }

  public void printRDF() {
    String str = "";
    str += "V RDF: " + this.vNum + "\n";
    Iterator<RDF> vRIter = this.vRDF.iterator();
    while (vRIter.hasNext()) {
      RDF tmp = vRIter.next();
      str += tmp.toString() + "\n";
    }
    str += "E RDF: " + this.eNum + "\n";
    Iterator<RDF> eRIter = this.eRDF.iterator();
    while (eRIter.hasNext()) {
      RDF tmp = eRIter.next();
      str += tmp.toString() + "\n";
    }
    System.out.print(str);
  }

  public void filterActive() {
    Iterator<RDF> vRIter = vRDF.iterator();
    while (vRIter.hasNext()) {
      RDF vtmp = vRIter.next();
      long id = vtmp.resource();
      boolean hasOutEdge = false;
      Iterator<RDF> eRIter = eRDF.iterator();
      while (eRIter.hasNext()) {
        RDF etmp = eRIter.next();
        if (id == etmp.resource()) {
          hasOutEdge = true;
          break;
        }
      }
      this.activeList.add(new Boolean(hasOutEdge));
    }
  }

  public String optNL() {
    String rlt = "";
    int count = 1;
    for (int index = 0; index < this.vNum; index++) {
      if (activeList.get(index) == false) {
        continue;
      }
      List<Triple> tList = new ArrayList<Triple>();
      RDF vtmp = this.vRDF.get(index);
      long id = vtmp.resource();
      Iterator<RDF> eIter = eRDF.iterator();
      while (eIter.hasNext()) {
        RDF etmp = eIter.next();
        if (etmp.resource() == id) {
          Triple te = Triple.create(
      			 NodeFactory.createURI("http://dbpedia.org/resource/" + vtmp.value()),
      			 NodeFactory.createURI("http://dbpedia.org/ontology/" + etmp.type()),
      			 NodeFactory.createURI("http://dbpedia.org/resource/" + this.vRDF.get(Integer.valueOf(etmp.value())).value()));
          tList.add(te);
        }
      }
      // print URI (subject-predicate-object)
      //printTripleList(tList);

      // Optionally, we can declare a knowledge base that contains the triple.
      // This can be useful during the verbalization process, e.g. the KB could contain labels for entities.
      // Here, we use the DBpedia SPARQL endpoint.
      SparqlEndpoint endpoint = SparqlEndpoint.getEndpointDBpedia();
      // create the triple converter
      TripleConverter converter = new TripleConverter(endpoint);
      // convert the triple into natural language
      String text = converter.convertTriplesToText(tList);
      rlt += String.valueOf(count++) + "." + vtmp.type() + " " + text + "\n";
    }
    return rlt;
  }

  public void printTripleList(List<Triple> tList) {
    String str = "";
    Iterator<Triple> tIter = tList.iterator();
    while (tIter.hasNext()) {
      Triple t = tIter.next();
      str += t.getSubject() + "-" + t.getPredicate() + "-" + t.getObject() + "\n";
    }
    System.out.println("Test:\n"+ str);
  }

  public void printNL() {
    String rlt = "";
    for (int index = 0; index < this.vNum; index++) {
      if (activeList.get(index) == false) {
        continue;
      }
      RDF vtmp = this.vRDF.get(index);
      long id = vtmp.resource();
      rlt += vtmp.type() + " " + vtmp.value();
      Iterator<RDF> eIter = eRDF.iterator();
      while (eIter.hasNext()) {
        RDF etmp = eIter.next();
        if (etmp.resource() == id) {
          rlt += ", " + etmp.type() + " "
          + this.vRDF.get(Integer.valueOf(etmp.value())).value();
        }
      }
      rlt += "\n";
    }
    System.out.print(rlt);
  }

}
