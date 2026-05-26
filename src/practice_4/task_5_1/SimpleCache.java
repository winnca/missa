package practice_4.task_5_1;

import java.lang.reflect.Field;
import java.util.*;

public class SimpleCache<K, V>{
    private final LinkedHashMap<K, V> linkedHashMap;
    public SimpleCache(int capacity){
        this.linkedHashMap=new LinkedHashMap<K, V>(capacity, 0.75f, true){
            @Override
            protected boolean removeEldestEntry(Map.Entry<K,V> eldest){
                return size() > capacity;
            }
        };
        if (!getAccessOrderValue(this.linkedHashMap)){
            throw new IllegalArgumentException("accessOrder должен быть true");
        }
    }
    public static boolean getAccessOrderValue(LinkedHashMap<?,?> linkedHashMap){
        try {
            Field field = LinkedHashMap.class.getDeclaredField("accessOrder");
            field.setAccessible(true); // открывает доступ к private полю
            return (boolean) field.get(linkedHashMap);
        } catch (Exception e){
            throw new RuntimeException("Не удалось получить доступ к полю accessOrder", e);
        }
    }
    public void put(K key, V value){
        linkedHashMap.put(key,value);
    }
    public V getKey(K key){
        return linkedHashMap.get(key);
    }
    public boolean containsKey(K key){
        return linkedHashMap.containsKey(key);
    }
    public int size(){
        return linkedHashMap.size();
    }
    public void clear(){
        linkedHashMap.clear();
    }

    @Override
    public String toString() {
        return "SimpleCache{" +
                "linkedHashMap=" + linkedHashMap +
                '}';
    }
}
class Test {
    public static void main(String[] args) {
        SimpleCache<String, Integer> cache = new SimpleCache<>(3);
        cache.put("a", 1);
        cache.put("b", 2);
        cache.put("c", 3);
        System.out.println("Кэш после добавления 3 элементов: " + cache);
        cache.put("d", 4);
        System.out.println("Кэш после добавления 4 элемента: " + cache);
        System.out.println("Получение элемента 'a' (ожидается null): " + cache.getKey("a"));
        System.out.println("Есть ли такой ключ 'b': " + cache.containsKey("b"));
        System.out.println("Размер: " + cache.size());
    }
}