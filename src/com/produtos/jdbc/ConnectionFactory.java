package com.produtos.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.produtos.enums.SentimentoEnum;

public class ConnectionFactory {

	private Connection connection;

	public ConnectionFactory() {
		createConnection();
	}

	private void createConnection() {
		try {
			Class.forName(ConnectionConfig.DRIVER);
			connection = DriverManager.getConnection(ConnectionConfig.getUrl(), ConnectionConfig.USER,
					ConnectionConfig.PASSWORD);
		} catch (SQLException e) {
			throw new IllegalArgumentException("Erro na conexão ao banco", e);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Driver do banco não encontrado", e);
		}
	}

	public void executeSql(String sql, Object... params) {
		try {
			execute(sql, params);
		} catch (SQLException e) {
			System.err.print("Erro na transação!" + e.getMessage());
		}
	}

	private int execute(String sql, Object... params) throws SQLException {
		int result = -1;
		PreparedStatement ps = null;
		try {
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(sql);

			int index = 1;
			for (Object param : params) {
				setParam(ps, index++, param);
			}

			result = ps.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			System.err.println("Houve um erro na execução da query");
			System.err.println(sql);
			connection.rollback();
			System.err.print("Rollback efetuado na transação");
		} finally {
			if (ps != null) {
				ps.close();
			}
			connection.setAutoCommit(true);
		}
		return result;
	}

	private void setParam(PreparedStatement ps, int index, Object obj) throws SQLException {
		if (obj instanceof Integer) {
			ps.setInt(index, (Integer) obj);
		} else if (obj instanceof Long) {
			ps.setLong(index, (Long) obj);
		} else if (obj instanceof Double) {
			ps.setDouble(index, (Double) obj);
		} else if (obj instanceof Float) {
			ps.setFloat(index, (Float) obj);
		} else if (obj instanceof String) {
			ps.setString(index, (String) obj);
		} else if (obj instanceof Character) {
			ps.setString(index, obj.toString());
		} else if (obj instanceof SentimentoEnum) {
			ps.setString(index, ((SentimentoEnum) obj).getDescricao());
		}
	}

	public ResultSet getResult(String sql, Object... params) {
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			int index = 1;
			for (Object param : params) {
				setParam(ps, index++, param);
			}
			return ps.executeQuery();
		} catch (SQLException e) {
			throw new IllegalArgumentException("Erro na execução da query", e);
		}
	}

}
