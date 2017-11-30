/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.StringTokenizer;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;
import org.apache.hadoop.mapreduce.filecache.DistributedCache;

public class Mapper1 extends Mapper<Object,Text,Text,IntWritable>{
    private Text word = new Text();
	private URI[] files;
	private HashMap<String, String> AFINN_map = new HashMap<String, String>();
	
	public void setup(Context context) throws IOException
	{
		files = DistributedCache.getCacheFiles(context.getConfiguration());
		System.out.println("files:"+ files);
		Path path = new Path(files[0]);
		FileSystem fs = FileSystem.get(context.getConfiguration());
		FSDataInputStream in = fs.open(path);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line="";

		while((line = br.readLine()) != null)
		{
			String splits[] = line.split("\t");
			AFINN_map.put(splits[0], splits[1]);
		}
		br.close();
		in.close();
	}
	
    public void map(Object key,Text value,Context context) throws IOException, InterruptedException{            
        //Data Pre-processing
    	String line = value.toString().replaceAll("\",","\"æ");
        String data[] = line.split("æ");
        Text productName = new Text(data[0]);
        String reviews = new String(data[2]);
        String revRating = new String(data[3]);
        reviews = reviews+"OriginalRating::"+revRating;
        reviews = reviews.replace("\"","");
        //Sentiment Analysis
        int sentimentSum = 0;
        String words[] = reviews.split(" ");        
        if (value.toString().contains("revText")) {

        } else {
        	for(String word : words)
			{
				if(word.length() > 1 && AFINN_map.containsKey(word))
				{
					Integer s = new Integer(AFINN_map.get(word));
					sentimentSum += s;
				}
			}
        }
        context.write(new Text(reviews), new IntWritable(sentimentSum));
    }
}
