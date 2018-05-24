import java.util.Hashtable;

public class Tree {
    private TreeNode root;
    
    
    //empty tree
    public Tree(){
    	this.root = null;
    }
    
    // rootItem, empty children
    public Tree(TreeNode root){
    	this.root = root;
    }
    
    public boolean isEmpty(){
    	return root==null;
    }
    
    public void preorderTraverse(){
    	if (!isEmpty())
    		preorderTraverse(root,"");
    	else
    		System.out.println("root is null");
    }
    
	public void preorderTraverse(TreeNode node, String indent){
		System.out.println(indent+node.getItem());
		if(node.getLeft()!=null) preorderTraverse(node.getLeft(),indent+" ");
		if(node.getRight()!=null) preorderTraverse(node.getRight(),indent+" ");	
	}
	
	public static void main(String[] args){
		TreeNode l1 = new TreeNode("12");
		TreeNode l2 = new TreeNode("345");
		TreeNode s1 = new TreeNode("+",l1,l2);
		TreeNode l3 = new TreeNode("67");
		TreeNode r  = new TreeNode("+",s1,l3);
		
		Tree T = new Tree(r);
		//System.out.println(T.postorderEval());
	}
	
    // if tree empty return null
    // else evaluate the tree by postorder traversal 
    // and return its value
    public Integer postorderEval(TreeNode node, String indent, Hashtable symTab){
    	if (node == null) return 0;
    	if (node.getLeft()!=null) postorderEval(node.getLeft(), indent+" ", symTab);
    	if (node.getRight()!=null) postorderEval(node.getRight(), indent+" ", symTab);
    	if (node.getItem().equals("+")){
    		return Integer.valueOf(postorderEval(node.getLeft(), indent+" ", symTab) + postorderEval(node.getRight(), indent+" ", symTab));
    	}
    	else if (node.getItem().equals("-")){
    		return Integer.valueOf(postorderEval(node.getLeft(), indent+" ", symTab) - postorderEval(node.getRight(), indent+" ", symTab));
    	}
    	else if (node.getItem().equals("*")){
    		return Integer.valueOf(postorderEval(node.getLeft(), indent+" ", symTab) * postorderEval(node.getRight(), indent+" ", symTab));
    	}
    	else if (node.getItem().equals("/")){
    		return Integer.valueOf(postorderEval(node.getLeft(), indent+" ", symTab) / postorderEval(node.getRight(), indent+" ", symTab));
    	}
    	else if ((node.getItem().charAt(0)>= 'A'&&node.getItem().charAt(0)<= 'Z')||(node.getItem().charAt(0)>= 'a'&&node.getItem().charAt(0)<= 'z')){
    		return (Integer) symTab.get(node.getItem());
    	}
    	else
		return Integer.parseInt(node.getItem());
		
	}
}