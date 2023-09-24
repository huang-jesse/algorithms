import java.util.HashMap;
import java.util.Map;

public class LRUCacheNew {
    static class Node {
        int key;
        int val;
        Node previous;
        Node next;
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    static class DoublyLinkedList {
        Node head;
        Node tail;
        int size;

        public DoublyLinkedList() {
            // dummy node
            this.head = new Node(-1, -1);
            this.tail = new Node(-1, -1);
            this.head.next = tail;
            this.tail.previous = head;
            this.size = 0;
        }

        private void addFirst(Node node) {
            node.next = this.head.next;
            this.head.next.previous = node;
            node.previous = this.head;
            this.head.next = node;
            this.size++;
        }

        public void addLast(Node node) {
            node.previous = this.tail.previous;
            this.tail.previous.next = node;
            node.next = this.tail;
            this.tail.previous = node;
            this.size++;
        }

        /**
         * remove tail
         */
        public Node removeLast() {
            if (isEmpty()) {
                return null;
            }
            Node removeNode = this.tail.previous;
            remove(removeNode);
            return removeNode;
        }

        private void remove(Node node) {
            // delete
            node.previous.next = node.next;
            node.next.previous = node.previous;
            this.size--;
        }

        public void moveToHead(Node node) {
            // delete
            remove(node);
            // add to head
            addFirst(node);
        }

        public boolean isEmpty() {
            return this.size == 0;
        }
    }
    private int capacity;
    private Map<Integer, Node> nodeMap;
    private DoublyLinkedList doublyLinkedList;

    public LRUCacheNew(int capacity) {
        this.capacity = capacity;
        this.nodeMap = new HashMap<>();
        this.doublyLinkedList = new DoublyLinkedList();
    }

    public int get(int key) {
        if (!this.nodeMap.containsKey(key)) {
            return -1;
        }
        Node node = nodeMap.get(key);
        // 移动节点
        doublyLinkedList.moveToHead(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (nodeMap.containsKey(key)) {
            Node node = nodeMap.get(key);
            // 更新值
            node.val = value;
            // 移动节点到头部
            doublyLinkedList.moveToHead(node);
        } else {
            // 新增节点
            Node node = new Node(key, value);
            doublyLinkedList.addFirst(node);
            nodeMap.put(key, node);
        }
        if (doublyLinkedList.size > this.capacity) {
            // 淘汰最近最少使用的节点
            this.evict();
        }
    }

    /**
     * 淘汰最近最少使用的节点
     */
    private void evict() {
        Node removeNode = doublyLinkedList.removeLast();
        nodeMap.remove(removeNode.key);
    }

    public static void main(String[] args) {
        LRUCacheNew lRUCache = new LRUCacheNew(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        System.out.println(lRUCache.get(1));    // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        System.out.println(lRUCache.get(2));    // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        System.out.println(lRUCache.get(1));    // return -1 (not found)
        System.out.println(lRUCache.get(3));    // return 3
        System.out.println(lRUCache.get(4));    // return 4
    }
}
