# manifold-sample-web-app
A sample web application using:
* [javalin](http://javalin.io/)
* [htmx](https://htmx.org/)
* [manifold-templates](https://github.com/manifold-systems/manifold/tree/master/manifold-deps-parent/manifold-templates)
* [manifold-json](https://github.com/manifold-systems/manifold/tree/master/manifold-deps-parent/manifold-json)

## Usage

### IntelliJ IDEA
Manifold is best experienced with [IntelliJ IDEA](https://www.jetbrains.com/idea/download/).
* Install the Manifold IntelliJ plugin directly from IntelliJ IDEA:

  <kbd>Settings</kbd> ➜ <kbd>Plugins</kbd> ➜ <kbd>Browse repositories</kbd> ➜ search: `Manifold`

* Close and relaunch IDEA
* Open this project: `manifold-sample-web-app`
* Be sure to setup an SDK for <b>Java 21</b>:
  <kbd>Project Structure</kbd> ➜ <kbd>SDKs</kbd> ➜ <kbd>+</kbd> ➜ <kbd>JDK</kbd>
* Or change the `pom.xml` file to use a JDK of your choosing, Manifold fully supports Java 8 - 21 + latest JDK release

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