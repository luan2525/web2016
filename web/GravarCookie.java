

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "GravarCookie", urlPatterns = {"/GravarCookie"})
public class GravarCookie extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GravarCookie</title>");
            out.println("</head>");
            out.println("<body>");

            out.println("<form method=\"post\">");
            out.println("<input type=\"text\" name=\"nome\" />");
            out.println("<input type=\"text\" name=\"valor\" />");
            out.println("<input type=\"submit\" value=\"Gravar\"/>");
            out.println("</form>");

            Cookie[] requestCookies = request.getCookies();

            out.write("<h3>Cookies</h3>");
            if (requestCookies != null) {
                out.write("<h3>Request Cookies:</h3>");
                for (Cookie c : requestCookies) {
                    out.write("Name=" + c.getName() + ", Value=" + c.getValue());
                    out.write("<br>");
                }
            }

            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Cookie cookie = new Cookie(request.getParameter("nome"), request.getParameter("valor"));
        cookie.setMaxAge(60 * 60); //1 hour
        response.addCookie(cookie);

        response.sendRedirect("GravarCookie");
    }

}
