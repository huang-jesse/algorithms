class SubStringHash {
    private static final int B = 31;
    private static final int M = (int)1e9 + 7;
    private int[] hashes;
    private int[] bPow;
    public SubStringHash(String s) {
        int n = s.length();
        this.hashes = new int[n + 1];
        this.bPow = new int[n + 1];
        bPow[0] = 1;
        for (int i = 1; i <= n; i++) {
            hashes[i] = (int)(((long)hashes[i - 1] * B + s.charAt(i - 1)) % M);
            bPow[i] = (int)(((long)bPow[i - 1] * B) % M);
        }
    }
    public int getHash(int l, int r) {
        return (int)((hashes[r + 1] + M - ((long)hashes[l] * bPow[r - l + 1]) % M) % M);
    }

    public static void main(String[] args) {
        String s = "abcabcdabc";
        SubStringHash subStringHash = new SubStringHash(s);
        System.out.println(subStringHash.getHash(0,2) == subStringHash.getHash(3, 5));// true
    }
}