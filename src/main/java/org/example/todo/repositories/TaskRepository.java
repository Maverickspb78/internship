package org.example.todo.repositories;

import org.example.todo.entities.Task;
import org.example.todo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByUserEqualsAndDoneFalseAndDescriptionContains(User user, String searchString);
    List<Task> findAllByUserEqualsAndDescriptionContains(User user, String searchString);
    Optional<Task> findFirstByIdAndUserEquals(Long id, User user);
    void deleteByIdAndUserEquals(Long id, User user);

}
