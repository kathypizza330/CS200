import java.util.ArrayList;

public class Stack implements StackIF {

	// Use opStack to push and pop operators
	private ArrayList<String> opStack = new ArrayList<String>(0);
	
	// debug controls debug reporting 
    private boolean debug;
    
    public Stack(boolean debug){
    	this.debug = debug;
    }


    public static void main(String[] args) throws StackException{
    	Stack s = new Stack(true);
    	//s.push("hi");
    	System.out.println(s.peek());
    	s.pop();
    }


	@Override
	public void push(String op) {
		String before = op;
		if (opStack.size()!=0){
			for (int i = 0; i<opStack.size(); i++){
				String temp = opStack.get(i);
				opStack.set(i, before);
				before = temp;
			}
		}
		opStack.add(before);
	}


	@Override
	public String pop() throws StackException {
		String first = opStack.get(0);
		for (int i = 0; i<opStack.size()-1; i++){
			String temp = opStack.get(i+1);
			opStack.set(i, temp);
		}
		opStack.remove(opStack.size()-1);
		return first;
	}


	@Override
	public String peek() throws StackException {
		return opStack.get(0);
	}


	@Override
	public boolean isEmpty() {
		return opStack.isEmpty();
	}
}
