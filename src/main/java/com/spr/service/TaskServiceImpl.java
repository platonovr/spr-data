package com.spr.service;

import com.spr.model.Task;
import com.spr.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Roman on 11.04.2014.
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    @Transactional
    public Task create(Task task) {
        Task createdTask = task;
        return taskRepository.save(createdTask);
    }

    @Override
    @Transactional
    public Task delete(Long id) {
        Task deletedTask = taskRepository.findOne(id);
        taskRepository.delete(id);
        return deletedTask;
    }

    @Override
    @Transactional
    public Iterable<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    @Transactional
    public Task update(Task task) {
        Task updatedTask = taskRepository.findOne(task.getId());
        updatedTask.setId(task.getId());
        updatedTask.setDate(task.getDate());
        updatedTask.setStatus(task.getStatus());
        updatedTask.setTaskActivities(task.getTaskActivities());
        updatedTask.setText(task.getText());
        updatedTask.setUser(task.getUser());
        updatedTask.setVisibility(task.getVisibility());
        return updatedTask;
    }

    @Override
    public Task findById(Long id) {
        return taskRepository.findOne(id);
    }

    @Override
    public List<Task> getVisibleTasks() {
        return taskRepository.getVisibleTasks();
    }

    @Override
    @Transactional
    public List<String> getTasksName() {
        return taskRepository.getTaskName();
    }

    @Override
    public Task findByName(String text) {
        return taskRepository.findByName(text);
    }
}
