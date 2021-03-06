package map_side_join_practise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Mapper1 extends Mapper<LongWritable, Text, Text, Text>{

	private HashMap<String, String> hm = new HashMap<String, String>();
	@Override
	protected void map(LongWritable key, Text value,
			org.apache.hadoop.mapreduce.Mapper.Context context)
			throws IOException, InterruptedException {
		String s1="";
		String arr[]=value.toString().split(",");
		try
		{
		if(arr[17].equals("SFO"))
		{
		int year=Integer.parseInt(arr[0]);
		int month=Integer.parseInt(arr[1]);
		int date=Integer.parseInt(arr[2]);
		s1=s1+year+""+month+""+date;
		if(hm.containsKey(s1))
		{
		context.write(value,new Text(hm.get(s1)));
		}
		}
		}
		catch(Exception e)
		{
			e.getMessage();
		}
		
	}

	@Override
	protected void setup(Context context)
			throws IOException, InterruptedException {
		
		Configuration conf = context.getConfiguration();
		FileSystem fs = FileSystem.getLocal(conf);
		
		Path df[]=DistributedCache.getLocalCacheFiles(conf);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(df[0])));
		
		String s="";
		try
		{
		while((s=br.readLine())!=null)
		{
			String arr[]=s.split(",");
			int year=Integer.parseInt(arr[1]);
			int month=Integer.parseInt(arr[2]);
			int date=Integer.parseInt(arr[3]);
			System.out.println(year+""+month+""+date);
			hm.put(year+""+month+""+date,arr[4]+","+arr[5]+","+arr[6]);
		}
		}
		catch(Exception e)
		{
			e.getMessage();
		}
	}

}
