package ceh.demo;

import java.net.URL;

import javax.script.ScriptException;

public interface ConfiguratorFactory {

  Configurator newConfigurator(URL rulesLocation) throws ScriptException;

}
