from typing import List


class Solution:
    def findWinners(self, matches: List[List[int]]) -> List[List[int]]:
        maxPlayer = max(max(lst) for lst in matches)
        lossesCounter = [-1] * (maxPlayer + 1)
        for match in matches:
            if lossesCounter[match[0]] == -1:
                lossesCounter[match[0]] = 0
            if lossesCounter[match[1]] == -1:
                lossesCounter[match[1]] = 0
            lossesCounter[match[1]] += 1
        ans = [[], []]
        for i in range(len(lossesCounter)):
            if lossesCounter[i] != 0 and lossesCounter[i] != 1:
                continue
            ans[lossesCounter[i]].append(i)
        return ans

if __name__ == '__main__':
    sol = Solution()
    # matches = [[1,3],[2,3],[3,6],[5,6],[5,7],[4,5],[4,8],[4,9],[10,4],[10,9]]
    matches = [[2,3],[1,3],[5,4],[6,4]]
    print("test:", sol.findWinners(matches))