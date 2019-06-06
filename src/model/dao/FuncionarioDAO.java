package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.vo.Funcionario;

public class FuncionarioDAO {

	public boolean atualizar(Funcionario funcionario) {
		boolean sucessoUpdate = false;

		// ****REVISAR QUERY
		String sql = " UPDATE FUNCIONARIO funcionario SET NOME=?, CPF=?" + " WHERE FUNCIONARIO.ID = ? ";

		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao);

		try {
			prepStmt.setString(1, funcionario.getNome());
			prepStmt.setString(2, funcionario.getCpf());
			prepStmt.setDouble(3, funcionario.getIdFuncionario());

			int codigoRetorno = prepStmt.executeUpdate();

			if (codigoRetorno == 1) {
				sucessoUpdate = true;
			}

		} catch (SQLException e) {
			System.out.println("Erro ao atualizar Funcion�rio. Causa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conexao);
		}

		return sucessoUpdate;
	}

	public int inserir(Funcionario funcionario) {
		int novoId = -1;

		String sql = " INSERT INTO FUNCIONARIO (NOME, CPF) " + " VALUES (?,?) ";

		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao);

		try {
			prepStmt.setString(1, funcionario.getNome());
			prepStmt.setString(2, funcionario.getCpf());

			prepStmt.execute();

			ResultSet generatedKeys = prepStmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				novoId = generatedKeys.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao inserir Funcion�rio. Causa: \n: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conexao);
		}
		return novoId;
	}

	public boolean existeFuncionarioNovo(Funcionario funcionarioNovo) {
		// TODO Auto-generated method stub
		return false;
	}

	public int excluir(Funcionario funcionario) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		int resultado = 0;

		String query = "DELETE FROM FUNCIONARIO WHERE IDFUNCIONARIO = " + funcionario.getIdFuncionario();
		try {
			resultado = stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query de Exclus�o do Funcion�rio. Erro: " + e.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return resultado;
	}

}