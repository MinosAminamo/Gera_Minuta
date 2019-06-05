package br.com.utilitarios;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Welington
 */
public class conexao {
	final private String drive = "org.apache.derby.jdbc.ClientDriver";
	final private String url = "jdbc:derby:FedexAereo";
	final private String usuario = "root";
	final private String senha = "root";
	private Connection conexao;
	public Statement statement;
	public ResultSet resultset;

	public boolean conecta() {
		boolean result = true;
		try {
			Class.forName(drive);
			conexao = DriverManager.getConnection(url, usuario, senha);
			// JOptionPane.showMessageDialog(null,"Conectou");
		} catch (ClassNotFoundException Drive) {
			JOptionPane.showMessageDialog(null, "Drive não localizado:" + Drive);
			result = false;
		} catch (SQLException Fonte) {
			JOptionPane.showMessageDialog(null, "Deu erro na conexão com a fonte de dados:" + Fonte);
			result = false;
		}
		return result;
	}

	public void desconecta() {
		try {
			conexao.close();
			JOptionPane.showMessageDialog(null, "Banco Fechado");
		} catch (SQLException erroSQL) {
			JOptionPane.showMessageDialog(null, "Não foi possivel fechar o banco de dados:" + erroSQL.getMessage());
		}
	}

	public void executeSQL(String sql) {
		try {
			statement = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			resultset = statement.executeQuery(sql);
		} catch (SQLException sqlex) {
			JOptionPane.showMessageDialog(null,
					"Não foi possivel executar o comando sql " + sqlex + ", o sql passo foi " + sql);

		}

	}

}
