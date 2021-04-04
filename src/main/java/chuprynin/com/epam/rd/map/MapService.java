package chuprynin.com.epam.rd.map;

import java.util.*;

/**
 * Класс заполняет Map<K,V> выводит результат
 */
public class MapService {
    /**
     * Конструктор с дефолтным заполнением мапы.
     */
    public HashMap getFillMap() {
        HashMap map = new HashMap<Integer, String>();

        map.put(150, "один");
        map.put(95, "два");
        map.put(45, "три");
        map.put(334, "четыре");
        map.put(657, "пять");
        map.put(123, "шесть");
        map.put(4, "семь");
        map.put(78, "восемь");
        map.put(987, "девять");
        map.put(12, "десять");

        return map;
    }

    /**
     * Сортировка по ключу
     *
     * @param map
     */
    public void sortByKey(HashMap<Integer, String> map) {
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.putAll(map);

        System.out.println(treeMap.toString());
    }

    /**
     * Сортировка по зевченмю
     *
     * @param map
     */
    public void sortByValue(HashMap<Integer, String> map) {
        SortedSet<String> sortedSet = new TreeSet<>();

        for(Map.Entry<Integer, String> entry : map.entrySet()) {
            sortedSet.add(entry.getValue());
        }

        for (String s: sortedSet) {
            for(Map.Entry<Integer, String> entry : map.entrySet()) {
                if (s.equals(entry.getValue())){
                    System.out.println(entry.toString());
                }
            }
        }
    }
}
