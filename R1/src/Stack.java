import java.util.ArrayList;
//Stack.java
//Class: cs200


public class Stack {
    private ArrayList<Character> list;
    
    //constructor
    public Stack()
    {
    	list=new ArrayList<Character>();
    }
	
    // returns true if the stack is empty otherwise it returns false.
	public boolean isEmpty()
	{
		return list.isEmpty();
	}
	
	public void push(char toPush)
	{
		//TODO: This method should push the character: toPush to the top of the stack.
		char before = toPush;
		if (list.size() != 0){
			for (int i = 0; i<list.size(); i++){
				char temp = list.get(i);
				list.set(i, before);
				before = temp;
			}
		}
		list.add(before);
	
	}
	
	public char pop()
	{
		//TODO: This method should remove the top character from the stack and return it.
		char first = list.get(0);
		for (int i = 0; i<list.size()-1; i++){
			
			char temp = list.get(i+1);
			list.set(i, temp);
		}
		list.remove(list.size()-1);
		return first;
	}
	
	public char peek()
	{
		//TODO: This method should return the top character from the stack but not remove it.
		return list.get(0);
	}
	
	public static void main(String[] args) {
		Stack s = new Stack();
		System.out.println("Pushing characters: a, b, and c onto the stack.");
		s.push('a');
		s.push('b');
		s.push('c');
		
		System.out.println("Peeking at the to character of the stack (Should be c).");
		System.out.println(s.peek());
		System.out.println("Popping all characters off the stack.");
		while(!s.isEmpty())
		{
			System.out.println(s.pop());
		}
		
		
		

	}

}
