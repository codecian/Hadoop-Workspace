package q2_most_rated_videos_top10;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Q2_Mapper extends Mapper <LongWritable,Text,Text,FloatWritable>{

	@Override
	protected void map(LongWritable key, Text value,Context context)
			throws IOException, InterruptedException {
		
		String line [] = value.toString().split("\t");
		
		context.write(new Text(line[0]),new FloatWritable(Float.parseFloat(line[6])) );
	}

}
