package com.fgk.im.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

/**
 * 
 * @author 范广凯
 * 
 * @param <E>
 *            实体对象名
 * @param <PK>
 *            主键类型
 */
public class BaseDao<E, PK extends Serializable>
{
	@Autowired
	private SessionFactory sessionFactory;//applicationContext中定义的

	private Class<E> entityClass;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BaseDao() 
	{
		this.entityClass = null;
		Class c = getClass();
		Type type = c.getGenericSuperclass();
		if (type instanceof ParameterizedType)
		{
			Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
			this.entityClass = (Class<E>) parameterizedType[0];
		}
	}

	protected Session getSession() 
	{
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public E queryById(PK id)
	{
		Assert.notNull(id, "id is required");
		return (E) getSession().get(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	public PK add(E entity)
	{
		Assert.notNull(entity, "entity is required");
		return (PK) getSession().save(entity);
	}

	public void addOrUpdate(E entity) 
	{
		Assert.notNull(entity, "entity is required");
		getSession().saveOrUpdate(entity);
	}

	public void update(E entity) 
	{
		Assert.notNull(entity, "entity is required");
		getSession().clear();
		getSession().update(entity);
	}

	public void delete(E entity)
	{
		Assert.notNull(entity, "entity is required");
		getSession().delete(entity);
	}

	public void delete(PK id) 
	{
		Assert.notNull(id, "id is required");
		E entity = queryById(id);
		getSession().delete(entity);
	}

	/**
	 * 通过主键删除一批记录 
	 * @param ids
	 */
	public void delete(PK[] ids) 
	{
		Assert.notEmpty(ids, "ids must not be empty");
		for (PK id : ids) 
		{
			E entity = queryById(id);
			getSession().delete(entity);
		}
	}

	/**
	 * 创建一个Query
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	public Query createQuery(final String hql, final Object... params) 
	{
		Assert.hasText(hql, "queryString不能为空");
		Query query = getSession().createQuery(hql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) 
			{
				query.setParameter(i, params[i]);
			}
		}
		return query;
	}

	/**
	 *创建一个SQLQuery
	 * 
	 * @param sql
	 * @param params
	 * @return SQLQuery
	 */
	public SQLQuery createSQLQuery(final String sql, final Object... params) 
	{
		Assert.hasText(sql, "queryString不能为空");
		SQLQuery query = getSession().createSQLQuery(sql);
		if (params != null) 
		{
			for (int i = 0; i < params.length; i++)
			{
				query.setParameter(i, params[i]);
			}
		}
		return query;
	}

	/**
	 * 执行HQL语句
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<E> queryListByHql(final String hql, final Object... params)
	{
		Assert.hasText(hql, "hql不能为空");
		Query query = getSession().createQuery(hql);
		if (params != null) 
		{
			for (int i = 0; i < params.length; i++)
			{
				query.setParameter(i, params[i]);
			}
		}
		return query.list();
	}

	/**
	 * 执行SQL语句
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<E> queryListBySql(final String sql, final Object... params) 
	{
		Assert.hasText(sql, "hql不能为空");
		SQLQuery query = getSession().createSQLQuery(sql);
		if (params != null)
		{
			for (int i = 0; i < params.length; i++) 
			{
				query.setParameter(i, params[i]);
			}
		}
		query.addEntity(entityClass);
		return query.list();
	}

}
