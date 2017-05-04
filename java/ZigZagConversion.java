package Medium;

public class ZigZagConversion {
	public static void main(String[] args){
		String s = "PAYPALISHIRING";
		int numRows = 3;
		System.out.println("The ZigZag conversion of "+s+" is: "+convert(s,numRows));
	}
	
	public static String convert(String s,int numRows){
		//±©Á¦Çó½â
	    int i=0;
	    int n = s.length();
	    if(numRows==1){
	    	return s;
	    }
	    
	    StringBuffer[] out = new StringBuffer[numRows];
	    StringBuffer covstr = new StringBuffer();
	    for(int line=0;line<out.length;line++){
	    	out[line] = new StringBuffer();
	    }
	    
	    while(i<n){
	    	for(int j=0;j<2*numRows-2;j++){
	    		if(i<n){
	    			if(j<numRows){
		    			out[j].append(s.charAt(i++)); 
		    		}else{
		    			out[2*numRows-2-j].append(s.charAt(i++));
		    		}
	    		}
	    		
	    		
	    	}
	    }
	    for(int line=0;line<out.length;line++){
	    	covstr.append(out[line]);
	    }
	    return covstr.toString();
	}

}
