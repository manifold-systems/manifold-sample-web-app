package todoapp;

import spark.Request;
import manifold.templates.ManifoldTemplates;
import api.ToDo;
import api.ToDo.Status;
import todoapp.view.layouts.Main;
import todoapp.view.todo.Display;
import todoapp.view.todo.Edit;

import java.util.*;

import static spark.Spark.*;

/**
 * A sample SparkJava-based Web application.
 * <p>
 * Demonstrates using ManTL (Manifold Templates) and Manifold JSON Schema to build a simple
 * web application.
 */
public class App {

  public static void main(String[] args) {

    exception(Exception.class, (e, req, res) -> e.printStackTrace()); // print all exceptions
    staticFiles.location("/public");
    port(4567);

    ManifoldTemplates.setDefaultLayout("todoapp", Main.asLayout());

    // Render main UI
    get("/", (req, res) -> renderTodos(req));

    // Add new
    post("/todos", (req, res) -> {
      ToDoService.add(req.queryParams("todo-title"));
      return renderTodos(req);
    });

    // Remove all completed
    delete("/todos/completed", (req, res) -> {
      ToDoService.removeCompleted();
      return renderTodos(req);
    });

    // Toggle all status
    put("/todos/toggle_status", (req, res) -> {
      ToDoService.toggleAll(req.queryParams("toggle-all") != null);
      return renderTodos(req);
    });

    // Remove by id
    delete("/todos/:id", (req, res) -> {
      ToDoService.remove(req.params("id"));
      return renderTodos(req);
    });

    // Update by id
    put("/todos/:id", (req, res) -> {
      ToDoService.update(req.params("id"), req.queryParams("todo-title"));
      return renderTodos(req);
    });

    // Toggle status by id
    put("/todos/:id/toggle_status", (req, res) -> {
      ToDoService.toggleStatus(req.params("id"));
      return renderTodos(req);
    });

    // Edit by id
    get("/todos/:id/edit", (req, res) -> renderEditTodo(req));
  }

  private static String renderEditTodo(Request req) {
    return Edit.withoutLayout().render(ToDoService.find(req.params("id")));
  }

  private static String renderTodos(Request req) {

    String statusStr = req.queryParams("status");

    List<ToDo> todos = ToDoService.ofStatus(statusStr);
    String filter = Optional.ofNullable(statusStr).orElse("");
    int activeCount = ToDoService.ofStatus(Status.active).size();
    boolean anyComplete = ToDoService.ofStatus(Status.complete).size() > 0;
    boolean allComplete = ToDoService.all().size() == ToDoService.ofStatus(Status.complete).size();

    if ("true".equals(req.queryParams("ic-request"))) {
      return Display.withoutLayout().render(todos, filter, activeCount, anyComplete, allComplete);
    } else {
      return Display.render(todos, filter, activeCount, anyComplete, allComplete);
    }
  }


}