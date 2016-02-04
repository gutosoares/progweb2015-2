package JavaServlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gutosoares
 */

public class calculoIMC extends HttpServlet{
    
    // Função para calculo do Índice IMC
    public double calcularIMC(double altura, double peso) {
        double imc;
        imc = peso/(altura * altura);
        return imc;
    }
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        
        double altura = Double.parseDouble(req.getParameter("altura"));
        double peso = Double.parseDouble(req.getParameter("peso"));
        double imc = calcularIMC(altura, peso);
        String resultado = String.format("%.2f", imc);
        res.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter pw = res.getWriter()) {
            pw.println("<html>");
            pw.println("<head>");
            pw.println("<title>Cálculo IMC - Servlet em Java</title>");
            pw.println("</head>");
            pw.println("<body>");
            pw.println("<h1>Resultado</h1>");
            pw.println("<hr/>");
            pw.println("<h2> Seu IMC é: " + resultado + ".</h2>");
            pw.println("<br/>");

            // Teste do IMC
            if (imc < 18.5) {
                pw.println("<h3>Cuidado, você está abaixo do peso ideal!</h3>");            
            }
            
            if (imc >= 18.5 && imc <= 24.9) {
                pw.println("<h3>Parabéns! Você está no peso ideal!</h3>");   
            }
            
            if (imc >= 25 && imc <= 29.9) {
                pw.println("<h3>Cuidado, você está no sobrepeso!</h3>");   
            }
            
            if (imc >= 30 && imc <= 34.9) {
                pw.println("<h3>Cuidado! Você está em obesidade grau I!</h3>");   
            }
            
            if (imc >= 35 && imc <= 39.9) {
                pw.println("<h3>Cuidado! Você está em obesidade grau II!</h3>");   
            }
            
            if (imc >= 40) {
                pw.println("<h3>Cuidado! Você está em obesidade grau III!</h3>");   
            }
            
            pw.println("<hr/>");        
            pw.println("</body>");
            pw.println("</html>");
        }
    }
}
