package br.com.form;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;
import br.com.utilitarios.conexao;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// MainTeste janela = new MainTeste();
		FormInicial janela = new FormInicial();

		// instanciou
		conexao Conexao;
		Conexao = new conexao();
		Conexao.conecta();

		Date DataCorte = new Date();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");

		try {

			Conexao.executeSQL("select * from validar");
			Conexao.resultset.next();
			// DataCorte = Conexao.resultset.getString("DATA");
			try {
				DataCorte = formatador.parse(Conexao.resultset.getString("DATA"));

			} catch (ParseException Erro) {

			}

			//JOptionPane.showMessageDialog(null, "teste " + DataCorte + " poha");

		} catch (SQLException erro) {

		}

		if (cal.getTime().after(DataCorte)) {
			// JOptionPane.showMessageDialog(null, "Esta Comparando");
			janela.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "Erro KUPP2.dll");

		}

	}

}
