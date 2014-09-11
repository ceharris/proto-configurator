package ceh.demo.configuration;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "configuration")
public class Configuration {

  @XmlElementRef
  public ConfigNode[] children;
  
}
