package todoapp;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.staticfiles.Location;
import manifold.templates.rt.ManifoldTemplates;
import api.ToDo;
import api.ToDo.Status;
import todoapp.view.layouts.Main;
import todoapp.view.todo.Display;
import todoapp.view.todo.Edit;

import java.util.*;

/**
 * A sample web application.
 * <p>
 * A simple web application utilizing the following components:
 * <ul>
 *   <li><a href="https://github.com/manifold-systems/manifold/tree/master/manifold-deps-parent/manifold-templates">manifold-templates</a> for type-safe templates</li>
 *   <li><a href="https://github.com/manifold-systems/manifold/tree/master/manifold-deps-parent/manifold-json">manifold-json</a> for service definition</li>
 *   <li><a href="https://javalin.io/">Javalin</a> for web framework</li>
 *   <li><a href="https://htmx.org/">htmx</a> for web UI</li>
 * </ul>
 */
public class App {

    public static void main(String[] args) {

        var app = Javalin.create(config ->
                config.staticFiles.add("/public", Location.CLASSPATH));

        ManifoldTemplates.setDefaultLayout("todoapp", Main.asLayout());

        app.exception(Exception.class,
                (e, ctx) -> logException(e));

        // Render main UI
        app.get("/", ctx -> ctx.result(renderTodos(ctx)));

        // Add new
        app.post("/todos", ctx -> {
            ToDoService.add(ctx.req().getParameter("todo-title"));
            ctx.result(renderTodos(ctx));
        });

        // Remove all completed
        app.delete("/todos/completed", ctx -> {
            ToDoService.removeCompleted();
            ctx.result(renderTodos(ctx));
        });

        // Toggle all status
        app.put("/todos/toggle_status", ctx -> {
            ToDoService.toggleAll(ctx.req().getParameter("toggle-all") != null);
            ctx.result(renderTodos(ctx));
        });

        // Remove by id
        app.delete("/todos/{id}", ctx -> {
            ToDoService.remove(ctx.pathParam("id"));
            ctx.result(renderTodos(ctx));
        });

        // Update by id
        app.put("/todos/{id}", ctx -> {
            ToDoService.update(ctx.pathParam("id"), ctx.req().getParameter("todo-title"));
            ctx.result(renderTodos(ctx));
        });

        // Toggle status by id
        app.put("/todos/{id}/toggle_status", ctx -> {
            ToDoService.toggleStatus(ctx.pathParam("id"));
            ctx.result(renderTodos(ctx));
        });

        // Edit by id
        app.get("/todos/{id}/edit", ctx ->
                ctx.result(renderEditTodo(ctx)));

        app.start(4567);
    }

    private static void logException(Exception e) {
        e.printStackTrace(); // log this in real life
    }

    private static String renderEditTodo(Context ctx) {
        ctx.res().contentType = "html";
        return Edit.withoutLayout().render(ToDoService.find(ctx.pathParam("id")));
    }

    private static String renderTodos(Context ctx) {
        ctx.res().contentType = "html";
        String statusStr = ctx.req().getParameter("status");

        List<ToDo> todos = ToDoService.ofStatus(statusStr);
        String filter = statusStr == null ? "" : statusStr;
        int activeCount = ToDoService.ofStatus(Status.active).size();
        boolean anyComplete = !ToDoService.ofStatus(Status.complete).isEmpty();
        boolean allComplete = ToDoService.all().size() == ToDoService.ofStatus(Status.complete).size();

        if ("true".equals(ctx.req().getParameter("hx-request"))) {
            return Display.withoutLayout().render(todos, filter, activeCount, anyComplete, allComplete);
        } else {
            return Display.render(todos, filter, activeCount, anyComplete, allComplete);
        }
    }
}