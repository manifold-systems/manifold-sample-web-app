# manifold-sample-web-app
A sample [Spark](http://sparkjava.com/)-based web application using Manifold Templates ([ManTL](http://manifold.systems/manifold-templates.html)) 
and [Intercooler](https://intercoolerjs.org/)

### Usage

#### Running and Using the App
* Run the `todoapp.App` class directly with Java
* _or_ load this project in [IntelliJ IDEA](https://www.jetbrains.com/idea/download/) and run the `todoapp.App` class
* Launch a browser and go to `http://localhost:4567`

#### Developing ManTL Files & the JSON Schema API
The Manifold plugin for IntelliJ provides professional quality ManTL template authoring as well as JSON Schema editing
including code completion, usage searching, navigation, and refactoring.

* Install the Manifold IntelliJ plugin directly from IntelliJ IDEA:
<kbd>Settings</kbd> ➜ <kbd>Plugins</kbd> ➜ <kbd>Browse repositories</kbd> ➜ search: <kbd>Manifold</kbd>
* Close and relaunch IDEA
* Open this project and begin editing *.mtl files directly

#### Hot Swap Usage
* Open this project in IntelliJ IDEA and debug the `todoapp.App` class
* Launch a browser and go to `http://localhost:4567` 
* Make changes to any ManTL file (*.mtl) and build your changes: <kbd>Build</kbd> ➜ <kbd>Build Project</kbd>
* Reload the page in your browser to see your changes 