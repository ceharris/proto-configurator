package ceh.demo.configurator;

import ceh.demo.Choice;
import ceh.demo.Selection;

public class SelectionInput implements Selection {

  private final int size;
  private final Choice choices;

  /**
   * Constructs a new instance.
   * @param size
   * @param choices
   */
  public SelectionInput(int size, Choice choices) {
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

}
