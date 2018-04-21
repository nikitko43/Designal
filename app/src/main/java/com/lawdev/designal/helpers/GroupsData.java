package com.lawdev.designal.helpers;

import com.lawdev.designal.entities.Group;
import com.lawdev.designal.entities.Performer;

import java.util.ArrayList;
import java.util.List;

public class GroupsData {
    public static ArrayList<Group> groups = new ArrayList<>();
    public GroupsData(){
        groups.add(new Group("Студенты МГТУ", "2"));
        groups.add(new Group("Участники BestHack", "4"));
        groups.add(new Group("Команда LawTeam", "6"));
    }
    public ArrayList<Group> getGroups() {
        return groups;
    }
}
