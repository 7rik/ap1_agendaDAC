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
	
	public String editar(Agenda aEdit) {
		try {
			
	        Agenda agendaOriginal = getAgendaById(aEdit.getId());
	        boolean nomeAlterado = !agendaOriginal.getPaciente().equals(aEdit.getPaciente());
	        System.out.println("NOME ORIGINAL" + agendaOriginal.getPaciente());
	        System.out.println("NOME EDITADO" + nomeAlterado);
	        System.out.println("DATA EDITADA" + nomeAlterado);
//	        if (nomeAlterado || !AgendaDAO.verificarAgendamentoExistente(aEdit)) {
//	        	System.out.println(aEdit);
//	            AgendaDAO.edit(aEdit);
//	            showMessage("Agenda editada com sucesso!", "");
//	            System.out.println("FOI EDITADO");
//	            return "agendamentos";
//	        } else {
//	            showMessage("Erro! Já existe um agendamento para a mesma data, hora e médico", "");
//	            System.out.println("NAO FOI EDITADO PQ JA EXISTE UM AGENDAMENTO COM DATA/HORA/MEDICO IGUAL");
//	            return "agendamentos";
//	        }
	    } catch (Exception e) {
	        System.out.println("Erro" + e.getMessage());
	        return null;
	    }
		return null;
	}
	
	public String excluir(Agenda a) {
		try {
			AgendaDAO.excluir(a);
			agenda = new Agenda();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exclusão", "Excluído com sucesso"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Culpe o programador"));
		}

		return null;
	}
	
	public Agenda getAgendaById(int id) {
		return AgendaDAO.acharPorId(id);
	}
	
	public void mostrarDetalhe(Agenda a) {
		try {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Detalhes",
							"Lancamento:\n" + "Data e Hora: " + a.getDataHoraConsulta() + "\n"
									+ "Paciente: " + a.getPaciente() + "\n" + "Médico: " + a.getMedico()));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Culpe o programador"));
		}
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
