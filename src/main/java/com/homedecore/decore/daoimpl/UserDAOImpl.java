package com.homedecore.decore.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.homedecore.decore.dao.UserDAO;
import com.homedecore.decore.model.User;
import com.homedecore.decore.model.UserDetails;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO {
	

	@Autowired
	private SessionFactory sessionFactory;


	public UserDAOImpl(SessionFactory sessionFactory) {
		System.out.println("in dao");
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public List<User> list() {
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) sessionFactory.getCurrentSession()
				.createCriteria(User.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return list;
	}

	@Transactional
	public void saveOrUpdate(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}
	
	@Transactional
	public void saveOrUpdate(UserDetails userDetails) {
		sessionFactory.getCurrentSession().saveOrUpdate(userDetails);
	}


	@Transactional
	public void delete(String username) {
		User user = new User();
		user.setUsername(username);
		sessionFactory.getCurrentSession().delete(user);
	}

	@Transactional
	public User get(String username) {
		String hql = "from User where username=" + "'"+ username+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) query.list();
		
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		
		return null;
	}
	
	@Transactional
	public boolean isValidUser(String username, String password, boolean isAdmin) {
		String hql = "from User where username= '" + username + "' and " + " password ='" + password+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) query.list();
		
		if (list != null && !list.isEmpty()) {
			return true;
		}
		
		return false;
	}


}
