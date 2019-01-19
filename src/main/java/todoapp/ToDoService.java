package todoapp;

import java.util.*;
import api.ToDo;
import api.ToDo.Status;

import static api.ToDo.Status.*;

/**
 * CRUD service for the {@code ToDo} JSON Schema.
 * <p>
 * Notice how the {@code ToDo} Java type not explicitly provided or generated -- this is the essence of Manifold --
 * the Java compiler is JSON Schema aware with Manifold, no need for a code generator build step, incremental
 * changes to your JSON schemas materialize instantly in your project.  Use IntelliJ to refactor and find usages
 * to/from your JSON schema files.
 * <p>
 * We will use the same JSON schema to build a REST API in another sample application.
 */
public class ToDoService {
  private static final List<ToDo> DATA = new ArrayList<>();

  public static void add(String title) {
    ToDo todo = ToDo.create(UUID.randomUUID().toString(), title);
    DATA.add(todo);
  }

  public static ToDo find(String id) {
    return DATA.first(t -> t.getId().equals(id));
  }

  public static void update(String id, String title) {
    find(id).setTitle(title);
  }

  public static List<ToDo> ofStatus(String statusString) {
    return statusString.isNullOrBlank()
           ? DATA
           : ofStatus(Status.valueOf(Status.class, statusString.toLowerCase()));
  }

  public static List<ToDo> ofStatus(Status status) {
    return DATA.filterToList(t -> t.getStatus() == status);
  }

  public static void remove(String id) {
    DATA.remove(find(id));
  }

  public static void removeCompleted() {
    ofStatus(complete).forEach(t -> remove(t.getId()));
  }

  public static void toggleStatus(String id) {
    ToDo todo = find(id);
    todo.setStatus(todo.getStatus() == active ? complete: active);
  }

  public static void toggleAll(boolean isComplete) {
    all().forEach(t -> t.setStatus(isComplete ? complete : active));
  }

  public static List<ToDo> all() {
    return DATA;
  }
}
