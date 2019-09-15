package br.com.alura.gerenciador.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Criando a rota/nome do servlet
// para ser acessado externamente
@WebServlet(urlPatterns = "/oi")
public class OiMundoServlet extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;

//	O m�todo SERVICE pega o GET e o POST n�o � espec�fico
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException  {
 		
		PrintWriter writer = resp.getWriter();
		
		writer.println("<html>");
		writer.println("<body>");
		writer.println("Parab�ns voc� escreveu seu zilion�simo Servlet e ainda n�o aprendeu :(");
		writer.println("</body>");
		writer.println("</html>");
		
	}
	
}
