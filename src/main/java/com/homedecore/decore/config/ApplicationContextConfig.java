package com.homedecore.decore.config;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.homedecore.decore.dao.AccountDAO;
import com.homedecore.decore.dao.CartDAO;
import com.homedecore.decore.dao.CategoryDAO;
import com.homedecore.decore.dao.ProductDAO;
import com.homedecore.decore.dao.SupplierDAO;
import com.homedecore.decore.dao.UserDAO;
import com.homedecore.decore.daoimpl.AccountDAOImpl;
import com.homedecore.decore.daoimpl.CartDAOImpl;
import com.homedecore.decore.daoimpl.CategoryDAOImpl;
import com.homedecore.decore.daoimpl.ProductDAOImpl;
import com.homedecore.decore.daoimpl.SupplierDAOImpl;
import com.homedecore.decore.daoimpl.UserDAOImpl;
import com.homedecore.decore.model.Account;
import com.homedecore.decore.model.Cart;
import com.homedecore.decore.model.Category;
import com.homedecore.decore.model.Product;
import com.homedecore.decore.model.Supplier;
import com.homedecore.decore.model.User;
import com.homedecore.decore.model.UserDetails;
import com.homedecore.decore.model.UserRole;


@Configuration
@ComponentScan("com.homedecore.decore")
@EnableTransactionManagement
public class ApplicationContextConfig {
	

    
    @Bean(name = "dataSource")
    public DataSource getDataSource() {
    	BasicDataSource dataSource = new BasicDataSource();
    	dataSource.setDriverClassName("org.h2.Driver");
    	dataSource.setUrl("jdbc:h2:tcp://localhost/~/test3");
    	dataSource.setUsername("sa");
    	dataSource.setPassword("123123");
    	
    	return dataSource;
    }
    
    
    private Properties getHibernateProperties() {
    	Properties properties = new Properties();
    	
    	properties.put("hibernate.show_sql", "true");
    	properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
    	properties.put("hibernate.hbm2ddl.auto", "update");
    	properties.put("hibernate.format_sql", "true");
    	return properties;
    }
    
    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {
    	LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
    	sessionBuilder.addProperties(getHibernateProperties());
    	sessionBuilder.addAnnotatedClasses(Category.class);
    	sessionBuilder.addAnnotatedClasses(Supplier.class);
    	sessionBuilder.addAnnotatedClasses(User.class);
    	sessionBuilder.addAnnotatedClasses(Product.class);
    	sessionBuilder.addAnnotatedClasses(UserDetails.class);
    	sessionBuilder.addAnnotatedClasses(UserRole.class);
    	sessionBuilder.addAnnotatedClasses(Cart.class);
    	sessionBuilder.addAnnotatedClasses(Account.class);
    	return sessionBuilder.buildSessionFactory();
    }
    
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(
			SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(
				sessionFactory);

		return transactionManager;
	}
    
    @Autowired
    @Bean(name = "categoryDao")
    public CategoryDAO getCategoryDao(SessionFactory sessionFactory) {
    	return new CategoryDAOImpl(sessionFactory);
    }
    @Autowired
    @Bean(name = "productDao")
    public ProductDAO getProductDao(SessionFactory sessionFactory) {
    	return new ProductDAOImpl(sessionFactory);
    }
    @Autowired
    @Bean(name = "cartDao")
    public CartDAO getCartDao(SessionFactory sessionFactory) {
    	return new CartDAOImpl(sessionFactory);
    }
    @Autowired
    @Bean(name = "supplierDao")
    public SupplierDAO getSupplierDao(SessionFactory sessionFactory) {
    	return new SupplierDAOImpl(sessionFactory);
    }
    @Autowired
    @Bean(name = "userDao")
    public UserDAO getUserDao(SessionFactory sessionFactory) {
    	return new UserDAOImpl(sessionFactory);
    }
    @Autowired
    @Bean(name = "accountDao")
    public AccountDAO getAccountDao(SessionFactory sessionFactory) {
    	return new AccountDAOImpl(sessionFactory);
    }


}
