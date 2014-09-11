package ceh.demo.configurator;

import ceh.demo.Node;
import ceh.demo.Section;

public class SectionNode extends AbstractNode implements Section {

  public SectionNode(String name, Node subtree, Node sibling) {
    super(name, subtree, sibling);
  }

}
