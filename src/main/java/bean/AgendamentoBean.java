package bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import DAO.AgendaDAO;
import entidades.Agenda;

@ManagedBean
public class AgendamentoBean {
    private Agenda agenda = new Agenda();
    private List<Agenda> lista;
    private Agenda agendaSelecionado;
    
    private void showMessage(String title, String content) {
    	FacesContext.getCurrentInstance().addMessage(null,
    			new FacesMessage(FacesMessage.SEVERITY_INFO, title, content));
    }

    public String salvar() {
    	try {
    		if (!AgendaDAO.agendaExistente(agenda)) {
    			AgendaDAO.salvar(agenda);
    			agenda = new Agenda();
    			showMessage("Sucesso!", "Agendamento concluído!");
    			return "agendamentos";
    			
    		} else
    		
    		showMessage("Ops!", "Agendamento já existe!");
    		return "gerar_agendamento";
    		
    	} catch (IllegalArgumentException e) {
    		showMessage("Error:", e.getMessage());
    		return "gerar_agendamento";
    	}
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

        return "agendamentos?faces-redirect=true";
    }
    
    public String editar(Agenda aEdit) {
    	Agenda agendaUnchanged = getAgendaById(aEdit.getId());
    	boolean nomeEdit = !agendaUnchanged.getPaciente().equals(aEdit.getPaciente());
    	System.out.println("Nome: " + agendaUnchanged.getPaciente() + "\n" 
    			+ "Alteração do nome: " + nomeEdit);
    	if (nomeEdit || !AgendaDAO.agendaExistente(aEdit)) {
    	AgendaDAO.editar(aEdit);
    	showMessage("OK", "Alterado com sucesso!");
    	return "agendamentos";
    	}
    	else {
    		showMessage("Ops!", "Não pode ser editado!");
    		return "agendamentos";
    	}

    }
    
    public void mostrarDetalhe(Agenda a) {
        agendaSelecionado = a;
        PrimeFaces.current().executeScript("PF('detalhesDialog').show()");
    }
    
    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }
    
    public Agenda getAgendaById(Integer id ) {
    	return AgendaDAO.acharPorId(id);
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
    
    public Agenda getAgendaSelecionado() {
        return agendaSelecionado;
    }

    public void setAgendaSelecionado(Agenda agendaSelecionado) {
        this.agendaSelecionado = agendaSelecionado;
    }
}
