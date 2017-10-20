import java.io.*;
import java.net.*;
import java.util.*;
class multiserver extends Thread
{
	private Socket soc;
	private ArrayList al;

	public multiserver(Socket soc,ArrayList al)
	{
		this.soc=soc;
		this.al=al;
	}

	public void run()
	{
		String name;

		try
		{

		BufferedReader in=new BufferedReader(new InputStreamReader(soc.getInputStream()));
		

		name=in.readLine();
		
		
		
		PrintWriter out1=new PrintWriter(new OutputStreamWriter(soc.getOutputStream()));
		String str;

		do
		{
			str=in.readLine();
			if(!str.equals("stop"))
			{
				
				
				Iterator it=al.iterator();
				while(it.hasNext())
				{
					Socket s1=(Socket)it.next();
					
					PrintWriter out=new PrintWriter(new OutputStreamWriter(s1.getOutputStream()));
					out.println("message from "+name+":"+str);
					out.flush();
					
				}
				
			}
			else
			{

				Iterator it=al.iterator();
				while(it.hasNext())
				{
					Socket s1=(Socket)it.next();
					
					PrintWriter out=new PrintWriter(new OutputStreamWriter(s1.getOutputStream()));
					out.println(name+" has left the conversation");
					out.flush();
					
				}
				
				out1.println(str);
				out1.flush();
					
			}
		}while(!str.equals("stop"));
		
		
		}
		catch(IOException e){}
		
	}

}