from typing import List

class Solution:
    def minimumRounds(self, tasks: List[int]) -> int:
        counter = {}
        for task in tasks:
            counter[task] = counter.get(task, 0) + 1
        ans = 0
        for count in counter.values():
            if count == 1:
                return -1
            remaining = count % 3
            ans += count // 3
            if remaining == 2 or remaining == 1:
                ans += 1
        return ans

if __name__ == '__main__':
    sol = Solution()
    tasks = [2,2,3,3,2,4,4,4,4,4]
    print("test:", sol.minimumRounds(tasks))