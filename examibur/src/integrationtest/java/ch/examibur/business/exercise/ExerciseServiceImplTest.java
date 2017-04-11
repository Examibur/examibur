package ch.examibur.business.exercise;

import ch.examibur.business.DatabaseResource;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

public class ExerciseServiceImplTest {

  @ClassRule
  public static final DatabaseResource RES = new DatabaseResource();
  
  private final ExerciseService exerciseService = new ExerciseServiceImpl(); 
  
  
  @Test
  public void testGetMaxPoints() {
    double maxPoints = exerciseService.getMaxPoints(4);
    Assert.assertEquals(6, maxPoints, 0.01);
  }
  
  @Test
  public void testGetMaxPointsForExamWithoutExercises() {
    double maxPoints = exerciseService.getMaxPoints(1);
    Assert.assertEquals(0, maxPoints, 0.01);
  }
  
  @Test
  public void testGetMaxPointsWithNonexistentExamId() {
    double maxPoints = exerciseService.getMaxPoints(4242);
    Assert.assertEquals(0, maxPoints, 0.01);
  }

}
