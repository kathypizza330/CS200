import java.util.Hashtable;

public class Equation {

	private boolean debug;

	private String nextToken;
	private TokenIter itTokens;

	Tree exprTree;

	private void nextTok(String indent) {
		if (itTokens.hasNext())
			nextToken = itTokens.next();
		else
			nextToken = null;
		// if (nextToken!= null && nextToken.equals(")")){
		// nextTok(indent);}
		if (debug)
			System.out.println(indent + "next token: " + nextToken);
	}

	// equation = identifier "=" expression
	public Equation(TokenIter iter, boolean debug) {
		itTokens = iter;
		this.debug = debug;
		// put the first token from iter in nextToken
		// initial indent ""
		nextTok("");
	}

	// expr = term(( "+" | "-" )term)*
	private TreeNode expr(String indent) throws ParseException {
		if (debug)
			System.out.println(indent + "expr");

		TreeNode left = term(indent + " ");
		while (nextToken != null && (nextToken.equals("-") || nextToken.equals("+"))) {
			String op = nextToken;
			nextTok(indent + " ");
			TreeNode right = term(indent + " ");
			left = new TreeNode(op, left, right);

		}
		return left;
	}

	// term = factor(( "*" | "/" )factor)*
	private TreeNode term(String indent) throws ParseException {
		if (debug)
			System.out.println(indent + "term");
		TreeNode left = factor(indent + " ");
		while (nextToken != null && ((nextToken.equals("*") || nextToken.equals("/")))) {
			String op = nextToken;
			nextTok(indent + " ");
			TreeNode right = factor(indent + " ");
			left = new TreeNode(op, left, right);
		}

		return left;
	}

	// factor = "-" factor | identifier | number | "(" expr ")"
	private TreeNode factor(String indent) throws ParseException {
		if (debug)
			System.out.println(indent + "factor");
		TreeNode left = null;
		// "-" factor
		if (nextToken != null && nextToken.equals("-")) {
			String op = nextToken;
			nextTok(indent + " ");
			TreeNode right = factor(indent + " ");
			left = new TreeNode(op, left, right);
		}

		// number
		else if (nextToken != null && Character.isDigit(nextToken.charAt(0))) {
			return number(indent + " ");
		}

		// "(" expr ")"
		else if (nextToken != null && nextToken.equals("(")) {
			nextTok(indent + " ");
			left = expr(indent + " ");
			nextTok(indent + " ");
		}

		// identifier
		else {
			return identifier(indent + " ");
		}

		return left;
	}

	// identifier = [A-Za-z]([A-Za-z0-9])*
	private TreeNode identifier(String indent) throws ParseException {
		if (debug)
			System.out.println(indent + " identifier");
		TreeNode left = null;
		if (nextToken != null && ((nextToken.charAt(0) >= 'A' && nextToken.charAt(0) <= 'Z')
				|| (nextToken.charAt(0) >= 'a' && nextToken.charAt(0) <= 'z'))) {
			left = new TreeNode(nextToken);
			nextTok(indent);
		} else {
			error("Identifier expected");
		}
		if (nextToken != null && ((nextToken.charAt(0) >= 'A' && nextToken.charAt(0) <= 'Z')
				|| (nextToken.charAt(0) >= 'a' && nextToken.charAt(0) <= 'z')
				|| (nextToken.charAt(0) >= '0' && nextToken.charAt(0) <= '9'))) {
			String id = nextToken;
			nextTok(indent);
			TreeNode right = null;
			left = new TreeNode(id, left, right);
		}
		return left;
	}

	private TreeNode number(String indent) throws ParseException {
		if (debug)
			System.out.println(indent + " number");
		if (nextToken != null && Character.isDigit(nextToken.charAt(0))) {
			String num = nextToken;
			nextTok(indent);
			return new TreeNode(num);
		} else {
			error("number expected");
			return null; // for java type checker
		}
	}

	private void error(String errMess) throws ParseException {
		throw new ParseException(errMess);

	}

	// line parses a line: lhs "=" expr
	// lhs is an identifier
	// and stores an IdVal (lhs, value) in the symbol table
	public String line(Hashtable<String,Integer> symbolTable) throws ParseException {
		// BSTNode root;
		exprTree = null;
		if (nextToken == null)
			return null;
		else {
			if (debug)
				System.out.println("line");
			// scan left hand side String lhs
			// if it is not an identifier
			// call error("Identifier expected");
			TreeNode lhs = identifier("");
			if (lhs != null) {
				if (nextToken.equals("=")) {
					nextTok(" ");
					TreeNode rhs = expr("");
					exprTree = new Tree(rhs);
					int value = exprTree.postorderEval(rhs, "", symbolTable);
					symbolTable.put(lhs.getItem(), value);

					if (debug)
						System.out.println("retrieving " + lhs + ": " + symbolTable.get((lhs.getItem())));

					if (nextToken != null) {
						error("end of line expected");
						return null;
					} else
						return lhs.getItem();
				} else {
					error("= expected");
					return null;
				}
			} else {
				error("unexpected end of line");
				return null;
			}
			// if no next token
			// call error("unexpected end of line");
			// scan "="
			// if not "=" call error("= expected");
			// parse expression and create expression tree
			// evaluate Expression tree
			// create Symbol lhsVal (lhs, value)
			// insert lhsVal in symbolTable
		}
	}
}
