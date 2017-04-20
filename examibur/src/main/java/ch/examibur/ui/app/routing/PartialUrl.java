package ch.examibur.ui.app.routing;

public final class PartialUrl {

  private final String url;

  public PartialUrl(PartialUrl url, UrlParameter param, Object replacement) {
    this(url.url, param, replacement);
  }

  public PartialUrl(String url, UrlParameter param, Object replacement) {
    this.url = url.replaceFirst(param.getRegex(),
        replacement == null ? "" : replacement.toString());
  }

  public PartialUrl with(UrlParameter parameter, Object replacement) {
    return new PartialUrl(this, parameter, replacement);
  }

  public String url() {
    return url;
  }

}
