package ch.examibur.integration.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public enum EntityManagerHelper {
  INSTANCE;
  private EntityManagerFactory factory;

  private EntityManagerHelper() {
    factory = Persistence.createEntityManagerFactory("examibur");
  }

  public EntityManager createEntityManager() {
    return factory.createEntityManager();
  }
}
