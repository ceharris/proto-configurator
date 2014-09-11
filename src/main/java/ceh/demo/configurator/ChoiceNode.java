package ceh.demo.configurator;

import ceh.demo.Choice;
import ceh.demo.Node;

public class ChoiceNode extends AbstractNode implements Choice {

  public ChoiceNode(String name, Node subtree, Choice sibling) {
    super(name, subtree, sibling);
  }

}
