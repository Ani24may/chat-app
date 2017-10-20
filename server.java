import java.net.*;
import java.io.*;
import java.util.*;
class server
{
	public static void main(String args[]) throws IOException
	{

		boolean listen=true;

		ArrayList<Socket> al=new ArrayList<Socket>();
		ServerSocket ss=new ServerSocket(9999);
		

		while(listen)
		{
			Socket s=ss.accept();
			al.add(s);
			System.out.println("connetion established for IP adrres: "+s.getInetAddress()+" with port number"+s.getPort());
			multiserver mul=new multiserver(s,al);
			mul.start();
		
		}
	}

}