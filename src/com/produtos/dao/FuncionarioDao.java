package com.produtos.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.produtos.domain.Funcionario;
import com.produtos.enums.SentimentoEnum;
import com.produtos.jdbc.ConnectionConfig;

public class FuncionarioDao extends AbstractDao<Funcionario> {

	public void insert(Funcionario element) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO ");
		sql.append(ConnectionConfig.DATABASE);
		sql.append(".");
		sql.append(Funcionario.TABLE_NAME);
		sql.append(" (");
		sql.append(Funcionario.COLUMN_NOME);
		sql.append(", ");
		sql.append(Funcionario.COLUMN_SENTIMENTO);
		sql.append(") values (?, ?)");
		connectionFactory.executeSql(sql.toString(), element.getNome(), element.getSentimento());
	}

	@Override
	protected Funcionario transformResultSetToEntity(ResultSet result) throws SQLException {
		if (!result.next()) {
			return null;
		}
		Funcionario funcionario = null;
		funcionario = new Funcionario();
		funcionario.setId(result.getLong(1));
		funcionario.setNome(result.getString(2));
		funcionario.setSentimento(SentimentoEnum.getEnumByDescricao(result.getString(5)));
		return funcionario;
	}

}
