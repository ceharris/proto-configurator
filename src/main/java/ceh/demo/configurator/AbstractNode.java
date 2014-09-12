package ceh.demo.configurator;

import java.util.HashMap;
import java.util.Map;

import ceh.demo.Node;

abstract class AbstractNode implements Node {

  private final Map<String, Object> attributes = new HashMap<>();
  
  private final String name;
  private final Node sibling;

  private Node subtree;
  private boolean enabled = true;
  
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
  public final boolean isEnabled() {
    return enabled;
  }

  @Override
  public boolean setEnabled(boolean enabled) {
    boolean oldEnabled = this.enabled;
    this.enabled = enabled;
    return oldEnabled;
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

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getAttribute(String name) {
    return attributes.get(name);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getAttribute(String name, Object defaultValue) {
    Object value = getAttribute(name);
    if (value == null) {
      value = defaultValue;
    }
    return value;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object setAttribute(String name, Object value) {
    return attributes.put(name, value);
  }

}
