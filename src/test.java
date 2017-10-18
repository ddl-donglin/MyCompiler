public class test {
    public static void main(String[] args){
        TestLexer testLexer = new TestLexer("input.txt");
        FileUtil.clearFile();//гЕ©унд╪Ч
        testLexer.analyse();
    }
}
