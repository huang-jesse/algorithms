class Solution {
    char[][] romans;
    public String intToRoman(int num) {
        this.initializeRomans();

        StringBuilder sb = new StringBuilder();
        int index = 1;
        while (num > 0) {
            int digital = num % 10;
            num = num / 10;
            String digitalStr = getRoman(digital, index);
            sb.insert(0, digitalStr);
            index++;
        }
        return sb.toString();
    }

    private String getRoman(int digital, int index) {
        StringBuilder sb = new StringBuilder();
        if (digital == 0) {
            return "";
        } else if (digital < 4) {
            for (int i = 0; i < digital; i++)
                sb.append(this.romans[index][0]);
        } else if (digital == 4) {
            sb.append(this.romans[index][0]).append(this.romans[index][1]);
        } else if (digital == 5) {
            sb.append(this.romans[index][1]);
        } else if (digital < 9) {
            sb.append(this.romans[index][1]);
            for (int i = 0; i < digital - 5; i++)
                sb.append(this.romans[index][0]);
        } else {
            // digital == 9
            sb.append(this.romans[index][0]).append(this.romans[index][2]);
        }
        return sb.toString();
    }

    private void initializeRomans() {
        this.romans = new char[5][3];
        romans[1][0] = 'I'; // 1
        romans[1][1] = 'V'; // 5
        romans[1][2] = 'X'; // 10
        
        romans[2][0] = 'X'; // 10
        romans[2][1] = 'L'; // 50
        romans[2][2] = 'C'; // 100
        
        romans[3][0] = 'C'; // 100
        romans[3][1] = 'D'; // 500
        romans[3][2] = 'M'; // 1000
        
        romans[4][0] = 'M'; // 1000
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int num = 1994;//  "MCMXCIV"
        System.out.println("test: " + sol.intToRoman(num));
    }
}