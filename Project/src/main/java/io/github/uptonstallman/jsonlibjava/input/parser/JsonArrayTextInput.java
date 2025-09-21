package io.github.uptonstallman.jsonlibjava.input.parser;

/**
 * The type Json array text input.
 * Wrapper class for a json array literal.
 * To be used as argument for {@link io.github.uptonstallman.jsonlibjava.process.parser.JsonArrayParser}. It is passed as constructor parameter.
 *
 * @see io.github.uptonstallman.jsonlibjava.process.parser.JsonArrayParser#parse()
 */
public class JsonArrayTextInput {

  private String jsonText;

  /**
   * Instantiates a new Json array text input.
   *
   * @param jsonText the json text
   */
  public JsonArrayTextInput(String jsonText) {
    this.jsonText = jsonText;
  }

  /**
   * Gets json text.
   *
   * @return the json text
   */
  public String getJsonText() {
    return jsonText;
  }

  /**
   * Sets json text.
   *
   * @param jsonText the json text
   */
  public void setJsonText(String jsonText) {
    this.jsonText = jsonText;
  }

}
