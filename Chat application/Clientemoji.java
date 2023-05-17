import java.io.*;
import java.net.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Scanner;

public class Clientemoji extends JFrame implements ActionListener {
    
    //Network variables
    Socket socket;
    String s="",name="",extra,Serveraddress;

    JFrame j1;
    JTextField jf1, jt1;
    JButton jb1, jb2;
    
    BufferedReader br;
    PrintWriter out;
    
    //GUI Variables
    public JLabel heading;
    private JTextArea messageArea=new JTextArea();
    private JTextField messageInput=new JTextField();
    private JComboBox emoj;
    private Font font=new Font("Roboto",Font.PLAIN,20);

    //constructor class
   Clientemoji() {
        j1 = new JFrame();
        j1.setTitle("Connection Establishment");
        j1.setSize(400, 300);
        j1.setLocationRelativeTo(null);
        j1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the main panel with GridLayout
        JPanel mainPanel = new JPanel(new GridLayout(4, 2));

        JLabel jl1 = new JLabel("<html><font size='8' color='black'>Connection</font></html>");
        mainPanel.add(jl1);

        JLabel jl2 = new JLabel("<html><font size='8' color='black'>Estabishment</font></html>");
        mainPanel.add(jl2);

        JLabel jl3 = new JLabel("<html><font size='6' color='black'>Name</font></html>");
        mainPanel.add(jl3);

        jt1 = new JTextField();
        mainPanel.add(jt1);
	jt1.setFont(font);

        JLabel jl4 = new JLabel("<html><font size='6' color='black'>Server Ip Address</font></html>");
        mainPanel.add(jl4);

        jf1 = new JTextField();
        mainPanel.add(jf1);
	jf1.setFont(font);


        jb1 = new JButton("<html><font size='5'>Reset</font></html>");
        jb1.addActionListener(this);
        mainPanel.add(jb1);

        jb2 = new JButton("<html><font size='5'>Connect</font></html>"); 
        jb2.addActionListener(this);
        mainPanel.add(jb2);

        j1.add(mainPanel);
        j1.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb1) {
            jt1.setText("");
	    jf1.setText("");
        }
        if (e.getSource() == jb2) {
           name=jt1.getText();
	   Serveraddress=jf1.getText();
	   System.out.println(name);
	   this.mainfunction();
        }
    }


   
    // creating chatting gui
    void mainfunction() 
    {
        try{
        Scanner sc=new Scanner(System.in);
        
        heading =new JLabel(name+" Area");

        socket=new Socket(Serveraddress,6767);
        br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out=new PrintWriter(socket.getOutputStream());
        
        //calling GUI
        createGUI();

        //CALLING HANDLEINPUTS
        handleEvents();
        
        //calling Reading and Writing methods
        startReading();
        //startWriting();---->it is a console code
        

        }catch(Exception E)
        {
            E.getStackTrace();
        }
    }
    //Handling Events code
    private void handleEvents(){

        messageInput.addKeyListener(new KeyListener(){
            public void keyTyped(KeyEvent e){

            }
            public void keyPressed(KeyEvent e){
                
            }
            public void keyReleased(KeyEvent e){
                //system.out.println("Key released"+e.getKeyCode());
                if(e.getKeyCode()==10)
                {
                    extra=name+" :> ";
                   //System.out.println("You have Pressed Enter button");
                   String contenttoSend=messageInput.getText();
                   messageArea.append("ME :>"+contenttoSend+"\n");
                   out.println(extra+contenttoSend);
                   out.flush();
                   messageInput.setText("");
                   messageInput.requestFocus();
                   s=contenttoSend;
                }
            }

        });
        emoj.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String selecteditem=(String)emoj.getSelectedItem();
                String ab=messageInput.getText();
                selecteditem=ab+selecteditem;
                messageInput.setText(selecteditem);

                int selectedindex=emoj.getSelectedIndex();
                System.out.println(selectedindex);
            }
        });


    }

    //GUI code
    private void createGUI()
    {
        this.setTitle(name+" Messanger");
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        //coding for components
        heading.setFont(font);
        messageArea.setFont(font);
        messageInput.setFont(font);

        //JPanel creating
        JPanel jp1=new JPanel(new GridLayout(1,0));

        heading.setHorizontalAlignment(SwingConstants.CENTER); 
        heading.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        // TEXTAREA IS DISABLING
        messageArea.setEditable(false);

        //creating icons or images
        //heading.setIcon(new ImageIcon("path"));
        //heading.setHorizontalTextPosition(SwingConstants.CENTER);
        //heading.setVerticalTextPosition(SwingConstants.BOTTOM);


        //MESSAGE POSITIONS
        //TO WRITE FROM CENTER
        //messageInput.setHorizontalAlignment(SwingConstants.CENTER);                   


        //setting frame layout
        this.setLayout(new BorderLayout());


        //adding emojis
     String emoji[]={
"\ud83d\ude00","\ud83d\ude03","\ud83d\ude04","\ud83d\ude01","\ud83d\ude06","\ud83d\ude05","\ud83e\udd23","\ud83d\ude02","\ud83d\ude42","\ud83d\ude43","\ud83e\udee0","\ud83d\ude09","\ud83d\ude0a","\ud83d\ude07",
"\ud83e\udd70","\ud83d\ude0d","\ud83e\udd29","\ud83d\ude18","\ud83d\ude17","\u263a\ufe0f","\ud83d\ude1a","\ud83d\ude19","\ud83e\udd72",
"\ud83d\ude0b","\ud83d\ude1b","\ud83d\ude1c","\ud83e\udd2a","\ud83d\ude1d","\ud83e\udd11","\ud83e\udd17","\ud83e\udd2d","\ud83e\udee2",
"\ud83e\udee3","\ud83e\udd2b","\ud83e\udd14","\ud83e\udee1","\ud83e\udd10","\ud83e\udd28","\ud83d\ude10","\ud83d\ude11",
"\ud83d\ude36","\ud83e\udee5","\ud83d\ude36\u200d\ud83c\udf2b\ufe0f","\ud83d\ude0f","\ud83d\ude12",
"\ud83d\ude44","\ud83d\ude2c","\ud83d\ude2e\u200d\ud83d\udca8","\ud83e\udd25","\ud83d\ude0c","\ud83d\ude14","\ud83d\ude2a",
"\ud83e\udd24","\ud83d\ude34","\ud83d\ude37","\ud83e\udd12","\ud83e\udd15","\ud83e\udd22","\ud83e\udd2e","\ud83e\udd27",
"\ud83e\udd75","\ud83e\udd76","\ud83e\udd74","\ud83d\ude35","\ud83d\ude35\u200d\ud83d\udcab","\ud83e\udd2f",
"\ud83e\udd20","\ud83e\udd73","\ud83e\udd78","\ud83d\ude0e","\ud83e\udd13","\ud83e\uddd0","\ud83d\ude15","\ud83e\udee4","\ud83d\ude1f","\ud83d\ude41",
"\u2639\ufe0f","\ud83d\ude2e","\ud83d\ude2f","\ud83d\ude32","\ud83d\ude33","\ud83e\udd7a","\ud83e\udd79","\ud83d\ude26","\ud83d\ude27","\ud83d\ude28","\ud83d\ude30",
"\ud83d\ude25","\ud83d\ude22","\ud83d\ude2d","\ud83d\ude31","\ud83d\ude16","\ud83e\udd21","\ud83d\udc7b","\ud83d\udc7d"};        
emoj=new JComboBox(emoji);
        //adding the components to the frame
        this.add(heading,BorderLayout.NORTH);
        JScrollPane jscrollpane=new JScrollPane(messageArea);
        this.add(jscrollpane,BorderLayout.CENTER);
        this.add(jp1,BorderLayout.SOUTH);
        jp1.add(messageInput);
        jp1.add(emoj);
        this.setVisible(true);
        


    }


//to read the data from the client
    public void startReading()
    {
        Runnable r1=()->{
        while(true)
        {
            try{
            String msg=br.readLine();
            //System.out.println("Server:>"+msg);
            messageArea.append(msg+"\n");
            if(s.equals("exit"))
            {
                JOptionPane.showMessageDialog(this,"server Terminated");
                messageInput.setEnabled(false);
                socket.close();
                break;
            }

            }catch(Exception e)
            {

            }
        }

        };
        new Thread(r1).start();

    }


    
//to send the data to the client
 /*   public void startWriting()
    {
        Runnable r2=()->{
        while(true)
        {
            try{
            BufferedReader br1=new BufferedReader(new InputStreamReader(System.in));
            String content=br1.readLine();
            s=content;
            out.println(content);
            out.flush();
            if(s.equals("exit"))
            {
                break;
            }

            }catch(Exception e)
            {
            System.out.println(e);
            }
        }
        };
        new Thread(r2).start();

    }*/ //to work in console we use strartWriting()

    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        
        new Clientemoji();
    }
}