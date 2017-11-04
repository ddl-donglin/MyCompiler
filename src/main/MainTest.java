package main;

import Lexer.TestLexer;
import Parser.LR1Parser.SyntaxError;
import Parser.TestGrammar;
import Semantic.TestUnderStand;
import Util.FileUtil;
import com.sun.deploy.util.SyncFileAccess;

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
	 *
	 * @param parser
	 * @param nonterminal
	 * @param terminal
	 * @param start
	 * @param text
	 * @return
	 * @throws IOException
	 * @throws SyntaxError
	 */
	public static String grammar(String parser, String nonterminal, String terminal, String start, String text) throws IOException, SyntaxError {


		FileUtil.clearFile("./terminalBack.txt");
		FileUtil.writeFile(terminal, "./terminalBack.txt");

		FileUtil.clearFile("./terminal.txt");
		FileUtil.writeFile(FileUtil.gramTermFilter(terminal),"./terminal.txt");

		FileUtil.clearFile("./grammarinBack.txt");
		parser = FileUtil.format(parser);
		FileUtil.writeFile(parser, "./grammarinBack.txt");

		FileUtil.clearFile("./grammarin.txt");
		FileUtil.writeFile(FileUtil.gramParserFilter(parser, FileUtil.gramTermFilter(terminal),nonterminal),"./grammarin.txt");

		FileUtil.clearFile("./nonterminal.txt");

		FileUtil.writeFile(nonterminal, "./nonterminal.txt");

		FileUtil.clearFile("./start.txt");

		FileUtil.writeFile(start, "./start.txt");

		FileUtil.clearFile("./textBack.txt");
		FileUtil.writeFile(text, "./textBack.txt");

		FileUtil.clearFile("./text.txt");
		FileUtil.writeFile(FileUtil.gramTextFilter(text,FileUtil.gramTermFilter(terminal)),"./text.txt");

		new TestGrammar();

		StringBuffer sb1 = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();

		String grammarString = "";

		if(FileUtil.readFile(sb1,"./grammarOut.txt")){
			grammarString += sb1.toString();
		}

		if(FileUtil.readFile(sb2,"./grammarOutPro.txt")){
			grammarString += sb2.toString();
		}
		return grammarString;
	}
}
