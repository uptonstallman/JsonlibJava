package io.github.uptonstallman.jsonlibjava.output.parser;

import java.util.List;

/**
 * The type Json array list output.
 * Wrapper class for a list of json array elements - text serialized.
 * Output of {@link io.github.uptonstallman.jsonlibjava.process.parser.JsonArrayParser}
 *
 * @see io.github.uptonstallman.jsonlibjava.process.parser.JsonArrayParser#parse()
 */
public class JsonArrayListOutput {

  private List<String> jsonItems;

  /**
   * Instantiates a new Json array list output.
   *
   * @param jsonItems the json items
   */
  public JsonArrayListOutput(List<String> jsonItems) {
    this.jsonItems = jsonItems;
  }

  /**
   * Gets json items.
   *
   * @return the json items
   */
  public List<String> getJsonItems() {
    return jsonItems;
  }
}
