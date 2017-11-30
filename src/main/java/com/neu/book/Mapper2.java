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
public class Mapper2 extends Mapper<Object, Text, Text, IntWritable>{
    public void map(Object key,Text value,Context context) throws IOException, InterruptedException{
        String[] data = value.toString().split("\t");
        int reviewStat = Integer.parseInt(data[1]);
        if(reviewStat == 0)
            context.write(new Text("Neutral"),new IntWritable(1));
        else if(reviewStat > 0)
            context.write(new Text("Positive"),new IntWritable(1));
        else
            context.write(new Text("Negative"),new IntWritable(1));
    }
}
