package com.demo.utils;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {
    public static <T> List<List<T>> groupList(List<T> list, int subLength) {
        List<List<T>> listGroup = new ArrayList<>();
        int size = list.size();
        for (int i = 0; i < size; i += subLength) {
            if (i + subLength > size) {
                subLength = size - i;
            }
            List<T> newList = list.subList(i, i + subLength);
            listGroup.add(newList);
        }
        return listGroup;
    }
}
