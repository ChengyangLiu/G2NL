
public class RDF {
  private long resource;
  private String type;
  private String value;

  public RDF(final long resource, final String type, final String value) {
    this.resource = resource;
    this.type = type;
    this.value = value;
  }

  public long resource() {
    return this.resource;
  }

  public String type() {
    return this.type;
  }

  public String value() {
    return this.value;
  }

  public String toString() {
    return String.valueOf(this.resource) + "-" + this.type + "-" + this.value;
  }

}
