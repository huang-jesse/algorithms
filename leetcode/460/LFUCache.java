import java.util.HashMap;
import java.util.Map;

class LFUCache {
    static class Node<K, V> {
        K key;
        V val;
        Node<K, V> previous;
        Node<K, V> next;
        public Node() {}
        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    static class DoublyLinkedList<K, V> {
        Node<K, V> head;
        Node<K, V> tail;
        int size;

        public DoublyLinkedList() {
            // dummy node
            this.head = new Node<K, V>();
            this.tail = new Node<K, V>();
            this.head.next = tail;
            this.tail.previous = head;
            this.size = 0;
        }

        private void addFirst(Node<K, V> node) {
            node.next = this.head.next;
            this.head.next.previous = node;
            node.previous = this.head;
            this.head.next = node;
            this.size++;
        }

        public void addLast(Node<K, V> node) {
            node.previous = this.tail.previous;
            this.tail.previous.next = node;
            node.next = this.tail;
            this.tail.previous = node;
            this.size++;
        }

        /**
         * remove tail
         */
        public Node<K, V> removeLast() {
            if (isEmpty()) {
                return null;
            }
            Node<K, V> removeNode = this.tail.previous;
            remove(removeNode);
            return removeNode;
        }

        /**
         * peek head
         */
        public Node<K, V> peekFirst() {
            if (isEmpty()) {
                return null;
            }
            return this.head.next;
        }

        private void remove(Node<K, V> node) {
            // delete
            node.previous.next = node.next;
            node.next.previous = node.previous;
            this.size--;
        }

        public void moveToHead(Node<K, V> node) {
            // delete
            remove(node);
            // add to head
            addFirst(node);
        }

        public void addNextNode(Node<K, V> currentNode, Node<K, V> nextNode) {
            nextNode.next = currentNode.next;
            currentNode.next.previous = nextNode;
            currentNode.next = nextNode;
            nextNode.previous = currentNode;
            this.size++;
        }

        public boolean isEmpty() {
            return this.size == 0;
        }
    }
    private static final int INF = 0x3fffffff;
    private int capacity;
    private int size;
    // {key, node}
    private Map<Integer, Node<Integer, Integer>> nodeMap;
    // {key, frequency}
    private Map<Integer, Integer> frequencyMap;
    // {frequency, nodeList}
    private Map<Integer, Node<Integer, DoublyLinkedList<Integer, Integer>>> frequencyListNodeMap;

    // node: {frequency, nodeList}
    // nodeList: {key, value}
    private DoublyLinkedList<Integer, DoublyLinkedList<Integer, Integer>> frequencyList;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.nodeMap = new HashMap<>();
        this.frequencyMap = new HashMap<>();
        this.frequencyListNodeMap = new HashMap<>();
        this.frequencyList = new DoublyLinkedList<>();
    }

    public int get(int key) {
        if (!this.nodeMap.containsKey(key)) {
            return -1;
        }
        Node<Integer, Integer> node = nodeMap.get(key);
        // 移动节点
        moveToNextList(node);
        // 累计 frequency
        increaseFrequency(key);
        return node.val;
    }

    public void put(int key, int value) {
        if (nodeMap.containsKey(key)) {
            Node<Integer, Integer> node = nodeMap.get(key);
            // 更新值
            node.val = value;
            // 移动节点
            moveToNextList(node);
            // 累计 frequency
            increaseFrequency(key);
        } else {
            if (this.size == this.capacity) {
                // 容量将超过限制，则淘汰一个节点
                evict();
            }
            addNewNode(key, value);
        }
    }

