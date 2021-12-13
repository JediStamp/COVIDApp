package servlets;

import beans.app.User;

public interface Observer {
	public void update(User user);
}
