from typing import List

class Solution:
    def findColumnWidth(self, grid: List[List[int]]) -> List[int]:
        m, n = len(grid), len(grid[0])
        ans = []
        for i in range(n):
            # 由于数字的绝对值越大，数字的长度就越长，所以只需要对每一列的最小值或最大值求长度。
            mn, mx = 0, 0
            for j in range(m):
                mn = min(mn, grid[j][i])
                mx = max(mx, grid[j][i])
            # 获取最大位数
            digitCount = 1
            # mx / 10 或者负数 mn 的绝对值的最大者的位数最大，最终需要额外加1
            num = max(mx // 10, -mn)
            while num > 0:
                num = num // 10
                digitCount += 1
            ans.append(digitCount)
        return ans

if __name__ == '__main__':
    sol = Solution()
    grid = [[-15,1,3],[15,7,12],[5,6,-2]]
    print("test:", sol.findColumnWidth(grid))