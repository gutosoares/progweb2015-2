/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Augusto
 */
@WebServlet(name = "Tentar", urlPatterns = {"/Tentar"})
public class Tentar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    public static final String ID_NUMERO_SECRETO = "numero_secreto";
    public static final String ID_TENTATIVA = "tentativa";
    public static final String ID_NUM_TENTATIVAS = "num_tentativas";
    public static final String ID_MSG_ERRO = "msg_erro";
    public static final String ID_NUM_MENOR_TENTATIVA = "menor_tentativa";

    private int obterNumeroSecreto(HttpServletRequest request) {
        HttpSession sessao = request.getSession();
        if (sessao.getAttribute(ID_NUMERO_SECRETO) == null) {
            sessao.setAttribute(ID_NUMERO_SECRETO, "" + (int) (Math.random() * 100));
        }
        return Integer.parseInt((String) sessao.getAttribute(ID_NUMERO_SECRETO));
    }

    private void incrementarNumeroDeTentativas(HttpServletRequest request) {
        HttpSession sessao = request.getSession();
        if (sessao.getAttribute(ID_NUM_TENTATIVAS) == null) {
            sessao.setAttribute(ID_NUM_TENTATIVAS, "1");
        } else {
            int numTentativas = Integer.parseInt((String) sessao.getAttribute(ID_NUM_TENTATIVAS));
            numTentativas++;
            sessao.setAttribute(ID_NUM_TENTATIVAS, "" + numTentativas);
        }
    }
    
    private void verificaMenorTentativa(HttpServletRequest request){
        HttpSession sessao = request.getSession();
        
        if(getServletContext().getAttribute(ID_NUM_MENOR_TENTATIVA) == null){
            getServletContext().setAttribute(ID_NUM_MENOR_TENTATIVA, sessao.getAttribute(ID_NUM_TENTATIVAS));
        }
        else{
            int tentativa = Integer.parseInt((String) sessao.getAttribute(ID_NUM_TENTATIVAS));
            int menorTentativa = Integer.parseInt((String)getServletContext().getAttribute(ID_NUM_MENOR_TENTATIVA));
            
            if(tentativa < menorTentativa){
                getServletContext().setAttribute(ID_NUM_MENOR_TENTATIVA, tentativa);
            }
        }
    }

    private void alterarMensagemDeErro(HttpServletRequest request, String msg) {
        request.setAttribute(ID_MSG_ERRO, msg);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        incrementarNumeroDeTentativas(request);
        int tentativa = Integer.parseInt(request.getParameter(ID_TENTATIVA));
        int numeroSecreto = obterNumeroSecreto(request);
        System.out.println(numeroSecreto);
        RequestDispatcher rd;
        if (tentativa != numeroSecreto) {
            if (tentativa > numeroSecreto) {
                alterarMensagemDeErro(request, "O número que pensei é MENOR do que esse...");
            } else {
                alterarMensagemDeErro(request, "O número que pensei é MAIOR do que esse...");
            }
            rd = request.getRequestDispatcher("errou.jsp");
        } else {
            verificaMenorTentativa(request);
            rd = request.getRequestDispatcher("acertou.jsp");
        }
        rd.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
