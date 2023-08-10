import java.util.HashMap;
import java.util.Map;

class LRUCacheOptimization {
    class Node {
        int key;
        int value;
        Node previous;
        Node next;
        public Node() {}
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    class DoublyLinkedList {
        private Node head;
        private Node tail;
        private int size;

        public DoublyLinkedList() {
            this.size = 0;
            // 使用 dummy 头和尾
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.previous = head;
        }

        public void addLast(Node node) {
            tail.previous.next = node;
            node.previous = tail.previous;
            node.next = tail;
            tail.previous = node;
            this.size++;
        }

        public Node removeFirst() {
            if (isEmpty()) {
                throw new IllegalArgumentException();
            }
            Node removeNode = head.next;
            removeNode(removeNode);
            return removeNode;
        }

        private void removeNode(Node node) {
            node.previous.next = node.next;
            node.next.previous = node.previous;
            this.size--;
        }

        public void moveNodeToTail(Node node) {
            if (node == null) {
                throw new IllegalArgumentException();
            }
            // 删除原位置节点
            removeNode(node);
            addLast(node);
        }

        public boolean isEmpty() {
            return this.size == 0;
        }
    }

    private Map<Integer, Node> map = new HashMap<>();
    private DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
    private int capacity;
    public LRUCacheOptimization(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            doublyLinkedList.moveNodeToTail(node);
            return node.value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        Node curNode;
        if (map.containsKey(key)) {
            curNode = map.get(key);
            curNode.value = value;
            doublyLinkedList.moveNodeToTail(curNode);
        } else {
            curNode = new Node(key, value);
            doublyLinkedList.addLast(curNode);
            map.put(key, curNode);
        }
        // LRU 淘汰最长时间未使用的一项
        if (map.size() > capacity) {
            Node removeNode = doublyLinkedList.removeFirst();
            map.remove(removeNode.key);
        }
    }

    public static void main(String[] args) {
        int capacity = 2;
        LRUCacheOptimization cache = new LRUCacheOptimization(capacity);
        // System.out.println("test: " + sol.test());
        cache.put(1, 100);
        cache.put(2, 200);
        cache.put(3, 300);
        System.out.println("get 1: " + cache.get(1));
        System.out.println("get 2: " + cache.get(2));
        cache.put(4, 400);
        System.out.println("get 3: " + cache.get(3));
        cache.put(5, 500);
        System.out.println("get 2: " + cache.get(2));
        cache.put(6, 600);
        System.out.println("get 4: " + cache.get(4));
        System.out.println("get 5: " + cache.get(5));
    }
}