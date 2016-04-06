package PA2;
import java.io.*;
import java.net.*;
import java.util.*;

import org.apache.commons.lang3.RandomStringUtils;
public class PClient 
{
	static int count;
	static String[][] peerinfo;
	public static Socket csocket[];
	
	//static String[][] peerinfo =new String[8][2];
	//public static Socket csocket[]=new Socket[8];
	
	public PClient()
	{
		initclient();
	}
	private static void initclient() {
		String k;
		BufferedReader br,br1;
		
		String currentDir=System.getProperty("user.dir");
		
		File f=new File(currentDir+"/config");
		try{
			br=new BufferedReader(new FileReader(f));
			br1=new BufferedReader(new FileReader(f));
		int j=0;
		while((k=br.readLine())!=null)
		{	
			count++;
		}		
		peerinfo=new String[count][2];
		csocket=new Socket[count];
				
		while((k=br1.readLine())!=null)
		{	
			StringTokenizer st=new StringTokenizer(k," ");
			int i=0;	
			while(st.hasMoreTokens())
			{		
				peerinfo[j][i]=st.nextToken().toString();		
				i++;
			}	
			j++;
		}		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		for(int i=0;i<count;i++)
		{
			try {
				PClient.csocket[i]=new Socket(PClient.peerinfo[i][0],Integer.parseInt(PClient.peerinfo[i][1]));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	public static void main(String[] args) throws Exception
	{
		 int final_result;
		 String val;
		PClient pn=new PClient();
		while(true)
		{
			System.out.println("\nOperations on DHT");
			System.out.println("1. put");
			System.out.println("2. retrieve");
			System.out.println("3. Delete");
			System.out.println("0. exit");
			
			System.out.println("\nSelect any operation");
			
			Scanner s=new Scanner(System.in);
			int in=s.nextInt();
			Scanner k=new Scanner(System.in);
			
			switch(in)
			{
			case 0: System.exit(0);
			case 1: System.out.println("Enter key");
					String key=k.nextLine();
					RandomStringUtils r=new RandomStringUtils();
					String value=r.randomAlphanumeric(90);
					final_result=pn.put(key,value);
					System.out.println(final_result);
					break;
			case 2: System.out.println("Enter key");
					 	key=k.nextLine();
					 	val=pn.retrieve(key);
					 	System.out.println(val);
					break;
			case 3: System.out.println("Enter key");
					key=k.nextLine();
					final_result=pn.delete(key);
					System.out.println(final_result);
					break;
			default: 
					System.out.println("Enter 1,2 or 3");
			}
		}
	}

int put(String key,String value) {
		int node;
		int result=0;
		String message,send;
		node=(int)hash(key)%count;	
		if(node<0)
			node=-(node);
		send="c "+1+" "+key+" "+value;
		try{
			ObjectOutputStream ops=new ObjectOutputStream(csocket[node].getOutputStream());
		
		ops.writeObject(send);
		ObjectInputStream ips=new ObjectInputStream(csocket[node].getInputStream());
		message=(String) ips.readObject();
		if(message.contentEquals("success"))
		{
			result=1;
		}
		}
		catch(Exception e)
		{
			result=0;
		}
return result;
}



String retrieve(String key) throws Exception {
	int node;
	String message,send;
	node=(int)hash(key)%count;
	if(node<0)
		node=-(node);
	send="c "+2+" "+key;
	try{
		ObjectOutputStream ops=new ObjectOutputStream(csocket[node].getOutputStream());
		
		ops.writeObject(send);
		ObjectInputStream ips=new ObjectInputStream(csocket[node].getInputStream());
		message=(String) ips.readObject();
		}
	catch(IOException e)
	{
		ObjectOutputStream oops=new ObjectOutputStream(csocket[node+1].getOutputStream());
		oops.writeObject(send);
		ObjectInputStream ips=new ObjectInputStream(csocket[node+1].getInputStream());
		message=(String) ips.readObject();
		
	}
		return message;
}
int delete(String key) throws Exception {
	int node;
	String message,send;
	int result=0;
	node=(int)hash(key)%count;
	if(node<0)
		node=-(node);
	send="c "+3+" "+key;
	try
	{
	ObjectOutputStream ops=new ObjectOutputStream(csocket[node].getOutputStream());
	ops.writeObject(send);
	ObjectInputStream ips=new ObjectInputStream(csocket[node].getInputStream());
	message=(String) ips.readObject();
	if(message.contains("removed"))
	{	result=1;
	}
	}
	catch(Exception e)
		{
			result=0;
		}
return result;
}

private long hash(String key) {
	
	 long hash=65599;
for (int j=0;j<key.length();j++)
{	
	hash=(key.charAt(j)+hash)*33+j;
	while(hash>Long.MAX_VALUE)
	{
		hash=hash/10;
	}
}
return hash;
}
}

