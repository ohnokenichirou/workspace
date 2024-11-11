package tech.ryuichi24.simple_spring_rest_api.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import tech.ryuichi24.simple_spring_rest_api.errors.NotFoundException;
import tech.ryuichi24.simple_spring_rest_api.models.TodoItem;
import tech.ryuichi24.simple_spring_rest_api.repositories.TodoRepository;

@Service
@Primary
public class TodoServiceImpl implements TodoService {

  @Autowired
  private TodoRepository _todoRepository;

  @Override
  public TodoItem getTodoItemById(int id) {
    return _findTodoItemById(id);
  }

  @Override
  public List<TodoItem> getTodoItems() {
    return _todoRepository.findAll();
  }

  @Override
  public void removeTodoItemById(int id) {
    _todoRepository.deleteById(id);
  }

  @Override
  public TodoItem saveTodoItem(TodoItem todoItem) {
    return _todoRepository.save(todoItem);
  }

  @Override
  public TodoItem updateTodoItem(int id, TodoItem todoItem) {
    return _todoRepository.save(todoItem);
  }

  private TodoItem _findTodoItemById(int id) throws NotFoundException {
    Optional<TodoItem> found = _todoRepository.findById(id);
    if (!found.isPresent()) {
      throw new NotFoundException("The todo item is not available.");
    }
    return found.get();
  }

}
