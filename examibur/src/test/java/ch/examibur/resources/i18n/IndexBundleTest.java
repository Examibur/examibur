package ch.examibur.resources.i18n;

import static org.junit.Assert.assertEquals;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;

public class IndexBundleTest {

  @Test
  public void loadIndexBundleWelcomeMessage() {
    ResourceBundle resBundle =
        ResourceBundle.getBundle("ch/examibur/resources/i18n/IndexBundle", Locale.ROOT);
    String rawMessage = resBundle.getString("welcomeMessage");
    String messageTxt = MessageFormat.format(rawMessage, "Examibur");
    assertEquals("Hallo Examibur", messageTxt);
  }

}
