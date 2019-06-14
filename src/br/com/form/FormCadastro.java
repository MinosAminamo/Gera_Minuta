/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.com.utilitarios.FixedLengthDocument;
import br.com.utilitarios.IntegerDocument;
import br.com.utilitarios.conexao;

/**
 *
 * @author Welington Zanon
 */
public class FormCadastro extends FormModel {

	// variaveis________________________________________________________________
	private String usuario, estado, cidade;
	private static final long serialVersionUID = 1L;
	private JLabel jl_busca, jl_codigo, jl_sigla, jl_nome, jl_cnpj, jl_insc_estadual, jl_logradouro, jl_bairro,
			jl_telefone, jl_cidade, jl_estado, jl_observacao;
	private JTextField jt_codigo, jt_sigla, jt_nome, jt_cnpj, jt_insc_estadual, jt_logradouro, jt_bairro, jt_telefone,
			jt_cidade, jt_estado, jt_observacao;
	private JButton jb_primeiro, jb_anterior, jb_proximo, jb_ultimo, jb_novo, jb_alterar, jb_salvar, jb_excluir,
			jb_sair;
	private JComboBox<String> jc_busca;
	conexao FormAereo;
	private int navega = 0;

	public FormCadastro(String usuario) {
		this.usuario = usuario;
		initCompo();
		FormAereo = new conexao();
		FormAereo.conecta();
		FormAereo.executeSQL("select  t1.*, t2.cidade_nome,t3.estado_sigla from filiais "
				+ "as t1 inner join cidade as t2  on t1.filial_cidade = t2.cidade_id inner join estado"
				+ " as t3  on t1.filial_estado = t3.estado_id order by filiais_sigla");

		try {
			FormAereo.resultset.first();
			mostra_dados();
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Não localizou dados");
		}
	}

