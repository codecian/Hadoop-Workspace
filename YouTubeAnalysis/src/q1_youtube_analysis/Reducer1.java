package q1_youtube_analysis;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class Reducer1 extends Reducer <Text,IntWritable,Text,IntWritable>{

	TreeMap<Integer,String> tm = new TreeMap<Integer,String>();
	private Map<String , Integer> map = new HashMap<String,Integer>();
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Context context)
			throws IOException, InterruptedException {
		int total= 0;
		for(IntWritable i:values)
		{
			total = total + i.get() ;
		}
//		tm.put(total,key.toString());
//		if(tm.size()>5)
//		{
//			tm.remove(tm.firstKey());
//		}
		map.put(key.toString(), total);
		
	}
	@Override
	protected void cleanup(Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
//		for(int i : tm.keySet())
//		{
//			context.write(new Text(tm.get(i)),new IntWritable(i));
//		}
		
		
		Map <String,Integer> sortedMap = sortByValues(map);
		 int counter = 0;
         for (String key : sortedMap.keySet()) {
             if (counter++ == 20) {
                 break;
             }
             context.write(new Text(key), new IntWritable(sortedMap.get(key)));
         }
	}
	private Map<String , Integer> sortByValues(Map<String, Integer> map2) {
		// TODO Auto-generated method stub
		
		List<Map.Entry<String, Integer>> entries = new LinkedList<Map.Entry<String, Integer>>(map2.entrySet());
		
		Collections.sort(entries, new Comparator<Map.Entry<String,Integer>>(){

			public int compare(Entry<String, Integer> arg0,
					Entry<String, Integer> arg1) {
				// TODO Auto-generated method stub
				return -1*arg0.getValue().compareTo(arg1.getValue());
			}});
		Map<String,Integer> sortedMap = new LinkedHashMap<String,Integer>();
		
		for(Map.Entry<String, Integer> entry : entries)
		{
		sortedMap.put(entry.getKey(),entry.getValue());	
		}
		return sortedMap;
	}
	

}
