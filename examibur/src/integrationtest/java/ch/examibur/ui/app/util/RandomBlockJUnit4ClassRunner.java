package ch.examibur.ui.app.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

public class RandomBlockJUnit4ClassRunner extends BlockJUnit4ClassRunner {

  public RandomBlockJUnit4ClassRunner(Class<?> klass) throws InitializationError {
    super(klass);
  }

  @Override
  protected List<FrameworkMethod> computeTestMethods() {
    java.util.List<FrameworkMethod> methods = new ArrayList<>(super.computeTestMethods());
    Collections.shuffle(methods);
    return methods;
  }

}
