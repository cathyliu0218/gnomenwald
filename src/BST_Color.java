/**
 * A binary search tree on string color
 * 
 */
public class BST_Color {

   TreeNode root; // The root of the BST

   /**
    * Constructor
    */
   public BST_Color() {
      root = null;
   }
   
	public boolean isEmpty() {// returns true if empty
		return root == null;
	}
	
   /**
    * Insert: first checks if tree is empty
    */
   public void insert(Gnome g) {
		if (isEmpty())
			root = new TreeNode(g);
		else {
			insert(g, this.root);
		}
	}

   private void insert(Gnome n, TreeNode t) {
      int compare = n.getColor().compareTo(t.getData().getColor());
      if (compare < 0)  {
    	  	if (t.hasLeft())
    	  		insert(n, t.getLeft());
    	  	else 
    	  		t.setLeft(new TreeNode(n));
      }
      else if (compare > 0) {
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
   public Llist<TreeIterator> findColor(String n) { 
		return findColor(n, root); 
	}
   
   /**
    * Find Recursive method 
    */
   
   public Llist<TreeIterator> all = new Llist<TreeIterator>();
	public Llist<TreeIterator> findColor(String n, TreeNode t) {
		
		int compare = n.compareTo(t.getData().getColor());
		if (n.equals(t.getData().getColor())) {
			TreeIterator it = new TreeIterator(t); 
			all.append(it);	
			if(t.getLeft()!=null)   
			    findColor(n, t.getLeft());
			
			if(t.getRight()!=null)
				findColor(n, t.getRight());
				
		}
		else if (compare <0) {
			
			if (t.getLeft() != null)
				return findColor(n, t.getLeft());
			else {
				return all;
			}
				
		} 
		else if (compare >0) {
		
			if (t.getRight() != null)
				return findColor(n, t.getRight());
			else {
				return all;
			}
		}
		return all;
	}
	public class TreeIterator {
		private TreeNode me;
		
		
		/**
		* TreeIterator Constructor
		*/
		public TreeIterator(TreeNode t) {
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