package com.homedecore.decore.dao;

import com.homedecore.decore.model.Account;

public interface AccountDAO {
	public Account get(String id);
	public boolean trasfer(String id, int amount);


}
