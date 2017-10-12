import javax.servlet.http.HttpSession;
import java.io.IOException;

public class TypeServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String text = request.getParameter("text");//获得输入的文本
        HttpSession session = request.getSession();
        //System.out.println("text:"+text);
        //System.out.println("ana:"+MainTest.Analyz(text));
        session.setAttribute("Compiler", MainTest.Analyz(text));
        request.getRequestDispatcher("/index.jsp").forward(request,response);
    }


}
