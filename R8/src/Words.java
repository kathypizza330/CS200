
public class Words {
    public static void main(String[] args) {
    	
    	// 1: Draw the class hierarchy
    	// which instance variables are defined where?
    	// which methods are overriden?
    	
        Dictionary webster = new Dictionary("Webster",100000);
        Book limbo = new Book("Alice in Wonderland");
        Book dosto = new Book("The brothers Karamazov");

        /*
        // 2: the 
        //    return this + ", a good book";
        // in Book's motto method refers to which toString method?*/
        System.out.println(limbo.motto()); 
        
        
        
        
        /*
        // 3: the 
        //    return this + ", a good book";
        // in Book's motto method refers to which toString method?*/
        System.out.println(webster.motto()); 
        
        
        
        /*
        // 4: Is this valid? If not, can it be fixed and how?*/
        //YES
        limbo = webster;
        // What will be printed?  Which toString is called?
        System.out.println(limbo);
        
                
                
        /*
         // 5: Is this valid? If not, can it be fixed and how?*/
         //NO
        if (limbo instanceof Dictionary){
        Dictionary d1 = (Dictionary) limbo;
 
		// When fixed, what will be printed?
         System.out.println(d1.motto());}
        
        
        
        /*
         // 6: Is this valid? If not, can it be fixed and how?*/
        // NO, can't be fixed
        // Dictionary d2 = (Dictionary)dosto;
         //System.out.println(d2);
        
      }
}
