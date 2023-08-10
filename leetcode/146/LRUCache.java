import java.util.LinkedHashMap;
import java.util.Map;

class LRUCache {
    private Map<Integer, Integer> cache;
    public LRUCache(int capacity) {
        this.cache = new LinkedHashMap<>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }
        };
    }

    public int get(int key) {
        return cache.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        cache.put(key, value);
    }

    public static void main(String[] args) {
        int capacity = 3;
        LRUCache cache = new LRUCache(capacity);
        // System.out.println("test: " + sol.test());
        cache.put(1, 100);
        cache.put(2, 200);
        cache.put(3, 300);
        cache.put(4, 400);
        System.out.println("get 1: " + cache.get(1));
        System.out.println("get 2: " + cache.get(2));
        cache.put(5, 500);
        System.out.println("get 2: " + cache.get(2));
        cache.put(6, 600);
        System.out.println("get 4: " + cache.get(4));
        System.out.println("get 5: " + cache.get(5));
    }
}