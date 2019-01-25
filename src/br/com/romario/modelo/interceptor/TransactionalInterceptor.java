package br.com.romario.modelo.interceptor;


import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import br.com.romario.modelo.anotacao.Transactional;


@Transactional
@Interceptor
public class TransactionalInterceptor {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@AroundInvoke
	public Object invoke(InvocationContext context) {
		Object o = null;
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			o = context.proceed();
            if (transaction.getRollbackOnly()) {
                transaction.rollback();
            } else {
                transaction.commit();
            }
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        return o;
	}
}
