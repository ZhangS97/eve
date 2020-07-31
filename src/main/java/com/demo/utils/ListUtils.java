package com.demo.utils;

import java.util.ArrayList;
import java.util.List;

public class ListUtils
{
    //线程数
    public static int threadNum = 4;

    /*
     * 将一个list分为X个list并存入一个list中
     * 根据线程数均分为X个
     * */
    public static <T> List<List<T>> groupList(List<T> list)
    {
        int subLength = list.size() / threadNum + 1;
        List<List<T>> listGroup = new ArrayList<>();
        int size = list.size();
        for (int i = 0; i < size; i += subLength)
        {
            if (i + subLength > size)
            {
                subLength = size - i;
            }
            List<T> newList = list.subList(i, i + subLength);
            listGroup.add(newList);
        }
        return listGroup;
    }
}
