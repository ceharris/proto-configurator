package ceh.demo;

public interface Configurator {

  Node getRoot();
  
  void update();
  
  Object get(String name);
  
  Object put(String name, Object value);
  
  boolean contains(String name);
  
}
