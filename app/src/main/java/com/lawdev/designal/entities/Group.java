package com.lawdev.designal.entities;

public class Group {
    public String name;
    public String mainTask;
    public Group(String _name, String _mainTask)
    {
        name=_name;
        mainTask=_mainTask;
    }

    public String getName(){
        return this.name;
    }

    public String getMainTask(){
        return this.mainTask;
    }
}
