# manifold-sample-web-app
A sample [Spark](http://sparkjava.com/)-based web application using:
* [Manifold: Templates (ManTL)](http://manifold.systems/manifold-templates.html)
* [Manifold: JSON Schema](http://manifold.systems/docs.html#json-and-json-schema)
* [Intercooler](https://intercoolerjs.org/)

## Usage

### IntelliJ IDEA
Manifold is best experienced in [IntelliJ IDEA](https://www.jetbrains.com/idea/download/).
* Install the Manifold IntelliJ plugin directly from IntelliJ IDEA:

  <kbd>Settings</kbd> ➜ <kbd>Plugins</kbd> ➜ <kbd>Browse repositories</kbd> ➜ search: `Manifold`

* Close and relaunch IDEA
* Open this project: `manifold-sample-web-app`
* Be sure to setup an SDK for <b>Java 11</b>:
  <kbd>Project Structure</kbd> ➜ <kbd>SDKs</kbd> ➜ <kbd>+</kbd> ➜ <kbd>JDK</kbd>
* Or change the `pom.xml` file to use a JDK of your choosing, Manifold fully supports Java 8 - 11

### Running and Using the App
* Run the `todoapp.App` class directly with Java
* _or_ load this project in IntelliJ and run the `todoapp.App` class
* Launch a browser and go to `http://localhost:4567`

### Developing ManTL Files & the JSON Schema API
The Manifold plugin for IntelliJ provides professional quality ManTL template authoring as well as JSON Schema editing
including code completion, usage searching, navigation, and refactoring.
* Open this project and begin developing `.mtl` & `.json` files directly with comprehensive IntelliJ feature support

### Hot Swap Usage
* Open this project in IntelliJ IDEA and debug the `todoapp.App` class
* Launch a browser and go to `http://localhost:4567` 
* Make changes to any ManTL file (*.mtl) and build your changes: <kbd>Build</kbd> ➜ <kbd>Build Project</kbd>
* Reload the page in your browser to see your changes 