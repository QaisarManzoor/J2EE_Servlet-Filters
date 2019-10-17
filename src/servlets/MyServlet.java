package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MyServlet")
public class MyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String regno = request.getParameter("regno");


        if (regno.toLowerCase().equals("bcs151")) {
            HttpSession session = request.getSession();
            session.setAttribute("userreg", regno);
            session.setMaxInactiveInterval(30 * 60);

            Cookie loginCookie = new Cookie("userreg", regno);
            loginCookie.setMaxAge(30 * 60);
            response.addCookie(loginCookie);

            response.sendRedirect("Welcome.jsp");
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            PrintWriter out = response.getWriter();
            out.write("<p style='color:red;'>Invalid Reg No</p>");
            rd.include(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.write("You are in Servlet");
    }
}
