package org.test;

import java.util.List;

public interface IUserDao {

	public void add(User user) throws UserException;
	
	public void delete(String name);
	
	public List<User> getList();
	
	public User getUser(int i);
}
