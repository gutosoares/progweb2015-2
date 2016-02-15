package servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TentarAvancado extends HttpServlet {

    public static final String ID_NUMERO_SECRETO = "numero_secreto";
    public static final String ID_TENTATIVA_AVANCADO = "tentativaAvancado";
    public static final String ID_NUM_TENTATIVAS = "num_tentativas";
    public static final String ID_MSG_ERRO = "msg_erro";

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

    private void alterarMensagemDeErro(HttpServletRequest request, String msg) {
        request.setAttribute(ID_MSG_ERRO, msg);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        incrementarNumeroDeTentativas(request);
        int chute = Integer.parseInt(request.getParameter(ID_TENTATIVA_AVANCADO));
        int numSecreto = obterNumeroSecreto(request);
        RequestDispatcher rd;
        
        // Avalição do chute do jogador
        if (chute != numSecreto) {
            if (chute > numSecreto) {
                if(chute <= numSecreto + 10) {
                    alterarMensagemDeErro(request, "Huuuu, está QUENTE!");
                } else if (chute < numSecreto + 15) {
                    alterarMensagemDeErro(request, "Ta quase... está MORNO!");
                } else {
                    alterarMensagemDeErro(request, "Está FRIO como a Russia!");
                }
            } else {
                 if(chute >= numSecreto - 10) {
                    alterarMensagemDeErro(request, "Huuuu, está QUENTE!");
                } else if (chute > numSecreto - 15) {
                    alterarMensagemDeErro(request, "Ta quase... está MORNO!");
                } else {
                    alterarMensagemDeErro(request, "Está FRIO como a Russia!");
                }
            }
            
            rd = request.getRequestDispatcher("errouAvancado.jsp");
            rd.forward(request, response);
        } else {
            response.sendRedirect("acertou.jsp");
        }
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
