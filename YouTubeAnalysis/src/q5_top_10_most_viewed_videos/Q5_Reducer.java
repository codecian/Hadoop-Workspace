package q5_top_10_most_viewed_videos;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Q5_Reducer extends Reducer <Text,Text,Text,Text>{

	private Map<String,Integer> initial = new HashMap<String,Integer>();
	@Override
	protected void reduce(Text key, Iterable<Text> values,Context context)
			throws IOException, InterruptedException {
		
		for(Text val:values)
		{
			String a[]=val.toString().split(",");
			initial.put(a[0], Integer.parseInt(a[1]));
		}
		
		Map<String,Integer> orderedMap = sortedValues(initial);
		
		int i=0;
		for(String s:orderedMap.keySet())
		{
			
			if(i++<10)
			{
				context.write(key, new Text(s+" "+orderedMap.get(s)));
			}
			else
			{
				break;
			}
		}
		
		
		}
	private Map<String, Integer> sortedValues(Map<String, Integer> initial2) {
		// TODO Auto-generated method stub
		
		Map <String,Integer> returnValue= new LinkedHashMap<String,Integer>();
		List<Map.Entry<String, Integer>> entries = new LinkedList<Map.Entry<String, Integer>>(initial2.entrySet());
		
		Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>(){
			public int compare(Entry<String, Integer> arg0,
					Entry<String, Integer> arg1) {
				// TODO Auto-generated method stub
				return -1*arg0.getValue().compareTo(arg1.getValue());
			}
			
		});
		
		for(Map.Entry<String,Integer> e:entries)
		{
			returnValue.put(e.getKey(), e.getValue());
		}
		
		return returnValue;
	}
		
	}


