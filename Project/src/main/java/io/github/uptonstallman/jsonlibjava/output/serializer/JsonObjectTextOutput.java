package io.github.uptonstallman.jsonlibjava.output.serializer;

/**
 * The type Json object text output.
 * Wrapper class for serialized json.
 * Output of {@link io.github.uptonstallman.jsonlibjava.process.serializer.JsonObjectSerializer}
 *
 * @see io.github.uptonstallman.jsonlibjava.process.serializer.JsonObjectSerializer#serialize(io.github.uptonstallman.jsonlibjava.input.serializer.JsonOjectMapInput)
 */
public class JsonObjectTextOutput {

  private String jsonText;

  /**
   * Instantiates a new Json object text output.
   *
   * @param jsonText the json text
   */
  public JsonObjectTextOutput(String jsonText) {
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
