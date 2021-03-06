package pack;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Example_Reducer extends Reducer<Text,LongWritable,Text,FloatWritable>{

	@Override
	protected void reduce(Text key, Iterable<LongWritable> values,Context context)
			throws IOException, InterruptedException {
		
		int count = 0 ;
		float total = 0;
		for(LongWritable l:values)
		{
			total = total + l.get();
			count ++;
		}
		total = total / count ;
		context.write(key, new FloatWritable(total));
		
	}
	
	
	

}
