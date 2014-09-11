package ceh.demo.configurator;

import ceh.demo.Node;

public class RootNode implements Node {

  private final Node subtree;
  
  public RootNode(Node subtree) {
    this.subtree = subtree;
  }
  
  @Override
  public String getName() {
    return null;
  }

  @Override
  public boolean isSatisfied() {
    return false;
  }

  @Override
  public boolean isEnabled() {
    return false;
  }

  @Override
  public Node getSubtree() {
    return subtree;
  }

  @Override
  public Node getSibling() {
    return null;
  }

}
