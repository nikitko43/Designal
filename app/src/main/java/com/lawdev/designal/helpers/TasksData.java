package com.lawdev.designal.helpers;

import com.lawdev.designal.entities.Task;

import java.util.ArrayList;

public class TasksData {
    public static ArrayList<Task> tasks = new ArrayList<>();

    public static ArrayList<Task> finished_tasks = new ArrayList<>();

    public TasksData(){
        if (tasks.size() == 0) {
            tasks.add(new Task("1", "2"));
            tasks.add(new Task("2", "3"));
            tasks.add(new Task("4", "5"));

            finished_tasks.add(new Task("1", "2"));
            finished_tasks.add(new Task("2", "3"));
            finished_tasks.add(new Task("4", "5"));
        }
    }
}
