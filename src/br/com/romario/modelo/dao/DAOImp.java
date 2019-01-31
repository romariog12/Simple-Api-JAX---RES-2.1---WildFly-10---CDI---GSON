package br.com.romario.modelo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;


public class DAOImp<T> implements DAO<T>{
	
	private EntityManager em;
	private Class<T> entity;

	public DAOImp(EntityManager em, Class<T> entity) {
		super();
		this.em = em;
		this.entity = entity;
	}
	@Override
	public T salvar(T entidade) {
		em.persist(entidade);
		return entidade;
	}
	@Override
	public T alterar(T entidade) {
		em.merge(entidade);
		return entidade;
	}
	@Override
	public void remover(T entidade) {
		em.remove(entidade);

	}
	@Override
	public T procurarPeloId(Long id) {
		return em.find(entity, id);
	}
	@Override
	public List<T> listarTudo() {
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(entity);
    	query.from(entity);
    	return em.createQuery(query).getResultList();
	}
	@Override
	public List<T> procurarComHQL(String hql, Object... values) {
		TypedQuery<T> query = em.createQuery(hql, entity);
		List<String> paramsId = pegarParametros(hql);
		for (int i = 0; i < paramsId.size(); i++)
			query.setParameter(paramsId.get(i), values[i]);
		return query.getResultList();
	}
	private List<String> pegarParametros(String hql) {
		Pattern pattern = Pattern.compile("(:\\w+)");
		Matcher matcher = pattern.matcher(hql);
		List<String> params = new ArrayList<>();
		while (matcher.find())
			params.add(matcher.group().replace(":", ""));
		return params;
	}

}
