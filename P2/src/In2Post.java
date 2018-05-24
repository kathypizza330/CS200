import java.util.ArrayList;
import java.util.Scanner;

public class In2Post {
	// Use expQueu to create the postfix expression
	// enqueue at the end of the queue
	// dequeue at the front of the queue
	private Queue expQueue;

	// opStack maintains operators
	private Stack opStack;

	// tokIt gets the input line in the constructor and scans it for tokens
	private TokenIter tokIt;
	
	// when scanning a next Token using tokIt, put it in nextToken
	private String nextToken;
	
	public In2Post(String line, boolean debug){
		tokIt = new TokenIter(line);
		opStack  = new Stack(debug);
		expQueue = new Queue(debug);
	}

	// Convert Infix to Postfix using expQueue and opStack
	// tokIt iteratively produces the tokens of the infix expression
	// return a String containing the Postfix expression
	public String convertIn2Post() throws StackException, QueueException{
		String result = "";
		while (tokIt.hasNext()){
			String s = tokIt.next();
			
			switch (s){
			
			case "+":case "-":
				if (opStack.isEmpty()||opStack.peek().equals("("))
					opStack.push(s);
				else{
					while (!opStack.isEmpty()){
						if (opStack.peek().equals("("))
							break;
						else{
							String temp = opStack.pop();
							expQueue.enqueue(temp);
						}
					}
					opStack.push(s);
				}
				break;
			case "*":case"/":
				if (opStack.isEmpty()||opStack.peek().equals("(")){
					opStack.push(s);
				}
				else if (opStack.peek().equals("*") || opStack.peek().equals("/")){
					while (!opStack.isEmpty()){
						if (opStack.peek().equals("(")||opStack.isEmpty()||opStack.peek().equals("+")||opStack.peek().equals("-"))
							break;
						else{
							String temp = opStack.pop();
							expQueue.enqueue(temp);
						}
					}
					opStack.push(s);
				}
				else
					opStack.push(s);
				break;

				
			case "(":
				opStack.push(s);
				break;
			case ")":
				while(!opStack.peek().equals("(")){
					String temp = opStack.pop();
					expQueue.enqueue(temp);
				}
				opStack.pop();
				break;
			//case "0":case "1":case "2":case "3":case "4":case "5":case "6":case "7":case "8":case "9":
				default:
				expQueue.enqueue(s);
				break;
			}
		}
		while (!opStack.isEmpty()){
			String temp = opStack.pop();
			expQueue.enqueue(temp);
			
			}
		while (expQueue.size()!=0){
			result+=expQueue.dequeue()+" ";
			
		}
		return result;
	}
	
	public static void main(String[] args) throws StackException, QueueException{
		// exercise with increasingly complex expressions
		boolean db = false;
		In2Post ex1 = new In2Post("1", db);
		System.out.println(ex1.convertIn2Post());
		In2Post ex2 = new In2Post("1/(2+3)", db);
		System.out.println(ex2.convertIn2Post());

	}
}
