package ceh.demo.configurator;

import ceh.demo.Choice;
import ceh.demo.Node;
import ceh.demo.Selection;

public class SelectionInput implements Selection {

  private final int size;
  private final ChoiceNode choices;

  /**
   * Constructs a new instance.
   * @param size
   * @param choices
   */
  public SelectionInput(int size, ChoiceNode choices) {
    this.size = size;
    this.choices = choices;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int size() {
    return size;
  }

  @Override
  public Choice getChoices() {
    return choices;
  }

  public ChoiceNode findChoice(String name) throws IllegalArgumentException {
    return findChoice(name, choices);
  }
  
  private ChoiceNode findChoice(String name, ChoiceNode choice) 
      throws IllegalArgumentException {
    if (choice == null) {
      throw new IllegalArgumentException("choice " + name + " not found");
    }
    if (choice.getName().equals(name)) return (ChoiceNode) choice;
    return findChoice(name, (ChoiceNode) choice.getSibling());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void enable(String... names) {
    setAllEnabled(false);
    for (String name : names) {
      findChoice(name).setEnabled(true);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void disable(String... names) {
    setAllEnabled(true);
    for (String name : names) {
      findChoice(name).setEnabled(false);
    }
  }

  private void setAllEnabled(boolean enabled) {
    Node choice = getChoices();
    while (choice != null) {
      choice.setEnabled(enabled);
      choice = choice.getSibling();
    }
  }
}
