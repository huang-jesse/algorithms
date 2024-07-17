import java.util.ArrayList;
import java.util.List;

class OrderedStream {
    private String[] arr;
    private int ptr = 0;
    public OrderedStream(int n) {
        this.arr = new String[n];
    }

    public List<String> insert(int idKey, String value) {
        arr[idKey-1] = value;
        List<String> orderedList = new ArrayList<>();
        while (ptr < arr.length && arr[ptr] != null) {
            orderedList.add(arr[ptr]);
            ptr++;
        }
        return orderedList;
    }

    public static void main(String[] args) {
        OrderedStream os= new OrderedStream(5);
        System.out.println(os.insert(3, "ccccc")); // 插入 (3, "ccccc")，返回 []
        System.out.println(os.insert(1, "aaaaa")); // 插入 (1, "aaaaa")，返回 ["aaaaa"]
        System.out.println(os.insert(2, "bbbbb")); // 插入 (2, "bbbbb")，返回 ["bbbbb", "ccccc"]
        System.out.println(os.insert(5, "eeeee")); // 插入 (5, "eeeee")，返回 []
        System.out.println(os.insert(4, "ddddd")); // 插入 (4, "ddddd")，返回 ["ddddd", "eeeee"]
    }
}