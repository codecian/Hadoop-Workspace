package q5_top10_CustomKey;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

public class CustomKey implements WritableComparable<CustomKey>{

	
	public Text categeory = new Text();
	public Text id = new Text();
	public LongWritable views = new LongWritable();
	
	public void readFields(DataInput arg0) throws IOException {
		// TODO Auto-generated method stub
		categeory.readFields(arg0);
		id.readFields(arg0);
		views.readFields(arg0);
	}

	public void write(DataOutput arg0) throws IOException {
		// TODO Auto-generated method stub
		categeory.write(arg0);
		id.write(arg0);
		views.write(arg0);
	}

	public int compareTo(CustomKey arg0) {
		// TODO Auto-generated method stub
		int cv = this.categeory.compareTo(arg0.categeory);
		if(cv==0)
			cv = this.views.compareTo(arg0.views);
		return cv;
	}
	
	public void setCategeory(Text c)
	{
		this.categeory = c;
	}
	public void setViews(LongWritable v)
	{
		this.views = v;
	}
	public void setId(Text i)
	{
		this.id=i;
	}

	

}
