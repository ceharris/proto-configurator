package ceh.demo.configuration;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "select")
public class ConfigSelectInput extends ConfigInput {

  @XmlElement(name = "choice")
  public ConfigChoice[] choices = new ConfigChoice[0];
  
}
