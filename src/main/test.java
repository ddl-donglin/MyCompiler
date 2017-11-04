package main;

import Parser.LR1Parser.SyntaxError;
import Parser.TestGrammar;
import Util.FileUtil;

import java.io.IOException;

public class test {
    public static void main(String[] args) throws IOException, SyntaxError {

        MainTest.grammar("S¡úag A d;\nS¡úb A c;\nS¡ú ag e c;\nS¡úb e d;\nA¡ú e","S,A","ag,b,c,d,e","S","ag e d#");

        //MainTest.grammar("S¡úa A d;\nS¡úb A c;\nS¡ú a e c;\nS¡úb e d;\nA¡ú e","S,A","a,b,c,d,e","S","a e d#");
        /*System.out.println(MainTest.grammar(
                "S¡úaAd;\nS¡úbAc;\nS¡úaec;\nS¡úbed;\nA¡úe",
                "S,A",
                "a,b,c,d,e",
                "S",
                "aed#"
        ));*/
    }
}
