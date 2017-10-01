class TreeNode:
    def __init__(self, value):
        self.value = value
        self.left = None
        self.right = None

    def __str__(self):
        left = str(self.left) if self.left is not None else ''
        right = str(self.left) if self.righ is not None else ''
        return "%s, %s, %s".format(left, self.value, right)
