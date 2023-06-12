package tech.claudioed.infra;

public enum CExtensions {

  EVENT_CONTEXT("eventcontext"),
  AUDIENCE("audience"),
  TAGS("tags");
  private final String name;

  CExtensions(String name) {
    this.name = name;
  }
  public String getName() {
    return name;
  }

  public enum Audience {

    INTERNAL_BOUNDED_CONTEXT("internal-bounded-context"),

    EXTERNAL_BOUNDED_CONTEXT("external-bounded-context");
    private final String name;

    Audience(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }

  }
  public enum EventContext {

    DOMAIN("domain"),

    SYSTEM("system");
    private final String name;

    EventContext(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }

  }

}
