package tech.ryuichi24.simple_spring_rest_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.ryuichi24.simple_spring_rest_api.models.TodoItem;

@Repository
public interface TodoRepository extends JpaRepository<TodoItem, Integer> {

}
