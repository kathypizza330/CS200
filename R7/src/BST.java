
public class BST{
	// this Binary Search Tree is used for the implementation of the 
	// Symbol Table containing Symbols: (id,value) pairs
	// A Symbol is a Comparable object containing a String Identifier  
	// and a Boolean value 
	private BSTNode<IdVal> root;

	//empty tree
	public BST(){
		this.root = null;
	}

	public boolean isEmpty(){
		return root==null;
	}

	public void insertItem(IdVal item) throws BSTException{
		root = insertItem(root, item);
	}

	private BSTNode<IdVal> insertItem(BSTNode<IdVal> node, IdVal item) throws BSTException{
		BSTNode<IdVal> newSubTree;
		if(node==null){
			newSubTree = new BSTNode<IdVal>(item);
			return newSubTree;
		}
		
		IdVal tok = node.getItem();
		if(item.getKey().compareTo(tok.getKey())<0){
			newSubTree = insertItem(node.getLeft(), item);
			node.setLeft(newSubTree);
			return node;
		}
		if(item.getKey().compareTo(tok.getKey())>0){
			newSubTree = insertItem(node.getRight(), item);
			node.setRight(newSubTree);
			return node;
		}
		// ERROR: inserting existing item
		else 
			throw new BSTException("Inserting item with existing key!");
	}

	public IdVal retrieveItem(String key){
		return retrieveItem(root,key);
	}
	
	private IdVal retrieveItem(BSTNode<IdVal> node, String key){
		IdVal treeItem;
		
		if(node==null)
			treeItem = null;
		else{
			IdVal nodeItem = node.getItem();
			if(key.compareTo(nodeItem.getKey()) == 0)
				//found
				treeItem = nodeItem;
			else if(key.compareTo(nodeItem.getKey()) < 0)
				//search left
				treeItem = retrieveItem(node.getLeft(), key);
			else
				// search right
				treeItem = retrieveItem(node.getRight(), key);
		}
		return treeItem;

	}
	

	public void preorderTraverse(){
		if (!isEmpty())
			preorderTraverse(root,"");
		else
			System.out.println("root is null");
	}

	public void preorderTraverse(BSTNode<IdVal> node, String indent){
		System.out.println(indent+node.getItem());		
		if(node.getLeft()!=null) {
			System.out.println(indent+"left");
			preorderTraverse(node.getLeft(),indent+" ");
		}

		if(node.getRight()!=null) {
			System.out.println(indent+"right");
			preorderTraverse(node.getRight(),indent+" ");
		}
	}
	
	public void deleteItem(String key){
		if (root==null) return;

		BSTNode<IdVal> right = root.getRight();
		BSTNode<IdVal> left = root.getRight();
		if (root.getItem().getKey().equals(key)){
			if (root.getLeft()==null&&root.getRight()!=null){
				root = root.getRight();
			}
			else if (root.getLeft()!=null&&root.getRight()==null){
				root = root.getLeft();
			}
			else if (root.getLeft()==null&&root.getRight()==null)
				root = null;
			else{
				if (root.getRight()!=null)
				root = findLeftmost(root.getRight());
				//root.setLeft(left);
				//root.setRight(right);
			}
		}
		else deleteItemHelper(key,root,left,right);
	}
	
	private void deleteItemHelper(String key,BSTNode<IdVal> node,BSTNode<IdVal> left,BSTNode<IdVal> right){
		if (node==null) return;
		if (left!=null&&left.getItem().getKey().equals(key)){
			if (left.getRight()!=null){
				node.setLeft(findLeftmost(left.getRight()));
				node.getLeft().setLeft(left.getLeft());
				node.getLeft().setRight(left.getRight());
			}
			else{
				node.setLeft(left.getLeft());
			}
		}
		else if (right!=null&&right.getItem().getKey().equals(key)){
			if (right.getRight()!=null){
				node.setRight(findLeftmost(right.getRight()));
				node.getRight().setLeft(right.getLeft());
				node.getRight().setRight(right.getRight());
			}
			else{
				node.setRight(right.getLeft());
			}
		}
		
		
		else{
			if (node.getRight()!=null){
				deleteItemHelper(key,node.getRight(),node.getRight().getLeft(),node.getRight().getRight());
			}
			if (node.getLeft()!=null){
				deleteItemHelper(key,node.getLeft(),node.getLeft().getLeft(),node.getLeft().getRight());
			}
		}
	}
	
	private BSTNode<IdVal> findLeftmost(BSTNode<IdVal> node){
		BSTNode<IdVal> result = null;
		if (node==null) return null;
		if (node.getLeft()!=null){
			result = findLeftmost(node.getLeft());
			String s = result.getItem().toString();
			deleteItem(s);
		}
		else result = node;
		return result;
	}

}
