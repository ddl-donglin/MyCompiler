public class TestGrammar {

    private StringBuffer buffer = new StringBuffer(); // 缓冲区
    private int i = 0;
    private char ch; // 字符变量，存放最新读进的源程序字符
    private String strToken; // 字符数组，存放构成单词符号的字符串

    /**
     * 读取指定路径文件
     * @param fileSrc 读取文件路径
     */
    public TestGrammar(String fileSrc) {
        FileUtil.readFile(buffer, fileSrc);
    }

    /**
     * 语法分析方法
     */
    public void grammar(){

    }
}
