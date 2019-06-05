package br.com.utilitarios;

import java.util.Comparator;

/**
 *
 * @author Welington Zanon
 */
public class ArrayListOrganiza {

	private String atribuicao;
	private int valor;

	public String getAtribuicao() {
		return atribuicao;
	}

	public void setAtribuicao() {
		this.atribuicao = atribuicao;
	}

	public int getValor() {
		return valor;
	}

	public void setValor() {
		this.valor = valor;
	}

	public ArrayListOrganiza(int valor, String atribuicao) {
		this.atribuicao = atribuicao;
		this.valor = valor;
	}

	public static Comparator<ArrayListOrganiza> FreteAtribuicao = new Comparator<ArrayListOrganiza>() {
		public int compare(ArrayListOrganiza s1, ArrayListOrganiza s2) {
			String atribuicao1 = s1.getAtribuicao().toUpperCase();
			String atribuicao2 = s2.getAtribuicao().toUpperCase();

			return atribuicao1.compareTo(atribuicao2);
		}
	};
	public static Comparator<ArrayListOrganiza> FreteValor = new Comparator<ArrayListOrganiza>() {
		public int compare(ArrayListOrganiza s1, ArrayListOrganiza s2) {
			int valor1 = s1.getValor();
			int valor2 = s2.getValor();

			return valor1 - valor2;
		}
	};

	public String toString() {
		return atribuicao + valor;
	}

}
