import java.util.ArrayList;

public class Stack implements StackIF{

	private ArrayList<Object> theStack;
	
	public Stack(){
		// initialize theStack
		theStack = new ArrayList<Object>();
	}
	
	@Override
	/*
	 * push op on Run Time Stack at end of theStack
	 */
	public void push(Object op) {
		Object before = op;
		if (theStack.size() != 0){
			for (int i = 0; i<theStack.size(); i++){
				Object temp = theStack.get(i);
				theStack.set(i, before);
				before = temp;
			}
		}
		theStack.add(before);
	}

	@Override
	/*
	 * pop and return top Object from Run Time Stack
	 * If Stack empty, throw StackException("Popping from empty stack!");
	 */
	public Object pop() throws StackException {
		Object popped = theStack.get(0);
		for (int i = 0; i<theStack.size()-1; i++){
			
			Object temp = theStack.get(i+1);
			theStack.set(i, temp);
		}
		theStack.remove(theStack.size()-1);
		return popped;
	}

	@Override
	public boolean isEmpty() {
		return theStack.isEmpty();
	}
	
	// return the String representation of theStack
	public String toString(){
		if (theStack.size()==0)
			return "[]";
		String s = "[";
		for (int i = theStack.size()-1; i>0;i--)
		{
			s += theStack.get(i).toString()+',' +' ';
		}
		s+=theStack.get(0).toString();
		s+=']';
		return s;
	}
	
	public static void main(String[] args) throws StackException{
       Stack st = new Stack();
       st.push(new Frame(1,3,1,3));
       st.push(new Frame(0,2,1,2));
       System.out.println("st: " + st);
       st.pop();
       System.out.println("st: " + st);
       st.pop();
       System.out.println("st: " + st);
	}
       
}
