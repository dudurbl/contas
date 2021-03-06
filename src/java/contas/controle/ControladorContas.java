package contas.controle;


import contas.modelo.Contas;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ControlarAutores", urlPatterns = {"/salvar", "/listar", "/novo", "/editar", "/remover", "/pagar", "/notas", "/salvarNota", "/excluirNota", "/editarNota"})
public class ControladorContas extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        String jsp = null;
        if (request.getRequestURI().endsWith("/salvar")) {
            jsp = salvar(request, response);
        } else if (request.getRequestURI().endsWith("/listar")) {
            jsp = listar(request, response);
        } else if (request.getRequestURI().endsWith("/pagar")) {
            jsp = pagar(request, response);
        } else if (request.getRequestURI().endsWith("/editar")) {
            jsp = editar(request, response);
        } else if (request.getRequestURI().endsWith("/remover")) {
            jsp = remover(request, response);
        } else if (request.getRequestURI().endsWith("/novo")) {
            jsp = novo(request, response);
        }  else {
            jsp = null;
        }

        if (jsp == null) {
            response.sendRedirect(request.getContextPath() + "/novo");
        } else {
            request.getRequestDispatcher(jsp).forward(request, response);
        }

    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ControladorContas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ControladorContas.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    private String salvar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String sCodigo = request.getParameter("codigo");
        Integer codigo = Integer.valueOf(sCodigo);
        String descricao = request.getParameter("descricao");
        String sValor = request.getParameter("valor");
        Double valor = Double.valueOf(sValor);
        String sDataVencimento = request.getParameter("data_vencimento");
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        Date dataVencimento = formater.parse(sDataVencimento);
        SimpleDateFormat AppDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        
        Contas conta = new Contas();
        conta.setIdconta(codigo);
        conta.setDescricao(descricao);
        conta.setValor(valor);
        conta.setData_vencimento(dataVencimento);
        Contas aux = RepositorioContas.getConta(codigo);
       if(aux != null){
            RepositorioContas.atualizar(conta);
        }else{
            RepositorioContas.inserir(conta);
        }
        return "novo";
    }

    private String listar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Contas> lista = RepositorioContas.getContas();
        request.setAttribute("lista", lista);
        return "listagem.jsp";
    }

    private String editar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String sCodigo = request.getParameter("codigo");
        Integer codigo = Integer.valueOf(sCodigo);
        Contas c = RepositorioContas.getConta(codigo);
        request.setAttribute("conta", c);
        request.setAttribute("codigo", codigo);
        return "novo";
    }

    private String remover(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String sCodigo = request.getParameter("codigo");
        Integer codigo = Integer.valueOf(sCodigo);
        RepositorioContas.excluir(codigo);
        return "novo";
    }

    private String pagar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String sCodigo = request.getParameter("codigo");
        Integer codigo = Integer.valueOf(sCodigo);
        RepositorioContas.pagarConta(codigo);
        return "novo";
    }

      private String novo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Contas> listaPagar = RepositorioContas.getContasAPagar();
        List<Contas> listaPago = RepositorioContas.getContasPagas();
        int codigo = RepositorioContas.getPproximoId();
        request.setAttribute("listaPagar", listaPagar);
        request.setAttribute("listaPago", listaPago);
        request.setAttribute("proximoId", codigo);
        return "cadastro.jsp";
    }

    
}
