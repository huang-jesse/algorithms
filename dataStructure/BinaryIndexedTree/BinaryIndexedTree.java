
public class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        this.c = new int[n + 1];// 1-indexed
    }

    public BinaryIndexedTree(int[] a) {
        this(a.length);
        this.init(a);
    }

    private int lowbit(int x) {
        return x & -x;
    }

    public void init(int[] a) {
        for (int i = 1; i <= n; i++) {
            c[i] += a[i - 1];
            int j = i + lowbit(i);
            if (j <= n) {
                c[j] += c[i];
            }
        }
    }

    public void add(int x, int delta) {
        while (x <= n) {
            c[x] += delta;
            x = x + lowbit(x);
        }
    }

    public int getSum(int x) {
        int res = 0;
        while (x > 0) {
            res += c[x];
            x = x - lowbit(x);
        }
        return res;
    }

    public int getRangeSum(int l, int r) {
        return getSum(r) - getSum(l - 1);
    }

    public static void main(String[] args) {
        BinaryIndexedTree bit = new BinaryIndexedTree(10);
        // Let's assume we have an array of size 10 and we are updating it
        bit.add(1, 5);
        bit.add(2, 3);
        bit.add(5, 2);

        System.out.println(bit.getSum(5)); // Output: 10 (5 + 3 + 2)
        System.out.println(bit.getRangeSum(2, 5)); // Output: 5 (3 + 2)
    }
}