package ceh.demo;

import java.net.URL;

import ceh.demo.configuration.JaxbConfigurationLoader;

public class ScriptingDemo {

  public static void main(String[] args) throws Exception {
    URL config = ConfiguratorDemo.class.getClassLoader().getResource(
        "configuration.xml");
    URL rules = ConfiguratorDemo.class.getClassLoader().getResource(
        "configuration.js");
    ConfigurationLoader loader = new JaxbConfigurationLoader();
    ConfiguratorFactory factory = loader.load(config);
    Configurator configurator = factory.newConfigurator(rules);
    System.out.println(configurator);
  }
  
}
