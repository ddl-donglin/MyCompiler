package main;

import Lexer.TestLexer;
import Parser.TestGrammar;
import Semantic.TestUnderStand;
import Util.FileUtil;

import java.io.IOException;

public class MainTest {

	/**
	 * 词法分析主函数
	 * @param text text
	 * @return
	 * @throws IOException
	 */
	public static String Analyz(String text) throws IOException {

		FileUtil.clearFile("./input.txt");

		FileUtil.writeFile(text, "./input.txt");

		//System.out.println(text);

		TestLexer testLexer = new TestLexer("./input.txt");

		testLexer.analyse();

		StringBuffer sb = new StringBuffer();

		String analyseString = "出错啦";

		if(FileUtil.readFile(sb,"./output.txt")){
			analyseString = sb.toString();
		}

		return analyseString;
	}


	/**
	 * 语义分析主函数
	 * @param text
	 * @return
	 * @throws IOException
	 */
	public static String understand(String text) throws IOException{

		FileUtil.clearFile("./input.txt");

		FileUtil.writeFile(text, "./input.txt");

		System.out.println(text);

		TestUnderStand testUnderStand = new TestUnderStand("./input.txt");

		testUnderStand.understand();

		StringBuffer sb = new StringBuffer();

		String understandString = "出错啦";

		if(FileUtil.readFile(sb,"./output.txt")){
			understandString = sb.toString();
		}
		return understandString;
	}

	/**
	 * 语法分析主函数
	 * @param text  parser
	 * @return
	 * @throws IOException
	 */
	public static String grammar(String text) throws IOException{
		FileUtil.clearFile("./grammarin.txt");

		FileUtil.writeFile(text, "./grammarin.txt");

		System.out.println(text);

		/*Parser.TestGrammar testGrammar = new Parser.TestGrammar("./grammarin.txt");

		testGrammar.grammar();*/

		new TestGrammar();

		StringBuffer sb = new StringBuffer();

		String grammarString = "出错啦";

		if(FileUtil.readFile(sb,"./grammarOut.txt")){
			grammarString = sb.toString();
		}
		return grammarString;
	}

	/**
	 * 获取终结符
	 * @param text terminal
	 * @return
	 */
	public static void setTerminal(String text) throws {
		FileUtil.clearFile("./terminal.txt");

		FileUtil.writeFile(text, "./terminal.txt");
	}

	/**
	 * 获取非终结符
	 * @param text nonterminal
	 * @return
	 */
	public static void setNonterminal(String text){
		FileUtil.clearFile("./nonterminal.txt");

		FileUtil.writeFile(text, "./nonterminal.txt");
	}

	/**
	 * 获取起始符
	 * @param text start
	 * @return
	 */
	public static void setStart(String text){
		FileUtil.clearFile("./start.txt");

		FileUtil.writeFile(text, "./start.txt");
	}
}
