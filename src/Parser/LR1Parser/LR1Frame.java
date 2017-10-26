package Parser.LR1Parser;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import Parser.ContextFreeGrammar.CFG;
import Parser.ContextFreeGrammar.TerminalsSet;

public class LR1Frame extends JFrame
{
	private JTextField NonTerminalSymbolField;
	private JTextField TerminalSymbolField;
	private JTextField startSymbolField;
	private JTextArea strToAnalyzeArea;
	private JTextArea ProductionArea;
	private JTextArea resultArea;
	private LR1Frame owner;
	
	class MyMenuBar extends JMenuBar {
		private static final long serialVersionUID = 1L;

		public MyMenuBar() {
			super();
			MenuItemListener mil = new MenuItemListener();
			
			JMenu file = new JMenu("文件");
			JMenuItem clear = new JMenuItem("清空记录");
			JMenuItem save = new JMenuItem("保存结果");
			JMenuItem exit = new JMenuItem("退出程序");
			clear.addActionListener(mil);
			save.addActionListener(mil);
			exit.addActionListener(mil);
			file.add(clear);
			file.add(save);
			file.addSeparator();
			file.add(exit);

			JMenu run = new JMenu("运行");
			JMenuItem analyse = new JMenuItem("开始分析");
			JMenuItem defaultData = new JMenuItem("举例");
			analyse.addActionListener(mil);
			defaultData.addActionListener(mil);
			run.add(analyse);
			run.addSeparator();
			run.add(defaultData);
			
			JMenu about = new JMenu("帮助");
			JMenuItem instruction = new JMenuItem("说明");
			instruction.addActionListener(mil);
			JMenuItem version = new JMenuItem("版本");
			version.addActionListener(mil);
			about.add(instruction);
			about.add(version);
			
			add(file);
			add(run);
			add(about);
		}
	}

	class MenuItemListener implements ActionListener{

		boolean simpleChoose;

		public void actionPerformed(ActionEvent e)
		{
			String Action = e.getActionCommand();
			if (Action == "退出程序")	System.exit(0);
			
			if (Action == "清空记录") {
				NonTerminalSymbolField.setText("");
				TerminalSymbolField.setText("");
				startSymbolField.setText("");
				ProductionArea.setText("");
				strToAnalyzeArea.setText("");
				resultArea.setText("");
			}
			
			if (Action == "举例"){
				
				simpleChoose = !simpleChoose;
				
				if (simpleChoose) {
					NonTerminalSymbolField.setText("S,A");
					TerminalSymbolField.setText("a,b,c,d,e");
					startSymbolField.setText("S");
					ProductionArea.setText("S→aAd;\nS→bAc;\nS→aec;\nS→bed;\nA→e");
					strToAnalyzeArea.setText("aed#");
					resultArea.setText("");
				} else {
					NonTerminalSymbolField.setText("S,A,B");
					TerminalSymbolField.setText("a,b");
					startSymbolField.setText("S");
					ProductionArea.setText("S→A;\nA→BA;\nA→ε;\nB→aB;\nB→b");
					strToAnalyzeArea.setText("abab");
					resultArea.setText("");
				}
			}
			if (Action == "开始分析") {
				//生成上下文无关
				CFG grammar = new CFG(NonTerminalSymbolField.getText(), 
						TerminalSymbolField.getText(), 
						startSymbolField.getText(),
						ProductionArea.getText());
				TerminalsSet toAnalyze = new TerminalsSet(strToAnalyzeArea.getText());
				//分析
				LR1Parser lr1Parser = new LR1Parser(grammar, toAnalyze);
				lr1Parser.precompute();//预计算，产生LR(1)分析表
				try {
					resultArea.setText(lr1Parser.getProcess(grammar)+lr1Parser.parse()+"\n\n");
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (SyntaxError e1) {
					e1.printStackTrace();
				}
			}
			if (Action == "保存结果")
			{
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = 
					new FileNameExtensionFilter("TXT Documents", new String[] {"txt"});
				fileChooser.setSelectedFile(
						new File( new StringBuilder("*").append(".txt").toString() )
						);
				fileChooser.setFileFilter(filter);
				int i = fileChooser.showSaveDialog(owner);
				if (i == 0) {
					File file = fileChooser.getSelectedFile();
					try {
						file.createNewFile();
						FileWriter fw = new FileWriter(file);
						fw.write(resultArea.getText());
						fw.close();
					} catch (IOException ioexception) {
						JOptionPane.showMessageDialog(null, ioexception.toString(), "无法保存!", 0);
					}
				}
			}
			if (Action == "说明")
			{
				JDialog jd = new JDialog(owner, "程序使用说明", true);
				String str = "  tip 1. 终结符及非终结符用英文逗号隔开\n";
				str = (new StringBuilder(String.valueOf(str))).append("  tip 2. 产生式以分号英文隔开\n" +
						"         且推导符号为：'→'。\n").toString();
				str = (new StringBuilder(String.valueOf(str))).append("  tip 3. 程序中带有两组测试数据，\n" +
						"         点击：\"运行->使用默认数据\"\n").toString();
				TextArea ta = new TextArea(str);
				ta.setEditable(false);
				jd.add(ta);
				Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
				jd.setBounds(screen.width / 2 - 200, screen.height / 2 - 150, 400, 300);
				jd.setVisible(true);
			}
			if (Action == "版本")
			{
				JDialog jd = new JDialog(owner, "版本说明", true);
				String str = "  LR(1)语法分析器\n";
				str = (new StringBuilder(String.valueOf(str))).append("  By: Jinlin\n").toString();
				TextArea ta = new TextArea(str);
				ta.setEditable(false);
				jd.add(ta);
				Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
				jd.setBounds(screen.width / 2 - 200, screen.height / 2 - 150, 400, 300);
				jd.setVisible(true);
			}
		}

		MenuItemListener()
		{
			super();
			simpleChoose = false;
		}
	}


