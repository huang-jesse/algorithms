from typing import List
class Solution:
    def maxOperations(self, nums: List[int]) -> int:
        score = nums[0] + nums[1]
        for i in range(3, len(nums), 2):
            if nums[i] + nums[i - 1] != score:
                return i // 2
        return len(nums) // 2

if __name__ == '__main__':
    sol = Solution()
    nums = [3,2,1,4,5]
    print("test:", sol.maxOperations(nums))