package com.lawdev.designal.helpers;

import com.lawdev.designal.entities.Group;
import com.lawdev.designal.entities.Performer;

import java.util.ArrayList;
import java.util.List;

public class GroupsData {
    public static ArrayList<Group> groups = new ArrayList<>();
    public GroupsData(){
        groups.add(new Group("Студенты МГТУ", "Общие задания"));
        groups.add(new Group("Участники BestHack", "Разработка таскменеджера"));
        groups.add(new Group("Команда LawTeam", "Особые задачи"));
    }
    public ArrayList<Group> getGroups() {
        return groups;
    }
}
