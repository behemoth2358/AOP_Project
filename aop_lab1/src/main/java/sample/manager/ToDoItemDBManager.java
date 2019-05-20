package sample.manager;

import sample.helper.LogHelper;
import sample.model.ToDoItem;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ToDoItemDBManager implements IRepository<ToDoItem> {
	private IDatabaseManager databaseManager;
	
	public ToDoItemDBManager(String databaseName, String username, String userPassword) {
		this.databaseManager = new PostgreSQLDatabaseManager(databaseName, username, userPassword);
	}
	
	private ToDoItem map(ResultSet rs) {
		try {
			return new ToDoItem(
					rs.getInt("id"),
					rs.getString("val"),
					rs.getString("date")
			);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public List<ToDoItem> executeQuery(String query) {
		List<ToDoItem> results = new ArrayList<>();
		ResultSet rs = databaseManager.executeQuery(query);
		try {
			while (rs.next()) {
				ToDoItem entity = map(rs);
				
				if (entity != null) {
					results.add(entity);
				}
			}
		} catch (Exception e) {
			LogHelper.Instance.LogError(e);
		}
		
		return results;
	}
	
	@Override
	public int executeUpdate(String statement) {
		return databaseManager.executeUpdate(statement);
	}
}
