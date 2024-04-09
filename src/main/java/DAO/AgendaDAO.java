package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entidades.Agenda;
import util.JPAUtil;

public class AgendaDAO {
	//salvar, editar, excluir e listar
	
	public static void salvar(Agenda agenda) {
		EntityManager em = JPAUtil.criarEntityManager();
		System.out.println("ENTROU NO DAO --- SALVAR");
		em.getTransaction().begin();
		em.persist(agenda);
		em.getTransaction().commit();
		em.close();
	}
	
	public static void editar(Agenda agenda) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		em.merge(agenda);
		em.getTransaction().commit();
		em.close();
	}
	
	public static void excluir(Agenda a) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		a = em.find(Agenda.class, a.getId());
		em.remove(a);
		em.getTransaction().commit();
		em.close();
	}
	
	public static List<Agenda> listar() {
		EntityManager em = JPAUtil.criarEntityManager();
		Query qry = em.createQuery("select a from Agenda a");
		List<Agenda> resultado = qry.getResultList();
		return resultado;
	}
	
	
	public static long contar() {
		EntityManager em = JPAUtil.criarEntityManager();
		Query qry = em.createNamedQuery("select count(a) from Agenda a");
		long qtd = (long) qry.getSingleResult();
		em.close();
		return qtd;
	}
	
	public static Agenda acharPorId(Integer id) {
		EntityManager em = JPAUtil.criarEntityManager();
		Agenda a = em.find(Agenda.class, id);
		em.close();
		return a;
	}
	
	
}
