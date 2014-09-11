package ceh.demo;

import java.net.URL;

public interface ConfigurationLoader {

  ConfiguratorFactory load(URL location) throws ConfigurationException;
  
}
