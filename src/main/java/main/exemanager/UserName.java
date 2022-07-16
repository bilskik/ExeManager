package main.exemanager;

public class UserName {
    String name;
    String surename;
    private static UserName userName = null;

    public static UserName getInstance() {
        if(userName == null)
            userName = new UserName();
        return userName;
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
}
