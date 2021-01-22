package com.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Converter {
	
	
	public void fileToCSV(String file) {		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));			
		    String line;
		    BufferedWriter bw = null;
		    int transactionNumber = 0;
		    boolean transactionStarted = false;
		    while ((line = br.readLine()) != null) {
		    	line = line.replaceAll("\\s+(?=([^\"]*\"[^\"]*\")*[^\"]*$)", ",");
		    
		    	if(line.contains("smh,[record")) {
		    		if(bw!=null)
		    			bw.close();
		    		transactionNumber++;
		    		transactionStarted = true;
		    		bw = new BufferedWriter(new FileWriter("a1_"+transactionNumber+".test_data.csv"));		    		
		    	}
				  if(transactionStarted == true && !line.contains("[record")) {
				 
				    	String a[] = line.split(",", 3);
					    	bw.append(a[1]+",");						
							bw.append(a[2].replaceAll(","," ").replaceAll("[\\[\\]]", ""));  	
					    	bw.append("\n");
			  }

		} 
		    bw.close();
		    br.close();
		    
		}catch(Exception e) {
		    e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		String file = "q1.test_data.txt"; 
		Converter obj = new Converter();
		obj.fileToCSV(file);
	}
}
