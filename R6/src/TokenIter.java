
import java.util.Iterator;
import java.util.*;


public class TokenIter implements Iterator<String>{

	//input line to be tokenized
	private String line;

	// the next Token, null if no next Token
	private String nextToken;
	
	private ArrayList<String> token = new ArrayList<>();
	
	private int count;

	public TokenIter(String line){
		this.line = line;
		
		initialize();
	}
	
	private void initialize(){
		String s = "";
		
		for (int i = 0; i<line.length(); i++){

			char temp = line.charAt(i);
			if (temp==' '){
				continue;
			}
			switch (temp){
			case '0':case '1':case '2':case '3':case '4':case '5':case '6':case '7':case '8':case '9':
				s+=temp;
				break;
			case '+':case '-':case '*':case'/':
			case '(':case ')':
				if (s!="")
				token.add(s);
				String s0 = ""+temp;
				token.add(s0);
				s = "";
				break;
			}
			if (i==line.length()-1){
				if (temp == '0'||temp == '1'||temp == '2'||temp == '3'||temp == '4'||temp == '5'||temp == '6'||temp == '7'||temp == '8'||temp == '9')
					token.add(s);
			}
			
		}
		token.add(null);
		nextToken = token.get(0);
		count = 0;
	}


	@Override
	public void remove() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	
	public static void main(String[] args){
		String line;
		if(args.length>0)
			line = args[0];
		else
		    line = "   15*(26+37)-489/5*61-(723-8456789)  ";
		System.out.println("line: [" + line + "]");
		TokenIter tokIt = new TokenIter(line);
		while(tokIt.hasNext()){
			System.out.println("next token: [" + tokIt.next() + "]");
		}
	}


	@Override
	public boolean hasNext() {
		if (nextToken!=null){
			return true;
		}
		return false;
	}


	@Override
	public String next() {
		String next = nextToken;
		count++;
		nextToken = token.get(count);
		return next;
	}

}
