# JSON Parser & Serializer (Java)

A lightweight and easy-to-use JSON serializer and deserializer library for Java.

## Features
 
- Serialize Java objects to JSON strings
- Deserialize JSON strings back to Java objects
- Supports nested objects, collections, and primitives
- Simple and intuitive API
- Minimal dependencies and fast performance

## Installation

Add the library to your project by including the JAR or using Maven/Gradle:

### Maven

```xml
<dependency>
    <groupId>io.github.uptonstallman</groupId>
    <artifactId>jsonlibjava</artifactId>
    <version>2.5.3</version>
</dependency>
```
### Gradle

implementation 'io.uptonstallman:jsonlibjava:2.5.3'

### Usage

### API Reference

#### `JsonObjectParser.parse()`
- **Parameters:**
  `JsonObjectTextInput` – JSON string to deserialize. Passed on constructor.
- **Returns:**  
  `JsonObjectMapOutput` – Map of JSON elements.

#### `JsonObjectSerializer.serialize(JsonObjectMapInput jsonObjectMapInput)`
- **Parameters:**  
  `jsonObjectMapInput` – Map of JSON elements to serialize.
- **Returns:**  
  `JsonObjectTextOutput` – JSON string.

#### `JsonArrayParser.parse()`
- **Parameters:**
  `JsonArrayTextInput` – JSON string to deserialize. Passed on constructor.
- **Returns:**  
  `JsonArrayListOutput` – List of JSON elements.

#### `JsonArraySerializer.serialize(JsonArrayListInput jsonArrayListInput)`
- **Parameters:**  
  `jsonArrayListInput` – List of JSON elements to serialize.
- **Returns:**  
  `JsonArrayTextOutput` – JSON string.

### Json specification
[RFC8259](https://datatracker.ietf.org/doc/html/rfc8259)

### Debug
System property: -DjsonlibLogLevel=DEBUG

### License

    Copyright (C) 2025  Upton Stallman

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.