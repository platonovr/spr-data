package com.spr.repository;

import com.spr.model.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Roman on 10.04.2014.
 */
public interface TaskRepository extends CrudRepository<Task, Long> {

    @Transactional
    @Query("select t from Task t where t.visibility=true")
    List<Task> getVisibleTasks();


    @Transactional
    @Query("select t.name from Task t")
    List<String> getTaskName();

    @Transactional
    @Query("select t from Task t where t.name= ?1")
    Task findByName(String name);


}
