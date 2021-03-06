package ru.job4j.map;

import java.util.Calendar;

public class User {
    public String name;

    public int children;

    public Calendar birthday;


    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public int hashCode() {
        int result = this.name.hashCode();
        result = 31 * result + Integer.hashCode(children);
        result = 31 * result + birthday.hashCode();
        return result;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        User o = (User) obj;
        return name.equals(o.name) && children == o.children && birthday.equals(o.birthday);
    }

}
