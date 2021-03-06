package model.bo;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import model.dao.AgendamentoDAO;
import model.util.GeradordePlanilha;
import model.vo.Agendamento;
import model.vo.Cliente;

public class AgendamentoBO {

	private GeradordePlanilha geradorPlanilha = new GeradordePlanilha();
	private AgendamentoDAO agendamentoDAO = new AgendamentoDAO();

	public boolean atualizar(Agendamento agendamento) {
		boolean sucesso = agendamentoDAO.atualizar(agendamento);
		return sucesso;
	}

	public boolean salvar(Agendamento agendamento) {
		int idGerado = agendamentoDAO.salvar(agendamento);
		return idGerado > 0;
	}

	public String excluir(Integer idAgendamento) {
		String mensagem = "";
		AgendamentoDAO agendamentoDAO = new AgendamentoDAO();

		if (!agendamentoDAO.existeAgendamentoNovo(idAgendamento)) {
			mensagem = "Agendamento inexistente";
		} else {
			int statusPersistencia = agendamentoDAO.excluir(idAgendamento);

			if (statusPersistencia == 1) {
				mensagem = "Agendamento excluido com sucesso";
			} else if (statusPersistencia == 0) {
				mensagem = "Erro ao excluir Agendamento";
			}
		}
		return mensagem;
	}

	public List<Agendamento> listarTodos(String nomeCliente, LocalDate dataSelecionada) {
		AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
		return agendamentoDAO.listarTodos(nomeCliente, dataSelecionada);
	}

	public String gerarRelatorio(String caminhoArquivo, String nomeCliente, LocalDate dataSelecionada) {
		if (!caminhoArquivo.toLowerCase().endsWith(".xlsx")) {
			caminhoArquivo += ".xlsx";
		}
		String mensagem = geradorPlanilha.gerarPlanilha(caminhoArquivo,
				agendamentoDAO.listarTodos(nomeCliente, dataSelecionada));
		return mensagem;
	}

	public String filtroStatus(String status) {
		String mensagem = "";

		
		return mensagem;
	}

	public Cliente listarCelularCliente(int idAgendamento) {
		AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
		return agendamentoDAO.listarCelularCliente(idAgendamento);
	}

}
