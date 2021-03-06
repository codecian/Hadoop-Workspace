/**
 * 
use the NYSE dataset with following attributes:
stockTicker � stock name or symbol   0
tradeDate       1
openingPrice    2
highPrice       3
lowPrice        4
closingPrice    5
volume          6

 *     1.Find the top3 Stock per day by volume
 * 
 */


package day_7_Q1;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Q1_Mapper extends Mapper<LongWritable, Text, CustomKey, Text> {

	CustomKey obj;
	public void map(LongWritable ikey, Text ivalue, Context context)
			throws IOException, InterruptedException {
		obj = new CustomKey();
		String line[]=ivalue.toString().split(",");
		obj.setDate(new Text(line[1]));
		obj.setStock(new Text(line[0]));
		obj.setVolume(new Text(line[6]));
		context.write(obj, new Text(line[6]));
	}

}
