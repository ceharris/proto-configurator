package ceh.demo.configurator;

import ceh.demo.Node;

abstract class AbstractNode implements Node {

  private final String name;
  private final Node subtree;
  private final Node sibling;
  
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
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public final boolean isEnabled() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public final Node getSubtree() {
    return subtree;
  }

  @Override
  public final Node getSibling() {
    return sibling;
  }

}
