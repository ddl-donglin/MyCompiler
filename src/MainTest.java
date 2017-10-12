import java.io.IOException;

public class MainTest {

	/**
	 * 词法分析主函数
	 * @param text
	 * @return
	 * @throws IOException
	 */
	public static String Analyz(String text) throws IOException {
		/*创建词法分析类*/
		/*TestLexer testLexer = new TestLexer("./src/input.txt");
		//FileUtil.clearFile();//清空文件
		testLexer.analyse();*/

		FileUtil.clearFile("./input.txt");

		FileUtil.writeFile(text, "./input.txt");

		System.out.println(text);

		TestLexer testLexer = new TestLexer("./input.txt");

		testLexer.analyse();

		StringBuffer sb = new StringBuffer();

		String analyseString = "出错啦";

		if(FileUtil.readFile(sb,"./output.txt")){
			analyseString = sb.toString();
		}

		return analyseString;
		//System.out.println(FileUtil.readFile("./src/output.txt"));
	}
}
