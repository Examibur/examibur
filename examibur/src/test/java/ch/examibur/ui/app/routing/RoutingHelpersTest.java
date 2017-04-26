package ch.examibur.ui.app.routing;

import ch.examibur.business.exception.InvalidParameterException;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import spark.Request;

public class RoutingHelpersTest {

  @Test
  public void getLongUrlParameter() throws InvalidParameterException {
    Request request = getRequestSpy("examId", "123");
    Assert.assertEquals(123L,
        RoutingHelpers.getUnsignedLongUrlParameter(request, UrlParameter.EXAM_ID));
  }

  @Test(expected = InvalidParameterException.class)
  public void getLongUrlParameterWhenNotSet() throws InvalidParameterException {
    Request request = getRequestSpy("examId", null);
    RoutingHelpers.getUnsignedLongUrlParameter(request, UrlParameter.EXAM_ID);
  }

  @Test(expected = InvalidParameterException.class)
  public void getLongUrlParameterWhenWithNegativeValue() throws InvalidParameterException {
    Request request = getRequestSpy("examId", "-1");
    RoutingHelpers.getUnsignedLongUrlParameter(request, UrlParameter.EXAM_ID);
  }

  @Test(expected = NumberFormatException.class)
  public void getLongUrlParameterWhenWithOverflowValue() throws InvalidParameterException {
    Request request = getRequestSpy("examId", "10000000000000000000");
    RoutingHelpers.getUnsignedLongUrlParameter(request, UrlParameter.EXAM_ID);
  }

  @Test(expected = NumberFormatException.class)
  public void getLongUrlParameterWhenWithNonNumericValue() throws InvalidParameterException {
    Request request = getRequestSpy("examId", "1l");
    RoutingHelpers.getUnsignedLongUrlParameter(request, UrlParameter.EXAM_ID);
  }

  @Test(expected = NumberFormatException.class)
  public void getLongUrlParameterWhenWithNonNumericValue2() throws InvalidParameterException {
    Request request = getRequestSpy("examId", "l1");
    RoutingHelpers.getUnsignedLongUrlParameter(request, UrlParameter.EXAM_ID);
  }

  private Request getRequestSpy(String urlParameter, String value) {
    Request mock = Mockito.mock(Request.class);
    Mockito.when(mock.params(urlParameter)).thenReturn(value);
    return mock;
  }

}
