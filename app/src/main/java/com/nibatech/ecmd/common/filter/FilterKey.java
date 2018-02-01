package com.nibatech.ecmd.common.filter;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FilterKey {

    public static List<Map<String, Object>> getListFilterKey(List<Map<String, Object>> allList, String key) {
        List<Map<String, Object>> filterList = new ArrayList<>();

        for (int i = 0; i < allList.size(); i++) {
            Map<String, Object> map = allList.get(i);
            if (map.equals(key)) {
                filterList.add(map);
            }
        }

        return filterList;
    }
}
