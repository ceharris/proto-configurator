package ceh.demo;

public interface Node {

  String getName();

  boolean isEnabled();
  
  boolean setEnabled(boolean enabled);

  Node getSubtree();

  Node getSibling();
  
  Object getAttribute(String name);
  
  Object getAttribute(String name, Object defaultValue);
  
  Object setAttribute(String name, Object value);

}
