package main;

import Parser.LR1Parser.SyntaxError;
import Parser.TestGrammar;
import Util.FileUtil;

import java.io.IOException;

public class test {
    public static void main(String[] args) throws IOException, SyntaxError {

        MainTest.grammar("S¡úar A dw;\nS¡úb A c;\nS¡ú ar e c;\nS¡úb e dw;\nA¡ú e","S,A","ar,b,c,dw,e","S","ar e dw#");

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
