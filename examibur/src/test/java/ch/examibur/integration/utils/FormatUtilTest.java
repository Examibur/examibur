package ch.examibur.integration.utils;

import org.junit.Assert;
import org.junit.Test;

public class FormatUtilTest {
  
  private static final double DOUBLE_DELTA = 0.0001;

  @Test
  public void testRoundRemainTheSame() {
    Assert.assertEquals(3.5D, FormatUtil.round(3.5D, FormatUtil.SCALE_NEAREST_HUNDRED), DOUBLE_DELTA);
    Assert.assertEquals(3.75D, FormatUtil.round(3.75D, FormatUtil.SCALE_NEAREST_HUNDRED), DOUBLE_DELTA);
  }
  
  @Test
  public void testRound() {
    Assert.assertEquals(3.76D, FormatUtil.round(3.755D, FormatUtil.SCALE_NEAREST_HUNDRED), DOUBLE_DELTA);
    Assert.assertEquals(3.25D, FormatUtil.round(3.245D, FormatUtil.SCALE_NEAREST_HUNDRED), DOUBLE_DELTA);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testRoundNegativePlaces() {
    FormatUtil.round(3.5D, -2);
  }

}
