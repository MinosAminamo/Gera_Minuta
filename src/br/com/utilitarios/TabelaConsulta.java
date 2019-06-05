/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.utilitarios;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author WelingtonZ01
 */
public class TabelaConsulta extends AbstractTableModel {
	private final int nome = 0;
	private final int telefone = 1;

	private final String colunas[] = { "CONTATO", "TELEFONE" };
	private final List<Consulta> dados;

	public TabelaConsulta(List<Consulta> dados) {
		this.dados = dados;
	}

	public int getColumnCount() {
		return colunas.length;
	}

	public int getRowCount() {
		return dados.size();
	}

	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case nome:
			return String.class;
		case telefone:
			return String.class;
		default:
			throw new IndexOutOfBoundsException("");

		}
	}

	public String getColumnName(int columnIndex) {
		return colunas[columnIndex];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Consulta consulta = dados.get(rowIndex);

		switch (columnIndex) {
		case nome:
			return consulta.getNome();
		case telefone:
			return consulta.getTelefone();
		default:
			throw new IndexOutOfBoundsException("");

		}

	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Consulta consulta = dados.get(rowIndex);
	}

	public Consulta getValue(int rowIndex) {
		return dados.get(rowIndex);
	}

}
