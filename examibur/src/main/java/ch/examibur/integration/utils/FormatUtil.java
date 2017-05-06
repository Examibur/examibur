package ch.examibur.integration.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class FormatUtil {
  
  public static final int SCALE_NEAREST_HUNDRED = 2;
  
  private FormatUtil(){
  }

  /**
   * Round a double to specified number of decimal places.
   * 
   * @param value
   *          value to round
   * @param places
   *          number of decimal places the rounded value should have
   * @return rounded value
   */
  public static double round(double value, int places) {
    if (places < 0) {
      throw new IllegalArgumentException();
    }
      
    BigDecimal bdToRound = BigDecimal.valueOf(value);
    bdToRound = bdToRound.setScale(places, RoundingMode.HALF_UP);
    return bdToRound.doubleValue();
  }
}
