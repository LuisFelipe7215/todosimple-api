package com.luisfelipe.todosimple.services;

import com.luisfelipe.todosimple.models.Task;
import com.luisfelipe.todosimple.models.User;
import com.luisfelipe.todosimple.repositories.TaskRepository;
import com.luisfelipe.todosimple.services.exceptions.DataBindingViolationException;
import com.luisfelipe.todosimple.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    public Task findById(Long id){
        Optional<Task> task = this.taskRepository.findById(id);
        return task.orElseThrow(() -> new ObjectNotFoundException("Task cannot be found! id: " + id + ", Type: " + Task.class.getName()));
    }

    public List<Task> findAllByUserId(Long userId){
        return this.taskRepository.findByUser_Id(userId);
    }

    @Transactional
    public Task create(Task task){
        User user = this.userService.findById(task.getUser().getId());
        task.setId(null);
        task.setUser(user);
        task = this.taskRepository.save(task);
        return task;
    }

    @Transactional
    public Task update(Task task){
        Task newTask = findById(task.getId());
        newTask.setDescription(task.getDescription());
        return this.taskRepository.save(newTask);
    }

    public void delete(Long id){
        findById(id);
        try {
            this.taskRepository.deleteById(id);
        } catch (Exception e){
            throw new DataBindingViolationException("It's not possible to delete when there are related entities!");
        }
    }
}
