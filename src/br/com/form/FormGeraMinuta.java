/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import br.com.utilitarios.ArrayListOrganiza;
import br.com.utilitarios.FixedLengthDocument;
import br.com.utilitarios.IntegerDocument;
import br.com.utilitarios.imprime;

/**
 *
 * @author Welington Zanon
 */
@SuppressWarnings("serial")
public class FormGeraMinuta extends FormModel {

	// variaveis___________________________________________________
	private JLabel jL_Adicionais, jL_Destino, jL_Origem, jL_Peso, jL_Qtd_Volumes, jL_NotaFiscal, jL_Embalagem,
			jL_Conteudo;
	private JComboBox<String> jCBX_Destino, jCBX_Origem, jCBX_Tarifa;
	private JTextField jT_Adicionais, jT_Peso, jT_Qtd_Volume, jT_NotaFiscal, jT_Embalagem, jT_Conteudo;
	private ButtonGroup buttonGroup_Opcoes, buttonGroup_Especial;
	private JRadioButton jRB_Opcao1, jRB_Opcao2, jRb_Opcao3, jRb_Opcao4, jRb_Opcao5, jRB_Especial, jRB_Normal;
	private JButton jB_Sair, jB_Imprimir, jB_Cadastro;
	private String valorFinal, CompaniaAerea = "", TipoServico, usuario,
			Aviso = " Clicando no icone no canto inferior da tela\n"
					+ "acesse mais informações sobre premissas e vetos.\n\n"
					+ "Em caso de duvidas entrar em contato utilizando telefones também listados abaixo.";
	private boolean TarifaEspecial = false;

	imprime minuta;

	public FormGeraMinuta(String usuario) {
		initComp(usuario);
		jcbx_atualiza();
		JOptionPane.showMessageDialog(null, Aviso, "     PREMISSAS E VETOS", JOptionPane.ERROR_MESSAGE,
				new javax.swing.ImageIcon(getClass().getResource("/br/com/img/Ajuda2.png")));

	}

