package com.spr.service;

import com.spr.model.Task;

import java.util.List;

/**
 * Created by Roman on 11.04.2014.
 */
public interface TaskService {
    public Task create(Task task);

    public Task delete(Long id);

    public Iterable<Task> findAll();

    public Task update(Task task);

    public Task findById(Long id);

    public List<Task> getVisibleTasks();

    public List<String> getTasksName();

    public Task findByName(String name);

}
