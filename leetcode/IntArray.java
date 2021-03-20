public class IntArray {
    private int[] ints = new int[26];

    public int get(int index) {
        return ints[index];
    }

    public void set(int index, int value) {
        ints[index] = value;
    }

    @Override
    public boolean equals(Object obj) {
        IntArray target = (IntArray)obj;
        for (int i = 0; i < 26; i++) {
            if (ints[i] != target.get(i)) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (int i = 0; i < 26; i++) {
            hash += i*ints[i];
            // hash += ints[i];
        }

        return hash;
    }
}