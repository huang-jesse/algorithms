from typing import List
class Solution:
    def maxDivScore(self, nums: List[int], divisors: List[int]) -> int:
        maxDivisor = 0x3fffffff
        maxDivScore = 0
        for divisor in divisors:
            divScore = 0
            for num in nums:
                if num % divisor == 0:
                    divScore += 1
            if maxDivScore < divScore:
                maxDivScore = divScore
                maxDivisor = divisor
            elif maxDivScore == divScore:
                maxDivisor = min(maxDivisor, divisor)
        return maxDivisor

if __name__ == '__main__':
    sol = Solution()
    nums = [4,7,9,3,9]
    divisors = [5,2,3]
    print("test:", sol.maxDivScore(nums, divisors))