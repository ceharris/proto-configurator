package ceh.demo;

public class ConfigurationException extends Exception {

  private static final long serialVersionUID = 7101958594724129691L;

  /**
   * Constructs a new instance.
   */
  public ConfigurationException() {
  }

  /**
   * Constructs a new instance.
   * @param message
   * @param cause
   */
  public ConfigurationException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructs a new instance.
   * @param message
   */
  public ConfigurationException(String message) {
    super(message);
  }

  /**
   * Constructs a new instance.
   * @param cause
   */
  public ConfigurationException(Throwable cause) {
    super(cause);
  }

}
