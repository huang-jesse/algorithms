from typing import List
class Solution:
    def findLUSlength(self, strs: List[str]) -> int:
        def is_subseq(s: str, t: str) -> bool:
            i = 0
            for c in t:
                if s[i] == c:
                    i += 1
                    if (i == len(s)):
                        # is sub
                        return True
            return False

        strs.sort(key=len, reverse=True)
        for i, sub in enumerate(strs):
            if (all(i == j or not is_subseq(sub, curStr) for j, curStr in enumerate(strs))):
                return len(sub)
        return -1

if __name__ == '__main__':
    sol = Solution()
    strs = ["aba","cdc","eae"] # 3
    # strs = ["aaa","aaa","aa"] # -1
    print("test:", sol.findLUSlength(strs))