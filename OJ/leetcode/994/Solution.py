from typing import List
from collections import deque

class Solution:
    def orangesRotting(self, grid: List[List[int]]) -> int:
        DIRECTIONS = ((1, 0), (-1, 0), (0, -1), (0, 1))
        m = len(grid)
        n = len(grid[0])
        freshCount = 0
        queue = deque()
        for i in range(m):
            for j in range(n):
                if grid[i][j] == 1:
                    freshCount += 1
                elif grid[i][j] == 2:
                    queue.append((i, j))
        elapse = -1
        while len(queue) > 0:
            size = len(queue)
            for i in range(size):
                curr, curc = queue.popleft()
                for nr, nc in DIRECTIONS:
                    nextr = curr + nr
                    nextc = curc + nc
                    if nextr < 0 or nextr >= m or nextc < 0 or nextc >= n or grid[nextr][nextc] != 1:
                        continue
                    freshCount -= 1
                    grid[nextr][nextc] = 2
                    queue.append([nextr, nextc])
            elapse += 1
        return max(elapse, 0) if freshCount == 0 else -1

if __name__ == '__main__':
    sol = Solution()
    grid = [[2,1,1],[1,1,0],[0,1,1]]
    print("test:", sol.orangesRotting(grid))