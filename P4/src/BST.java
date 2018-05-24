
public class BST {
	// this Binary Search Tree is used for the implementation of the
	// Symbol Table containing Symbols: (id,value) pairs
	// A Symbol is a Comparable object containing a String Identifier
	// and a Boolean value
	private BSTNode<Symbol> root;

	// empty tree
	public BST() {
		this.root = null;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public void insertItem(Symbol item) throws BSTException {
		root = insertItem(root, item);
	}

	private BSTNode<Symbol> insertItem(BSTNode<Symbol> node, Symbol item) throws BSTException {
		BSTNode<Symbol> newSubTree;
		if (node == null) {
			newSubTree = new BSTNode<Symbol>(item);
			return newSubTree;
		}

		Symbol tok = node.getItem();
		if (item.getKey().compareTo(tok.getKey()) < 0) {
			newSubTree = insertItem(node.getLeft(), item);
			node.setLeft(newSubTree);
			return node;
		}
		if (item.getKey().compareTo(tok.getKey()) > 0) {
			newSubTree = insertItem(node.getRight(), item);
			node.setRight(newSubTree);
			return node;
		}
		// ERROR: inserting existing item
		else
			throw new BSTException("Inserting item with existing key!");
	}

	public Symbol retrieveItem(String key) {
		return retrieveItem(root, key);
	}

	private Symbol retrieveItem(BSTNode<Symbol> node, String key) {
		Symbol treeItem;

		if (node == null)
			treeItem = null;
		else {
			Symbol nodeItem = node.getItem();
			if (key.compareTo(nodeItem.getKey()) == 0)
				// found
				treeItem = nodeItem;
			else if (key.compareTo(nodeItem.getKey()) < 0)
				// search left
				treeItem = retrieveItem(node.getLeft(), key);
			else
				// search right
				treeItem = retrieveItem(node.getRight(), key);
		}
		return treeItem;

	}

	public void preorderTraverse() {
		if (!isEmpty())
			preorderTraverse(root, "");
		else
			System.out.println("root is null");
	}

	public void preorderTraverse(BSTNode<Symbol> node, String indent) {
		System.out.println(indent + node.getItem());
		if (node.getLeft() != null) {
			System.out.println(indent + "left");
			preorderTraverse(node.getLeft(), indent + " ");
		}

		if (node.getRight() != null) {
			System.out.println(indent + "right");
			preorderTraverse(node.getRight(), indent + " ");
		}
	}

//	public void deleteItem(String value) {
//		if (root == null)
//			return;
//		else {
//			if (root.getItem().getKey().equals(value)) {
//				BSTNode auxRoot = new BSTNode(0);
//				auxRoot.setLeft(root);
//				int val = root.getItem().getVal();
//				remove(val, auxRoot, root);
//				root = auxRoot.getLeft();
//				return;
//			} else {
//				remove(val, null, root);
//			}
//		}
//	}
//
//	private boolean remove(int value, BSTNode<Symbol> parent, BSTNode<Symbol> thisRoot) {
//		if (value < thisRoot.getItem().getVal()) {
//			if (thisRoot.getLeft() != null)
//				return remove(value, thisRoot, thisRoot.getLeft());
//			else
//				return false;
//		} else if (value > thisRoot.getItem().getVal()) {
//			if (thisRoot.getRight() != null)
//				return remove(value, thisRoot, thisRoot.getRight());
//			else
//				return false;
//		} else {
//			if (thisRoot.getLeft() != null && thisRoot.getRight() != null) {
//				int val = minValue(thisRoot.getRight());
//				remove(val, thisRoot, thisRoot.getRight());
//			} else if (parent.getLeft().equals(thisRoot)) {
//				if (thisRoot.getLeft() != null)
//					parent.setLeft(thisRoot.getLeft());
//				else
//					parent.setLeft(thisRoot.getRight());
//			} else if (parent.getRight().equals(thisRoot)) {
//				if (thisRoot.getLeft() != null)
//					parent.setRight(thisRoot.getLeft());
//				else
//					parent.setRight(thisRoot.getRight());
//			}
//			return true;
//		}
//	}
//
//	public int minValue(BSTNode<Symbol> parent) {
//		if (parent.getLeft() == null)
//			return parent.getItem().getVal();
//		else
//			return minValue(parent.getLeft());
//	}
}
