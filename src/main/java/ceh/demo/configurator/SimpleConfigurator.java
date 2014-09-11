package ceh.demo.configurator;

import ceh.demo.Configurator;
import ceh.demo.Node;

public class SimpleConfigurator implements Configurator {

  private final Node root;
  
  /**
   * Constructs a new instance.
   * @param root
   */
  public SimpleConfigurator(Node root) {
    this.root = root;
  }

  @Override
  public Node getRoot() {
    return root;
  }

  @Override
  public void update() {
    // TODO Auto-generated method stub

  }

  @Override
  public Object get(String name) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object put(String name, Object value) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean contains(String name) {
    // TODO Auto-generated method stub
    return false;
  }

}
