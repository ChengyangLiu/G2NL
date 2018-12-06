
public class Vertex {
  private long id;
  protected String label;
  protected String data;

  public Vertex() {
    this.id = 0;
    this.label = "";
    this.data = "";
  }

  public Vertex(final long id) {
    this.id = id;
  }

  public Vertex(final long id, final String label, final String data) {
    this.id = id;
    this.label = label;
    this.data = data;
  }

  public long id() {
    return this.id;
  }

  public String label() {
    return this.label;
  }

  public String data() {
    return this.data;
  }

  public String toString() {
    return String.valueOf(this.id) + ", " + this.label + ", " + this.data;
  }

}
