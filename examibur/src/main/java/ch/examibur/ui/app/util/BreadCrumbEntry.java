package ch.examibur.ui.app.util;

public final class BreadCrumbEntry {
  private String title;
  private String location;

  /**
   * 
   * @param title
   *          The title/label of this breadcrumb.
   * @param location
   *          the href location of this breadcurmb entry.
   */
  public BreadCrumbEntry(String title, String location) {
    super();
    this.title = title;
    this.location = location;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }
}
