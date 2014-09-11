package ceh.demo.configuration;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "section")
public class ConfigSectionNode extends ConfigNode {

  @XmlElementRef
  public ConfigNode[] children = new ConfigNode[0];
  
}