	private void initComp(String usuario) {

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle(titulo + " Usuário ativo: " + usuario);
		this.usuario = usuario;
		// iniciando variaveis________________________________________
		jL_Origem = new JLabel("ORIGEM: ");
		jL_Destino = new JLabel("DESTINO: ");
		jL_Qtd_Volumes = new JLabel("VOLUMES:");
		jL_Peso = new JLabel("PESO Kg:");
		jL_Adicionais = new JLabel("INFORMAÇÕES ADICIONAIS:");
		jL_NotaFiscal = new JLabel("NOTA FISCAL:");
		jL_Embalagem = new JLabel("EMBALAGEM / DESCRIÇÃO");
		jL_Conteudo = new JLabel("DECLARAÇÃO DE CONTEUDO");
		jCBX_Destino = new JComboBox<String>();
		jCBX_Origem = new JComboBox<String>();
		jCBX_Tarifa = new JComboBox<String>();
		jT_Qtd_Volume = new JTextField();
		jT_Peso = new JTextField();
		jT_Adicionais = new JTextField();
		jT_NotaFiscal = new JTextField();
		jT_Embalagem = new JTextField();
		jT_Conteudo = new JTextField();
		buttonGroup_Especial = new ButtonGroup();
		buttonGroup_Opcoes = new ButtonGroup();
		jRB_Especial = new JRadioButton("Com Tarifa Especial:");
		jRB_Normal = new JRadioButton("Sem Tarifa Especial:");
		jRB_Opcao1 = new JRadioButton("OPÇÃO 1");
		jRB_Opcao2 = new JRadioButton("OPÇÃO 2");
		jRb_Opcao3 = new JRadioButton("OPÇÃO 3");
		jRb_Opcao4 = new JRadioButton("OPÇÃO 4");
		jRb_Opcao5 = new JRadioButton("OPÇÃO 5");
		jB_Cadastro = new javax.swing.JButton("CADASTRO");
		jB_Imprimir = new javax.swing.JButton("IMPRIMIR");
		jB_Sair = new javax.swing.JButton("SAIR");
		// Jlabel_______________________________________________________
		jL_Origem.setBounds(35, 30, 70, 30);
		add(jL_Origem);
		jL_Destino.setBounds(240, 30, 70, 30);
		add(jL_Destino);
		jL_Peso.setBounds(445, 30, 70, 30);
		add(jL_Peso);
		jL_Qtd_Volumes.setBounds(600, 30, 100, 30);
		add(jL_Qtd_Volumes);
		jL_Embalagem.setBounds(605, 60, 160, 30);
		jL_Embalagem.setFont(new java.awt.Font("Arial", 0, 10));
		add(jL_Embalagem);
		jL_Conteudo.setBounds(598, 110, 160, 30);
		jL_Conteudo.setFont(new java.awt.Font("Arial", 0, 10));
		add(jL_Conteudo);
		jL_NotaFiscal.setBounds(35, 135, 150, 30);
		add(jL_NotaFiscal);
		jL_Adicionais.setBounds(35, 175, 200, 30);
		add(jL_Adicionais);

		// Jcombox_________________________________________________________
		jCBX_Origem.setBounds(90, 30, 130, 35);
		jCBX_Origem.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "LDB" }));
		add(jCBX_Origem);
		jCBX_Destino.setBounds(300, 30, 130, 35);
		add(jCBX_Destino);
		jCBX_Tarifa.setBounds(175, 82, 370, 35);
		jCBX_Tarifa.setModel(
				new javax.swing.DefaultComboBoxModel<String>(new String[] { "001-Jornais, Revistas", "010-Animais Vivos",
						"020-Restos Mortais", "070-Confecções em geral, fibras, tecidos e manufaturados", "080-Vacinas",
						"140-Livros em Geral", "180-Alime. Ind., Geral Frut. do Mar, Peixes Orna., Doces, Hortifruti",
						"200-Prod. Farm., Cosméticos, Dentários," + "Opticos e Hospitalares",
						"210-Flores e Plantas Vivas", "240-Material Escolar" }));
		jCBX_Tarifa.setEnabled(false);
		jCBX_Tarifa.setToolTipText(
				"Selecione \"Com Tarifa Especial\" para habilitar categoria de tarifas especiais e então selecione um categoria aqui.");
		add(jCBX_Tarifa);

		// jtexfield______________________________________
		jT_Peso.setBounds(505, 30, 80, 35);
		jT_Peso.setDocument(new IntegerDocument(8));
		jT_Peso.setHorizontalAlignment(JTextField.CENTER);
		add(jT_Peso);
		jT_Qtd_Volume.setBounds(665, 30, 80, 35);
		jT_Qtd_Volume.setDocument(new IntegerDocument(6));
		jT_Qtd_Volume.setHorizontalAlignment(JTextField.CENTER);
		add(jT_Qtd_Volume);
		jT_Embalagem.setBounds(600, 85, 148, 30);
		jT_Embalagem.setHorizontalAlignment(JTextField.CENTER);
		jT_Embalagem.setDocument(new FixedLengthDocument(14));
		add(jT_Embalagem);
		jT_NotaFiscal.setBounds(120, 135, 425, 30);
		jT_NotaFiscal.setDocument(new FixedLengthDocument(64));
		add(jT_NotaFiscal);
		jT_Conteudo.setBounds(600, 135, 148, 30);
		jT_Conteudo.setHorizontalAlignment(JTextField.CENTER);
		jT_Conteudo.setDocument(new FixedLengthDocument(16));
		add(jT_Conteudo);
		jT_Adicionais.setBounds(200, 175, 550, 30);
		jT_Adicionais.setDocument(new FixedLengthDocument(100));
		add(jT_Adicionais);

		// Botoes______________________________________________________________
		jB_Imprimir.setBounds(630, 250, 100, 35);
		add(jB_Imprimir);
		jB_Cadastro.setBounds(630, 300, 100, 35);
		add(jB_Cadastro);
		jB_Sair.setBounds(630, 350, 100, 35);
		add(jB_Sair);

		// radiobutton_________________________________________________
		ActionListener radioButtonEspActionListener = new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				radioButtonEspActionPerformed(evt);
			}
		};
		buttonGroup_Especial.add(jRB_Especial);
		jRB_Especial.setBounds(30, 95, 250, 35);
		jRB_Especial.addActionListener(radioButtonEspActionListener);
		jRB_Especial.setToolTipText(
				"Para usar uma tabela com valores especiais  marque aqui e depois selecione uma categoria a seguir");
		add(jRB_Especial);
		buttonGroup_Especial.add(jRB_Normal);
		jRB_Normal.setSelected(true);
		jRB_Normal.setBounds(30, 70, 250, 35);
		jRB_Normal.addActionListener(radioButtonEspActionListener);
		jRB_Normal.setToolTipText("Marque aqui para usar a tabela de frete normal");
		add(jRB_Normal);

		ActionListener radioButtonActionListener = new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				radioButtonActionPerformed(evt);
			}
		};
		buttonGroup_Opcoes.add(jRB_Opcao1);
		jRB_Opcao1.setEnabled(false);
		jRB_Opcao1.setBounds(35, 240, 250, 35);
		jRB_Opcao1.addActionListener(radioButtonActionListener);
		add(jRB_Opcao1);
		buttonGroup_Opcoes.add(jRB_Opcao2);
		jRB_Opcao2.setEnabled(false);
		jRB_Opcao2.setBounds(35, 270, 250, 35);
		jRB_Opcao2.addActionListener(radioButtonActionListener);
		add(jRB_Opcao2);
		buttonGroup_Opcoes.add(jRb_Opcao3);
		jRb_Opcao3.setEnabled(false);
		jRb_Opcao3.setBounds(35, 300, 250, 35);
		jRb_Opcao3.addActionListener(radioButtonActionListener);
		add(jRb_Opcao3);
		buttonGroup_Opcoes.add(jRb_Opcao4);
		jRb_Opcao4.setEnabled(false);
		jRb_Opcao4.setBounds(35, 330, 250, 35);
		jRb_Opcao4.addActionListener(radioButtonActionListener);
		add(jRb_Opcao4);
		buttonGroup_Opcoes.add(jRb_Opcao5);
		jRb_Opcao5.setEnabled(false);
		jRb_Opcao5.setBounds(35, 360, 250, 35);
		jRb_Opcao5.addActionListener(radioButtonActionListener);
		add(jRb_Opcao5);

		// Listeners_____________________________________________________
		jT_Peso.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jT_PesoActionPerformed(evt);
			}
		});
		jT_Peso.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusLost(java.awt.event.FocusEvent evt) {
				jT_PesoFocusLost(evt);
			}
		});
		jCBX_Destino.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				jCBX_DestinoFocusGained(evt);
			}
		});
		jCBX_Origem.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				jCBX_OrigemFocusGained(evt);
			}
		});
		jCBX_Tarifa.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				jCBX_TarifaFocusGained(evt);
			}
		});
		jB_Imprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jB_ImprimirPerformed(evt);

			}
		});
		jB_Cadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jB_CadastroPerformed(evt);

			}
		});
		jB_Sair.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jb_sairActionPerformed(evt);
			}
		});

		add(jP_Principal);
	}

	// OBjetos______________________________________________
	public void jB_ImprimirPerformed(ActionEvent evt) {
		// minuta = new imprime("GOL","AJU","2","2");
		// JOptionPane.showMessageDialog(null, "compania aerea=
		// "+CompaniaAerea);

		if (CompaniaAerea.equals("")) {
			JOptionPane.showMessageDialog(null,
					"Um tipo de serviço deve ser selecionado após o calculo de peso para efetuar a impressão!");
			return;
		}

		minuta = new imprime(CompaniaAerea, String.valueOf(jCBX_Origem.getSelectedItem()),
				String.valueOf(jCBX_Destino.getSelectedItem()), jT_Peso.getText(), jT_Qtd_Volume.getText(),
				jT_Adicionais.getText(), TipoServico, usuario, jT_Embalagem.getText(), jT_Conteudo.getText(),
				jT_NotaFiscal.getText());

	}

	public void jB_CadastroPerformed(ActionEvent evt) {
		// minuta = new imprime("GOL","AJU","2","2");
		FormCadastro cadastro = new FormCadastro(this.usuario);
		cadastro.setVisible(true);

	}

	private void formatarValor(String valor) {
		String temp;
		temp = valor.substring(0, valor.length() - 2);
		valorFinal = temp + "," + valor.substring(temp.length(), valor.length());

	}

	private void jCBX_DestinoFocusGained(java.awt.event.FocusEvent evt) {
		// TODO add your handling code here:
		jcbx_atualiza();
		limpaCampo();
		limpaRadioButton();
	}

	private void jCBX_OrigemFocusGained(java.awt.event.FocusEvent evt) {
		// TODO add your handling code here:
		limpaCampo();
		limpaRadioButton();
	}

	private JRadioButton[] camposTarifa() {
		JRadioButton[] campos = { jRB_Opcao1, jRB_Opcao2, jRb_Opcao3, jRb_Opcao4, jRb_Opcao5 };
		return campos;
	}

	private void jT_PesoFocusLost(java.awt.event.FocusEvent evt) {
		// TODO add your handling code here:
		limpaRadioButton();
		calcula_tarifa();

	}

	private void jT_PesoActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:

		limpaRadioButton();
		calcula_tarifa();

	}

	private void jb_sairActionPerformed(ActionEvent evt) {
		System.exit(0);
	}

	private void limpaRadioButton() {

		jRB_Opcao1.setText("Opção 1");
		jRB_Opcao1.setEnabled(false);
		jRB_Opcao2.setText("Opção 2");
		jRB_Opcao2.setEnabled(false);
		jRb_Opcao3.setText("Opção 3");
		jRb_Opcao3.setEnabled(false);
		jRb_Opcao4.setText("Opção 4");
		jRb_Opcao4.setEnabled(false);
		jRb_Opcao5.setText("Opção 5");
		jRb_Opcao5.setEnabled(false);
		buttonGroup_Opcoes.clearSelection();
	}

	private void jCBX_TarifaFocusGained(java.awt.event.FocusEvent evt) {
		// TODO add your handling code here:
		CompaniaAerea = "";
		jT_Peso.setText("");
		limpaRadioButton();

	}

	public void radioButtonEspActionPerformed(ActionEvent evt) {

		if (evt.getActionCommand().equals("Com Tarifa Especial:")) {
			jCBX_Tarifa.setEnabled(true);
			TarifaEspecial = true;
			jT_Peso.setText("");
			JOptionPane.showMessageDialog(null,
					"Escolha uma categoria de tarifa especial e preencha novamente o campo peso para novo calculo.");
			limpaRadioButton();
			CompaniaAerea = "";
		}
		if (evt.getActionCommand().equals("Sem Tarifa Especial:")) {
			jCBX_Tarifa.setEnabled(false);
			jT_Peso.setText("");
			JOptionPane.showMessageDialog(null, "Preencha novamente campo peso para novo calculo.");
			TarifaEspecial = false;
			limpaRadioButton();
			CompaniaAerea = "";
		}
	}

	public void radioButtonActionPerformed(ActionEvent evt) {
		// JOptionPane.showMessageDialog(null, evt.getActionCommand());
		CompaniaAerea = evt.getActionCommand().substring(13, 16);
		TipoServico = evt.getActionCommand().substring(0, 13);
	}

	private void jcbx_atualiza() {
		Conexao.conecta();
		jCBX_Destino.removeAllItems();
		// jCBX_Origem.removeAllItems();
		Conexao.executeSQL("select Filiais_Sigla from Filiais order by Filiais_Sigla");
		try {
			while (Conexao.resultset.next()) {
				jCBX_Destino.addItem(Conexao.resultset.getString("Filiais_Sigla"));
				// jCBX_Origem.addItem(Conexao.resultset.getString("Filiais_Sigla"));
			}

		} catch (SQLException erro) {

		}
	}

	private void calcula_tarifa() {
		if (jCBX_Destino.getSelectedItem().equals("CPQ  INTERNACI")) {
			ArrayList<ArrayListOrganiza> arraylist = new ArrayList<ArrayListOrganiza>();
			JRadioButton[] campos = camposTarifa();
			// JOptionPane.showMessageDialog(null, "teste");
			campos[0].setText("Convencional AZU");
			campos[0].setEnabled(true);
			campos[1].setText("Proximo Dia  AZU");
			campos[1].setEnabled(true);

		} else {
			CompaniaAerea = "";
			String codigo;
			ArrayList<ArrayListOrganiza> arraylist = new ArrayList<ArrayListOrganiza>();
			int i, calc;
			String B;
			int peso = Integer.parseInt(jT_Peso.getText());
			codigo = jCBX_Destino.getSelectedItem() + "";
			// JOptionPane.showMessageDialog(null, codigo);
			codigo = jCBX_Origem.getSelectedItem() + codigo.substring(0, 3) + "%";
			// JOptionPane.showMessageDialog(null, codigo);

			JRadioButton[] campos = camposTarifa();
			// JOptionPane.showMessageDialog(null, "pego o peso "+peso+" e o
			// codigo é "+codigo);
			try {
				Conexao.executeSQL("select * from convencional where convencional_codigo like '" + codigo
						+ "' order by tx_minima");
				while (Conexao.resultset.next()) {

					if (TarifaEspecial == true) {
						String temp = jCBX_Tarifa.getSelectedItem() + "";
						temp = "COD_" + temp.substring(0, 3);
						if (temp.equals("COD_010") || temp.equals("COD_020") || temp.equals("COD_210")) {
							temp = "COD_010_020_210";
						}

						// JOptionPane.showMessageDialog(null, temp);
						// JOptionPane.showMessageDialog(null, "Com tarifa
						// especial");
						if (peso <= Conexao.resultset.getInt("peso_minimo")) {
							calc = Conexao.resultset.getInt("tx_minima");
							B = "Convencional " + Conexao.resultset.getString("convencional_codigo").substring(6, 9)
									+ " R$: ";
							arraylist.add(new ArrayListOrganiza(calc, B));
						}
						if (peso > Conexao.resultset.getInt("peso_minimo")) {
							calc = Conexao.resultset.getInt(temp) * peso;
							B = "Convencional " + Conexao.resultset.getString("convencional_codigo").substring(6, 9)
									+ " R$: ";
							arraylist.add(new ArrayListOrganiza(calc, B));

						}

					}

					if (TarifaEspecial == false) {
						// JOptionPane.showMessageDialog(null, "entro no sem
						// tarifa");
						if (peso <= Conexao.resultset.getInt("peso_minimo")) {
							calc = Conexao.resultset.getInt("tx_minima");
							B = "Convencional " + Conexao.resultset.getString("convencional_codigo").substring(6, 9)
									+ " R$: ";
							arraylist.add(new ArrayListOrganiza(calc, B));
							// JOptionPane.showMessageDialog(null, "entrou peso
							// minimo"+Conexao.resultset.getString("convencional_codigo"));
						}
						if (peso > Conexao.resultset.getInt("peso_minimo") && peso <= 25) {
							calc = Conexao.resultset.getInt("ate25") * peso;
							B = "Convencional " + Conexao.resultset.getString("convencional_codigo").substring(6, 9)
									+ " R$: ";
							arraylist.add(new ArrayListOrganiza(calc, B));

						}
						if (peso > Conexao.resultset.getInt("peso_minimo") && peso > 25 && peso <= 50) {
							calc = Conexao.resultset.getInt("de25a50") * peso;
							B = "Convencional " + Conexao.resultset.getString("convencional_codigo").substring(6, 9)
									+ " R$: ";
							arraylist.add(new ArrayListOrganiza(calc, B));

						}
						if (peso > Conexao.resultset.getInt("peso_minimo") && peso > 50 && peso <= 300) {
							calc = Conexao.resultset.getInt("de50a300") * peso;
							B = "Convencional " + Conexao.resultset.getString("convencional_codigo").substring(6, 9)
									+ " R$: ";
							arraylist.add(new ArrayListOrganiza(calc, B));

						}
						if (peso > Conexao.resultset.getInt("peso_minimo") && peso > 300 && peso <= 500) {
							calc = Conexao.resultset.getInt("de300a500") * peso;
							B = "Convencional " + Conexao.resultset.getString("convencional_codigo").substring(6, 9)
									+ " R$: ";
							arraylist.add(new ArrayListOrganiza(calc, B));

						}
						if (peso > Conexao.resultset.getInt("peso_minimo") && peso > 500 && peso <= 1000) {
							calc = Conexao.resultset.getInt("de500a1000") * peso;
							B = "Convencional " + Conexao.resultset.getString("convencional_codigo").substring(6, 9)
									+ " R$: ";
							arraylist.add(new ArrayListOrganiza(calc, B));
						}
						if (peso > Conexao.resultset.getInt("peso_minimo") && peso > 1000) {
							calc = Conexao.resultset.getInt("acima1000") * peso;
							B = "Convencional " + Conexao.resultset.getString("convencional_codigo").substring(6, 9)
									+ " R$: ";
							arraylist.add(new ArrayListOrganiza(calc, B));

						}
					}

				}
				if (peso <= 30) {
					Conexao.executeSQL("select * from proximodia where proximodia_codigo like '" + codigo
							+ "' order by KG" + peso + "");
					while (Conexao.resultset.next()) {
						calc = Conexao.resultset.getInt("KG" + peso + "");
						B = "Proximo Dia  " + Conexao.resultset.getString("proximodia_codigo").substring(6, 9)
								+ " R$: ";
						arraylist.add(new ArrayListOrganiza(calc, B));

					}

				}

			} catch (SQLException erro) {

			}
			Collections.sort(arraylist, ArrayListOrganiza.FreteValor);
			i = 0;
			for (ArrayListOrganiza srt : arraylist) {

				formatarValor(String.valueOf(srt));

				campos[i].setText(valorFinal);
				campos[i].setEnabled(true);
				i++;
				// System.out.println(srt);
			}

		}
	}

	private void limpaCampo() {
		jT_Peso.setText("");
		jT_NotaFiscal.setText("");
		jT_Adicionais.setText("");
		jT_NotaFiscal.setText("");
		jRB_Normal.setSelected(true);
		jCBX_Tarifa.setEnabled(false);
		CompaniaAerea = "";
	}

}
