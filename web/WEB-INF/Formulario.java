/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "NovoTeste", urlPatterns = "/teste2")
public class Formulario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        montaForm(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String erro = null;
        if (req.getParameter("nome").isEmpty()) {
            erro = "Nome não informado.";
        }

        System.out.println("ERRRO: " + erro);

        if (erro == null) {
            req.setAttribute("msg", "OK, gravado.");
        } else {
            req.setAttribute("msg", erro);
        }

        System.out.println("LOG: " + req.getParameter("nome"));

        montaForm(req, resp);
    }

    protected void montaForm(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            String msg = null;
            if (req.getAttribute("msg") != null) {
                msg = (String) req.getAttribute("msg");
                out.println(req.getAttribute("msg"));
            }
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet teste</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<form method=\"post\">");

            String valor = "";
            if (req.getParameter("nome") != null) {
                valor = req.getParameter("nome").toString();
            }
            System.out.println("Valor: " + valor);

            out.println("<input type=\"text\" name=\"nome\" value=\""
                    + valor
                    + "\"/>");

            out.println("<input type=\"submit\" value=\"Gravar\"/>");
            out.println("</form>");

            out.println("</body>");
            out.println("</html>");
        }
    }

}
