package ru.job4j.emailcombain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EmailCombain {
    public List<User> combain(List<User> users) {
        List<User> result = new ArrayList<User>();
       // User referingUser = null;

        for(User user : users) {
            for (User referingUser : users) {
                if (emailsEqual(referingUser.getEmails(), user.getEmails())) {
                    referingUser.getEmails().addAll(user.getEmails());
                } else {
                    result.add(referingUser);
                }
            }
//            if (referingUser == null) {
//                referingUser = user;
//                result.add(referingUser);
//                continue;
//            }else{
//                if (emailsEqual(referingUser.getEmails(), user.getEmails())) {
//                    referingUser.getEmails().addAll(user.getEmails());
//                } else {
//                    referingUser = user;
//                    result.add(referingUser);
//                }
//            }
        }
        return result;
    }

    private boolean emailsEqual(List<String> left, List<String> right) {
        var result = false;
        Collections.sort(left, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        Collections.sort(right, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        var leftIndex = 0;
        var rightIndex = 0;
        while(leftIndex < left.size() && rightIndex <right.size()) {
            if (left.get(leftIndex).length() < right.get(rightIndex).length()) {
                leftIndex++;
            }else if (left.get(leftIndex).length() > right.get(rightIndex).length()) {
                rightIndex++;
            }else{
                if (left.get(leftIndex).equals(right.get(rightIndex))) {
                    result = true;
                    break;
                }else{
                    leftIndex++;
                    rightIndex++;
                }
            }
        }

        return result;
    }
}
