import java.util.ArrayList;


public class Queue implements QueueIF {

	// maintains expression
	private ArrayList<String> expQueue = new ArrayList<String>(0);
	
	// debug controls debug reporting
	private boolean debug;
	
	// constructor
	public Queue(boolean debug){
		this.debug = debug;
	}
	
    public static void main(String[] args) throws QueueException{
    	Queue q = new Queue(false);
    	q.dequeue();
    }

	@Override
	public void enqueue(String token) {
		expQueue.add(token);
		if (debug)
			System.out.println("enqueue: " + token);
	}

	@Override
	public String dequeue() throws QueueException {
		String result = expQueue.get(0);
		expQueue.remove(0);
		if (debug)
			System.out.println("deque: " + result);
		return result;
	}

	@Override
	public int size() {
		return expQueue.size();
	}
}