	public LR1Frame()
	{
		super("LR(1)语法分析");
		owner = this;
		init();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Dimension fullScreen = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((fullScreen.width - 800) / 2, (fullScreen.height - 600) / 2, 800, 600);
		setResizable(false);
		setVisible(true);
	}

	public void init()
	{
		getContentPane().setLayout(null);
		
		//菜单栏
		setJMenuBar(new MyMenuBar());
		
		/** 输入面板*/
		JPanel inputPanel = new JPanel();
		inputPanel.setBounds(0, 0, 300, 545);
		inputPanel.setLayout(new BorderLayout());
		inputPanel.setBorder(new TitledBorder(new EtchedBorder(), "在此输入数据"));
		getContentPane().add(inputPanel);
		inputPanel.setLayout(null);
		//Symbol输入处
		JLabel label1 = new JLabel("非终结符：");
		JLabel label2 = new JLabel("终结符：");
		JLabel label3 = new JLabel("起始符：");
		label1.setBounds(15, 20, 100, 25);
		label2.setBounds(15, 50, 100, 25);
		label3.setBounds(15, 80, 100, 25);
		inputPanel.add(label1);
		inputPanel.add(label2);
		inputPanel.add(label3);
		
		NonTerminalSymbolField = new JTextField();
		TerminalSymbolField = new JTextField();
		startSymbolField = new JTextField();
		NonTerminalSymbolField.setBounds(80, 20, 210, 25);
		TerminalSymbolField.setBounds(80, 50, 210, 25);
		startSymbolField.setBounds(80, 80, 210, 25);
		inputPanel.add(NonTerminalSymbolField);
		inputPanel.add(TerminalSymbolField);
		inputPanel.add(startSymbolField);
		//Production输入处
		JPanel productionPanel = new JPanel();
		productionPanel.setBounds(5, 110, 290, 300);
		productionPanel.setLayout(new BorderLayout());
		productionPanel.setBorder(new TitledBorder(new EtchedBorder(), "产生式："));
		ProductionArea = new JTextArea();
		JScrollPane jsp1 = new JScrollPane(ProductionArea);
		productionPanel.add(jsp1);
		inputPanel.add(productionPanel);
		//待分析Code
		JPanel strToAnalyzePanel = new JPanel();
		strToAnalyzePanel.setBounds(5, 410, 290, 130);
		strToAnalyzePanel.setLayout(new BorderLayout());
		strToAnalyzePanel.setBorder(new TitledBorder(new EtchedBorder(), "待分Code："));
		strToAnalyzeArea = new JTextArea();
		JScrollPane jsp0 = new JScrollPane(strToAnalyzeArea);
		strToAnalyzePanel.add(jsp0);
		inputPanel.add(strToAnalyzePanel);
		
		
		//输出面板
		JPanel resultPanel = new JPanel();
		resultPanel.setBounds(305, 0, 490, 545);
		resultPanel.setLayout(new BorderLayout());
		resultPanel.setBorder(new TitledBorder(new EtchedBorder(), "结果输出"));
		getContentPane().add(resultPanel);
		resultArea = new JTextArea();
		resultArea.setEditable(false);
		JScrollPane jsp2 = new JScrollPane(resultArea);
		resultPanel.add(jsp2);
	}

	public static void main(String args[])
	{
		new LR1Frame();
	}
}
