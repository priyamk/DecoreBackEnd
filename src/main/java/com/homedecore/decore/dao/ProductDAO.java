package com.homedecore.decore.dao;

import java.util.List;

import com.homedecore.decore.model.Product;

public interface ProductDAO {


	public List<Product> list();

	public Product get(String id);

	public void saveOrUpdate(Product product);

	public void delete(String id);


}
