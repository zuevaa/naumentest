package com.zaa.naumentest.server.model;

// Получение реализации DAO

public class ContactDAOSingleton {
  private static ContactDAO instance;

  public static synchronized ContactDAO getInstance() {
    if (instance == null) {
      instance = new ContactDAODS();
    }

    return instance;
  }    
}
