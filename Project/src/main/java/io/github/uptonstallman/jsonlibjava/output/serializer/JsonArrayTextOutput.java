package io.github.uptonstallman.jsonlibjava.output.serializer;

/**
 * The type Json array text output.
 * Wrapper class for serialized json.
 * Output of {@link io.github.uptonstallman.jsonlibjava.process.serializer.JsonArraySerializer}
 *
 * @see io.github.uptonstallman.jsonlibjava.process.serializer.JsonArraySerializer#serialize(io.github.uptonstallman.jsonlibjava.input.serializer.JsonArrayListInput) ()
 */
public class JsonArrayTextOutput {

  private String jsonText;

  /**
   * Instantiates a new Json array text output.
   *
   * @param jsonText the json text
   */
  public JsonArrayTextOutput(String jsonText) {
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
