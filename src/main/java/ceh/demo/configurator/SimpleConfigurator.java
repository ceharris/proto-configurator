package ceh.demo.configurator;

import java.util.LinkedList;
import java.util.List;

import javax.script.Invocable;
import javax.script.ScriptEngine;

import ceh.demo.Configurator;
import ceh.demo.Node;

public class SimpleConfigurator implements Configurator {

  private final List<Rule> rules = new LinkedList<>();
  
  private final Node root;
  private final ScriptEngine engine;

  /**
   * Constructs a new instance.
   * @param root
   */
  public SimpleConfigurator(Node root, ScriptEngine engine) {
    this.root = root;
    this.engine = engine;
  }

  @Override
  public Node getRoot() {
    return root;
  }

  public void rule(Object rule) {
    rules.add(((Invocable) engine).getInterface(rule, Rule.class));    
  }

  @Override
  public Node node(String name) {
    Node node = root != null ? node(name, root) : null;
    if (node != null) return node;
    throw new IllegalArgumentException("node " + name + " not found");      
  }

  private Node node(String name, Node node) {
    if (node == null) return null;
    
    if (node.getName().equals(name)) return node;
    
    Node sibling = node(name, node.getSibling());
    if (sibling != null) return sibling;
    
    Node subtree = node(name, node.getSubtree());
    if (subtree != null) return subtree;

    if (node instanceof SelectionPropertyNode) {
      Node choice = node(name, 
          ((SelectionPropertyNode) node).getSelection().getChoices());
      if (choice != null) return choice;
    }
    
    return null;
  }
  
  @Override
  public void update() {
  }

  @Override
  public String toString() {
    return toString(root);
  }
  
  private String toString(Node node) {
    if (node == null) return "";

    String tail = toString(node.getSubtree())
        + toString(node.getSibling());

    if (node instanceof PropertyNode) {
      tail = node.getName() + "=" + ((PropertyNode) node).getValue()
          + "\n" + tail;
    }
      
    return tail;
  }
  
}
