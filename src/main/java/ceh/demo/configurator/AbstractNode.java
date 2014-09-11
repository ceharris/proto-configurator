package ceh.demo.configurator;

import ceh.demo.Node;

abstract class AbstractNode implements Node {

  private final String name;
  private final Node sibling;
  
  private Node subtree;
  private boolean satisfied;
  
  /**
   * Constructs a new instance.
   * @param name
   * @param subtree
   * @param sibling
   */
  protected AbstractNode(String name, Node subtree, Node sibling) {
    this.name = name;
    this.subtree = subtree;
    this.sibling = sibling;
  }

  @Override
  public final String getName() {
    return name;
  }

  @Override
  public final boolean isSatisfied() {
    return satisfied;
  }

  public void setSatisfied(boolean satisfied) {
	this.satisfied = satisfied;
  }
  
  @Override
  public final boolean isEnabled() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public Node getSubtree() {
    return subtree;
  }

  public void setSubtree(Node subtree) {
	this.subtree = subtree;
  }

  @Override
  public final Node getSibling() {
    return sibling;
  }

}
