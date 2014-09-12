package ceh.demo.configurator;

import ceh.demo.Configurator;

public interface Rule {

  boolean when(Configurator ctx);
  
  void then(Configurator ctx);
  
}
