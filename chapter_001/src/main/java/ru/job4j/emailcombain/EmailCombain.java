package ru.job4j.emailcombain;

import java.util.*;

public class EmailCombain {
    public List<User> combain(List<User> users) {
        List<User> result = new ArrayList<User>();

        Set<Integer> ignoreList = new HashSet<>();

        while (ignoreList.size() < users.size()) {
            for (int i = 0; i < users.size(); i++) {
                if (!checkelementIntList(users.get(i), result)) {
                    result.add(users.get(i));
                } else {
                    ignoreList.add(i);
                }
            }
        }
        return result;
    }

    private boolean checkelementIntList(User user, List<User> users) {
        var result = false;
        for (User el : users) {
            if (emailsEqual(el.getEmails(), user.getEmails())) {
                Set<String> emails = new HashSet(el.getEmails());
                emails.addAll(user.getEmails());
                el.setEmails(new ArrayList<>(emails));
                result = true;
            }
        }

        return result;
    }

    private boolean emailsEqual(List<String> left, List<String> right) {
        var result = false;
        Collections.sort(left, new StringLengthComparator());
        Collections.sort(right, new StringLengthComparator());
        var leftIndex = 0;
        var rightIndex = 0;
        while (leftIndex < left.size()) {
            if (left.get(leftIndex).length() < right.get(rightIndex).length()) {
                leftIndex++;
            } else if (left.get(leftIndex).length() > right.get(rightIndex).length()) {
                rightIndex++;
            } else {
                if (left.get(leftIndex).equals(right.get(rightIndex))) {
                    result = true;
                    break;
                } else {
                    rightIndex++;
                }
            }
            if (rightIndex >= right.size()) {
                rightIndex = 0;
                leftIndex++;
            }
        }

        return result;
    }


    private class StringLengthComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.length() - o2.length();
        }
    }
}
