package ceh.demo.configurator;

import ceh.demo.Input;
import ceh.demo.Node;
import ceh.demo.Property;

public class PropertyNode extends AbstractNode implements Property {

  private final Input input;

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

}
