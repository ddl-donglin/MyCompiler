public class test {
    public static void main(String[] args){
        TestLexer testLexer = new TestLexer("input.txt");
        FileUtil.clearFile();//Çå¿ÕÎÄ¼þ
        testLexer.analyse();
        //System.out.println(isKeyWord);
    }
}
