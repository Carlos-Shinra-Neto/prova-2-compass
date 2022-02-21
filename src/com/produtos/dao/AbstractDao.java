package com.produtos.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.produtos.jdbc.ConnectionFactory;

public abstract class AbstractDao<T> {

	protected ConnectionFactory connectionFactory;

	public AbstractDao() {
		connectionFactory = new ConnectionFactory();
	}

	protected abstract T transformResultSetToEntity(ResultSet result) throws SQLException;

	protected List<T> transformResultSetToEntityList(ResultSet result) throws SQLException {
		List<T> results = new ArrayList<>();
		T element = null;
		do {
			element = transformResultSetToEntity(result);
			if (element != null) {
				results.add(element);
			}
		} while (element != null);
		return results;
	}

}
