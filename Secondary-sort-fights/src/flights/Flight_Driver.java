package flights;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.log4j.BasicConfigurator;

public class Flight_Driver {

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "JobName");
		BasicConfigurator.configure();
		job.setJarByClass(flights.Flight_Driver.class);
		job.setMapperClass(flights.Flight_Mapper.class);
		job.setReducerClass(flights.Flight_Reducer.class);

		// TODO: specify output types
		job.setOutputKeyClass(Flight.class);
		job.setOutputValueClass(Text.class);

		// TODO: specify input and output DIRECTORIES (not files)
		FileInputFormat.setInputPaths(job, new Path("hdfs://localhost:9000/MapJoinDelay/flight_delays1.csv"));
		FileOutputFormat.setOutputPath(job, new Path("output"));

		if (!job.waitForCompletion(true))
			return;
	}

}
