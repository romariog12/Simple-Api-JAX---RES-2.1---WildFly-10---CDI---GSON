package br.com.romario.modelo.dao;

import java.lang.reflect.ParameterizedType;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class DAOFactory {

	@PersistenceContext(unitName="livroDS")
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Dependent
	@Produces
	public <T> DAOImp<T> getDAO(InjectionPoint injectionPoint) {
		ParameterizedType parameterizedType = (ParameterizedType) injectionPoint.getType();
		Class<T> classe = (Class<T>) parameterizedType.getActualTypeArguments()[0];
		return new DAOImp<T>(entityManager, classe);
	}

}
