import java.awt.*;
import java.awt.event.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TextEditor extends JFrame
{
	private JTextArea ta;
	private JFileChooser fc;
	private JToolBar tb;
	
	public TextEditor()
	{
		ta = new JTextArea(20, 60);
		fc = new JFileChooser();
		tb = new JToolBar(); 
		JScrollPane sp = new JScrollPane(ta, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		FileFilter ff = new FileNameExtensionFilter("Plain text", "txt");
		fc.setFileFilter(ff);
		
		add(sp);
		
		JMenuBar mb = new JMenuBar();
		setJMenuBar(mb);
		
		JMenu file = new JMenu("File");
		mb.add(file);
		
		file.add(Open);
		file.add(Save);
		file.addSeparator();
		file.add(Exit);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JMenu file2 = new JMenu("Help");
		mb.add(file2);
		file2.add(About);
		
		JPanel p = new JPanel(); 
		JButton b1 = new JButton("Bold"); 
		JButton b2 = new JButton("Enlarge and Italic"); 
		
		b1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				ta.setFont(ta.getFont().deriveFont(Font.BOLD, 14f));
			}
		});
		 
		b2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				ta.setFont(ta.getFont().deriveFont(Font.ITALIC, 10f));
			}
		});

        p.add(b1); 
        p.add(b2); 
        tb.add(p); 
        
		add(tb, BorderLayout.NORTH); 
				
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	Action Open = new AbstractAction("Open File")
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
				openFile(fc.getSelectedFile().getAbsolutePath());
		}
	};
	
	Action Save = new AbstractAction("Save File")
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			saveFile();
		}
	};
	
	Action Exit = new AbstractAction("Exit")
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	};
	
	Action About = new AbstractAction("About")
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			JFrame frame2 = new JFrame();
		    frame2.setBounds(100, 100, 450, 300);
		    frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    frame2.getContentPane().setLayout(null);
		    frame2.setVisible(true);
		}
	};
	
	public void openFile(String fileName)
	{
		try
		{
			FileReader fr = new FileReader(fileName);
			ta.read(fr, null);
			fr.close();
			setTitle(fileName);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void saveFile()
	{
		if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
		{
			try
			{
				FileWriter fw = new FileWriter(fc.getSelectedFile().getAbsolutePath() + ".txt");
				ta.write(fw);
				fw.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}