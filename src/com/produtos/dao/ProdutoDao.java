package com.produtos.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.produtos.domain.Produto;
import com.produtos.jdbc.ConnectionConfig;

public class ProdutoDao extends AbstractDao<Produto> {

	public Produto findById(Long id) {
		StringBuilder sql = new StringBuilder(getSelectAllFields("PRODUTO"));

		sql.append("WHERE		");
		sql.append("	PRODUTO.").append(Produto.COLUMN_ID).append("= ?				");

		try {
			return transformResultSetToEntity(connectionFactory.getResult(sql.toString(), id));
		} catch (SQLException e) {
			throw new IllegalArgumentException("Erro na execução da query", e);
		}
	}

	public List<Produto> findByNome(String nome) {
		StringBuilder sql = new StringBuilder(getSelectAllFields("PRODUTO"));

		sql.append("WHERE																	");
		sql.append("	LOWER(PRODUTO.").append(Produto.COLUMN_NOME).append(") LIKE ?		");
		sql.append("ORDER BY																");
		sql.append("	PRODUTO.").append(Produto.COLUMN_ID).append(" ASC					");

		try {
			return transformResultSetToEntityList(
					connectionFactory.getResult(sql.toString(), "%" + nome.toLowerCase() + "%"));
		} catch (SQLException e) {
			throw new IllegalArgumentException("Erro na execução da query", e);
		}
	}

	public List<Produto> findAll() {
		StringBuilder sql = new StringBuilder(getSelectAllFields("PRODUTO"));

		sql.append("ORDER BY												");
		sql.append("	PRODUTO.").append(Produto.COLUMN_ID).append(" ASC	");

		try {
			return transformResultSetToEntityList(connectionFactory.getResult(sql.toString()));
		} catch (SQLException e) {
			throw new IllegalArgumentException("Erro na execução da query", e);
		}
	}

	public void update(Produto element) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE ");
		sql.append(ConnectionConfig.DATABASE);
		sql.append(".");
		sql.append(Produto.TABLE_NAME);
		sql.append(" SET ");
		sql.append(Produto.COLUMN_DESCONTO);
		sql.append("=? WHERE ");
		sql.append(Produto.COLUMN_ID);
		sql.append("=?");
		connectionFactory.executeSql(sql.toString(), element.getDesconto(), element.getId());
	}

	public void deleteDesconto(Produto element) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE ");
		sql.append(ConnectionConfig.DATABASE);
		sql.append(".");
		sql.append(Produto.TABLE_NAME);
		sql.append(" SET ");
		sql.append(Produto.COLUMN_DESCONTO);
		sql.append("=null WHERE ");
		sql.append(Produto.COLUMN_ID);
		sql.append("=?");
		connectionFactory.executeSql(sql.toString(), element.getId());
	}

	public void update(List<Produto> elements) {
		elements.forEach(this::update);
	}

	@Override
	protected Produto transformResultSetToEntity(ResultSet result) throws SQLException {
		if (!result.next()) {
			return null;
		}
		Produto produto = null;
		produto = new Produto();
		produto.setId(result.getLong(1));
		produto.setNome(result.getString(2));
		produto.setDescricao(result.getString(3));
		produto.setDesconto(result.getDouble(4));
		produto.setDataInicio(result.getDate(5));
		return produto;
	}

	private String getSelectAllFields(String alias) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT																				");
		sql.append("	" + alias + "." + Produto.COLUMN_ID + ",										");
		sql.append("	" + alias + "." + Produto.COLUMN_NOME + ",										");
		sql.append("	" + alias + "." + Produto.COLUMN_DESCRICAO + ",									");
		sql.append("	" + alias + "." + Produto.COLUMN_DESCONTO + ",									");
		sql.append("	" + alias + "." + Produto.COLUMN_DATA_INICIO + "								");
		sql.append("FROM																				");
		sql.append("	" + ConnectionConfig.DATABASE + "." + Produto.TABLE_NAME + " " + alias + "		");
		return sql.toString();
	}

}
