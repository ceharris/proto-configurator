package ceh.demo.configurator;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

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
  public Configurator newConfigurator(URL rulesLocation) 
      throws ScriptException {
    ScriptEngineManager manager = new ScriptEngineManager();
    ScriptEngine engine = manager.getEngineByMimeType("text/javascript"); 
    
    SimpleConfigurator configurator = new SimpleConfigurator(
        makeSiblings(config.children), engine);
    
    loadRules(rulesLocation, configurator, engine);    
    return configurator;
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
    Input input = makeInput(configNode.input);
    if (input instanceof SelectionInput) {
      return new SelectionPropertyNode(configNode.name, sibling, 
          (SelectionInput) input);
    }
    return new PropertyNode(configNode.name, sibling,
        input);
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
    ChoiceNode choice = null;
    ChoiceNode lastChoice = null;
    int size = 0;
    for (int i = selection.choices.length - 1; i >= 0; i--) {
      choice = makeChoice(selection.choices[i], lastChoice);
      lastChoice = choice;
      size++;
    }
    return new SelectionInput(size, choice);
  }

  private ChoiceNode makeChoice(ConfigChoice choice, Choice sibling) {
    Node subtree = choice.subtree != null ? 
        makeSiblings(new ConfigNode[] { choice.subtree }) : null;
    return new ChoiceNode(choice.name, subtree, sibling);
  }

  private void loadRules(URL location, Configurator configurator, 
      ScriptEngine engine) throws ScriptException {
    engine.put("out", System.out);
    engine.put("ctx", configurator);
    engine.eval("function rule(r) { ctx.rule(r) }");
    try {
      InputStream inputStream = location.openStream();
      try {
        engine.eval(new InputStreamReader(inputStream));
      }
      finally {
        try {
          inputStream.close();
        }
        catch (IOException ex) {
          ex.printStackTrace(System.err);
        }
      }
    }
    catch (IOException ex) {
      throw new ScriptException(ex);
    }
  }

}
