package servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Tentar extends HttpServlet {

    public static final String ID_NUMERO_SECRETO = "numero_secreto";
    public static final String ID_TENTATIVA = "tentativa";
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
        HttpSession sessao = request.getSession();
        sessao.setAttribute(ID_MSG_ERRO, msg);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        incrementarNumeroDeTentativas(request);
        int tentativa = Integer.parseInt(request.getParameter(ID_TENTATIVA));
        int numeroSecreto = obterNumeroSecreto(request);
        RequestDispatcher rd;
        
        if (tentativa != numeroSecreto) {
            if (tentativa > numeroSecreto) {
                alterarMensagemDeErro(request, "O número que pensei é MENOR do que esse...");
            } else {
                alterarMensagemDeErro(request, "O número que pensei é MAIOR do que esse...");
            }
            rd = request.getRequestDispatcher("errou.jsp");
            rd.forward(request,response);
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
