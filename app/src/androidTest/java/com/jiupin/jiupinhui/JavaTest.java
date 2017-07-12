package com.jiupin.jiupinhui;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：czb on 2017/7/12 10:33
 */
public class JavaTest {

    public void useAppContext() throws Exception {
        List<String> lists = new ArrayList<>();
        lists.add("nihao");
        lists.add("hello");
        lists.add("world");
        String area = "";
        for (int i = 0; i < lists.size(); i++) {
            area = lists.get(i)+" ";
        }
        System.out.println(area);
        System.out.println(area.trim());
    }
}
