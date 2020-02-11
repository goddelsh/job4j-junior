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

        int fast = 0;
        int slow = 0;
        while (slow < users.size()) {
            if (fast == slow) {
                fast++;
            } else if(fast >= users.size()) {
                fast = 0;
                result.add(users.get(slow));
                slow++;
            } else {
                if (emailsEqual(users.get(slow).getEmails(), users.get(fast).getEmails())) {
                    if (slow < fast) {
                        users.get(slow).getEmails().addAll(users.get(fast).getEmails());
                    } else {
                        users.get(fast).getEmails().addAll(users.get(slow).getEmails());
                        slow++;
                    }
                }
                fast++;
            }
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
