


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author guilherme
 */
@WebServlet(name = "Formulario", urlPatterns = {"/Formulario"})
public class Formulario extends HttpServlet {

    protected void montarFormulario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Cadastro de Eexemplo</title>");            
            out.println("</head>");
            out.println("<body>");
            
            if(request.getAttribute("msg") != null){
                out.println(request.getAttribute("msg"));
            }
            
            out.println("<form method=\"post\">");            
            String valor = "";
            
            if(request.getParameter("nome") != null){
                valor = request.getParameter("nome");
            }
            out.println("<input type=\"text\" "
                        + "name=\"nome\" "
                        + "value = \""
                        + valor
                        +"\">");
            
            out.println("<input type=\"submit\" "
                        + "value=\"Gravar\">");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
                   
         
            
        }
    }

 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {       
        montarFormulario(request, response);   
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        String erro = null;
        
        if(request.getParameter("nome").isEmpty()){
            erro = "Nome é obrigatório!";
        }
        
        if(erro == null){
            request.setAttribute("msg", "OK, formulário gravado com sucesso!");
        } else {
            request.setAttribute("msg", erro);
        }
        
        montarFormulario(request, response);
        
    }


}
