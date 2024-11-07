package tech.ryuichi24.simple_spring_rest_api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Service;
import tech.ryuichi24.simple_spring_rest_api.errors.BadRequestException;
import tech.ryuichi24.simple_spring_rest_api.errors.NotFoundException;
import tech.ryuichi24.simple_spring_rest_api.models.TodoItem;

@Service
public class TodoServiceImpl implements TodoService {
  private final AtomicInteger _counter = new AtomicInteger();

  private final List<TodoItem> _todoItems = new ArrayList<>() {
    {
      add(new TodoItem(_counter.incrementAndGet(), "todo 1"));
      add(new TodoItem(_counter.incrementAndGet(), "todo 2"));
      add(new TodoItem(_counter.incrementAndGet(), "todo 3"));
    }
  };

  @Override
  public TodoItem saveTodoItem(TodoItem todoItem) {
    if (Objects.isNull(todoItem.getTitle())) {
      throw new BadRequestException("Title must not be null.");
    }
    todoItem.setId(_counter.incrementAndGet());
    _todoItems.add(todoItem);

    return todoItem;
  }

  @Override
  public List<TodoItem> getTodoItems() {
    return this._todoItems;
  }

  @Override
  public TodoItem getTodoItemById(int id) {
    return _getTodoItemById(id);
  }

  @Override
  public void removeTodoItemById(int id) {
    TodoItem found = _getTodoItemById(id);
    _todoItems.remove(found);
  }

  @Override
  public TodoItem updateTodoItem(int id, TodoItem todoItem) {
    TodoItem found = _getTodoItemById(id);
    _todoItems.remove(found);
    _todoItems.add(todoItem);
    return todoItem;
  }

  private TodoItem _getTodoItemById(int id) {
    Optional<TodoItem> found = _todoItems.stream().filter(item -> item.getId() == id).findAny();
    if (!found.isPresent()) {
      throw new NotFoundException("The todo item is not available");
    }
    return found.get();
  }
}
