from typing import List

class Solution:
    def findIndices(self, nums: List[int], indexDifference: int, valueDifference: int) -> List[int]:
        n = len(nums)
        minIdx = 0
        maxIdx = 0
        for j in range(indexDifference, n):
            i = j - indexDifference
            if nums[i] < nums[minIdx]:
                minIdx = i
            elif nums[i] > nums[maxIdx]:
                maxIdx = i
            value = nums[j]
            if nums[maxIdx] - value >= valueDifference:
                return [maxIdx, j]
            if value - nums[minIdx] >= valueDifference:
                return [minIdx, j]
        return [-1, -1]

if __name__ == '__main__':
    sol = Solution()
    nums = [5,1,4,1]
    indexDifference = 2
    valueDifference = 4
    print("test:", sol.findIndices(nums, indexDifference, valueDifference))