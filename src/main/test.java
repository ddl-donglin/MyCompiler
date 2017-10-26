package main;

import Parser.TestGrammar;

import java.io.IOException;

public class test {
    public static void main(String[] args) throws IOException {
        /*Lexer.TestLexer testLexer = new Lexer.TestLexer("input.txt");
        Util.FileUtil.clearFile();//Çå¿ÕÎÄ¼þ
        testLexer.analyse();*/
        //System.out.println(isKeyWord);

        new TestGrammar();

        //System.out.print(Util.FileUtil.replaceBlankLine(Util.FileUtil.readFile("input.txt")));
    }
}
