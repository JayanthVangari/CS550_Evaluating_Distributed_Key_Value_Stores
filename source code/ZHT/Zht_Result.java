import java.io.*;
import java.util.*;
public class Zht_Result
{
	static float insert_lat;
	static float retrieve_lat;
	static float delete_lat;
	static float insert_tp,retrieve_tp,delete_tp;
	static float avg_latency,avg_throughput;
	
	public static void main(String[] args) throws Exception
	{
		ArrayList<Float> latency_put=new ArrayList<Float>();
		ArrayList<Float> latency_get=new ArrayList<Float>();	
		ArrayList<Float> latency_del=new ArrayList<Float>();
		ArrayList<Float> tp_put=new ArrayList<Float>();
		ArrayList<Float> tp_get=new ArrayList<Float>();	
		ArrayList<Float> tp_del=new ArrayList<Float>();
		int ops;
		float lat;
		float throughput,kilo_throughput;	
		//latency_put=new int[args[0]];
		Result r=new Result();
		String currentDir=System.getProperty("user.dir");
		//read config file
		File f=new File(currentDir+"/output.txt");
		if(args.length>0)
		{
			f=new File(args[0]);
		}
		try{
			BufferedReader br=new BufferedReader(new FileReader(f));
			int j=0,m=0,n=0;
			String k;
			while((k=br.readLine())!=null)
			{
					if(k.contains("Inserted"))
					{
						{
							String[] split=k.split(", ");
							
							ops=Integer.parseInt(split[1]);	
							lat=(Float.parseFloat(split[4]))/ops;	
							//k=k.replaceAll("[^0-9\\.]","");
							throughput=ops*1000/Float.parseFloat(split[4]);
							kilo_throughput=throughput/1000;
							//lat=split[]	
							//System.out.println(k);		
							latency_put.add(j,lat);
							tp_put.add(j,kilo_throughput);
							
							j++;
						}
					}
				
					if(k.contains("Lookuped") )
					{
							String[] split=k.split(", ");
							ops=Integer.parseInt(split[1]);	
							lat=(Float.parseFloat(split[4]))/ops;	
							throughput=ops*1000/Float.parseFloat(split[4]);
							kilo_throughput=throughput/1000;
							latency_get.add(m,lat);
							tp_get.add(m,kilo_throughput);
							m++;
					
					}
					if(k.contains("Removed"))
					{
						String[] split=k.split(", ");
						ops=Integer.parseInt(split[1]);	
						lat=(Float.parseFloat(split[4]))/ops;	
						throughput=ops*1000/Float.parseFloat(split[4]);
						kilo_throughput=throughput/1000;
						latency_del.add(n,lat);
						tp_del.add(n,kilo_throughput);
						n++;
						
					}
					
			}
			r.put(latency_put,tp_put);
			r.get(latency_get,tp_get);
			r.del(latency_del,tp_del);
			
			avg_latency=(insert_lat+retrieve_lat+delete_lat)/3;
			System.out.println("Average Latency: " + avg_latency+ " millisecs");
			avg_throughput=(insert_tp+retrieve_tp+delete_tp)/3;
			System.out.println("Average Throughput : "+avg_throughput+" K_Ops/sec");
			
			
		}catch(Exception e)
		{
				e.printStackTrace();
		}
	}
	void put(ArrayList<Float> latency_p,ArrayList<Float> tp_p)
	{
		float tmp=0;
		float sum=0;
		float max=0;
			for(float put: latency_p)
			{
				tmp=put;
				if(tmp>max)
					max=tmp;
				
			}
			for(float p: tp_p)
			{
				sum=sum+p;
			}
		insert_lat=max;
		insert_tp=sum;
		
		System.out.println("Latency for put : "+ max+" millisecs");
		System.out.println("Throughput for put: "+sum+" K_Ops/sec");
	}	
	void get(ArrayList<Float> latency_g,ArrayList<Float> tp_g)
	{
		float tmp=0;
		float sum=0;
		float max=0;
		for(float get: latency_g)
			{
				tmp=get;
				if(tmp>max)
					max=tmp;
				
			}
		for(float p: tp_g)
			{
				sum=sum+p;
			}
		
		retrieve_lat=max;
		retrieve_tp=sum;	
			
		System.out.println("Latency for get : "+ max+" millisecs");
		System.out.println("Throughput for get: "+sum+" K_Ops/sec");
			
	}
	void del(ArrayList<Float> latency_d,ArrayList<Float> tp_d)
	{
		float tmp=0;
		float sum=0;
		float max=0;
		for(float del: latency_d)
		{
			tmp=del;
			if(tmp>max)
				max=tmp;
				
		}
		for(float d: tp_d)
			{
				sum=sum+d;
			}
		delete_lat=max;
		delete_tp=sum;
			
		System.out.println("Latency for delete : "+ max+" millisecs");
		System.out.println("Throughput for delete: "+sum+" K_Ops/sec");
	}
}
