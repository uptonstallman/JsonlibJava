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
    <version>3.0.0</version>
</dependency>
```
### Gradle

implementation 'io.uptonstallman:jsonlibjava:3.0.0'

## Usage

### API Reference

#### Json Objects

##### `JsonObjectParser.parse(JsonObjectTextInput jsonObjectTextInput)`
- **Parameters:**
  `JsonObjectTextInput` – JSON string to deserialize.
- **Returns:**  
  `JsonObjectMapOutput` – Map of JSON elements.

##### `JsonObjectSerializer.serialize(JsonObjectMapInput jsonObjectMapInput)`
- **Parameters:**  
  `jsonObjectMapInput` – Map of JSON elements to serialize.
- **Returns:**  
  `JsonObjectTextOutput` – JSON string.

#### Json Arrays

##### `JsonArrayParser.parse(JsonArrayTextInput jsonArrayTextInput)`
- **Parameters:**
  `JsonArrayTextInput` – JSON string to deserialize.
- **Returns:**  
  `JsonArrayListOutput` – List of JSON elements.

##### `JsonArraySerializer.serialize(JsonArrayListInput jsonArrayListInput)`
- **Parameters:**  
  `jsonArrayListInput` – List of JSON elements to serialize.
- **Returns:**  
  `JsonArrayTextOutput` – JSON string.

### Javadoc
[https://uptonstallman.github.io/JsonlibJava/javadoc/3.0.0/](https://uptonstallman.github.io/JsonlibJava/javadoc/3.0.0/)

### Debug
System property: -DjsonlibLogLevel=DEBUG

## Json specification
[RFC8259](https://datatracker.ietf.org/doc/html/rfc8259)

## License

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