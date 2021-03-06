package br.senac.sp.entidade.dao;

import br.senac.sp.db.ConexaoDB;
import br.senac.sp.entidade.enums.ConvertStringForDepartamento;
import br.senac.sp.entidade.enums.ConvertStringForGenero;
import br.senac.sp.entidade.enums.ConvertStringForUf;
import br.senac.sp.entidade.exception.FuncionarioException;
import br.senac.sp.entidade.model.Funcionario;
import br.senac.sp.servlet.funcionario.SenhaUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDao implements Dao<Funcionario> {

    private Connection conn;
    private PreparedStatement stmt;
    private Statement st;
    private ResultSet rs;

    @Override
    public boolean inserir(Funcionario entidade) throws SQLException {

        try {
            conn = ConexaoDB.getConexao();
            //`id`, `nome`, `sobrenome`, `cpf`, `email`, `genero`, `data_nascimento`,tell,cep,rua,bairro,complemen,cit,num,estado,senha,depart,salario
            String sql = "INSERT INTO funcionario VALUES (default,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, entidade.getNomeUsuario());
            stmt.setString(2, entidade.getSobrenomeUsuario());
            stmt.setString(3, entidade.getCpf());
            stmt.setString(4, entidade.getEmail());
            stmt.setString(5, String.valueOf(entidade.getGenero()));
            stmt.setString(6, entidade.getDataNascimento());
            stmt.setString(7, entidade.getTelefone());
            stmt.setString(8, entidade.getCep());
            stmt.setString(9, entidade.getRua());
            stmt.setString(10, entidade.getBairro());
            stmt.setString(11, entidade.getComplemento());
            stmt.setString(12, entidade.getCidade());
            stmt.setInt(13, entidade.getNumero());
            stmt.setString(14, String.valueOf(entidade.getEstado()));
            stmt.setString(15, String.valueOf(entidade.getSenha()));
            stmt.setString(16, String.valueOf(entidade.getDepartamento()));
            stmt.setDouble(17, entidade.getSalario());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Funcionario erro: "+e.getMessage());
            return false;
        } finally {
            conn.close();
        }
    }

    @Override
    public List<Funcionario> buscar() throws FuncionarioException {
        List<Funcionario> listaDeFuncionarios = new ArrayList<>();
        try {
            conn = ConexaoDB.getConexao();
            String sql = "SELECT * FROM funcionario";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionario funcionario = new Funcionario();

                funcionario.setIdUsuario(rs.getInt("id"));
                funcionario.setNomeUsuario(rs.getString("nome"));
                funcionario.setSobrenomeUsuario(rs.getString("sobrenome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setGenero(ConvertStringForGenero.parse(rs.getString("genero")));
                funcionario.setDataNascimento(rs.getString("data_nascimento"));
                funcionario.setTelefone(rs.getString("tell"));
                funcionario.setCep(rs.getString("cep"));
                funcionario.setRua(rs.getString("rua"));
                funcionario.setBairro(rs.getString("bairro"));
                funcionario.setComplemento(rs.getString("complemento"));
                funcionario.setCidade(rs.getString("cidade"));
                funcionario.setNumero(rs.getInt("numero"));
                funcionario.setEstado(ConvertStringForUf.parse(rs.getString("estado")));
                funcionario.setDepartamento(ConvertStringForDepartamento.parse(rs.getString("departamento")));
                funcionario.setSalario(rs.getDouble("salario"));
                listaDeFuncionarios.add(funcionario);
            }

            rs.close();

        } catch (SQLException e) {
            throw new FuncionarioException("Erro ao Buscar Funcionario!\nErro: " + e.getMessage());
        }
        return listaDeFuncionarios;
    }

    @Override
    public boolean editar(Funcionario entidade) throws SQLException {

        try {
            Funcionario funcionarioEncontrado = buscarFuncionarioPeloCpf(entidade.getCpf());
            entidade.setIdUsuario(funcionarioEncontrado.getId());
            conn = ConexaoDB.getConexao();
            if (!entidade.equals(funcionarioEncontrado)) {
                if (entidade.getSenha() == null){
                    entidade.setSenha(funcionarioEncontrado.getSenha());
                }


                String sql = "UPDATE funcionario SET nome = ?,sobrenome = ?,cpf = ?"
                        + ",genero = ?,data_nascimento = ?"
                        + ",tell = ?,cep = ?,rua = ?,bairro = ?"
                        + ",complemento = ?,cidade = ?,numero = ?,estado = ?"
                        + ",senha = ?,departamento = ?, salario = ?WHERE id = ?";

                stmt = conn.prepareStatement(sql);
                stmt.setString(1, entidade.getNomeUsuario());
                stmt.setString(2, entidade.getSobrenomeUsuario());
                stmt.setString(3, entidade.getCpf());
                System.out.println("GENERO: "+entidade.getGenero());
                stmt.setString(4, String.valueOf(entidade.getGenero()));
                stmt.setString(5, entidade.getDataNascimento());
                stmt.setString(6, entidade.getTelefone());
                stmt.setString(7, entidade.getCep());
                stmt.setString(8, entidade.getRua());
                stmt.setString(9, entidade.getBairro());
                stmt.setString(10, entidade.getComplemento());
                stmt.setString(11, entidade.getCidade());
                stmt.setInt(12, entidade.getNumero());
                stmt.setString(13, String.valueOf(entidade.getEstado()));
                stmt.setString(14, String.valueOf(entidade.getSenha()));
                stmt.setString(15, String.valueOf(entidade.getDepartamento()));
                stmt.setDouble(16, entidade.getSalario());
                stmt.setDouble(17, entidade.getId());
                stmt.execute();
                return true;
            }
        } catch (SQLException e) {
            throw new FuncionarioException("Erro ao Editar Funcionario!\nErro: " + e.getMessage());
        } finally {
            conn.close();
        }
        return false;
    }

    public Funcionario buscaFuncionarioPeloId(int id) {
        try {
            conn = ConexaoDB.getConexao();

            String sql = "SELECT * FROM funcionario WHERE id = ?";

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();
            if (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setIdUsuario(rs.getInt("id"));
                funcionario.setNomeUsuario(rs.getString("nome"));
                funcionario.setSobrenomeUsuario(rs.getString("sobrenome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setGenero(ConvertStringForGenero.parse(rs.getString("genero")));
                funcionario.setDataNascimento(rs.getString("data_nascimento"));
                funcionario.setTelefone(rs.getString("tell"));
                funcionario.setCep(rs.getString("cep"));
                funcionario.setRua(rs.getString("rua"));
                funcionario.setBairro(rs.getString("bairro"));
                funcionario.setComplemento(rs.getString("complemento"));
                funcionario.setCidade(rs.getString("cidade"));
                funcionario.setNumero(rs.getInt("numero"));
                funcionario.setEstado(ConvertStringForUf.parse(rs.getString("estado")));
                funcionario.setDepartamento(ConvertStringForDepartamento.parse(rs.getString("departamento")));
                funcionario.setSalario(rs.getDouble("salario"));

                return funcionario;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Funcionario buscarFuncionarioPeloCpf(String cpf) throws SQLException {
        conn = ConexaoDB.getConexao();
        Funcionario funcionario = null;

        try {
            String sql = "SELECT * FROM Funcionario WHERE cpf = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cpf);
            rs = stmt.executeQuery();

            if (rs.next()) {
                funcionario = new Funcionario();

                funcionario.setIdUsuario(rs.getInt("id"));
                funcionario.setNomeUsuario(rs.getString("nome"));
                funcionario.setSobrenomeUsuario(rs.getString("sobrenome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setGenero(ConvertStringForGenero.parse(rs.getString("genero")));
                funcionario.setDataNascimento(rs.getString("data_nascimento"));
                funcionario.setTelefone(rs.getString("tell"));
                funcionario.setCep(rs.getString("cep"));
                funcionario.setRua(rs.getString("rua"));
                funcionario.setBairro(rs.getString("bairro"));
                funcionario.setComplemento(rs.getString("complemento"));
                funcionario.setCidade(rs.getString("cidade"));
                funcionario.setNumero(rs.getInt("numero"));
                funcionario.setEstado(ConvertStringForUf.parse(rs.getString("estado")));
                funcionario.setSenha(rs.getString("senha"));
                funcionario.setDepartamento(ConvertStringForDepartamento.parse(rs.getString("departamento")));
                funcionario.setSalario(rs.getDouble("salario"));

            }

        } catch (SQLException e) {
            throw new FuncionarioException("Erro ao Buscar Funcionario!\nErro: " + e.getMessage());
        } finally {
            conn.close();
        }

        return funcionario;

    }

    public void removerFuncionario(int idUsuario) throws SQLException {

        try {
            String sql = "DELETE FROM funcionario where id = " + idUsuario;
            st = conn.createStatement();
            st.execute(sql);
        } catch (SQLException e) {
            throw new FuncionarioException("Erro ao Deletar Funcionario!\nErro: " + e.getMessage());
        } finally {
            st.close();
        }

    }

    @Override
    public boolean remover(Funcionario entidade) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
