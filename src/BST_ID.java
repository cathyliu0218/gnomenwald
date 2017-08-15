/**
 * A binary search tree on int ID
 * 
 */
public class BST_ID {
	private TreeNode root;
	
	/**
	* Constructor
	*/
	public BST_ID() {// constructor
	}

	public boolean isEmpty() {// returns true if empty
		return root == null;
	}
	
	/**
	* Insert: first checks if tree is empty
	*/
	public void insert(Gnome p) { // helper method to insert Gnome
		if (isEmpty())
			root = new TreeNode(p);
		else {
			insert(p, this.root);
		}
	}

	private void insert(Gnome n, TreeNode t) { // recursive method to
		// insert Gnome
		if (n.getID() < t.getData().getID()) {// if Gnome's id<root's id
			// Gnome goes left
			if (t.hasLeft())
				insert(n, t.getLeft());
			else
				t.setLeft(new TreeNode(n));
		} else {// if Gnome's id>root's id
				// Gnome goes right
			if (t.hasRight())
				insert(n, t.getRight());
			else
				t.setRight(new TreeNode(n));
		}
	}

	/**
	* Find helper method: return null if not found and a new TreeIterator if found
	*/
	public TreeIterator findID(int n) { 
		return findID(n, root); 
	}
	
	/**
	* Find Recursive method 
	*/
	public TreeIterator findID(int n, TreeNode t) {
		if (n == t.getData().getID())
			return new TreeIterator(t);
		if (n < t.getData().getID()) {
			if (t.getLeft() == null)
				return null;
			else
				return findID(n, t.getLeft());
		} else {
			if (t.getRight() == null)
				return null;
			else
				return findID(n, t.getRight());
		}
	}

	public class TreeIterator {// constructor
		private TreeNode me;

		public TreeIterator(TreeNode t) {// constructor
			this.me = t;
		}

		public Gnome getData() { // getGnome
			if (me != null)
				return me.getData();
			return null;
		}

		public void goLeft() {// go left of tree
			if (me != null)
				me = me.getLeft();
		}

		public void goRight() { // go right of tree
			if (me != null)
				me = me.getRight();
		}

		public void goUp() {// go up of tree
			if (me != null && me != root)
				me = me.getParent();
		}

		public boolean hasLeft() {// hasLeft child
			return me.hasLeft();
		}

		public boolean hasRight() {// has right child
			return me.hasRight();
		}

		public boolean isRoot() {// is root
			return me == root;
		}

		public boolean isLeaf() {// is =leaf
			return !(hasLeft() || hasRight());
		}

		public int numChildren() {
			if (isLeaf())
				return 0;
			if (hasLeft() && hasRight())
				return 2;
			return 1; // since would otherwise have already returned
		}
	}
}