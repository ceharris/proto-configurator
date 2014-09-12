package ceh.demo;

public interface Node {

  String getName();

  boolean isSatisfied();

  boolean isEnabled();

  Node getSubtree();

  Node getSibling();

}
