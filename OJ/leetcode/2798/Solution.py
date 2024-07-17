from typing import List

class Solution:
    def numberOfEmployeesWhoMetTarget(self, hours: List[int], target: int) -> int:
        return sum(h >= target for h in hours)

if __name__ == '__main__':
    sol = Solution()
    hours = [0,1,2,3,4]
    target = 2
    print("test:", sol.numberOfEmployeesWhoMetTarget(hours, target))