	private void initCompo() {
		// setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle(titulo + " Usuário ativo: " + usuario);

		// texto___________________________________________________________
		jt_codigo = new JTextField();
		jt_codigo.setEditable(false);
		add(jt_codigo);
		jt_codigo.setBounds(20, 45, 60, 32);
		jt_codigo.setDocument(new IntegerDocument(2));
		jt_codigo.setHorizontalAlignment(JTextField.CENTER);
		jt_sigla = new JTextField();
		try {
			MaskFormatter mk = new MaskFormatter("***  *********");
			mk.setPlaceholderCharacter(' ');
			mk.setValidCharacters("QWERTYUIOPASDFGHJKLCZXCVBNM ");
			jt_sigla = new JFormattedTextField(mk);
			jt_sigla.setBounds(100, 45, 110, 32);
			jt_sigla.setToolTipText(
					"A sigla deve ser cadastrada usando letras maiúsculas, sem caracteres especiais sendo as 3 primeiras letras relacionado ao destino seguido de sua especificação caso nescessário.");

			add(jt_sigla);
			jt_sigla.setHorizontalAlignment(JTextField.CENTER);
		} catch (Exception e) {
		}
		jt_nome = new JTextField();
		add(jt_nome);
		jt_nome.setBounds(20, 102, 379, 32);
		jt_nome.setDocument(new FixedLengthDocument(33));
		jt_cnpj = new JTextField();
		add(jt_cnpj);
		jt_cnpj.setBounds(20, 159, 180, 32);
		jt_cnpj.setDocument(new FixedLengthDocument(20));
		jt_cnpj.setEditable(false);
		jt_insc_estadual = new JTextField();
		add(jt_insc_estadual);
		jt_insc_estadual.setBounds(220, 159, 180, 32);
		jt_insc_estadual.setDocument(new FixedLengthDocument(21));
		jt_logradouro = new JTextField();
		add(jt_logradouro);
		jt_logradouro.setBounds(20, 216, 379, 32);
		jt_logradouro.setDocument(new FixedLengthDocument(33));
		jt_observacao = new JTextField();
		add(jt_observacao);
		jt_observacao.setBounds(20, 387, 379, 32);
		jt_observacao.setDocument(new FixedLengthDocument(33));
		jt_bairro = new JTextField();
		add(jt_bairro);
		jt_bairro.setBounds(20, 273, 210, 32);
		jt_bairro.setDocument(new FixedLengthDocument(25));
		jt_telefone = new JTextField();
		try {
			MaskFormatter mk = new MaskFormatter("(###) ####-####");
			mk.setPlaceholderCharacter(' ');
			jt_telefone = new JFormattedTextField(mk);
			jt_telefone.setBounds(250, 273, 120, 32);
			add(jt_telefone);
		} catch (Exception e) {
		}
		jt_cidade = new JTextField();
		add(jt_cidade);
		jt_cidade.setBounds(20, 330, 210, 32);
		jt_cidade.setDocument(new FixedLengthDocument(25));
		jt_estado = new JTextField();
		try {
			MaskFormatter mk = new MaskFormatter("**");
			mk.setPlaceholderCharacter(' ');
			mk.setValidCharacters("QWERTYUIOPASDFGHJKLCZXCVBNM");
			jt_estado = new JFormattedTextField(mk);
			jt_estado.setBounds(250, 330, 60, 32);
			jt_estado.setToolTipText("Somente letras maiúsculas");
			add(jt_estado);
			jt_estado.setHorizontalAlignment(JTextField.CENTER);
		} catch (Exception e) {
		}

		// Botoes_____________________________________________________________________
		jb_primeiro = new JButton();
		add(jb_primeiro);
		jb_primeiro.setBounds(430, 335, 60, 40);
		jb_primeiro.setToolTipText("Retorna para o primeiro registro");
		jb_primeiro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/img/primeiro_registro.gif")));
		jb_anterior = new JButton();
		add(jb_anterior);
		jb_anterior.setBounds(495, 335, 60, 40);
		jb_anterior.setToolTipText("Retorna para o registro anterior");
		jb_anterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/img/registro_anterior.gif")));
		jb_proximo = new JButton();
		add(jb_proximo);
		jb_proximo.setBounds(560, 335, 60, 40);
		jb_proximo.setToolTipText("Avan�a para o proximo registro");
		jb_proximo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/img/proximo_registro.gif")));
		jb_ultimo = new JButton();
		add(jb_ultimo);
		jb_ultimo.setBounds(625, 335, 60, 40);
		jb_ultimo.setToolTipText("Avança para o ultimo registro");
		jb_ultimo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/img/ultimo_registro.gif")));
		jb_sair = new JButton();
		add(jb_sair);
		jb_sair.setBounds(690, 335, 75, 90);
		jb_sair.setToolTipText("Encerra tela de cadastro");
		jb_sair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/img/sair.gif")));
		jb_novo = new JButton();
		add(jb_novo);
		jb_novo.setBounds(430, 385, 60, 40);
		jb_novo.setToolTipText("Libera para inserir um novo cadastro");
		jb_novo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/img/novo_registro.gif")));
		jb_alterar = new JButton();
		add(jb_alterar);
		jb_alterar.setBounds(495, 385, 60, 40);
		jb_alterar.setToolTipText("Salva alteração do cadastro");
		jb_alterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/img/alterar_registro.gif")));
		jb_salvar = new JButton();
		add(jb_salvar);
		jb_salvar.setBounds(560, 385, 60, 40);
		jb_salvar.setToolTipText("Salva o cadastro");
		jb_salvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/img/gravar_registro.gif")));
		jb_excluir = new JButton();
		add(jb_excluir);
		jb_excluir.setBounds(625, 385, 60, 40);
		jb_excluir.setToolTipText("Exclue o cadastro");
		jb_excluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/img/delete_registro.gif")));

		jb_primeiro.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jb_primeiroActionPerformed(evt);
			}
		});
		jb_proximo.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jb_proximoActionPerformed(evt);
			}
		});
		jb_anterior.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jb_anteriorActionPerformed(evt);
			}
		});
		jb_ultimo.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jb_ultimoActionPerformed(evt);
			}
		});
		jb_novo.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jb_novoActionPerformed(evt);
			}
		});
		jb_alterar.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jb_alterarActionPerformed(evt);
			}
		});
		jb_salvar.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jb_salvarActionPerformed(evt);
			}
		});
		jb_excluir.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jb_excluirActionPerformed(evt);
			}
		});
		jb_sair.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jb_sairActionPerformed(evt);
			}
		});

		// Textos______________________________________________________________________________________________________________________
		jl_codigo = new JLabel("CODIGO");
		add(jl_codigo);
		jl_codigo.setBounds(20, 21, 60, 32);
		jl_sigla = new JLabel("SIGLA");
		add(jl_sigla);
		jl_sigla.setBounds(100, 21, 60, 32);
		jl_nome = new JLabel("NOME");
		add(jl_nome);
		jl_nome.setBounds(20, 78, 241, 32);
		jl_cnpj = new JLabel("CNPJ");
		add(jl_cnpj);
		jl_cnpj.setBounds(20, 135, 241, 32);
		jl_insc_estadual = new JLabel("INSC. ESTADUAL");
		add(jl_insc_estadual);
		jl_insc_estadual.setBounds(220, 135, 120, 32);
		jl_logradouro = new JLabel("LOGRADOURO");
		add(jl_logradouro);
		jl_logradouro.setBounds(20, 192, 379, 32);
		jl_bairro = new JLabel("BAIRRO");
		add(jl_bairro);
		jl_bairro.setBounds(20, 249, 210, 32);
		jl_telefone = new JLabel("TELEFONE");
		add(jl_telefone);
		jl_telefone.setBounds(250, 249, 120, 32);
		jl_cidade = new JLabel("CIDADE");
		add(jl_cidade);
		jl_cidade.setBounds(20, 306, 210, 32);
		jl_observacao = new JLabel("INFORMAÇÕES ADICIONAIS");
		add(jl_observacao);
		jl_observacao.setBounds(20, 363, 210, 32);
		jl_estado = new JLabel("ESTADO");
		add(jl_estado);
		jl_estado.setBounds(250, 306, 60, 32);
		jl_busca = new JLabel("BUSCA");
		add(jl_busca);
		jl_busca.setBounds(230, 21, 120, 32);

		jc_busca = new JComboBox<String>();
		add(jc_busca);
		jc_busca.setBounds(230, 45, 120, 32);

		JButton jb_busca = new JButton();
		add(jb_busca);
		jb_busca.setBounds(355, 45, 45, 32);
		jb_busca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/img/localizar.gif")));

		add(jP_Principal);

		jc_busca.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				jc_buscaFocusGained(evt);
			}
		});
		jb_busca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jb_buscaActionPerformed(evt);
			}
		});
		jt_estado.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusLost(java.awt.event.FocusEvent evt1) {
				jt_estadoFocusLost(evt1);
			}
		});
		jt_cidade.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusLost(java.awt.event.FocusEvent evt) {
				jt_cidadeFocusLost(evt);
			}
		});

	}

	private void jt_cidadeFocusLost(java.awt.event.FocusEvent evt) {
		Conexao.conecta();
		try {
			Conexao.executeSQL("select * from cidade Where cidade_nome = '" + jt_cidade.getText() + "'");
			Conexao.resultset.first();
			cidade = Conexao.resultset.getString("cidade_id");
			// JOptionPane.showMessageDialog(null, "existe a cidade = " +
			// Conexao.resultset.getString("cidade_nome"));
		} catch (SQLException erro) {

			try {

				String sql = "";
				// Conexao.executeSQL(sql);
				// Conexao.resultset.first();
				String nome = "A cidade " + jt_cidade.getText() + " não existe, incluir registro?";
				int opcao_escolhida = JOptionPane.showConfirmDialog(null, nome, " INCLUSÃO! ",
						JOptionPane.YES_NO_OPTION);
				if (opcao_escolhida == JOptionPane.YES_OPTION) {
					sql = "insert into cidade (cidade_nome) values ('" + jt_cidade.getText() + "')";
					int conseguiu_alterar = Conexao.statement.executeUpdate(sql);

					if (conseguiu_alterar == 1) {

						Conexao.executeSQL("select * from cidade Where cidade_nome = '" + jt_cidade.getText() + "'");

						JOptionPane.showMessageDialog(null, "Nova cidade incluida com sucesso");
						FormAereo.executeSQL("select  t1.*, t2.cidade_nome,t3.estado_sigla from filiais "
								+ "as t1 inner join cidade as t2  on t1.filial_cidade = t2.cidade_id inner join estado"
								+ " as t3  on t1.filial_estado = t3.estado_id order by filiais_sigla");
					}
				} else {
					jt_cidade.requestFocus();

				}

			} catch (SQLException N) {
				JOptionPane.showMessageDialog(null, "Não conseguio cadastrar a cidade" + N);
				FormAereo.executeSQL("select  t1.*, t2.cidade_nome,t3.estado_sigla from filiais "
						+ "as t1 inner join cidade as t2  on t1.filial_cidade = t2.cidade_id inner join estado"
						+ " as t3  on t1.filial_estado = t3.estado_id order by filiais_sigla");

			}

		}

	}

	private void jt_estadoFocusLost(java.awt.event.FocusEvent evt1) {
		Conexao.conecta();
		try {
			Conexao.executeSQL("select * from estado Where estado_sigla = '" + jt_estado.getText() + "'");
			Conexao.resultset.first();
			// JOptionPane.showMessageDialog(null, "existe a cidade = " +
			// Conexao.resultset.getString("uf_nome"));
			estado = Conexao.resultset.getString("estado_id");
			jb_alterar.setEnabled(true);
		} catch (SQLException erro) {

			try {

				String sql = "";
				// Conexao.executeSQL(sql);
				// Conexao.resultset.first();
				String nome = "O estado " + jt_estado.getText() + " não existe, incluir registro?";
				int opcao_escolhida = JOptionPane.showConfirmDialog(null, nome, " INCLUSÃO! ",
						JOptionPane.YES_NO_OPTION);
				if (opcao_escolhida == JOptionPane.YES_OPTION) {
					sql = "insert into estado (estado_sigla) values ('" + jt_estado.getText() + "')";
					int conseguiu_alterar = Conexao.statement.executeUpdate(sql);
					if (conseguiu_alterar == 1) {
						JOptionPane.showMessageDialog(null, "Novo registro incluido com sucesso");
						FormAereo.executeSQL("select  t1.*, t2.cidade_nome,t3.estado_sigla from filiais "
								+ "as t1 inner join cidade as t2  on t1.filial_cidade = t2.cidade_id inner join estado"
								+ " as t3  on t1.filial_estado = t3.estado_id order by filiais_sigla");

					}
				} else {
					jt_estado.requestFocus();
					FormAereo.executeSQL("select  t1.*, t2.cidade_nome,t3.estado_sigla from filiais "
							+ "as t1 inner join cidade as t2  on t1.filial_cidade = t2.cidade_id inner join estado"
							+ " as t3  on t1.filial_estado = t3.estado_id order by filiais_sigla");

				}

			} catch (SQLException N) {
				JOptionPane.showMessageDialog(null, "Não conseguio cadastrar o registro " + N);
				FormAereo.executeSQL("select  t1.*, t2.cidade_nome,t3.estado_sigla from filiais "
						+ "as t1 inner join cidade as t2  on t1.filial_cidade = t2.cidade_id inner join estado"
						+ " as t3  on t1.filial_estado = t3.estado_id order by filiais_sigla");

			}
		}

	}

	private void jb_primeiroActionPerformed(ActionEvent evt) {
		try {
			FormAereo.resultset.first();
			mostra_dados();
			navega = 1;
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Não localizou o primeiro");
		}
	}

	private void jb_proximoActionPerformed(ActionEvent evt) {
		try {
			FormAereo.resultset.next();
			mostra_dados();
			navega = 2;
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Não localizou dados");
		}
	}

	private void jb_anteriorActionPerformed(ActionEvent evt) {
		try {
			FormAereo.resultset.previous();
			mostra_dados();
			navega = 1;
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Não foi possivel retornar registro");
		}
	}

	private void jb_ultimoActionPerformed(ActionEvent evt) {
		try {
			FormAereo.resultset.last();
			mostra_dados();
			navega = 2;
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Não localizou o ultimo dado");
		}

	}

	private void jb_novoActionPerformed(ActionEvent evt) {
		jt_codigo.setText("");
		jt_codigo.setFocusable(true);
		jt_sigla.setText("");
		jt_nome.setText("");
		jt_cnpj.setText("");
		jt_cnpj.setEditable(true);
		jt_insc_estadual.setText("");
		jt_logradouro.setText("");
		jt_bairro.setText("");
		jt_telefone.setText("");
		jt_cidade.setText("");
		jt_estado.setText("");
	}

	private void jb_alterarActionPerformed(ActionEvent evt) {
		try {
			Conexao.executeSQL("select * from cidade Where cidade_nome = '" + jt_cidade.getText() + "'");
			Conexao.resultset.first();
			cidade = Conexao.resultset.getString("cidade_id");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"ERRO: \"Cadastro Cidade\" favor confirmar nova Cidade antes de " + "alter o cadastro.");
			return;
		}
		try {
			Conexao.executeSQL("select * from estado Where estado_sigla = '" + jt_estado.getText() + "'");
			Conexao.resultset.first();
			estado = Conexao.resultset.getString("estado_id");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"ERRO: \"Cadastro Estado\" favor confirmar novo Estado antes de " + "alter o cadastro.");
			return;
		}

		try {
			String sql = "select * from filiais Where filial_cnpj ='" + jt_cnpj.getText() + "'";
			FormAereo.executeSQL(sql);
			FormAereo.resultset.first();
			String nome = "Alterar o Destino: " + FormAereo.resultset.getString("filial_nome") + " ?";
			int opcao_escolhida = JOptionPane.showConfirmDialog(null, nome, " Alterar ", JOptionPane.YES_NO_OPTION);
			if (opcao_escolhida == JOptionPane.YES_OPTION) {
				sql = "update filiais set filiais_sigla = '" + jt_sigla.getText() + "',filial_nome = '"
						+ jt_nome.getText() + "',filial_cnpj = '" + jt_cnpj.getText() + "', filial_insc = '"
						+ jt_insc_estadual.getText() + "',filial_logradouro = '" + jt_logradouro.getText()
						+ "', filial_bairro ='" + jt_bairro.getText() + "', filial_cidade = " + cidade
						+ ", filial_estado =" + estado + ",filial_Observacao = '" + jt_observacao.getText()
						+ "' where filial_cnpj = '" + jt_cnpj.getText() + "'";
				int conseguiu_alterar = FormAereo.statement.executeUpdate(sql);
				if (conseguiu_alterar == 1) {
					JOptionPane.showMessageDialog(null, "Alteração realizada com sucesso");
					FormAereo.executeSQL("select  t1.*, t2.cidade_nome,t3.estado_sigla from filiais "
							+ "as t1 inner join cidade as t2  on t1.filial_cidade = t2.cidade_id inner join estado"
							+ " as t3  on t1.filial_estado = t3.estado_id order by filiais_sigla");
					FormAereo.resultset.first();
					mostra_dados();
				}
			} else {
				FormAereo.executeSQL("select  t1.*, t2.cidade_nome,t3.estado_sigla from filiais "
						+ "as t1 inner join cidade as t2  on t1.filial_cidade = t2.cidade_id inner join estado"
						+ " as t3  on t1.filial_estado = t3.estado_id order by filiais_sigla");
			}
			return;
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Erro ao tentar alterar o registro..." + erro);

		}
	}

	private void jb_salvarActionPerformed(ActionEvent evt) {
		Conexao.conecta();
		try {
			Conexao.executeSQL("select * from cidade Where cidade_nome = '" + jt_cidade.getText() + "'");
			Conexao.resultset.first();
			cidade = Conexao.resultset.getString("cidade_id");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"ERRO: \"Cadastro Cidade\" favor confirmar nova Cidade antes de " + "alter o cadastro.");
			return;
		}
		try {
			Conexao.executeSQL("select * from estado Where estado_sigla = '" + jt_estado.getText() + "'");
			Conexao.resultset.first();
			estado = Conexao.resultset.getString("estado_id");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"ERRO: \"Cadastro Estado\" favor confirmar novo Estado antes de " + "alter o cadastro.");
			return;
		}

		try {
			FormAereo.statement.executeUpdate(
					"INSERT INTO ROOT.FILIAIS ( FILIAIS_SIGLA, FILIAL_NOME, FILIAL_CNPJ, FILIAL_INSC,FILIAL_LOGRADOURO, FILIAL_BAIRRO, FILIAL_TELEFONE, FILIAL_CIDADE, FILIAL_ESTADO, FILIAL_OBSERVACAO) VALUES ('"
							+ jt_sigla.getText() + "','" + jt_nome.getText() + "','" + jt_cnpj.getText() + "','"
							+ jt_insc_estadual.getText() + "','" + jt_logradouro.getText() + "','" + jt_bairro.getText()
							+ "','" + jt_telefone.getText() + "'," + cidade + "," + estado + ",'"
							+ jt_observacao.getText() + "')");
			JOptionPane.showMessageDialog(null, "Novo registro inserido.");
			FormAereo.executeSQL("select  t1.*, t2.cidade_nome,t3.estado_sigla from filiais "
					+ "as t1 inner join cidade as t2  on t1.filial_cidade = t2.cidade_id inner join estado"
					+ " as t3  on t1.filial_estado = t3.estado_id order by filiais_sigla");
			mostra_dados();
			jt_cnpj.setEditable(false);
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Não conseguio salvar registro");
		}

	}

	private void jb_excluirActionPerformed(ActionEvent evt) {
		try {
			String sql = "select * from filiais Where filial_cnpj = '" + jt_cnpj.getText() + "'";
			FormAereo.executeSQL(sql);
			FormAereo.resultset.first();
			String nome = "Deletar o Destino: " + FormAereo.resultset.getString("filial_nome") + " ?";
			int opcao_escolhida = JOptionPane.showConfirmDialog(null, nome, " Exclusão ", JOptionPane.YES_NO_OPTION);
			if (opcao_escolhida == JOptionPane.YES_OPTION) {
				sql = "delete from filiais where filial_cnpj = '" + jt_cnpj.getText() + "'";
				int conseguiu_excluir = FormAereo.statement.executeUpdate(sql);
				if (conseguiu_excluir == 1) {
					JOptionPane.showMessageDialog(null, "Exclusão realizada com sucesso");
					FormAereo.executeSQL("select * from filiais order by filiais_sigla");
					FormAereo.resultset.first();
					mostra_dados();
				}
			} else {
				FormAereo.executeSQL("select * from filiais");
			}
			return;
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Erro ao tentar excluir o registro..." + erro);

		}

	}

	private void jb_sairActionPerformed(ActionEvent evt) {
		dispose();
	}

	private void jb_buscaActionPerformed(ActionEvent evt) {
		try {
			FormAereo.executeSQL("select  t1.*, t2.cidade_nome,t3.estado_sigla from filiais "
					+ "as t1 inner join cidade as t2  on t1.filial_cidade = t2.cidade_id inner join estado"
					+ " as t3  on t1.filial_estado = t3.estado_id where filiais_sigla = '" + jc_busca.getSelectedItem()
					+ "'");
			FormAereo.resultset.first();
			mostra_dados();
		} catch (SQLException erro) {
		}
		FormAereo.executeSQL("select  t1.*, t2.cidade_nome,t3.estado_sigla from filiais "
				+ "as t1 inner join cidade as t2  on t1.filial_cidade = t2.cidade_id inner join estado"
				+ " as t3  on t1.filial_estado = t3.estado_id order by filiais_sigla");
	}

	private void jc_buscaFocusGained(java.awt.event.FocusEvent evt) {
		jcbx_atualiza();
	}

	public void mostra_dados() {
		try {
			jt_codigo.setText(FormAereo.resultset.getString("filiais_id"));
			jt_sigla.setText(FormAereo.resultset.getString("filiais_sigla"));
			jt_nome.setText(FormAereo.resultset.getString("filial_nome"));
			jt_cidade.setText(FormAereo.resultset.getString("cidade_nome"));
			jt_logradouro.setText(FormAereo.resultset.getString("filial_logradouro"));
			jt_cnpj.setText(FormAereo.resultset.getString("filial_cnpj"));
			jt_estado.setText(FormAereo.resultset.getString("estado_sigla"));
			jt_bairro.setText(FormAereo.resultset.getString("filial_bairro"));
			jt_insc_estadual.setText(FormAereo.resultset.getString("filial_insc"));
			jt_telefone.setText(FormAereo.resultset.getString("filial_telefone"));
			jc_busca.addItem(FormAereo.resultset.getString("filiais_sigla"));
			jc_busca.setSelectedItem(FormAereo.resultset.getString("filiais_sigla"));
			jt_observacao.setText(FormAereo.resultset.getString("filial_observacao"));

			cidade = FormAereo.resultset.getString("filial_cidade");
			estado = FormAereo.resultset.getString("filial_estado");

		} catch (SQLException erro) {
			if (navega == 1) {
				JOptionPane.showMessageDialog(null, "Você já está no primeiro registro");
			} else if (navega == 2) {
				JOptionPane.showMessageDialog(null, "Você já está no Último registro");
			} else {
				JOptionPane.showMessageDialog(null, "Não localizou dados");
			}
		}
	}

	public void jcbx_atualiza() {
		jc_busca.removeAllItems();
		FormAereo.executeSQL("select * from filiais order by filiais_sigla");
		try {
			// FormAereo.resultset.first();
			while (FormAereo.resultset.next()) {
				jc_busca.addItem(FormAereo.resultset.getString("filiais_sigla"));
			}
		} catch (SQLException erro) {

		}

	}
}
