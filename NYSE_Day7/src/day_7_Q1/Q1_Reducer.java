package day_7_Q1;

import java.io.IOException;
import java.util.TreeMap;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Q1_Reducer extends Reducer<CustomKey, Text, Text, Text> {

	public void reduce(CustomKey _key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
	
		StringBuilder sb =new StringBuilder();
		int i=0;
		// process values
		for (Text val : values) {
			if(i<3)
			{
			i++;
			context.write(new Text(_key.date+" "+_key.stock), val);
			}
			else
				break;
		}
			
		}
		
	}
