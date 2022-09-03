package com.pad.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class UUIDUtils {
    /**
     * 带横杆的UUID
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString();
    }

    /**
     * 获取多个带横杆的UUID
     * @param count 所需UUID个数
     * @return
     */
    public static List<String> getUUID(Integer count){
        List<String> list = new ArrayList<>();
        while (0 <= (count--) ){
            list.add(getUUID());
        }
        return list;
    }

    /**
     * 不带横杆的UUID
     * @return
     */
    public static String getSimpleUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    /**
     * 获取多个不带横杆的UUID
     * @param count 所需UUID个数
     * @return
     */
    public static List<String> getSimpleUUID(Integer count){
        List<String> list = new ArrayList<>();
        while (0 <= (count--) ){
            list.add(getSimpleUUID());
        }
        return list;
    }

}
