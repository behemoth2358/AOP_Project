package sample.manager;

import java.util.List;

public interface IRepository<T> {
	List<T> executeQuery (String query);
	int executeUpdate (String statement);
}
