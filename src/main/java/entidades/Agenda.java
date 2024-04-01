package entidades;

import java.util.Date;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
public class Agenda {
	@Id
	@GeneratedValue
	private Integer Id;
	@Column(name = "paciente", length = 100)
	private String paciente;
	@Column(name = "medico", length = 100)
	private String medico;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHoraConsulta;
	
	@Override
	public String toString() {
		return "Agenda [Id=" + Id + ", paciente=" + paciente + ", medico=" + medico + ", dataHoraConsulta="
				+ dataHoraConsulta + "]";
	}
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getPaciente() {
		return paciente;
	}
	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}
	public String getMedico() {
		return medico;
	}
	public void setMedico(String medico) {
		this.medico = medico;
	}
	public Date getDataHoraConsulta() {
		return dataHoraConsulta;
	}
	public void setDataHoraConsulta(Date dataHoraConsulta) {
		this.dataHoraConsulta = dataHoraConsulta;
	} 
	
	
}
