from typing import Tuple, List, Optional

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
    def __repr__(self):
        return 'TreeNode({})'.format(self.val)

def deserialize(string):
    if string == '{}':
        return None
    nodes = [None if val == 'null' else TreeNode(int(val))
             for val in string.strip('[]{}').split(',')]
    kids = nodes[::-1]
    root = kids.pop()
    for node in nodes:
        if node:
            if kids: node.left  = kids.pop()
            if kids: node.right = kids.pop()
    return root

def isSameTree(p: TreeNode, q: TreeNode) -> bool:
    if not p or not q: return p == q
    if p.val != q.val: return False
    return isSameTree(p.left, q.left) and isSameTree(p.right, q.right)

class Solution:
    def __init__(self):
        self.ans = 0

    def amountOfTime(self, root: Optional[TreeNode], start: int) -> int:
        self.dfs(root, start)
        return self.ans

    def dfs(self, node: Optional[TreeNode], start: int) -> Tuple[int, bool]:
        if node is None:
            return [0, False] # [depth, isStartFound]
        leftLen, leftFound = self.dfs(node.left, start)
        rightLen, rightFound = self.dfs(node.right, start)
        if node.val == start:
            # 找到 start 节点
            self.ans = max(leftLen, rightLen)
            return [1, True]
        elif leftFound or rightFound:
            # 子树存在 start
            self.ans = max(self.ans, leftLen + rightLen) # 两条链拼成直径
            # 保证 start 是直径端点
            startDepth = leftLen if leftFound else rightLen
            return [startDepth + 1, True]
        else:
            return [max(leftLen, rightLen) + 1, False]

if __name__ == '__main__':
    sol = Solution()
    root = deserialize('[1,5,3,null,4,10,6,9,2]')
    start = 3
    print("test:", sol.amountOfTime(root, start))

