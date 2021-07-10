import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class TimeMap {
    static final String EMPTY = "";
    Map<String, TreeMap<Integer, String>> keyMap;
    /** Initialize your data structure here. */
    public TimeMap() {
        this.keyMap = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        TreeMap<Integer, String> valueMap = keyMap.getOrDefault(key, new TreeMap<>());
        valueMap.put(timestamp, value);
        keyMap.put(key, valueMap);
    }
    
    public String get(String key, int timestamp) {
        if (!keyMap.containsKey(key)) {
            return EMPTY;
        }
        TreeMap<Integer, String> valueMap = keyMap.get(key);
        Map.Entry<Integer, String> curEntry = valueMap.floorEntry(timestamp);
        return curEntry == null ? EMPTY : curEntry.getValue();
    }

    public static void main(String[] args) {
        TimeMap kv = new TimeMap();
        kv.set("foo", "bar", 1); // 存储键 "foo" 和值 "bar" 以及时间戳 timestamp = 1   
        System.out.println(kv.get("foo", 1)); // 输出 "bar"   
        System.out.println(kv.get("foo", 3));  // 输出 "bar" 因为在时间戳 3 和时间戳 2 处没有对应 "foo" 的值，所以唯一的值位于时间戳 1 处（即 "bar"）   
        kv.set("foo", "bar2", 4); 
        System.out.println(kv.get("foo", 4));  // 输出 "bar2"   
        System.out.println(kv.get("foo", 5));  // 输出 "bar2"
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */