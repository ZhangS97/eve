package com.demo.utils;

public class StringUtil
{
    /**
     * 将esi返回结果中 , 类似于[] , [1，2，3]带有[]的结果处理成没有[]的字符串
     * [] return null
     * [1，2，3] return 1，2，3
     *
     * @param str
     * @return
     */
    public static String handelListInEsiRes(String str)
    {
        if (str.equals("[]"))
        {
            return null;
        }
        else
        {
            //截取第二位和倒数第二位并范围，以此去除[]
            return str.substring(1, str.length() - 2);
        }
    }
}