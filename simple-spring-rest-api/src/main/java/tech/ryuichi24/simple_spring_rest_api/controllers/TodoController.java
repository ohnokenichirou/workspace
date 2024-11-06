package tech.ryuichi24.simple_spring_rest_api.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.ryuichi24.simple_spring_rest_api.models.TodoItem;

@RestController
@RequestMapping(path = TodoController.BASE_URL)
public class TodoController {
  public static final String BASE_URL = "/api/v1/todos";
  private final AtomicInteger _counter = new AtomicInteger();

  private final List<TodoItem> _todoItems = new ArrayList<>() {
    {
      add(new TodoItem(_counter.incrementAndGet(), "todo 1"));
      add(new TodoItem(_counter.incrementAndGet(), "todo 2"));
      add(new TodoItem(_counter.incrementAndGet(), "todo 3"));
    }
  };

  // get todos
  @RequestMapping(method = RequestMethod.GET, path = "")
  public List<TodoItem> getTodoItems() {
    return _todoItems;
  }

  // get todo
  @RequestMapping(method = RequestMethod.GET, path = "/{id}")
  public TodoItem getTodoItem(@PathVariable int id) {
    TodoItem found = _getTodoItemById(id);
    if (found == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Notfound");
    }

    return found;
  }

  // create todo
  @RequestMapping(method = RequestMethod.POST, path = "")
  public ResponseEntity<TodoItem> createTodoItem(@RequestBody TodoItem todoItem) {
    todoItem.setId(_counter.incrementAndGet());
    _todoItems.add(todoItem);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(todoItem.getId()).toUri();
    return ResponseEntity.created(location).body(todoItem);
  }

  // update todo
  @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
  public ResponseEntity<?> updateTodoItem(@RequestBody TodoItem todoItem, @PathVariable int id) {
    TodoItem found = _getTodoItemById(id);
    if (found == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Notfound");
    }

    _todoItems.remove(found);
    _todoItems.add(todoItem);

    return ResponseEntity.noContent().build();
  }

  // delete todo
  @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
  public ResponseEntity<?> removeTodoItem(@PathVariable int id) {
    TodoItem found = _getTodoItemById(id);
    if (found == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Notfound");
    }
    _todoItems.remove(found);
  
    return ResponseEntity.noContent().build();
}

  private TodoItem _getTodoItemById(int id) {
    return _todoItems.stream().filter(item -> item.getId() == id).findAny().orElse(null);
  }

}
