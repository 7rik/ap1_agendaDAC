package bean;

import java.util.List;

import DAO.AgendaDAO;
import entidades.Agenda;

public class AgendamentoBean {
	private Agenda agenda = new Agenda();
	private List<Agenda> lista;
	
	public String salvar() {
		AgendaDAO.salvar(agenda);
		agenda = new Agenda();
		return null;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public List<Agenda> getLista() {
		if (lista == null) {
			lista = AgendaDAO.listar();
		}
		return lista;
	}
	/* Se a lista Agenda ainda não foi carregada, ela a carrrega
	 * chamando o método Listar() da classe AgendaDAO. 
	 * armazena em uma variável de instância chamada lista. Se a lista já 
	 * foi carregada anteriormente, ele simplesmente retorna a lista existente. 
	 * Isso pode ser útil para evitar múltiplas consultas ao banco de dados se a lista 
	 * já foi carregada anteriormente.*/

	public void setLista(List<Agenda> lista) {
		this.lista = lista;
	}
	

}
