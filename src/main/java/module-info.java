module manifold.sample.web.app {
    // Use the JSON manifold for type-safe access to JSON schema and REST API
    requires manifold.json;
    // Use the Templates manifold for type-safe access to Java-based templates
    requires manifold.templates;
    // Use the Collections extension library
    requires manifold.collections;
    // Use the Text extension library
    requires manifold.text;

    // Include transitive dependencies manually since manifold jars are "automatic" modules
    // (they don't define manifold-info.java files, thus no 'requires' to their dependencies)
    requires manifold;
    requires manifold.ext;
    requires spark.core;
    requires java.scripting;
    requires jdk.unsupported;
}