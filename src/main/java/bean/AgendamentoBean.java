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
    
    public String salvar() {
        AgendaDAO.salvar(agenda);
        agenda = new Agenda();
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

        return "agendamentos?faces-redirect=true";
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
