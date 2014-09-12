package ceh.demo;


public interface Configurator {

  Node getRoot();

  void update();

  Node node(String name) throws IllegalArgumentException;

}
