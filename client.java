import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

class mycl
{
	public static void main(String args[]) throws IOException
	{
		String name=null;
		boolean control=false;
		while(!control)
		{
		name= JOptionPane.showInputDialog(null,"Enter your name");
		if(name!=null)
		{
		control=true;
		}
		}
		client c=new client(name);
	}
}



class client extends JFrame implements ActionListener,Runnable
{

	
		private BufferedReader str;
		private String cname;
		private PrintWriter out;
		private BufferedReader in;
		private String abc="";
		private Socket s;
		private myclient me;
		Thread t=null;
		private String s1;
		
		private JPanel p1,p2,p;
    		private TitledBorder b1,b2,b3;
    		private JTextArea t1,t2;
    		private JTextField tf;
    		private JScrollPane sp;
		private JButton jb;
		//private JScrollBar scroll;

	public client(String n) throws IOException
	{
		cname=n;
		setTitle("LETS CHAT");
		setSize(600,300);		
		
		t1 = new JTextArea(10,25);
		JScrollPane sp = new JScrollPane(t1);
		//getContentPane().add(sp);
        	t1.setVisible(true);
        	t1.setEditable(false);

        	TitledBorder b1 = new TitledBorder("Message");
        	t1.setBorder(b1);
		
        
        	t2 = new JTextArea(10, 15);
        	t2.setVisible(true);
        	t2.setEditable(false);
        	TitledBorder b3 = new TitledBorder("Notification");
        	t2.setBorder(b3);
		t2.append(cname+" is online"+"\n");
        
        	TitledBorder b2 = new TitledBorder("Enter message");
        	tf = new JTextField(40);
        	tf.setVisible(true);
        	tf.setEditable(true);
        	tf.setBorder(b2);
        
		jb=new JButton("Send");
        	jb.setPreferredSize(new Dimension(80,40));
		jb.addActionListener(this);
        
        	p1 = new JPanel();
        	p1.add(t1);
		p1.add(sp);
        	p1.add(t2);
        
        	p2 = new JPanel();
        	p2.add(tf); 
        	p2.add(jb);
	
		p = new JPanel();
        	p.add(p1);
        	p.add(p2);
        	this.add(p);



		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);


		
		s=new Socket("localhost",9999);
		
		


		
		out=new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
		out.println(cname);		

		in=new BufferedReader(new InputStreamReader(s.getInputStream()));
		
		
		

		t=new Thread(this);
        	t.start();

		
		



	}


	public void actionPerformed(ActionEvent e)
	{
		
				
				abc=tf.getText().toString();
				//t1.append(abc+"\n");
				out.println(abc);
				out.flush();
				
		
		
	}


	public void run()
	{
		//t2.append(cname+" is online"+"\n");
		try
	{
		
			do
		{
			
			s1=in.readLine();
			//readtext(s1);	
			//System.out.println(s1);
			t1.append(s1+"\n");
			
		}
		while(!s1.equals("stop"));
		
	}
	catch(IOException e){}
	}
	

	


}


