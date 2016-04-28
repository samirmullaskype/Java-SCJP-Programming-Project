import java.awt.*;				// color, font
import javax.swing.*;			// All swing components are here
import javax.swing.border.*;	

import java.awt.event.*;		//For LoginFrame class

public class MainScreen
{
    public static void main(String[] args) 
    {
		MyFrame f1 = new MyFrame();	
    }
}

class MyFrame extends JFrame
{
	public MyFrame()
	{
		//Set the size and titles of the window.
		setBounds(150,100,700,600);		// x,y,width,height
		setTitle("Mail Order Project");
		
		//JFrame has 3 layers the top one is Container.
		Container c = getContentPane();
		c.setBackground(Color.white);
		c.setLayout(new FlowLayout());
		////////////////////////////////////////////////////////////////////////////////////////////////////
		JButton b1 = new JButton("Log In", new ImageIcon("images/login.jpg"));
		JButton b2 = new JButton("Shopping", new ImageIcon("images/shopping.jpg"));
		JButton b3 = new JButton("View Basket", new ImageIcon("images/basket.jpg"));
		JButton b4 = new JButton("Order Report", new ImageIcon("images/report.jpg"));
		JButton b5 = new JButton("Log Out", new ImageIcon("images/logout.jpg"));
		
		b1.setBounds(0,0,100,35);
		b1.setMargin(new Insets(0,0,0,0));
		b1.setVerticalTextPosition(SwingConstants.BOTTOM);
		b1.setHorizontalTextPosition(SwingConstants.CENTER);
		b1.setOpaque(true);
		b1.setBackground(Color.white);
		b1.setForeground(Color.black);
		
		b2.setBounds(0,0,100,35);
		b2.setMargin(new Insets(0,0,0,0));
		b2.setVerticalTextPosition(SwingConstants.BOTTOM);
		b2.setHorizontalTextPosition(SwingConstants.CENTER);
		b2.setOpaque(false);
		b2.setBackground(Color.white);
		b2.setForeground(Color.black);
		b2.setEnabled(false);
		
		b3.setBounds(0,0,100,35);
		b3.setMargin(new Insets(0,0,0,0));
		b3.setVerticalTextPosition(SwingConstants.BOTTOM);
		b3.setHorizontalTextPosition(SwingConstants.CENTER);
		b3.setOpaque(false);
		b3.setBackground(Color.white);
		b3.setForeground(Color.black);
		b3.setEnabled(false);
		
		b4.setBounds(0,0,100,35);
		b4.setMargin(new Insets(0,0,0,0));
		b4.setVerticalTextPosition(SwingConstants.BOTTOM);
		b4.setHorizontalTextPosition(SwingConstants.CENTER);
		b4.setOpaque(false);
		b4.setBackground(Color.white);
		b4.setForeground(Color.black);
		b4.setEnabled(false);
		
		b5.setBounds(0,0,100,35);
		b5.setMargin(new Insets(0,0,0,0));
		b5.setVerticalTextPosition(SwingConstants.BOTTOM);
		b5.setHorizontalTextPosition(SwingConstants.CENTER);
		b5.setOpaque(false);
		b5.setBackground(Color.white);
		b5.setForeground(Color.black);		
		b5.setEnabled(false);
		
		add(b1);
		add(b2);
		add(b3);
		add(b4);
		add(b5);
		
		////////////////////////////////////////////////////////////////////////////////////////////////////
		JLabel img = new JLabel(new ImageIcon("images/mailordersystem.jpg"));
		add(img, BorderLayout.CENTER);
		
		//Log in button, close this window and open the log in window.
		b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginScn window = new LoginScn();
            }
        });
////////////////////////////////////////////////////////    
        //if user closes the window, the terminate the program
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                    dispose();
            }
        });
////////////////////////////////////////////////////////	
		setVisible(true);		
	}
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
