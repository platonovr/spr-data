package com.spr.controller;

import com.spr.model.ActivityForm;
import com.spr.model.TaskActivity;
import com.spr.service.TaskActivityService;
import com.spr.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Roman on 22.04.2014.
 */
@Controller
@SessionAttributes("userLogin")
@RequestMapping("/activity")
public class ActivityController {

    private final TaskActivityService taskActivityService;

    private final TaskService taskService;

    @Autowired
    public ActivityController(TaskActivityService taskActivityService, TaskService taskService) {
        this.taskActivityService = taskActivityService;
        this.taskService = taskService;
    }

    @RequestMapping(value = "/activities", method = RequestMethod.GET)
    public ModelAndView listOfActivities() {
        ModelAndView modelAndView = new ModelAndView("activity-list");
        List<TaskActivity> taskActivities = (List<TaskActivity>) taskActivityService.findAll();
        modelAndView.addObject("activities", taskActivities);
        return modelAndView;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView getActivityForm() {
        ModelAndView modelAndView = new ModelAndView("activity-new", "activityForm", new ActivityForm());
        modelAndView.addObject("taskList", taskService.getTasksName());
        return modelAndView;
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView createTaskActivity(@ModelAttribute ActivityForm activityForm, BindingResult result, final RedirectAttributes redirectAttributes, @ModelAttribute("userLogin") String userLogin) {
        ModelAndView modelAndView;

        if (userLogin.equals("") || userLogin.equals(null)) {
            modelAndView = new ModelAndView("403");

            return modelAndView;
        } else {
            if (result.hasErrors()) {
                String message1 = "Nope";
                redirectAttributes.addAttribute("message1", message1);
                modelAndView = new ModelAndView("activity-new");
                modelAndView.addObject("taskList", taskService.getTasksName());
                return modelAndView;


            } else {
                String message = "activity successfuly created";
                TaskActivity taskActivity = new TaskActivity();
                taskActivity.setId(activityForm.getId());
                taskActivity.setComment(activityForm.getComment());
                taskActivity.setTime(activityForm.getTime());
                taskActivity.setTask(taskService.findByName(activityForm.getTask()));
                taskActivityService.create(taskActivity);
                modelAndView = new ModelAndView();
                modelAndView.setViewName("redirect:/index.html");
                redirectAttributes.addFlashAttribute("message", message);
                return modelAndView;

            }
        }
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editTaskActivityPage(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("activity-edit");
        TaskActivity taskActivity = taskActivityService.findById(id);

        ActivityForm activityForm = new ActivityForm();
        activityForm.setId(taskActivity.getId());
        activityForm.setTime(taskActivity.getTime());
        activityForm.setComment(taskActivity.getComment());
        activityForm.setTask(taskActivity.getTask().getName());

        mav.addObject("activityForm", activityForm);
        mav.addObject("taskList", taskService.getTasksName());
        return mav;
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public ModelAndView editTaskActivity(@ModelAttribute @Valid ActivityForm activityForm,
                                         BindingResult result,
                                         @PathVariable Long id,
                                         final RedirectAttributes redirectAttributes) {

        if (result.hasErrors())
            return new ModelAndView("activity-edit");

        ModelAndView mav = new ModelAndView("redirect:/index.html");
        String message = "Task was successfully updated.";
        mav.addObject("taskList", taskService.getTasksName());
        TaskActivity taskActivity = taskActivityService.findById(activityForm.getId());
        taskActivity.setId(activityForm.getId());
        taskActivity.setTime(activityForm.getTime());
        taskActivity.setComment(activityForm.getComment());
        taskActivity.setTask(taskService.findByName(activityForm.getTask()));

        taskActivityService.update(taskActivity);

        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteTask(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("activity-delete");
        modelAndView.addObject("activity", taskActivityService.findById(id));
        return modelAndView;
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public ModelAndView deleteTask(@PathVariable Long id,
                                   final RedirectAttributes redirectAttributes, @ModelAttribute("userLogin") String userLogin) {
        String message = "";
        ModelAndView mav = new ModelAndView("redirect:/index.html");
        if ((userLogin != null) || (userLogin.equals(""))) {
            TaskActivity taskActivity = taskActivityService.delete(id);
            message = "The task " + taskActivity.getId() + " was successfully deleted.";
        }
        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }


}
