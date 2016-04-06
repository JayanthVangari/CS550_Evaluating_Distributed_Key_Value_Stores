package PA2;
import java.io.*;
import java.util.*;
import org.apache.commons.lang3.RandomStringUtils;

public class Evaluation
{
	
	long start_time,tot_time;
	static PClient pc=new PClient();
	static String[] key,value;
	public int ops=100000;	
	float latency;	
	public static void main(String[] args) throws Exception
	{

		key=new String[100000];
		value=new String[100000];
		for(int i=0;i<=99999;i++)
		{	
			key[i]=RandomStringUtils.randomAlphanumeric(10);
			value[i]=RandomStringUtils.randomAlphanumeric(90);
		}
		Evaluation e=new Evaluation();
		e.getPutTime();
		e.getRetrieveTime();
		e.getdelTime();
					
	}

	private void getPutTime() throws Exception {
		long throughput;
		float kilo_throughput;
		int result;
		start_time=System.currentTimeMillis();
		for(int i=0;i<=99999;i++)			
		{			
			pc.put(key[i], value[i]);
			
		}
		tot_time=System.currentTimeMillis()-start_time;	
		latency=(float)tot_time/100000;
		System.out.println("Latency for PUT: "+latency+" millisecs");
		
		throughput=ops*1000/tot_time;
		kilo_throughput=(float)throughput/1000;
		System.out.println("Throughput for PUT: "+kilo_throughput+" K Ops/sec");
	}

	private void getRetrieveTime() throws Exception {
		long throughput;
		float kilo_throughput;
		start_time=System.currentTimeMillis();
		for(int i=0;i<=99999;i++)		
		{
			pc.retrieve(key[i]);
			
		}
		tot_time=System.currentTimeMillis()-start_time;
		latency=(float)tot_time/100000;
        		System.out.println("Latency for RETRIEVE: "+latency+" millisecs");
		
		throughput=ops*1000/tot_time;
		kilo_throughput=(float)throughput/1000;
		System.out.println("Throughput for RETRIEVE:"+kilo_throughput+" K Ops/sec");
	}


	private void getdelTime() throws Exception {
		long throughput;
		float kilo_throughput;
		int result;
		start_time=System.currentTimeMillis();
		for(int i=0;i<=99999;i++)		
		{
			pc.delete(key[i]);
		}
		tot_time=System.currentTimeMillis()-start_time;
		latency=(float)tot_time/100000;
        	System.out.println("Latency for DELETE: "+latency+" millisecs");
		throughput=ops*1000/tot_time;
		kilo_throughput=(float)throughput/1000;
		System.out.println("Throughput for DELETE: "+kilo_throughput+" K Ops/sec");
		
	}
	
	
}

