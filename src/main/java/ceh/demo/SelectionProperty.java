package ceh.demo;

public interface SelectionProperty extends Property {

  void enable(String... names);
  
  void disable(String... names);
    
  Selection getSelection();
  
}
