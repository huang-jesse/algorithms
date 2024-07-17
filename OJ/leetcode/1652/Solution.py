from typing import List

class Solution:
    def decrypt(self, code: List[int], k: int) -> List[int]:
        n = len(code)
        ans = [0] * n
        if k == 0:
            return ans
        l = 1 if k > 0 else n + k
        r = k if k > 0 else n - 1
        sumCode = sum(code[l:r + 1])
        for i in range(n):
            ans[i] = sumCode
            sumCode = sumCode - code[l]
            l = (l + 1) % n
            r = (r + 1) % n
            sumCode = sumCode + code[r]
        return ans

if __name__ == '__main__':
    sol = Solution()
    code = [5,7,1,4]
    k = 3
    print("test:", sol.decrypt(code, k))