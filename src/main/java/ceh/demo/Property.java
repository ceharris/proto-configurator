package ceh.demo;

public interface Property extends Node {

  Input getInput();

  boolean isNull();
  
  Object getValue();
  
  Object setValue(Object value);
  
}
