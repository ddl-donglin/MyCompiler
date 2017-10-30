package Parser;

import main.MainTest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Parser.GrammarServlet")
public class GrammarServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String text = request.getParameter("text");//获得输入的测试用例
        String nonterminal = request.getParameter("nonterminal");
        String terminal = request.getParameter("terminal");
        String start = request.getParameter("start");
        String parser = request.getParameter("parser");
        System.out.println(text);
        System.out.println(nonterminal);
        System.out.println(terminal);
        System.out.println(parser);
        System.out.println(start);
        out.print(MainTest.grammar(parser));
    }
}
