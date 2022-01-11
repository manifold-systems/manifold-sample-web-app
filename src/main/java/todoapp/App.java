package todoapp;

import spark.Request;
import manifold.templates.rt.ManifoldTemplates;
import api.ToDo;
import api.ToDo.Status;
import todoapp.view.layouts.Main;
import todoapp.view.todo.Display;
import todoapp.view.todo.Edit;

import java.util.*;

import static spark.Spark.*;

/**
 * A sample web application.
 * <p>
 * A simple web application utilizing the following components:
 * <ul>
 *   <li><a href="https://github.com/manifold-systems/manifold/tree/master/manifold-deps-parent/manifold-templates">manifold-templates</a> for type-safe templates</li>
 *   <li><a href="https://github.com/manifold-systems/manifold/tree/master/manifold-deps-parent/manifold-json">manifold-json</a> for service definition</li>
 *   <li><a href="https://sparkjava.com/">Spark</a> for web framework</li>
 *   <li><a href="https://htmx.org/">htmx</a> for web UI</li>
 * </ul>
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

    if ("true".equals(req.queryParams("hx-request"))) {
      return Display.withoutLayout().render(todos, filter, activeCount, anyComplete, allComplete);
    } else {
      return Display.render(todos, filter, activeCount, anyComplete, allComplete);
    }
  }


}