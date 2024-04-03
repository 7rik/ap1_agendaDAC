package bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import DAO.AgendaDAO;
import entidades.Agenda;

@ManagedBean
public class AgendamentoBean {
	private Agenda agenda = new Agenda();
	private List<Agenda> lista;
	
	public String salvar() {
		AgendaDAO.salvar(agenda);
		agenda = new Agenda();
		return null;
	}
	
	public String excluir(Integer id) {
		try {
			System.out.println("PENETROU BEAN NO DELETE");
			AgendaDAO.excluir(id);
			System.out.println("ELE EXCLUIU O AGENDAMENTO" + id);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exclusão", "Excluído com sucesso"));
		} catch (Exception e) {
			System.out.println("NAO EXCLUIU");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Culpe o programador"));
		}

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


	public void setLista(List<Agenda> lista) {
		this.lista = lista;
	}
	

}
