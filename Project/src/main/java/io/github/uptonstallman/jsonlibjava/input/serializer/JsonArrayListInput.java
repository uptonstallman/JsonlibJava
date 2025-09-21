package io.github.uptonstallman.jsonlibjava.input.serializer;

import java.util.List;

/**
 * The type Json array list input.
 * Wrapper class for a list of json array elements - text serialized.
 * To be used as argument for {@link io.github.uptonstallman.jsonlibjava.process.serializer.JsonArraySerializer}
 *
 * @see io.github.uptonstallman.jsonlibjava.process.serializer.JsonArraySerializer#serialize(JsonArrayListInput)
 */
public class JsonArrayListInput {

  private List<String> jsonItems;

  /**
   * Instantiates a new Json array list input.
   *
   * @param jsonItems the json items
   */
  public JsonArrayListInput(List<String> jsonItems) {
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

  /**
   * Sets json items.
   *
   * @param jsonItems the json items
   */
  public void setJsonItems(List<String> jsonItems) {
    this.jsonItems = jsonItems;
  }
}
