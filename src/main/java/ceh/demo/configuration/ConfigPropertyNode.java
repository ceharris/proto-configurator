package ceh.demo.configuration;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "property")
public class ConfigPropertyNode extends ConfigNode {

  @XmlElementRef
  public ConfigInput input;
  
}
