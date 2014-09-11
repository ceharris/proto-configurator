package ceh.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;

import ceh.demo.configuration.JaxbConfigurationLoader;

public class ConsoleUI implements Runnable {

  private final BufferedReader in;
  private final PrintWriter out;
  private final Configurator configurator;

  /**
   * Constructs a new instance.
   * @param in
   * @param out
   * @param configurator
   */
  private ConsoleUI(BufferedReader in, PrintWriter out,
      Configurator configurator) {
    this.in = in;
    this.out = out;
    this.configurator = configurator;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void run() {
    try {
      Node node = getNextPrompt(configurator);
      while (node != null) {
        if (node instanceof Section) {
          handleSection((Section) node);
        }
        if (node instanceof Property) {
          handleProperty((Property) node, configurator);
          configurator.update();
        }
        node = getNextPrompt(configurator);
      }
    }
    catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

  private Node getNextPrompt(Configurator configurator) {
    return findNextNode(configurator.getRoot());
  }

  private Node findNextNode(Node node) {
    if (node == null) return null;
    if (node instanceof Section || 
        node.isEnabled() && !node.isSatisfied()) return node;
    if (node.getSubtree() != null) return findNextNode(node.getSubtree());
    return findNextNode(node.getSibling());
  }
  

  private void handleSection(Section node) {
    out.format("\nSection: %s\n", node.getName());
  }

  private void handleProperty(Property node, Configurator configurator) 
      throws IOException {
    Input input = node.getInput();
    if (input instanceof Selection) {
      handleSelectInput(node, configurator);
    }
    else if (input instanceof Text) {
      handleTextInput(node, configurator);
    }
    else {
      throw new IllegalStateException("unrecognized input type");
    }
  }

  private void handleSelectInput(Property node, Configurator configurator) 
      throws IOException {
    
    Selection selection = (Selection) node.getInput();
    int choice = -1;
    while (choice < 0 || choice >= selection.size()) {
      out.println();
      Choice c = selection.getChoices();
      for (int i = 0; i < selection.size(); i++) {
        out.format("%d) %s\n", i + i, c.getName());
        c = (Choice) c.getSibling();
      }
      out.format("Select [1-%d]:  ", selection.size());
      String value = in.readLine();
      if (value == null) {
        throw new RuntimeException("unexpected end of input");
      }
      try {
        choice = Integer.parseInt(value);
      }
      catch (NumberFormatException ex) {
        choice = -1;
      }
    }
  }

  private void handleTextInput(Property node, Configurator configurator) 
      throws IOException {
    
    String value = "";
    while (value.isEmpty()) {
      out.format("%s:  ", node.getName());
      out.flush();
      value = in.readLine();
      if (value == null) {
        throw new RuntimeException("unexpected end of input");
      }
      value = value.trim();
    }

    configurator.put(node.getName(), value.trim());
  }

  public static void start(URL config, InputStream in, OutputStream out)
      throws IOException, ConfigurationException {
    ConfigurationLoader loader = new JaxbConfigurationLoader();
    ConfiguratorFactory factory = loader.load(config);
    Configurator configurator = factory.newConfigurator();

    ConsoleUI ui =
        new ConsoleUI(new BufferedReader(new InputStreamReader(in)),
            new PrintWriter(new OutputStreamWriter(out)), configurator);

    ui.run();
  }

}
