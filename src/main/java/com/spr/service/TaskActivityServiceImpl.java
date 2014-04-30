package com.spr.service;


import com.spr.model.TaskActivity;
import com.spr.repository.TaskActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Roman on 23.04.2014.
 */
@Service
public class TaskActivityServiceImpl implements TaskActivityService {
    @Autowired
    private TaskActivityRepository taskActivityRepository;

    @Override
    @Transactional
    public TaskActivity create(TaskActivity taskActivity) {
        TaskActivity createdTask = taskActivity;
        return taskActivityRepository.save(createdTask);
    }

    @Override
    @Transactional
    public TaskActivity delete(Long id) {
        TaskActivity deletedTaskActivity = taskActivityRepository.findOne(id);
        taskActivityRepository.delete(id);
        return deletedTaskActivity;
    }

    @Override
    public Iterable<TaskActivity> findAll() {
        return taskActivityRepository.findAll();
    }

    @Override
    @Transactional
    public TaskActivity update(TaskActivity taskActivity) {
        TaskActivity updatedTaskActivity = taskActivityRepository.findOne(taskActivity.getId());
        updatedTaskActivity.setId(taskActivity.getId());
        updatedTaskActivity.setComment(taskActivity.getComment());
        updatedTaskActivity.setTime(taskActivity.getTime());
        updatedTaskActivity.setTask(taskActivity.getTask());
        return updatedTaskActivity;
    }

    @Override
    public TaskActivity findById(Long id) {
        return taskActivityRepository.findOne(id);
    }
}
