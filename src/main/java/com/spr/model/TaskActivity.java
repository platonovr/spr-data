package com.spr.model;

import javax.persistence.*;

/**
 * Created by Roman on 16.03.14.
 */
@Entity
@Table(name = "taskactivity")
public class TaskActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "taskactivity_id_seq")
    @SequenceGenerator(name = "taskactivity_id_seq", sequenceName = "taskactivity_id_seq", allocationSize = 1)
    private Long id;

    private String time;

    private String comment;


    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private Task task;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
