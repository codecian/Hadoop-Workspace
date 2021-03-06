package q5_top10_CustomKey;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.log4j.BasicConfigurator;

// 19:11

public class Q5_Driver_CustomKey {

	public static void main(String[] args) throws Exception
	{
		Job job = new Job();
		Configuration conf = job.getConfiguration();
		BasicConfigurator.configure();
		job.setJarByClass(q5_top10_CustomKey.Q5_Driver_CustomKey.class);
		job.setMapperClass(q5_top10_CustomKey.Mapper1.class);
		job.setReducerClass(q5_top10_CustomKey.Reducer1.class);
		job.setGroupingComparatorClass(q5_top10_CustomKey.Grouping.class);
		
		job.setMapOutputKeyClass(CustomKey.class);
		job.setMapOutputValueClass(Text.class);
	
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		FileInputFormat.addInputPath(job, new Path("hdfs://localhost:9000/youtube/0.txt"));
		FileOutputFormat.setOutputPath(job, new Path("output-top-10-CustomKey"));
		
		if(!job.waitForCompletion(true))
			return;
	}

}
