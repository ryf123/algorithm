import unittest

from Leetcode.src import TreeNode


class LongestIncreaseSubSequence:
    longest_seq = []

    def get_global_longest_sequence(self, root: TreeNode):
        self.get_current_longest_sequence(root)
        return self.longest_seq

    def get_current_longest_sequence(self, root: TreeNode):
        """
        :param TreeNode root: 
        :return: []
        """
        if root is None:
            return []
        left_sequence = self.get_current_longest_sequence(root.left)
        right_sequence = self.get_current_longest_sequence(root.right)

        left_len = len(left_sequence)
        right_len = len(right_sequence)

        if left_len > right_len and left_len > 0 and left_sequence[0] > root.value:
            ret = [root.value] + left_sequence

        elif right_len > left_len and right_len > 0 and right_sequence[0] > root.value:
            ret = [root.value] + right_sequence
        else:
            ret = [root.value]

        if len(ret) > len(self.longest_seq):
            self.longest_seq = ret
        return ret


class TestLongestIncreaseSubSequence(unittest.TestCase):
    def test_get_global_longest_sequence(self):
        nodes = []
        for i in range(10):
            nodes.append(TreeNode.TreeNode(i))

        #       0
        #   /        \
        #   5         8
        #  / \       /
        # 6   4     2
        # \     \    \
        #  7     3    9
        test_cases = [
            {
                'name':  'node is none',
                'root':  None,
                'ret': []
            },
            {
                'name':  'node does not have left/right child',
                'root':  nodes[9],
                'ret': [9]
            },
            {
                'name':  'root 0 ',
                'root':  nodes[0],
                'ret': [0, 5, 6, 7]
            },
            {
                'name':  'root 8',
                'root':  nodes[8],
                'ret': [2, 9]
            },
        ]

        nodes[0].left = nodes[5]
        nodes[0].right = nodes[2]
        nodes[5].right = nodes[6]
        nodes[6].right = nodes[7]
        nodes[8].left = nodes[2]
        nodes[2].right = nodes[9]

        for tc in test_cases:
            lis = LongestIncreaseSubSequence()
            seq = lis.get_global_longest_sequence(tc['root'])
            self.assertEqual(','.join(str(x) for x in seq), ','.join(str(x) for x in tc['ret']), tc['name'])

