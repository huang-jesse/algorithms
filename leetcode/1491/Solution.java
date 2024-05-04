class Solution {
    public double average(int[] salary) {
        int total = 0;
        int min = (int)(1e7);
        int max = 0;
        for (int s : salary) {
            min = Math.min(s, min);
            max = Math.max(s, max);
            total += s;
        }
        return (double)(total - min - max) / (salary.length - 2);

    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] salary = {4000,3000,1000,2000};
        System.out.println("test: " + sol.average(salary));
    }
}