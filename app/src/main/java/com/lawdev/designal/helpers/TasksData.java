package com.lawdev.designal.helpers;

import com.lawdev.designal.entities.Task;

import java.util.ArrayList;

public class TasksData {
    public static ArrayList<Task> tasks = new ArrayList<>();

    public static ArrayList<Task> finished_tasks = new ArrayList<>();

    public TasksData(){
        tasks.add(new Task("Купить хлеб", "2 батона белого и половину черного"));
        tasks.add(new Task("Купить MacBook 2017", "И переходники!"));
        tasks.add(new Task("Купить права", "На категорию B"));

        finished_tasks.add(new Task("Победить в хакатоне", "Занять первое место"));
        finished_tasks.add(new Task("Узнать, что такое ListView", "В гугле"));
        finished_tasks.add(new Task("Выучить Java", "Возможное невозможно"));
    }
}
