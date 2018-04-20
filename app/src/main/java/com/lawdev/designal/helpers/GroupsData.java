package com.lawdev.designal.helpers;

import com.lawdev.designal.entities.Group;
import com.lawdev.designal.entities.Performer;

import java.util.ArrayList;
import java.util.List;

public class GroupsData {
    public static ArrayList<Group> groups = new ArrayList<>();
    public GroupsData(){
        groups.add(new Group("1", "2"));
        groups.add(new Group("3", "4"));
        groups.add(new Group("5", "6"));
    }
    public ArrayList<Group> getGroups() {
        return groups;
    }
}
