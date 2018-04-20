package com.lawdev.designal.helpers;

import com.lawdev.designal.entities.Performer;

import java.util.ArrayList;

public class PerformersData {
    private static ArrayList<Performer> group = new ArrayList<Performer>(){};

    //список участников

    public PerformersData() {
        if (group.size() == 0) {
            group.add(new Performer(0,"Реутов Никита", "Капитан"));
            group.add(new Performer(1,"Кондратьев Максим", "Мозг"));
            group.add(new Performer(2,"Чеснавский Марк", "Машина"));
        }
    }

    public ArrayList<Performer> getGroup() {
        return group;
    }
}
