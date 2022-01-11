// Note, this module-info.java file is optional. It's only useful if you want named modules.
// Otherwise, it can be deleted.
module manifold.sample.web.app {
    // Use the JSON manifold for type-safe access to JSON schema and REST API
    requires manifold.json.rt;
    // Use the Templates manifold for type-safe access to high performance, Java-based templates
    requires manifold.templates.rt;
    // Use properties manifold to eliminate getter/setter code
    requires manifold.props.rt;
    // Use the Collections extension library
    requires manifold.collections;
    // Use the Text extension library
    requires manifold.text;

    // Include transitive dependencies manually since manifold jars are "automatic" modules
    // (they don't define manifold-info.java files, thus no 'requires' to their dependencies)
    requires manifold.rt;
    requires manifold.ext.rt;
    requires spark.core;
    requires jdk.unsupported;
}