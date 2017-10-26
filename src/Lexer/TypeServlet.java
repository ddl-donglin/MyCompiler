package Lexer;

import main.MainTest;

import java.io.IOException;
import java.io.PrintWriter;

public class TypeServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        PrintWriter out = response.getWriter();
        String text = request.getParameter("text");//获得输入的文本
        out.print(MainTest.Analyz(text));
    }
}
