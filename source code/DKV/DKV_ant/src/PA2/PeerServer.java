package PA2;
import java.io.*;
import java.net.*;
import java.util.*;
import PA2.Replication;
public class PeerServer implements Runnable
{
	public static ServerSocket ss;
	//public static String[][] peerinfo =new String[8][2];
	public static String[][] peerinfo;
	static int port;
	static int count;
	
	static String replication; 
	public Socket nextserver,client;
	public static Hashtable<String,String> ht=new Hashtable<String,String>(1000003);
	public static void main(String[] args) throws Exception
	{
		PeerServer p=new PeerServer();
		p.start();
		
		if(args.length>0)
		{
			port=Integer.parseInt(peerinfo[Integer.parseInt(args[0])-1][1]);
			//replication=args[1];		
			System.out.println(port);
			
		}
		ss=new ServerSocket(port);
			System.out.println("started server");
			while(true)
			{
				try {
					Socket connection=ss.accept();
					if(connection!=null)
					{
						PeerServer ps=new PeerServer();
						ps.client=connection;
						System.out.println("connected");
						new Thread(ps).start();
					}
				}
				catch(Exception e)
				{ e.printStackTrace();
				}
			}
	}
	public void start() throws Exception
	{
		PeerServer pe=new PeerServer();
		String k;
		String currentDir = System.getProperty("user.dir");
		File f=new File(currentDir+"/config");
		BufferedReader br ,br1;
		br=new BufferedReader(new FileReader(f));
		br1=new BufferedReader(new FileReader(f));

		int j=0;
		while((k=br.readLine())!=null)
		{	
			count++;
		}		
		//System.out.println("Total Servers : "+count);
		peerinfo=new String[count][2];
		
		while((k=br1.readLine())!=null)
		{	
			StringTokenizer st=new StringTokenizer(k," ");
			int i=0;	
			while(st.hasMoreTokens())
			{	
				peerinfo[j][i]=st.nextToken().toString();	
				//System.out.println(peerinfo[j][i]);	
				i++;
			}
			j++;
		}		
	}
	@Override
	public void run() {
		 		while(true)
				{
					try
					{
						String newmes;
						ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
						ObjectOutputStream ops=new ObjectOutputStream(client.getOutputStream());
						String message=(String)ois.readObject();
						//System.out.println(message);
						String[] parts=message.split(" ");
						if( Integer.parseInt(parts[1])==1)
						{
							ht.put(parts[2], parts[3]);
							ops.writeObject("success");
							if(replication.contentEquals("rep"))
							{	if(parts[0].contentEquals("c")) 
								{
									newmes=message.replaceFirst(parts[0],"s");
									Replication r=new Replication();
									r.Replica(newmes);
								}
							}
						}
						else if(Integer.parseInt(parts[1])==2)
						{
						String value;
						
						if(ht.containsKey(parts[2]))
						{
							value=ht.get(parts[2]);
							ops.writeObject(value);
							//System.out.println(value);
						}
						else
						{
							value="key not found";
									ops.writeObject(value);
									//System.out.println(value);
						}
						}
						else if(Integer.parseInt(parts[1])==3)
						{
						String value;
						
						if(ht.containsKey(parts[2]))
								{
									ht.remove(parts[2]);
									value="removed";
									ops.writeObject(value);
								}
						else
						{
							value="key not found";
							ops.writeObject(value);
						}
						}
				
					}
					catch(EOFException eof)
					{}
					catch (Exception e) {}
				}
		}
}
