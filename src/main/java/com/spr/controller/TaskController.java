package com.spr.controller;

import com.spr.model.Task;
import com.spr.model.TaskForm;
import com.spr.service.TaskService;
import com.spr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Roman on 17.03.14.
 */
@Controller
@SessionAttributes("userLogin")
@RequestMapping("/task")

public class TaskController {


    private final TaskService taskService;

    private final UserService userService;

    @Autowired
    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }


    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public ModelAndView listOfTasks() {
        ModelAndView modelAndView = new ModelAndView("list-of-tasks");
        List<Task> tasks = taskService.getVisibleTasks();
        modelAndView.addObject("tasks", tasks);
        return modelAndView;
    }


    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView getTaskForm() {
        ModelAndView modelAndView = new ModelAndView("task-new", "taskForm", new TaskForm());
        modelAndView.addObject("userList", userService.getUserLogins());

        return modelAndView;
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView createTask(@ModelAttribute TaskForm taskForm, BindingResult result, final RedirectAttributes redirectAttributes, @RequestParam String user, @ModelAttribute("userLogin") String userLogin) {
        ModelAndView modelAndView;

        if (userLogin.equals("") || userLogin.equals(null)) {
            modelAndView = new ModelAndView("403");

            return modelAndView;
        } else {
            if (result.hasErrors()) {
                String message1 = "Nope";
                redirectAttributes.addAttribute("message1", message1);
                modelAndView = new ModelAndView("task-new");
                modelAndView.addObject("userList", userService.getUserLogins());
                return modelAndView;


            } else {
                String message = "task successfuly created";

                Task task = new Task();
                task.setId(taskForm.getId());
                task.setText(taskForm.getText());
                task.setName(taskForm.getName());
                task.setUser(userService.getUserByLogin(taskForm.getUser()));
                task.setVisibility(taskForm.getVisibility());
                task.setStatus(taskForm.getStatus());
                task.setDate(taskForm.getDate());
                taskService.create(task);
                modelAndView = new ModelAndView();
                modelAndView.setViewName("redirect:/index.html");
                redirectAttributes.addFlashAttribute("message", message);
                return modelAndView;

            }
        }
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editTaskPage(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("task-edit");
        Task task = taskService.findById(id);

        TaskForm taskForm = new TaskForm();
        taskForm.setId(task.getId());
        taskForm.setName(task.getName());
        taskForm.setStatus(task.getStatus());
        taskForm.setDate(task.getDate());
        taskForm.setVisibility(task.getVisibility());
        taskForm.setUser(task.getUser().getFirstname());
        taskForm.setText(task.getText());
        mav.addObject("taskForm", taskForm);
        mav.addObject("userList", userService.getUserLogins());
        return mav;
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public ModelAndView editTask(@ModelAttribute @Valid TaskForm taskForm,
                                 BindingResult result,
                                 @PathVariable Long id,
                                 final RedirectAttributes redirectAttributes) {

        if (result.hasErrors())
            return new ModelAndView("task-edit");

        ModelAndView mav = new ModelAndView("redirect:/index.html");
        String message = "Task was successfully updated.";
        mav.addObject("userList", userService.getUserLogins());
        Task task = taskService.findById(taskForm.getId());
        task.setId(taskForm.getId());
        task.setName(taskForm.getName());
        task.setVisibility(taskForm.getVisibility());
        task.setUser(userService.getUserByLogin(taskForm.getUser()));
        task.setText(taskForm.getText());
        task.setDate(taskForm.getDate());
        taskService.update(task);

        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }


    @RequestMapping("/{taskId}")
    public ModelAndView showUser(@PathVariable("taskId") Long taskID) {
        ModelAndView mav = new ModelAndView("taskDetails");
        mav.addObject(this.taskService.findById(taskID));
        mav.addObject(this.taskService.findById(taskID).getTaskActivities());
        return mav;
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteTask(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("task-delete");
        modelAndView.addObject("task", taskService.findById(id));
        return modelAndView;
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public ModelAndView deleteTask(@PathVariable Long id,
                                   final RedirectAttributes redirectAttributes, @ModelAttribute("userLogin") String userLogin) {
        String message = "";
        ModelAndView mav = new ModelAndView("redirect:/index.html");
        if ((userLogin != null) || (userLogin.equals(""))) {
            Task task = taskService.delete(id);
            message = "The task " + task.getText() + " was successfully deleted.";
        }
        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

}
