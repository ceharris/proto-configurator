package ceh.demo.configurator;

import java.util.LinkedHashMap;
import java.util.Map;

import ceh.demo.Choice;
import ceh.demo.Configurator;
import ceh.demo.Node;
import ceh.demo.Property;
import ceh.demo.Selection;

public class SimpleConfigurator implements Configurator {

  private final Map<String, Object> properties = new LinkedHashMap<>(); 

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
    updateIsSatisfied(root);
    linkToSelectedChoices(root);
  }

  private void updateIsSatisfied(Node node) {
	if (node == null) return;
	updateIsSatisfied(node.getSubtree());
	if (contains(node.getName())) {
	  ((AbstractNode) node).setSatisfied(true);
	}
	updateIsSatisfied(node.getSibling());
  }
  
  private void linkToSelectedChoices(Node node) {
	if (node == null) return;
	linkToSelectedChoices(node.getSubtree());
	if (node instanceof Property
	    && ((Property) node).getInput() instanceof Selection) {
		PropertyNode property = (PropertyNode) node;
		if (property.isSatisfied() && property.isEnabled()) {
		  property.setSubtree(findChoice((String) properties.get(property.getName()),
				  ((Selection) property.getInput()).getChoices()));
		}
		else {
		  property.setSubtree(null);
		}
	}
	linkToSelectedChoices(node.getSibling());
  }
  

  private Node findChoice(String name, Choice choice) {
    if (choice == null) {
    	throw new IllegalStateException("can't find choice " + name);
    }
    if (choice.getName().equals(name)) return choice.getSubtree();
    return findChoice(name, (Choice) choice.getSibling());
  }

  @Override
  public Object get(String name) {
	return properties.get(name);
  }

  @Override
  public Object put(String name, Object value) {
	return properties.put(name, value);
  }

  @Override
  public boolean contains(String name) {
    return properties.containsKey(name);
  }

  @Override
  public String toString() {
	  return properties.toString();
  }
}
