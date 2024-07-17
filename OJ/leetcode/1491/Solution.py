from typing import List

class Solution:
    def average(self, salary: List[int]) -> float:
        mins = min(salary)
        maxs = max(salary)
        total = sum(salary)
        return (total - mins - maxs) / (len(salary) - 2)

if __name__ == '__main__':
    sol = Solution()
    salary = [4000,3000,1000,2000]
    print("test:", sol.average(salary))