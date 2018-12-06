import java.util.ArrayList;
import java.util.Iterator;

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
      this.vRDF.add(new RDF(v.id(), v.label(), v.data()));
    }
  }

  public void addERDF(ArrayList<Edge> eList) {
    this.eNum = eList.size();
    Iterator<Edge> eIter = eList.iterator();
    while (eIter.hasNext()) {
      Edge e = eIter.next();
      this.eRDF.add(new RDF(e.src(), e.label(), String.valueOf(e.dst())));
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

  public void optimize() {

  }

  public void printNL() {
    final String PRE = "是";
    final String PRONOUN = "其";
    String rlt = "";
    for (int index = 0; index < this.vNum; index++) {
      if (activeList.get(index) == false) {
        continue;
      }
      RDF vtmp = this.vRDF.get(index);
      long id = vtmp.resource();
      rlt += vtmp.type() + vtmp.value();
      Iterator<RDF> eIter = eRDF.iterator();
      while (eIter.hasNext()) {
        RDF etmp = eIter.next();
        if (etmp.resource() == id) {
          rlt += "," + etmp.type()
          + this.vRDF.get(Integer.valueOf(etmp.value())).value();
        }
      }
      rlt += "\n";
    }
    System.out.print(rlt);
  }

  public static void main(String[] args) {
    Graph testGraph = new Graph();
    testGraph.addVertex(0, "导演", "詹姆斯·卡梅隆");
    testGraph.addVertex(1, "电影", "《泰坦尼克号》");
    testGraph.addVertex(2, "国家", "加拿大");
    testGraph.addVertex(3, "学校", "加州州立大学");
    testGraph.addVertex(4, "奖项", "奥斯卡最佳导演奖");
    testGraph.addVertex(5, "人名", "Jack");
    testGraph.addVertex(6, "人名", "Rose");
    testGraph.addEdge(0, 0, 1, "代表作", "Data");
    testGraph.addEdge(1, 1, 0, "导演", "Data");
    testGraph.addEdge(2, 0, 2, "国籍", "Data");
    testGraph.addEdge(3, 0, 3, "毕业于", "Data");
    testGraph.addEdge(4, 0, 4, "荣获", "Data");
    testGraph.addEdge(5, 1, 5, "男主角", "Data");
    testGraph.addEdge(6, 1, 6, "女主角", "Data");

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
    testNL.optimize();

    // print NL
    System.out.println("---------------NL---------------");
    testNL.printNL();
  }

}
