from typing import List

class Solution:
    def minimumRefill(self, plants: List[int], capacityA: int, capacityB: int) -> int:
        n = len(plants)
        numOfRefill = 0
        l = 0
        r = n - 1
        curA = capacityA
        curB = capacityB
        while l < r:
            if curA < plants[l]:
                curA = capacityA
                numOfRefill += 1
            curA -= plants[l]
            if curB < plants[r]:
                curB = capacityB
                numOfRefill += 1
            curB -= plants[r]
            l += 1
            r -= 1
        if l == r and max(curA, curB) < plants[l]:
            numOfRefill += 1
        return numOfRefill

if __name__ == '__main__':
    sol = Solution()
    plants = [2,2,3,3]
    capacityA = 3
    capacityB = 4
    print("test:", sol.minimumRefill(plants, capacityA, capacityB))