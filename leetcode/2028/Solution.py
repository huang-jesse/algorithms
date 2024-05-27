from typing import List

class Solution:
    def missingRolls(self, rolls: List[int], mean: int, n: int) -> List[int]:
        m = len(rolls)
        totalOfM = sum(rolls)
        totalOfN = (n + m) * mean - totalOfM
        if totalOfN < n or totalOfN > 6 * n:
            return []
        meanOfN = totalOfN // n
        remainderOfN = totalOfN % n
        ans = []
        for i in range(n):
            if i < remainderOfN:
                ans.append(meanOfN + 1)
            else:
                ans.append(meanOfN)
        return ans

if __name__ == '__main__':
    sol = Solution()
    rolls = [3,2,4,3]
    mean = 4
    n = 2
    print("test:", sol.missingRolls(rolls, mean, n))
