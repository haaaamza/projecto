import java.lang.Math.*;

class ExpressionTree {
    private String value;
    private ExpressionTree leftChild, rightChild, parent;
    
    ExpressionTree() {
        value = null; 
        leftChild = rightChild = parent = null;
    }
    
    // Constructor
    /* Arguments: String s: Value to be stored in the node
                  ExpressionTree l, r, p: the left child, right child, and parent of the node to created      
       Returns: the newly created ExpressionTree               
    */
    ExpressionTree(String s, ExpressionTree l, ExpressionTree r, ExpressionTree p) {
        value = s; 
        leftChild = l; 
        rightChild = r;
        parent = p;
    }
    
    /* Basic access methods */
    String getValue() { return value; }

    ExpressionTree getLeftChild() { return leftChild; }

    ExpressionTree getRightChild() { return rightChild; }

    ExpressionTree getParent() { return parent; }


    /* Basic setting methods */ 
    void setValue(String o) { value = o; }
    
    // sets the left child of this node to n
    void setLeftChild(ExpressionTree n) { 
        leftChild = n; 
        n.parent = this; 
    }
    
    // sets the right child of this node to n
    void setRightChild(ExpressionTree n) { 
        rightChild = n; 
        n.parent=this; 
    }
    

    // Returns the root of the tree describing the expression s
    // Watch out: it makes no validity checks whatsoever!
    ExpressionTree(String s) {
        // check if s contains parentheses. If it doesn't, then it's a leaf
        if (s.indexOf("(")==-1) setValue(s);
        else {  // it's not a leaf

            /* break the string into three parts: the operator, the left operand,
               and the right operand. ***/
            setValue( s.substring( 0 , s.indexOf( "(" ) ) );
            // delimit the left operand 2008
            int left = s.indexOf("(")+1;
            int i = left;
            int parCount = 0;
            // find the comma separating the two operands
            while (parCount>=0 && !(s.charAt(i)==',' && parCount==0)) {
                if ( s.charAt(i) == '(' ) parCount++;
                if ( s.charAt(i) == ')' ) parCount--;
                i++;
            }
            int mid=i;
            if (parCount<0) mid--;

        // recursively build the left subtree
            setLeftChild(new ExpressionTree(s.substring(left,mid)));
    
            if (parCount==0) {
                // it is a binary operator
                // find the end of the second operand.F13
                while ( ! (s.charAt(i) == ')' && parCount == 0 ) )  {
                    if ( s.charAt(i) == '(' ) parCount++;
                    if ( s.charAt(i) == ')' ) parCount--;
                    i++;
                }
                int right=i;
                setRightChild( new ExpressionTree( s.substring( mid + 1, right)));
        }
    }
    }


    // Returns a copy of the subtree rooted at this node... 2014
    ExpressionTree deepCopy() {
        ExpressionTree n = new ExpressionTree();
        n.setValue( getValue() );
        if ( getLeftChild()!=null ) n.setLeftChild( getLeftChild().deepCopy() );
        if ( getRightChild()!=null ) n.setRightChild( getRightChild().deepCopy() );
        return n;
    }
    
    // Returns a String describing the subtree rooted at a certain node.
    public String toString() {
        String ret = value;
        if ( getLeftChild() == null ) return ret;
        else ret = ret + "(" + getLeftChild().toString();
        if ( getRightChild() == null ) return ret + ")";
        else ret = ret + "," + getRightChild().toString();
        ret = ret + ")";
        return ret;
    } 


