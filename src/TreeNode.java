public class TreeNode {
	private Gnome data;
	private TreeNode left, right, parent;

	public TreeNode(Gnome p) {
		this.data = p;
	}

	public Gnome getData() {
		return this.data;
	}

	public void setData(Gnome p) {
		this.data = p;
	}

	public TreeNode getLeft() {
		return this.left;
	}

	public TreeNode getRight() {
		return this.right;
	}

	public TreeNode getParent() {
		return this.parent;
	}

	public void setLeft(TreeNode L) {
		this.left = L;
	}

	public void setRight(TreeNode R) {
		this.right = R;
	}

	public boolean hasLeft() {
		return this.left != null;
	}

	public boolean hasRight() {
		return this.right != null;
	}
}