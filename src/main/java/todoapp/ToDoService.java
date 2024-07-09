package todoapp;

import java.util.*;

// All the types below are automatically provided by manifold-json from the ToDo.json schema
import api.ToDo;
import api.ToDo.Status;

import static api.ToDo.Status.*;

/**
 * CRUD service for the {@link ToDo} JSON Schema.
 * <p>
 * Note, the manifold-json dependency automatically projects a Java type for the {@link ToDo} schema from within the
 * Java compiler. The manifold IntelliJ plugin provides seamless interaction with the {@link ToDo} schema as well.
 */
public class ToDoService {
  private static final List<ToDo> DATA = new ArrayList<>();

  public static void add(String title) {
    ToDo todo = ToDo.create(UUID.randomUUID().toString(), title);
    DATA.add(todo);
  }

  public static ToDo find(String id) {
    return DATA.first(t -> t.id.equals(id));
  }

  public static void update(String id, String title) {
    find(id).title = title;
  }

  public static List<ToDo> ofStatus(String statusString) {
    return statusString == null || statusString.isEmpty
      ? DATA
      : ofStatus(Status.valueOf(Status.class, statusString.toLowerCase()));
  }

  public static List<ToDo> ofStatus(Status status) {
    return DATA.filterToList(t -> t.status == status);
  }

  public static void remove(String id) {
    DATA.remove(find(id));
  }

  public static void removeCompleted() {
    ofStatus(complete).forEach(t -> remove(t.id));
  }

  public static void toggleStatus(String id) {
    ToDo todo = find(id);
    todo.status = todo.status == active ? complete : active;
  }

  public static void toggleAll(boolean isComplete) {
    all().forEach(t -> t.status = isComplete ? complete : active );
  }

  public static List<ToDo> all() {
    return DATA;
  }
}
