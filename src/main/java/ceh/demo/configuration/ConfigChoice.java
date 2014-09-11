package ceh.demo.configuration;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;

public class ConfigChoice {

  @XmlAttribute
  public String name;
  
  @XmlElementRef
  public ConfigNode subtree;

  
}
