from typing import List

class Solution:
    def wateringPlants(self, plants: List[int], capacity: int) -> int:
        ans = 0
        currentCap = capacity
        for i, require in enumerate(plants):
            if (currentCap < require) :
                # refill water can and walk to (i - 1)
                currentCap = capacity
                ans +=2 * i
            currentCap -= require
            ans += 1
        return ans

if __name__ == '__main__':
    sol = Solution()
    plants = [1,1,1,4,2,3]
    capacity = 4 # ans = 30
    print("test:", sol.wateringPlants(plants, capacity))