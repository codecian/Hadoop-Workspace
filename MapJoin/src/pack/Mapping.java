
/**
 * 
 * 
 * Year 0	Month 1	DayofMonth 2	DayOfWeek 3	  DepTime 4	  CRSDepTime 5	ArrTime 6	CRSArrTime 7	UniqueCarrier 8	
 * FlightNum  9  	TailNum  10	 ActualElapsedTime  11	CRSElapsedTime	  12   AirTime  13	ArrDelay 14	DepDelay 15
 * Origin 16	Dest  17	Distance 18	 TaxiIn	19   TaxiOut 20   Cancelled  21   CancellationCode 22	Diverted 23
 * CarrierDelay 24	WeatherDelay 25  NASDelay 26	SecurityDelay 27	LateAircraftDelay 28

 * 
 * 
 * 
 */


package pack;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.filecache.DistributedCache;

import java.lang.*;
import java.util.*;
import java.io.*;

public class Mapping extends Mapper<Object, Text, Text, Text> {
	
	
	private HashMap<String,String> hm = new HashMap<String,String>();
	@Override
	public void setup(Context context) throws IOException,
			InterruptedException {
		Configuration conf= context.getConfiguration();
		FileSystem fs=FileSystem.getLocal( conf);
		 Path[] dataFile = DistributedCache.getLocalCacheFiles(conf);
		    // [0] because we added just one file.
		    String str = "";
		    BufferedReader cacheReader = new BufferedReader(new InputStreamReader(fs.open(dataFile[0])));
		    // now one can use BufferedReader's readLine() to read data
			while ((str = cacheReader.readLine()) != null) {    
					String[] arr=str.split(",");
					int year=Integer.parseInt(arr[1]);
					int month=Integer.parseInt(arr[2]);
					int date=Integer.parseInt(arr[3]);
					hm.put(year+""+month+""+date,arr[4]+","+arr[5]+","+arr[6]);
				}   
	} 
	

	public void map(Object ikey ,Text ivalue, Context context)
			throws IOException, InterruptedException {
		String s1="";
		String arr[]=ivalue.toString().split(",");
		if(arr[17].equals("SFO"))
		{
		int year=Integer.parseInt(arr[0]);
		int month=Integer.parseInt(arr[1]);
		int date=Integer.parseInt(arr[2]);
		s1=s1+year+""+month+""+date;
		if(hm.containsKey(s1))
		{
		System.out.println(ivalue);
		context.write(ivalue,new Text(hm.get(s1)));
		}
		
		}
	}

}
