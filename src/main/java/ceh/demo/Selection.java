package ceh.demo;

public interface Selection extends Input {

  int size();

  Choice getChoices();
  
  void enable(String... names);
  
  void disable(String... names);
  
}
