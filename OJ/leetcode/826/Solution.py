from typing import List

class Solution:
    def maxProfitAssignment(self, difficulty: List[int], profit: List[int], worker: List[int]) -> int:
        worker.sort()
        jobs = []
        for i in range(len(difficulty)):
            jobs.append((difficulty[i], profit[i]))
        jobs.sort(key=lambda x: x[0])
        ans = 0
        maxProfit = 0
        i = 0
        for work in worker:
            while i < len(jobs) and work >= jobs[i][0]:
                maxProfit = max(maxProfit, jobs[i][1])
                i += 1
            ans += maxProfit
        return ans

if __name__ == '__main__':
    sol = Solution()
    difficulty = [2,4,6,8,10]
    profit = [10,20,30,40,50]
    worker = [4,5,6,7]
    print("test:", sol.maxProfitAssignment(difficulty, profit, worker))