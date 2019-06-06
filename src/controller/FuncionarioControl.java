package controller;

import model.bo.FuncionarioBO;
import model.vo.Funcionario;

public class FuncionarioControl {

	// Verificar se os campos s�o nulos ou vazios senao chamar BO
	private static final FuncionarioBO FuncionarioBO = new FuncionarioBO();

	public static String salvar(Funcionario funcionario) {
		String validacao = validarFuncionario(funcionario);

		if (validacao == "") {
			if (funcionario.getIdFuncionario() > 0) {
				if (FuncionarioBO.atualizar(funcionario)) {
					validacao = "Funcion�rio atualizado com sucesso!";
				} else {
					validacao = "Erro ao atualizar produto";
				}
			} else {
				if (FuncionarioBO.inserir(funcionario)) {
					validacao = "Funcion�rio salvo com sucesso!";
				} else {
					validacao = "Erro ao salvar Funcion�rio";
				}
			}
		}
		return validacao;
	}

	private static String validarFuncionario(Funcionario funcionario) {
		String validacao = "";

		if (funcionario == null) {
			validacao = "Funcion�rio est� NULO!";
		} else {
			// Validar o preenchimento
			if (funcionario.getNome().trim().equals("")) {
				validacao += "- Nome � obrigat�rio \n";
			}
			if (funcionario.getCpf().trim().equals("")) {
				validacao += "- O CPF � obrigat�rio \n";
			}
		}
		return validacao;
	}

	public String excluir(Funcionario funcionario, String nome, String cpf) {
		String mensagem = "";

		if (nome == null || nome.trim().isEmpty()) {
			mensagem = "Preenche o nome";
		}
		if (cpf == null || cpf.trim().isEmpty()) {
			mensagem = "Preenche o cpf";
		}
		if (mensagem.isEmpty()) {
			Funcionario funcionarioNovo = new Funcionario();
			funcionarioNovo.setIdFuncionario(funcionario.getIdFuncionario());

			FuncionarioBO funcionarioBO = new FuncionarioBO();
			funcionarioBO.excluir(funcionarioNovo, funcionarioNovo);
		}
		return mensagem;
	}

	public static boolean close() {
		return false;

	}

}