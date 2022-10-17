class StringHash {
    private static final int B = 31;
    private static final int M = (int)1e9 + 7;
    public static int getHash(String s) {
        int n = s.length();
        long hash = 0;
        for (int i = 0; i < n; i++) {
            hash = (hash * B + s.charAt(i)) % M;
        }
        return (int)hash;
    }

    public void main(String[] args) {

    }
}