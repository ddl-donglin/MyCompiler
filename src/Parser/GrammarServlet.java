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

        MainTest.setNonterminal(request.getParameter("nonterminal"));
        MainTest.setTerminal(request.getParameter("terminal"));
        MainTest.setStart(request.getParameter("start"));

        out.print(MainTest.grammar(request.getParameter("parser")));
    }
}
