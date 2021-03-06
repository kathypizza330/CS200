
public class Queue implements QueueIF{
	private LinkedList queueLL;
	boolean debug;
	
	public Queue(boolean debug){
		queueLL = new LinkedList();
		this.debug = debug;
	}
	
	public static void main(String[] args) throws QueueException{
		Queue intQueue = new Queue(true);
		System.out.println("create Queue of Integers 12 34 56");
		intQueue.enqueue(new Integer(12));	
		intQueue.enqueue(new Integer(34));
		intQueue.enqueue(new Integer(56));
				
		if(intQueue.debug)
			System.out.println("intQueue: " + intQueue.queueLL.toString());	
		Object o12 = intQueue.dequeue();
		if(intQueue.debug)
			System.out.println("o12: " + o12);		
		Object o34 = intQueue.dequeue();
		if(intQueue.debug){
			System.out.println("o34: " + o34);		
			System.out.println("intQueue: " + intQueue.queueLL.toString() + 
				           ", size: " + intQueue.size());
		}
		intQueue.dequeue();
		// test Exception handling
		intQueue.dequeue();
	}

	public void enqueue(Object token) {
		queueLL.add(token);
		
	}

	public Object dequeue() throws QueueException {
//		if (queueLL.size() <= 0)
//            throw new IndexOutOfBoundsException(
//                            "Empty queue.");
		Object result = queueLL.get(0);
		queueLL.remove(0);
		return result;
	}

	public int size() {
		return queueLL.size();
	}


}
