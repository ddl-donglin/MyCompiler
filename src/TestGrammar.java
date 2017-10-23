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
     * 采用自底向上方法LR(1)，自动计算CLOSURE(I)和GOTO()函数
     * 自动生成LR分析表
     * 具备语法错误处理能力，且准确给出错误位置，采用错误恢复策略
     * 输出错误提示信息格式：Error at Line[行号]:[说明文字]
     *
     *
     */
    public void grammar(){

    }

    /**
     * 生成FIRST集
     * @return
     */
    public String myfirst(){
        return null;
    }

    /**
     * 生成FOLLOW集
     * @return
     */
    public String myfollow(){
        return null;
    }

    /**
     * 自动计算CLOSURE(1)函数的方法
     * @return
     */
    public String myclosure(){
        return null;
    }

    /**
     * 自动计算GOTO()函数
     * @return
     */
    public String mygoto(){
        return null;
    }

    /**
     * 自动生成LR分析表
     * @return
     */
    public String myLRtable(){
        return null;
    }

    /**
     * 打印输出语法分析的结果
     * 将语法分析树按照先序遍历方式打印每个节点信息
     *
     * @param result
     */
    public void printree(String result){

    }
}
