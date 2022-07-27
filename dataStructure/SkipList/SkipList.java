public class SkipList<T> {
    private static final int DEFAULT_CAP = 65536;
    private static final double SKIPLIST_P = 0.5;
    private int maxLevel = 16;
    private SkipNode<T> head;

    public SkipList(int cap) {
        this.maxLevel = calculateMaxLevel(cap);
        head = new SkipNode<T>(null, null, maxLevel);
    }

    public SkipList() {
        this(DEFAULT_CAP);
    }

    public T get(Integer key) {
        if (key == null) throw new NullPointerException();
        SkipNode<T> cur = this.head;
        for (int l = maxLevel - 1; l >= 0; l--) {
            while (cur.forwards[l] != null && cur.forwards[l].key < key) {
                cur = cur.forwards[l];
            }
        }
        if (cur.forwards[0] != null && key.equals(cur.forwards[0].key)) {
            return (T)cur.forwards[0].value;
        } else {
            return null;
        }
    }

    public boolean set(Integer key, T value) {
        if (key == null) throw new NullPointerException();
        int newNodeMaxLevel = randomLevel();
        SkipNode<T> newNode = new SkipNode<T>(key, value, newNodeMaxLevel);
        SkipNode<T> cur = head;
        SkipNode<T>[] update = (SkipNode<T>[])new SkipNode[newNodeMaxLevel];//记录更新的节点
        // 遍历找到插入的节点，并记录下需要更新层指针的节点
        for (int l = this.maxLevel - 1; l >= 0; l--) {
            while (cur.forwards[l] != null && cur.forwards[l].key < key) {
                cur = cur.forwards[l];
            }
            if (cur.forwards[l] != null && key == cur.forwards[l].key) {
                return false;
            }
            if (l < newNodeMaxLevel) {
                update[l] = cur;
            }
        }
        // 执行插入更新操作
        for (int l = 0; l < newNodeMaxLevel; l++) {
            newNode.forwards[l] = update[l].forwards[l];
            update[l].forwards[l] = newNode;
        }
        return true;
    }

    public void remove(Integer key) {
        if (key == null) throw new NullPointerException();
        SkipNode<T> cur = head;
        SkipNode<T>[] update = (SkipNode<T>[])new SkipNode[this.maxLevel];
        // 遍历找到要删除的节点，并记录下需要更新层指针的节点
        for (int l = this.maxLevel - 1; l >= 0; l--) {
            while (cur.forwards[l] != null && cur.forwards[l].key < key) {
                cur = cur.forwards[l];
            }
            update[l] = cur;
        }
        cur = cur.forwards[0];
        if (cur == null || !key.equals(cur.key)) return;
        for (int l = 0; l < this.maxLevel; l++) {
            if (update[l].forwards[l] != cur) {
                break;
            }
            update[l].forwards[l] = cur.forwards[l];
        }
    }

    /**
     * 随机获得节点层数
     * 理论来讲，一级索引中元素个数应该占原始数据的 50%，二级索引中元素个数占 25%，三级索引12.5% ，一直到最顶层。
     * 因为这里每一层的晋升概率是 50%。对于每一个新插入的节点，都需要调用 randomLevel 生成一个合理的层数。
     * 该 randomLevel 方法会随机生成 1~MAX_LEVEL 之间的数，且：
     *      50%的概率返回   1
     *      25%的概率返回   2
     *      12.5%的概率返回 3
     *      ...
     * @return
     */
    private int randomLevel() {
        int level = 1;
        while (Math.random() <= SKIPLIST_P && level < this.maxLevel) {
            level++;
        }
        return level;
    }

    private static int calculateMaxLevel(int cap) {
        double maxLevel = Math.log(cap) / Math.log(1/SKIPLIST_P);
        return (int)Math.ceil(maxLevel);
    }

    public class SkipNode<N> {
        private Integer key;
        private N value;
        private int level;
        private SkipNode<N>[] forwards;

        public SkipNode() {}

        public SkipNode(Integer key, N value, int level) {
            this.key = key;
            this.value = value;
            this.level = level;
            this.forwards = (SkipNode<N>[])new SkipNode[level];
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("{ key: ");
            builder.append(key);
            builder.append("; levels: ");
            builder.append(level);
            builder.append(" }");

            return builder.toString();
        }
    }

    public boolean isEmpty() {
        return head.forwards[0] == null ? true : false;
    }

    public void printAll() {
        SkipNode<T> p = head;
        while (p.forwards[0] != null) {
            System.out.print(p.forwards[0] + " ");
            p = p.forwards[0];
        }
        System.out.println();
    }

    @Override
    public String toString() {
        SkipNode<T> cur = null;
        StringBuilder sb = new StringBuilder();
        for (int l = maxLevel - 1; l >= 0; l--) {
            sb.append("level ").append(l+1).append(": ");
            cur = head.forwards[l];
            while (cur != null) {
                sb.append(cur.key).append(", ");
                cur = cur.forwards[l];
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        SkipList<String> skiplist = new SkipList<>();
        skiplist.set(30, "30");
        skiplist.set(40, "40");
        skiplist.set(50, "50");
        skiplist.set(60, "60");
        skiplist.remove(30);
        skiplist.set(70, "70");
        skiplist.set(90, "90");
        skiplist.set(80, "80");
        skiplist.set(45, "45");
        skiplist.remove(90);
        System.out.println(skiplist);
        skiplist.printAll();
    }
}