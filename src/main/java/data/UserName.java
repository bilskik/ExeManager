package data;

import java.util.LinkedList;
import java.util.List;

public class UserName {
    String name = "";
    String surename = "";
    List<String> allNames;
    private static UserName userName = null;
    UserName() {
        this.allNames = new LinkedList<>();
    }

    public static UserName getInstance() {
        if(userName == null)
            userName = new UserName();
        return userName;
    }
    public void addList(String name) {
        allNames.add(name);
    }
    public void removeFromList(String name) {
        allNames.remove(name);
    }
    public String getName() {
        return name;
    }
    public String getSurename() {
        return surename;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSurename(String surename) {
        this.surename = surename;
    }
    public List<String> getList() {
        return allNames;
    }
}
