class Skiplist {
    private static final int DEFAULT_CAP = 65536;
    private static final double SKIPLIST_P = 0.5;
    private int maxLevel = 16;
    private SkipNode head;

    public Skiplist(int cap) {
        this.maxLevel = calculateMaxLevel(cap);
        head = new SkipNode(0, maxLevel);
    }

    public Skiplist() {
        this(DEFAULT_CAP);
    }

    public boolean search(int key) {
        SkipNode cur = this.head;
        for (int l = maxLevel - 1; l >= 0; l--) {
            while (cur.forwards[l] != null && cur.forwards[l].key < key) {
                cur = cur.forwards[l];
            }
        }
        if (cur.forwards[0] != null && cur.forwards[0].key == key) {
            return true;
        } else {
            return false;
        }
    }

    public void add(int key) {
        int newNodeMaxLevel = randomLevel();
        SkipNode newNode = new SkipNode(key, newNodeMaxLevel);
        SkipNode cur = head;
        SkipNode[] update = (SkipNode[])new SkipNode[newNodeMaxLevel];//记录更新的节点
        // 遍历找到插入的节点，并记录下需要更新层指针的节点
        for (int l = this.maxLevel - 1; l >= 0; l--) {
            while (cur.forwards[l] != null && cur.forwards[l].key < key) {
                cur = cur.forwards[l];
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
    }

    public boolean erase(int key) {
        SkipNode cur = this.head;
        SkipNode[] update = (SkipNode[])new SkipNode[this.maxLevel];
        // 遍历找到要删除的节点，并记录下需要更新层指针的节点
        for (int l = this.maxLevel - 1; l >= 0; l--) {
            while (cur.forwards[l] != null && cur.forwards[l].key < key) {
                cur = cur.forwards[l];
            }
            update[l] = cur;
        }
        cur = cur.forwards[0];
        if (cur == null || cur.key != key) {
            return false;
        }
        for (int l = 0; l < this.maxLevel; l++) {
            if (update[l].forwards[l] != cur) {
                break;
            }
            update[l].forwards[l] = cur.forwards[l];
        }
        return true;
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

    public class SkipNode {
        private int key;
        private int level;
        private SkipNode[] forwards;

        public SkipNode() {}

        public SkipNode(int key, int level) {
            this.key = key;
            this.level = level;
            this.forwards = (SkipNode[])new SkipNode[level];
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

    @Override
    public String toString() {
        SkipNode cur = null;
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
        Skiplist Skiplist = new Skiplist();
        Skiplist.add(30);
        Skiplist.add(40);
        Skiplist.add(50);
        Skiplist.add(60);
        Skiplist.add(70);
        Skiplist.add(90);
        Skiplist.add(50);
        Skiplist.add(90);
        Skiplist.add(90);
        Skiplist.add(50);
        Skiplist.add(50);
        Skiplist.add(50);
        Skiplist.add(80);
        Skiplist.add(50);
        Skiplist.add(4300);
        System.out.println(Skiplist);
        System.out.println(Skiplist.search(4300));
    }
}