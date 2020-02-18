package ru.job4j.analizr;

import java.util.Comparator;
import java.util.List;

public class Analize {


    public Info diff(List<User> previous, List<User> current) {
        Info result = new Info();

        previous.sort(new IdComparator());
        current.sort(new IdComparator());

        var slow = 0;
        var fast = 0;

        while (slow < previous.size()) {
            if (previous.get(slow).getId() == current.get(fast).getId()) {
                if (!previous.get(slow).getName().equals(current.get(fast).getName())) {
                    result.setChanged(result.getChanged() + 1);
                }
                slow++;
            } else if (previous.get(slow).getId() > current.get(fast).getId() && slow <= fast) {
                result.setAdded(result.getAdded() + 1);
                slow++;
            } else {
                fast++;
            }
            if (fast >= current.size()) {
                fast = 0;
                slow++;
                result.setDeleted(result.getDeleted() + 1);
            }
        }
        result.setAdded(current.size() - (previous.size() - result.getDeleted()));

        return result;
    }

    public static class User {
        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Info {
        private int added;
        private int changed;
        private int deleted;

        public int getAdded() {
            return added;
        }

        public void setAdded(int added) {
            this.added = added;
        }

        public int getChanged() {
            return changed;
        }

        public void setChanged(int changed) {
            this.changed = changed;
        }

        public int getDeleted() {
            return deleted;
        }

        public void setDeleted(int deleted) {
            this.deleted = deleted;
        }
    }

    private class IdComparator implements Comparator<User> {
        @Override
        public int compare(User o1, User o2) {
            return o1.id - o2.id;
        }
    }

}
