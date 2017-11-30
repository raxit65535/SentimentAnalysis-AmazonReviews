package com.neu.book;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.io.Text;

public class DataClean {
	public static void main(String a[]) throws IOException {

		String path = "/home/raxit/eclipse-workspace/dataset/ProductReviews.csv";
		File file = new File(path);
		BufferedReader br = new BufferedReader(new java.io.FileReader(file));
		


		File facurl = new File("/home/raxit/eclipse-workspace/dataset/new-ProductReviews.csv");
		FileWriter fw = new FileWriter(facurl);
		BufferedWriter bw = new BufferedWriter(fw);
		int raxit = 0;

		try {

			String line;
			while ((line = br.readLine()) != null) {
				
				String xline = line.toString().replaceAll("\",","\"æ");
		        String arr[] = xline.split("æ");
		        //System.out.println(line);
				// facultyprofileurl = br.readLine();
				// System.out.println(facultyprofileurl);

				String p = "", at = "", rev = "", r ="";
				//String arr[] = line.split(",");
				p = arr[0].replaceAll("^\"|\"$|[-+.^:,]", "");
				p = p.replace("\"", "");
				
				at = arr[1].replaceAll("^\"|\"$ |[-+.^:,]","");
						at = at.replace("\"", "");
						
						rev = arr[2].replaceAll("^\"|\"$ |[-+.^:,]", "");
				
				rev = rev.replaceAll("^\"|\"$ |[-+.^:,]", "");
				String c = Integer.toString(raxit)+"yes";
				
				String newrev = rev + c;
				newrev = newrev.replaceAll("^\"|\"$ |[-+.^:,]","");
				newrev = newrev.replace("\"", "");
				
				r = arr[3].replaceAll("^\"|\"$ |[-+.^:,]", "");
				r = r.replace("\"", "");
				//arr[4] = rev;	
				
			
				//System.out.println(p);
				
				//String newline = Arrays.toString(arr);
				String newline = "\""+p+"\""+","+"\""+at+"\""+","+"\""+newrev+"\""+","+r;

				System.out.println(newline); 
				bw.write(newline); 
				bw.newLine(); 
				raxit = raxit+1;
				 

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
