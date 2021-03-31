package chuprynin.com.epam.rd.map;


import java.util.*;

/**
 * Класс заполняет Map<K,V> выводит результат
 */
public class MapService {
    Map map = new HashMap<Integer, String>();

    /**
     * Конструктор с дефолтным заполнением мапы.
     */
    public MapService() {
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
    }

    /**
     * Метод для демонстрации работы
     */
    public void service() {
        print("Вывод первоначальной мапы", map);

        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.putAll(map);
        print("Вывод с сортировкой по ключу", treeMap);

        System.out.println("Вывод с сортировакой по значению");
        Iterator iterator = valueIterator(treeMap);
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toString());
        }
    }

    /**
     * Мето для сортировки TreeMap по значению, возращает
     *
     * @param map
     * @return Iterator
     */
    private Iterator valueIterator(TreeMap map) {
        Set set = new TreeSet(new Comparator<Map.Entry<Integer, String>>() {
            @Override
            public int compare(Map.Entry<Integer, String> o1, Map.Entry<Integer, String> o2) {
                return o1.getValue().compareTo(o2.getValue()) > 0 ? 1 : -1;
            }
        });
        set.addAll(map.entrySet());
        return set.iterator();
    }

    /**
     * Метод для вывода данных
     *
     * @param title
     * @param m
     */
    private void print(String title, Map m) {
        System.out.println(title + m.toString());
    }
}
