package br.senac.sp.servlet.cliente;

import br.senac.sp.entidade.dao.ClienteDAO;
import br.senac.sp.entidade.exception.ClienteException;
import br.senac.sp.entidade.model.Cliente;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

/**
 * @author walter_prata
 */
@WebServlet(name = "CadastraClienteServlet", urlPatterns = {"/CadastroCliente"})
public class CadastraClienteServlet extends HttpServlet {

    private static String INSERIR_OU_EDITAR = "/cadastroCliente.jsp";
    private static String LISTA_CLIENTE = "/listaClientes.jsp";
    private static String LISTAR = "CadastroCliente?action=listar";
    private ClienteDAO dao = new ClienteDAO();
    private RequestDispatcher view;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String acao = request.getParameter("action");
        if (acao != null){
            if (acao.equalsIgnoreCase("deletar")) {
               deletaCliente(request, response);
            } else if (acao.equalsIgnoreCase("editar")) {
                editaCliente(request, response);
            } else if (acao.equalsIgnoreCase("listar")) {
                listaCliente(request,response);
            } else {
                view = request.getRequestDispatcher(INSERIR_OU_EDITAR);
                view.forward(request, response);
            }
        }

    }

    private void listaCliente(HttpServletRequest request, HttpServletResponse response) {
        List<Cliente> listaDeCliente;
        try {
            listaDeCliente = dao.buscar();
            request.setAttribute("clientes", listaDeCliente);
            view = request.getRequestDispatcher(LISTA_CLIENTE);
            view.forward(request, response);
        } catch (ClienteException | ServletException | IOException ex) {
            System.out.println("Erro ao listar clientes\nErro: "+ex.getMessage());
            Logger.getLogger(CadastraClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void editaCliente(HttpServletRequest request, HttpServletResponse response) {
        try {
            String cpf = request.getParameter("cpf");
            Cliente cliente = dao.buscarClientePeloCpf(cpf);
            request.setAttribute("cliente", cliente);
            view = request.getRequestDispatcher(INSERIR_OU_EDITAR);
            view.forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(CadastraClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void deletaCliente(HttpServletRequest request, HttpServletResponse response) {
        try {
            int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
            String cpf = request.getParameter("cpf");
            dao.removerCliente(idUsuario);
            request.setAttribute("cliente", dao.buscarClientePeloCpf(cpf));
            view = request.getRequestDispatcher(LISTA_CLIENTE);
            view.forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(CadastraClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cliente cliente = criaCliente(request);
        try {
//            String cpf = request.getParameter("cpfCliente");
//            if(cpf == null || cpf.isEmpty())
//            {
//                dao.inserir(cliente);
//            }
//            else
//            {
//                cliente.setCpf(cpf);
//                dao.editar(cliente);
//            }

        dao.inserir(cliente);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        RequestDispatcher view = request.getRequestDispatcher(LISTA_CLIENTE);
        view.forward(request, response);
    }

    private Cliente criaCliente(HttpServletRequest request) {
        Cliente cliente = new Cliente();

        cliente.setNomeUsuario(request.getParameter("nomeCliente"));
        cliente.setSobrenomeUsuario(request.getParameter("sobrenomeCliente"));
        cliente.setCpf(request.getParameter("cpfCliente"));
        cliente.setEmail(request.getParameter("emailCliente"));
        cliente.setGenero(request.getParameter("generoCliente").charAt(0));

        String dataDeNascimento = request.getParameter("data_nascimento");
        cliente.setDataNascimento(dataDeNascimento);

        cliente.setTelefone(request.getParameter("telefoneCliente"));
        cliente.setCep(request.getParameter("cepCliente"));
        cliente.setRua(request.getParameter("ruaCliente"));
        cliente.setBairro(request.getParameter("bairroCliente"));
        cliente.setComplemento(request.getParameter("complementoCliente"));
        cliente.setCidade(request.getParameter("cidadeCliente"));
        cliente.setNumero(Integer.parseInt(request.getParameter("numeroCliente")));
        cliente.setEstado(request.getParameter("ufCliente"));
        return cliente;
    }
}
