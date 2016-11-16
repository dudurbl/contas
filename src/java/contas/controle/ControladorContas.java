package contas.controle;


import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ControlarAutores", urlPatterns = {"/salvar", "/listar", "/novo", "/editar", "/remover", "/concluir", "/notas", "/salvarNota", "/excluirNota", "/editarNota"})
public class ControladorContas extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String jsp = null;
        if (request.getRequestURI().endsWith("/salvar")) {
            jsp = salvar(request, response);
        } else if (request.getRequestURI().endsWith("/listar")) {
            jsp = listar(request, response);
        } else if (request.getRequestURI().endsWith("/novo")) {
            jsp = novo(request, response);
        } else if (request.getRequestURI().endsWith("/editar")) {
            jsp = editar(request, response);
        } else if (request.getRequestURI().endsWith("/remover")) {
            jsp = remover(request, response);
        } else if (request.getRequestURI().endsWith("/concluir")) {
            jsp = concluir(request, response);
        } else if (request.getRequestURI().endsWith("/notas")) {
            jsp = notas(request, response);
        } else if (request.getRequestURI().endsWith("/salvarNota")) {
            jsp = salvarNota(request, response);
        } else if (request.getRequestURI().endsWith("/excluirNota")) {
            jsp = excluirNota(request, response);
        } else if (request.getRequestURI().endsWith("/editarNota")) {
            jsp = editarNota(request, response);
        } else {
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
        processRequest(request, response);
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

    private String salvar(HttpServletRequest request, HttpServletResponse response) {
        String sCodigo = request.getParameter("codigo");
        Integer codigo = Integer.valueOf(sCodigo);
        String descricao = request.getParameter("descricao");
        String sEstagio = request.getParameter("estagio");
        Integer estagio = Integer.valueOf(sEstagio);

        Atividade atividade = RepositorioAtividade.getAtividade(codigo);
        if (atividade == null) {
            atividade = new Atividade();
            atividade.setCodigo(codigo);
        }
        atividade.setDescricao(descricao);
        atividade.setDataCadastro(new Date());
        atividade.setEstagio(estagio);
        RepositorioAtividade.salvar(atividade);
        return "listar";
    }

    private String listar(HttpServletRequest request, HttpServletResponse response) {
        List<Atividade> lista = RepositorioAtividade.getAtividades();
        request.setAttribute("lista", lista);
        return "listagem.jsp";
    }

    private String novo(HttpServletRequest request, HttpServletResponse response) {
        List<Contas> lista = RepositorioAtividade.getAtividades();
        request.setAttribute("lista", lista);
        return "cadastro.jsp";
    }

    private String editar(HttpServletRequest request, HttpServletResponse response) {
        String sCodigo = request.getParameter("codigo");
        Integer codigo = Integer.valueOf(sCodigo);
        Atividade a = RepositorioAtividade.getAtividade(codigo);
        if (new Integer(100).compareTo(a.getEstagio()) == 0) {
            return "listar";
        }
        request.setAttribute("e", a);
        return "novo";
    }

    private String remover(HttpServletRequest request, HttpServletResponse response) {
        String sCodigo = request.getParameter("codigo");
        Integer codigo = Integer.valueOf(sCodigo);
        RepositorioAtividade.getAtividades().remove(RepositorioAtividade.getAtividade(codigo));
        return "listar";
    }

    private String concluir(HttpServletRequest request, HttpServletResponse response) {
        String sCodigo = request.getParameter("codigo");
        Integer codigo = Integer.valueOf(sCodigo);
        Atividade atv = RepositorioAtividade.getAtividade(codigo);
        atv.setEstagio(100);
        RepositorioAtividade.salvar(atv);
        return "listar";
    }

    private String notas(HttpServletRequest request, HttpServletResponse response) {
        String sCodigo = request.getParameter("codigo");
        Integer codigo = Integer.valueOf(sCodigo);
        request.setAttribute("e", RepositorioAtividade.getAtividade(codigo));
        return "notas.jsp";
    }

    private String salvarNota(HttpServletRequest request, HttpServletResponse response) {
        String sCodigoAtv = request.getParameter("codigoAtv");
        Integer codigoAtv = Integer.valueOf(sCodigoAtv);
        String sCodigo = request.getParameter("codigo");
        Integer codigo = Integer.valueOf(sCodigo);
        String sNota = request.getParameter("nota");

        Atividade atividade = RepositorioAtividade.getAtividade(codigoAtv);
        Nota nota = atividade.getNota(codigo);
        if (nota == null) {
            nota = new Nota();
            nota.setCodigo(codigo);
        }
        nota.setNota(sNota);
        nota.setData(new Date());
        atividade.addNota(nota);
        RepositorioAtividade.salvar(atividade);
        return "listar";
    }

    private String excluirNota(HttpServletRequest request, HttpServletResponse response) {
        String sCodigoAtv = request.getParameter("codigoAtv");
        Integer codigoAtv = Integer.valueOf(sCodigoAtv);
        String sCodigo = request.getParameter("codigo");
        Integer codigo = Integer.valueOf(sCodigo);
        Nota n = RepositorioAtividade.getAtividade(codigoAtv).getNota(codigo);
        RepositorioAtividade.getAtividade(codigoAtv).getNotas().remove(n);
        request.setAttribute("e", RepositorioAtividade.getAtividade(codigoAtv));
        return "notas.jsp";
    }

    private String editarNota(HttpServletRequest request, HttpServletResponse response) {
        String sCodigoAtv = request.getParameter("codigoAtv");
        Integer codigoAtv = Integer.valueOf(sCodigoAtv);
        String sCodigo = request.getParameter("codigo");
        Integer codigo = Integer.valueOf(sCodigo);
        Nota n = RepositorioAtividade.getAtividade(codigoAtv).getNota(codigo);
        request.setAttribute("e", RepositorioAtividade.getAtividade(codigoAtv));
        request.setAttribute("n", n);
        return "notas.jsp";
    }
}
