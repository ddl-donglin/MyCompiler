import java.io.IOException;

public class TestGrammar {

    //private StringBuffer grammarbuffer = new StringBuffer(); // 语法分析结果缓冲区
    private final String point = "->";
    private String grammarin;   //输入的文法
    private StringBuffer grammarinbuf;  //输入的文法缓冲（将文法中的注释去掉


    public TestGrammar() throws IOException {
        System.out.println(getInput());
    }

    /**
     * 读取指定路径文件
     * @param fileSrc 读取文件路径
     */
    public TestGrammar(String fileSrc) throws IOException {

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
    public void grammar() throws IOException {

    }

    /**
     * 获取词法分析的结果,token序列
     * @return
     */
    public String getToken() throws IOException {

        String textToken = MainTest.Analyz(FileUtil.readFile("input.txt"));
        StringBuffer token = new StringBuffer();
        for(int i = 0; i < textToken.length(); i++){
            if(textToken.charAt(i) == ';' && textToken.charAt(++i) == '('){
                while(true){
                    if(textToken.charAt(i++) != ')'){
                        token.append(textToken.charAt(i));
                    }else{
                        if(textToken.charAt(i++) == ')')
                            token.append(')');
                        token.deleteCharAt(token.length()-1);
                        token.append('\n');
                        break;
                    }
                }
            }
        }
        return token.toString();
    }


    /**
     * 获取语法输入文本，且忽略注释
     * @return
     * @throws IOException
     */
    public String getInput() throws IOException {
        grammarin = FileUtil.readFile("grammarin.txt");
        grammarinbuf = new StringBuffer();
        for(int i = 0; i < grammarin.length(); i++){

            if(grammarin.charAt(i) == '/' && grammarin.charAt(++i) == '*'){
                while(true){
                    if(grammarin.charAt(i++) == '*' && grammarin.charAt(i) == '/')
                        break;
                }
                i++;
            }
            grammarinbuf.append(grammarin.charAt(i));
        }
        return grammarinbuf.toString();
        //return FileUtil.readFile("grammarin.txt");
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
