package ceh.demo.configurator;

import ceh.demo.Choice;
import ceh.demo.Configurator;
import ceh.demo.ConfiguratorFactory;
import ceh.demo.Input;
import ceh.demo.Node;
import ceh.demo.configuration.ConfigChoice;
import ceh.demo.configuration.ConfigInput;
import ceh.demo.configuration.ConfigNode;
import ceh.demo.configuration.ConfigPropertyNode;
import ceh.demo.configuration.ConfigSectionNode;
import ceh.demo.configuration.ConfigSelectInput;
import ceh.demo.configuration.ConfigTextInput;
import ceh.demo.configuration.Configuration;

public class SimpleConfiguratorFactory implements ConfiguratorFactory {

  private final Configuration config;

  /**
   * Constructs a new instance.
   * @param config
   */
  public SimpleConfiguratorFactory(Configuration config) {
    this.config = config;
  }

  @Override
  public Configurator newConfigurator() {
    return new SimpleConfigurator(makeSiblings(config.children));
  }

  private Node makeSiblings(ConfigNode[] siblings) {
    Node node = null;
    Node lastNode = null;
    for (int i = siblings.length - 1; i >= 0; i--) {
      ConfigNode configNode = siblings[i];
      if (configNode instanceof ConfigSectionNode) {
        node = makeSection(configNode, lastNode);
      }
      else if (configNode instanceof ConfigPropertyNode) {
        node = makeProperty((ConfigPropertyNode) configNode, lastNode);
      }
      else {
        throw new IllegalArgumentException("unrecognized node type");
      }
      lastNode = node;
    }
    
    return node;
  }

  private Node makeSection(ConfigNode configNode, Node sibling) {
    Node subtree = makeSiblings(((ConfigSectionNode) configNode).children);
    return new SectionNode(configNode.name, subtree, sibling);
  }

  private Node makeProperty(ConfigPropertyNode configNode, Node sibling) {
    return new PropertyNode(configNode.name, sibling, 
        makeInput(configNode.input));
  }

  private Input makeInput(ConfigInput input) {
    if (input instanceof ConfigSelectInput) {
      return makeSelection((ConfigSelectInput) input);
    }
    else if (input instanceof ConfigTextInput) {
      return new TextInput();
    }
    else {
      throw new IllegalArgumentException("unrecognized input type");
    }

  }

  private Input makeSelection(ConfigSelectInput selection) {
    Choice choice = null;
    Choice lastChoice = null;
    int size = 0;
    for (int i = selection.choices.length - 1; i >= 0; i--) {
      choice = makeChoice(selection.choices[i], lastChoice); 
      lastChoice = choice;
      size++;
    }
    return new SelectionInput(size, choice);
  }

  private Choice makeChoice(ConfigChoice choice, Choice sibling) {
    Node subtree = choice.subtree != null ? makeSiblings(new ConfigNode[] { choice.subtree }) : null;
	return new ChoiceNode(choice.name, subtree, sibling);
  }
  
}
