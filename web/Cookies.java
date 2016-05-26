
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author guilherme
 */
@WebServlet(name = "Cookies", urlPatterns = {"/Cookies"})
public class Cookies extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Cookies</title>");
            out.println("</head>");
            out.println("<body>");

            out.println("<form method=\"post\">");
            out.println("<input type=\"text\" name=\"nome\">");
            out.println("<input type=\"text\" name=\"valor\">");
            out.println("<input type=\"submit\" name=\"Gravar\">");
            out.println("</form>");

            out.println("<hr>");

            Cookie[] requestCokieses = request.getCookies();

            if (requestCokieses != null) {
                out.println("<h3>Cookies Recebidos</h3>");
                for (Cookie c : requestCokieses) {
                    out.println("Nome=" + c.getName() + ", Valor=" + c.getValue() + "<br/ >");
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
        cookie.setMaxAge(60*60);
        response.addCookie(cookie); //Response responde o que veio do navegador já o request manda o que foi feito no navegador
        //Resumindo, request é o que o navegador envia e response o que recebe
        response.sendRedirect("Cookies");
    }

}
