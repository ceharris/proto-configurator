package ceh.demo.configuration;

import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import ceh.demo.ConfigurationException;
import ceh.demo.ConfigurationLoader;
import ceh.demo.ConfiguratorFactory;
import ceh.demo.configurator.SimpleConfiguratorFactory;

public class JaxbConfigurationLoader implements ConfigurationLoader {

  @Override
  public ConfiguratorFactory load(URL location)
      throws ConfigurationException {
    try {
      JAXBContext context = JAXBContext.newInstance(
          Configuration.class, ConfigPropertyNode.class, ConfigSectionNode.class, 
          ConfigSelectInput.class, ConfigTextInput.class);
      Unmarshaller unmarshaller = context.createUnmarshaller();
      Configuration config = (Configuration) unmarshaller.unmarshal(location);
      return new SimpleConfiguratorFactory(config);
    }
    catch (JAXBException ex) {
      throw new ConfigurationException(ex);
    }
  }
  
}
