import math
from sys import maxsize
from typing import List


class Solution:
    def maxSatisfied(self, customers: List[int], grumpy: List[int], minutes: int) -> int:
        n = len(customers)
        unstatisfiedCustomers = []
        ans = 0
        for i in range(n):
            if grumpy[i] == 1:
                unstatisfiedCustomers.append(customers[i])
            else:
                unstatisfiedCustomers.append(0)
                ans += customers[i]
        currentSum = 0
        maxSum = 0
        for i in range(n):
            currentSum += unstatisfiedCustomers[i]
            if i >= minutes:
                currentSum -= unstatisfiedCustomers[i - minutes]
            maxSum = max(currentSum, maxSum)

        return ans + maxSum


if __name__ == '__main__':
    sol = Solution()
    customers = [1,0,1,2,1,1,7,5]
    grumpy = [0,1,0,1,0,1,0,1]
    minutes = 3
    ans = sol.maxSatisfied(customers, grumpy, minutes)
    print("test:", ans)