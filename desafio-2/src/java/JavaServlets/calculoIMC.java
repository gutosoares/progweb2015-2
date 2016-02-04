package JavaServlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gutosoares
 */

public class calculoIMC extends HttpServlet {
    
    public static final String ID_IMC = "imc";
    public static final String ID_MSG = "msg";

   
    private double calcularIMC(double altura, double peso) {
        double imc;
        imc = peso/(altura * altura);
        return imc;
    }

    private void resultadoIMC(HttpServletRequest request, String imc) {
        request.setAttribute(ID_IMC, imc);
    }

    private void mensagemResultado(HttpServletRequest request, String msg) {
        request.setAttribute(ID_MSG, msg);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        double altura = Double.parseDouble(request.getParameter("altura"));
        double peso = Double.parseDouble(request.getParameter("peso"));
        double imc = calcularIMC(altura, peso);
        String resultado = String.format("%.2f", imc);
        
        resultadoIMC(request,resultado);
        
        // Teste do IMC
        if (imc >= 18.5 && imc <= 24.9) {
          mensagemResultado(request,"Parabéns, você está no peso ideal!");
        }
        
        if (imc >= 25 && imc <= 29.9) {
            mensagemResultado(request,"Cuidado! Você está no sobrepeso!"); 
        }
        
        if (imc >= 30 && imc <= 34.9) {
            mensagemResultado(request,"Cuidado! Você está em obesidade grau I!");  
        }
        
        if (imc >= 35 && imc <= 39.9){
            mensagemResultado(request,"Cuidado! Você está em obesidade grau II!"); 
        }
        
        if (imc >= 40){
            mensagemResultado(request,"Cuidado! Você está em obesidade grau III!"); 
        }
        
        RequestDispatcher rd;
        rd = request.getRequestDispatcher("imcResult.jsp");
        rd.forward(request, response);
    }
}
