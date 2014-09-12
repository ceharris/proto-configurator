package ceh.demo.configurator;

import ceh.demo.Input;
import ceh.demo.Node;
import ceh.demo.Property;

public class PropertyNode extends AbstractNode implements Property {

  private final Input input;

  private Object value;
  
  /**
   * Constructs a new instance.
   * 
   * @param name
   * @param subtree
   * @param sibling
   */
  public PropertyNode(String name, Node sibling, Input input) {
    super(name, null, sibling);
    this.input = input;
  }

  @Override
  public Input getInput() {
    return input;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isNull() {
    return value == null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getValue() {
    return value;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object setValue(Object value) {
    Object oldValue = this.value;
    this.value = value;
    return oldValue;
  }

  @Override
  public String toString() {
    if (value == null) return "";
    return value.toString();
  }

}
