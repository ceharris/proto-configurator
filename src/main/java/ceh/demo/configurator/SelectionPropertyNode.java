package ceh.demo.configurator;

import ceh.demo.Node;
import ceh.demo.SelectionProperty;


public class SelectionPropertyNode extends PropertyNode 
    implements SelectionProperty {

  public SelectionPropertyNode(String name, Node sibling, SelectionInput input) {
    super(name, sibling, input);
  }

  @Override
  public Object setValue(Object value) {
    Object result = super.setValue(value);
    if (value != null) {
      ChoiceNode choice = getSelection().findChoice((String) value);
      setSubtree(choice.getSubtree());
    }
    else {
      setSubtree(null);
    }
    return result;
  }

  @Override
  public void enable(String... names) {
    getSelection().enable(names);
  }

  @Override
  public void disable(String... names) {
    getSelection().disable(names);
  }

  @Override
  public SelectionInput getSelection() {
    return (SelectionInput) getInput();
  }

}
