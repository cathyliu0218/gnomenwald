/**
 * A binary search tree on int VIP
 * 
 */
public class BST_VIP {
	private TreeNode root;

	/**
	* Constructor
	*/
	public BST_VIP() {
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
		if (n.getVIP() < t.getData().getVIP()) {// if Gnome's VIP<root's VIP
			// Gnome goes left
			if (t.hasLeft())
				insert(n, t.getLeft());
			else
				t.setLeft(new TreeNode(n));
		} 
		else if (n.getVIP() > t.getData().getVIP()) {// if Gnome's id>root's id
				// Gnome goes right
			if (t.hasRight())
				insert(n, t.getRight());
			else
				t.setRight(new TreeNode(n));
		}
		else {
	  	  	if (t.hasRight())
		  		insert(n, t.getRight());
		  	else 
		  		t.setRight(new TreeNode(n));
	      }
	}
	/**
	* Find helper method: return null if not found and a new TreeIterator if found
	*/
	public Llist<TreeIterator> findVIP(int n) { 
		return findVIP(n, root); 
	}
	
	/**
	* Find Recursive method 
	*/
	public Llist<TreeIterator> all = new Llist<TreeIterator>();
	public Llist<TreeIterator> findVIP(int n, TreeNode t) {// recursive method
		if (n == t.getData().getVIP()) {
			TreeIterator it = new TreeIterator(t); 
			all.append(it);	
			if(t.getLeft()!=null)   
			    findVIP(n, t.getLeft());
		
			if(t.getRight()!=null)
				findVIP(n, t.getRight());
		}	
		else if (n < t.getData().getVIP()) {	
			if (t.getLeft() != null)
				return findVIP(n, t.getLeft());
			else {
				return all;
			}
		} 
		else if (n > t.getData().getVIP()){
			if (t.getRight() != null)
				return findVIP(n, t.getRight());
			else {
				return all;
			}
		}
		return all;
	}

	public class TreeIterator {
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
