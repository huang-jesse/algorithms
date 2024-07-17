from typing import List

class Solution:
    M_CHAR = 'M'
    P_CHAR = 'P'
    G_CHAR = 'G'
    def garbageCollection(self, garbage: List[str], travel: List[int]) -> int:
        ans = len(garbage[0])
        currentTravel = 0
        mTravel = 0
        pTravel = 0
        gTravel = 0
        for i in range(1, len(garbage)):
            ans += len(garbage[i])
            currentTravel += travel[i - 1]
            for cur in garbage[i]:
                if cur == Solution.M_CHAR:
                    mTravel = currentTravel
                if cur == Solution.P_CHAR:
                    pTravel = currentTravel
                if cur == Solution.G_CHAR:
                    gTravel = currentTravel
        return ans + mTravel + pTravel + gTravel

if __name__ == '__main__':
    sol = Solution()
    garbage = ["G","P","GP","GG"]
    travel = [2,4,3]
    print("test:", sol.garbageCollection(garbage, travel))