package ch.examibur.ui.app.routing;

import ch.examibur.service.exception.InvalidParameterException;
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
  public void getLongUrlParameterWithNegativeValue() throws InvalidParameterException {
    Request request = getRequestSpy("examId", "-1");
    RoutingHelpers.getUnsignedLongUrlParameter(request, UrlParameter.EXAM_ID);
  }

  @Test(expected = NumberFormatException.class)
  public void getLongUrlParameterWithOverflowValue() throws InvalidParameterException {
    Request request = getRequestSpy("examId", "10000000000000000000");
    RoutingHelpers.getUnsignedLongUrlParameter(request, UrlParameter.EXAM_ID);
  }

  @Test(expected = NumberFormatException.class)
  public void getLongUrlParameterWithNonNumericValue() throws InvalidParameterException {
    Request request = getRequestSpy("examId", "1l");
    RoutingHelpers.getUnsignedLongUrlParameter(request, UrlParameter.EXAM_ID);
  }

  @Test(expected = NumberFormatException.class)
  public void getLongUrlParameterWithNonNumericValue2() throws InvalidParameterException {
    Request request = getRequestSpy("examId", "l1");
    RoutingHelpers.getUnsignedLongUrlParameter(request, UrlParameter.EXAM_ID);
  }

  @Test
  public void getDoubleBodyParameter() throws InvalidParameterException {
    String queryParamKey = "paramId";
    Request request = getRequestSpyForQueryParam(queryParamKey, "123");
    Assert.assertEquals(123D, RoutingHelpers.getUnsignedDoubleBodyParameter(request, queryParamKey),
        0.01);
  }

  @Test(expected = InvalidParameterException.class)
  public void getDoubleBodyParameterWhenNotSet() throws InvalidParameterException {
    String queryParamKey = "paramId";
    Request request = getRequestSpyForQueryParam(queryParamKey, null);
    RoutingHelpers.getUnsignedDoubleBodyParameter(request, queryParamKey);
  }

  @Test(expected = InvalidParameterException.class)
  public void getDoubleBodyParameterWithNegativeValue() throws InvalidParameterException {
    String queryParamKey = "paramId";
    Request request = getRequestSpyForQueryParam(queryParamKey, "-1");
    RoutingHelpers.getUnsignedDoubleBodyParameter(request, queryParamKey);
  }

  @Test(expected = NumberFormatException.class)
  public void getDoubleBodyParameterWithNonNumericValue() throws InvalidParameterException {
    String queryParamKey = "paramId";
    Request request = getRequestSpyForQueryParam(queryParamKey, "1l");
    RoutingHelpers.getUnsignedDoubleBodyParameter(request, queryParamKey);
  }

  @Test(expected = NumberFormatException.class)
  public void getDoubleBodyParameterWithNonNumericValue2() throws InvalidParameterException {
    String queryParamKey = "paramId";
    Request request = getRequestSpyForQueryParam(queryParamKey, "l1");
    RoutingHelpers.getUnsignedDoubleBodyParameter(request, queryParamKey);
  }

  private Request getRequestSpy(String urlParameter, String value) {
    Request mock = Mockito.mock(Request.class);
    Mockito.when(mock.params(urlParameter)).thenReturn(value);
    return mock;
  }

  private Request getRequestSpyForQueryParam(String key, String value) {
    Request mock = Mockito.mock(Request.class);
    Mockito.when(mock.queryParams(key)).thenReturn(value);
    return mock;
  }

}
