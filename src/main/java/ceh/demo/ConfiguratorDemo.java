package ceh.demo;


public class ConfiguratorDemo {

  public static void main(String[] args) throws Exception {
    ConsoleUI.start(ConfiguratorDemo.class.getClassLoader().
        getResource("configuration.xml"), System.in, System.out);
  }
  
}
