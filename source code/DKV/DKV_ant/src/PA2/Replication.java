package PA2;
import java.io.*;
import java.net.*;
import java.util.*;
class Replication implements Runnable
{
	Socket nextserver;
		String message;
	int node;
	public Replication()
	{
		
	}
	public Replication(String message,int k)
	{
		this.message=message;
		this.node=k;
	}

	public void Replica(String message) throws Exception {
		PeerServer p=new PeerServer();
		
		
		int l,j;
		for(int i=0;i<8;i++)
		{
			l=Integer.parseInt(PeerServer.peerinfo[i][1]);
			if(l==p.port)
			{ 	
				j=i;
				if(j!=7)
				{	
					node=j+1;
					new Thread(new Replication(message,node)).start();
				}
				else if(j==7)
				{
					node=0;
					new Thread(new Replication(message,node)).start();
				}
			}
		}
	}
	
	@Override
	public void run() {
		try {
			ObjectOutputStream ops;
			nextserver=new Socket(PeerServer.peerinfo[node][0],Integer.parseInt(PeerServer.peerinfo[node][1]));
			//System.out.println("neigh port: "+ Integer.parseInt(PeerServer.peerinfo[node][1]));
			//System.out.println("message to be replicated is:"+message);
			ops = new ObjectOutputStream(nextserver.getOutputStream());
			ops.writeObject(message);	
		
		} catch (Exception e) {
			
		}
	}
}