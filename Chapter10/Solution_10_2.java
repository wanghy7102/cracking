package Chapter10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Solution_10_2
 */
public class Solution_10_2 {

    static String sortChars(String s) {
        char[] content = s.toCharArray();
        Arrays.sort(content);
        return new String(content);
    }

    static void sort1(String[] array) {
        Arrays.sort(array, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return sortChars(o1).compareTo(sortChars(o2));
            }
        });
    }

    static void sort2(String[] array) {
        Map<String,List<String>> map = new HashMap<>();
        for (String s : array) {
            String key = sortChars(s);
            List<String> list = null;
            if (!map.containsKey(key)) {
                list = new ArrayList<>();
                map.put(key,list);
            } else{
                list = map.get(key);
            }
            list.add(s);
        }

        int i = 0;
        for (String key : map.keySet()) {
            List<String> list = map.get(key);
            for (String s : list) {
                array[i++] = s;
            }
        }
    }

    public static void main(String[] args) {
        String[] array = new String[]{"a", "b", "anagram", "c", "d", "nagaram", "adobe", "abode"};
        sort2(array);
        System.out.println(Arrays.toString(array));
    }
}