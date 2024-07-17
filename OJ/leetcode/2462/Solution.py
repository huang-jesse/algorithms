from typing import List
import heapq

class Solution:
    def totalCost(self, costs: List[int], k: int, candidates: int) -> int:
        n = len(costs)
        total = 0
        if 2 * candidates + k > n:
            costs.sort()
            total = sum(costs[:k])
            return total
        leftMinHeap = []  # 建堆
        rightMinHeap = []
        for i in range(candidates):
            heapq.heappush(leftMinHeap, costs[i])
            heapq.heappush(rightMinHeap, costs[n - 1 - i])
        l = candidates
        r = n - 1 - candidates
        # k sessions
        for i in range(k):
            if leftMinHeap[0] <= rightMinHeap[0]:
                total += heapq.heappop(leftMinHeap)
                heapq.heappush(leftMinHeap, costs[l])
                l += 1
            else:
                total += heapq.heappop(rightMinHeap)
                heapq.heappush(rightMinHeap, costs[r])
                r -= 1
        return total

if __name__ == '__main__':
    sol = Solution()
    costs = [17,12,10,2,7,2,11,20,8]
    k = 3
    candidates = 4
    print("test:", sol.totalCost(costs, k, candidates))