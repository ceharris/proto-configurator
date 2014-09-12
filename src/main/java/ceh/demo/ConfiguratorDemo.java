package ceh.demo;

public class ConfiguratorDemo {

  public static void main(String[] args) throws Exception {
    ClassLoader classLoader = ConfiguratorDemo.class.getClassLoader();
    ConsoleUI.start(classLoader.getResource("configuration.xml"), 
        classLoader.getResource("configuration.js"), System.in, System.out);
  }

}
