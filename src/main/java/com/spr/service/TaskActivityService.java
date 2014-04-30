package com.spr.service;

import com.spr.model.TaskActivity;

/**
 * Created by Roman on 22.04.2014.
 */
public interface TaskActivityService {
    public TaskActivity create(TaskActivity taskActivity);

    public TaskActivity delete(Long id);

    public Iterable<TaskActivity> findAll();

    public TaskActivity update(TaskActivity taskActivity);

    public TaskActivity findById(Long id);


}