    // Returns the value of the expression rooted at a given node
    // when x has a certain value
    double evaluate(double x) {
    	double ret=0;
	if (getLeftChild()==null){
		if (getValue().equals("x")){
			ret=x;
			return ret;
		}else if (!(getValue().equals("x"))){
			ret= Double.parseDouble(getValue());
			return ret;
		}	
		
	}else if (!(getLeftChild()==null)){
	if (getRightChild()==null){
			String com = getValue();
			double var1=getLeftChild().evaluate(x);
			double var2=getRightChild().evaluate(x);
		
					if (com.equals("minus")){
						ret=var1-var2;
					}else if (com.equals("add")){
						ret=var1+var2;
					}else if (com.equals("mult")){
						ret=var1*var2;
					}else if (com.equals("cos")){
						ret=Math.cos(var1);
					}else if (com.equals("sin")){
						ret=Math.sin(var1);
					}else if (com.equals("exp")){
						ret=Math.exp(var1);
					}else {
						ret=0;
					}
						return ret;
						}else {
						String com = getValue();
						double var1=getLeftChild().evaluate(x);
						if (com.equals("minus")){
							ret=var1;
						}else if (com.equals("add")){
							ret=var1;
						}else if (com.equals("mult")){
							ret=var1;
						}else if (com.equals("cos")){
							ret=Math.cos(var1);
						}else if (com.equals("sin")){
							ret=Math.sin(var1);
						}else if (com.equals("exp")){
							ret=Math.exp(var1);
						}else {
							ret=0;
						}
					return ret;	
					}
		}
	return ret;
    }                                                 

    /* returns the root of a new expression tree representing the derivative of the
       original expression */
    ExpressionTree differentiate() {
    	ExpressionTree ret= new ExpressionTree();
    	
    	if	((getValue()!=null)){
    		if(getValue().equals("x")) ret.setValue("1");
    		else if (getValue().equals("minus")||(getValue().equals("add"))){
    			ret.setValue(getValue());
    			ret.setLeftChild(getLeftChild().differentiate());
    			ret.setRightChild(getRightChild().differentiate());
    		}else if (getValue().equals("mult")){
    			ret.setValue("add");
    			ret.setLeftChild(new ExpressionTree());
    			ret.setRightChild(new ExpressionTree());
    			ret.getLeftChild().setValue("mult");
    			ret.getRightChild().setValue("mult");
    			
    			ret.getLeftChild().setLeftChild(getLeftChild().differentiate());
    			ret.getLeftChild().setRightChild(getRightChild());	//setting left child values with new ExpressionTree
    			
    			ret.getRightChild().setRightChild(getLeftChild());
    			ret.getRightChild().setLeftChild(getRightChild().differentiate()); //setting left child values with new ExpressionTree
    		}else if (getValue().equals("sin")){
    			ret.setValue("mult");
    			ret.setLeftChild(new ExpressionTree());
    			ret.getLeftChild().setValue("cos");
    				ret.setRightChild(getLeftChild().deepCopy().differentiate());
    				ret.getLeftChild().setLeftChild(getLeftChild().deepCopy());
    		}else if (getValue().equals("cos")){
    			ret.setValue("minus");
    			ret.setLeftChild(new ExpressionTree());
    			ret.getLeftChild().setValue("0");
    			
    			ret.setRightChild(new ExpressionTree());
    				ret.getRightChild().setValue("mult");
    					ret.getRightChild().setLeftChild(new ExpressionTree());
    						ret.getRightChild().getLeftChild().setValue("sin");
    						ret.getRightChild().getLeftChild().setLeftChild(getLeftChild().deepCopy());
    					ret.getRightChild().setRightChild(getLeftChild().deepCopy().differentiate());
    						
    		}else if (getValue().equals("exp")){
    			ret.setValue("mult");
    			ret.setLeftChild(new ExpressionTree());
    			ret.getLeftChild().setValue("exp");
    			ret.setRightChild(getLeftChild().deepCopy().differentiate());
    			ret.getLeftChild().setLeftChild(getLeftChild().deepCopy());
    		}
    		
    		
    	}else ret.setValue("0");
    	
    	return ret;
       
    }
        
    
    public static void main(String args[]) {
        ExpressionTree e = new ExpressionTree("mult(add(2,x),cos(x))");
        System.out.println(e);
        System.out.println(e.evaluate(1));
        System.out.println(e.differentiate());
   
 }
}