    /**
     * 移动节点到下一访问频率的链表的头部
     * @param key
     */
    private void moveToNextList(Node<Integer, Integer> node) {
        int frequency = frequencyMap.get(node.key);
        // 查找当前链表
        Node<Integer, DoublyLinkedList<Integer, Integer>> listNode = getOrCreateListNode(frequency);
        // 如果 frequency 达到最大值，则说明没有下一项链表，此时只需要移动当前链表中的节点即可
        if (frequency == INF) {
            listNode.val.moveToHead(node);
            return;
        }

        // 移除当前链表中的该节点
        listNode.val.remove(node);
        // 追加该 node 到下一访问频率的链表
        int nextFrequency = getNextFrequency(frequency);
        Node<Integer, DoublyLinkedList<Integer, Integer>> nextListNode = getOrCreateListNode(nextFrequency);
        if (!this.frequencyListNodeMap.containsKey(nextListNode.key)) {
            this.frequencyListNodeMap.put(nextListNode.key, nextListNode);
            // 添加 listNode 到 frequencyList 中
            this.frequencyList.addNextNode(listNode, nextListNode);
        }
        // 追加 node 到下一链表
        nextListNode.val.addFirst(node);

        // 当链表为空时应该移除链表
        if (listNode.val.size == 0) {
            removeListNode(listNode);
        }
    }

    private Node<Integer, DoublyLinkedList<Integer, Integer>> getOrCreateListNode(int frequency) {
        if (frequencyListNodeMap.containsKey(frequency)) {
            return frequencyListNodeMap.get(frequency);
        } else {
            DoublyLinkedList<Integer, Integer> list = new DoublyLinkedList<>();
            Node<Integer, DoublyLinkedList<Integer, Integer>> newListNode = new Node<>(frequency, list);
            return newListNode;
        }
    }

    private int getNextFrequency(int count) {
        return Math.min(count + 1, INF);
    }

    private void increaseFrequency(int key) {
        int nextFrequency = getNextFrequency(this.frequencyMap.getOrDefault(key, 0));
        this.frequencyMap.put(key, nextFrequency);
    }

    private void addNewNode(int key, int value) {
        // 新增节点
        Node<Integer, Integer> node = new Node<>(key, value);
        // 新增节点的访问频率 frequency 为 1
        int frequency = 1;
        Node<Integer, DoublyLinkedList<Integer, Integer>> listNode = getOrCreateListNode(frequency);
        // 将链表新增到 frequencyList 的头部
        if (!this.frequencyListNodeMap.containsKey(frequency)) {
            this.frequencyListNodeMap.put(frequency, listNode);
            this.frequencyList.addFirst(listNode);
        }
        // 将新增节点添加到链表头部
        listNode.val.addFirst(node);
        nodeMap.put(key, node);
        // 累计 frequency
        increaseFrequency(key);
        this.size++;
    }

    private void removeListNode(Node<Integer, DoublyLinkedList<Integer, Integer>> listNode) {
        // 移除链表
        this.frequencyList.remove(listNode);
        int frequency = listNode.key;
        // 移除 frequencyListNodeMap
        this.frequencyListNodeMap.remove(frequency);
    }

    private void removeNodeFromMap(Node<Integer, Integer> node) {
        // nodeMap
        this.nodeMap.remove(node.key);
        // frequencyMap
        this.frequencyMap.remove(node.key);
    }

    /**
     * 淘汰最不经常使用节点
     */
    private void evict() {
        // frequencyList 中的 head 为最不经常使用的链表
        Node<Integer, DoublyLinkedList<Integer, Integer>> lfuListNode = this.frequencyList.peekFirst();
        // 移除最不经常使用的链表中，最近最少使用的节点
        Node<Integer, Integer> lruNode = lfuListNode.val.removeLast();
        removeNodeFromMap(lruNode);
        if (lfuListNode.val.size == 0) {
            removeListNode(lfuListNode);
        }
        this.size--;
    }

    public static void main(String[] args) {
        LFUCache lfu = new LFUCache(2);
        lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
        lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
        System.out.println(lfu.get(1));      // return 1
        // cache=[1,2], cnt(2)=1, cnt(1)=2
        lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
        // cache=[3,1], cnt(3)=1, cnt(1)=2
        System.out.println(lfu.get(2));      // return -1 (not found)
        System.out.println(lfu.get(3));      // return 3
        // cache=[3,1], cnt(3)=2, cnt(1)=2
        lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
        // cache=[4,3], cnt(4)=1, cnt(3)=2
        System.out.println(lfu.get(1));;      // return -1 (not found)
        System.out.println(lfu.get(3));;      // return 3
        // cache=[3,4], cnt(4)=1, cnt(3)=3
        System.out.println(lfu.get(4));;      // return 4
        // cache=[4,3], cnt(4)=2, cnt(3)=3
    }
}