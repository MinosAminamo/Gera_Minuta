/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.form;

import br.com.utilitarios.FixedLengthDocument;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Welington Zanon
 */
public class FormInicial extends FormModel {

	// variaveis__________________________________
	JTextField jT_usuario, jT_senha;
	JLabel jL_usuario, jL_senha;
	JButton jB_entrar;

	public FormInicial() {
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		// iniciando componentes_____________________________________________
		jT_usuario = new JTextField();
		jT_senha = new JTextField();
		jL_usuario = new JLabel("USUARIO:");
		jL_senha = new JLabel("SENHA:");
		jB_entrar = new JButton("ACESSAR");
		// textfield_________________________________________

		jT_usuario.setBounds(310, 200, 150, 30);
		jT_usuario.setHorizontalAlignment(JTextField.CENTER);
		jT_usuario.setDocument(new FixedLengthDocument(18));
		jT_usuario.setToolTipText("Usuário que saira impresso na minuta.");
		add(jT_usuario);

		// Botoes_______________________________________________
		jB_entrar.setBounds(335, 240, 100, 30);
		add(jB_entrar);

		add(jP_Principal);

		// listener_______________________________________________________
		jB_entrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jB_entrarPerformed(evt);

			}
		});
		jT_usuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jT_usuarioPerformed(evt);

			}
		});

	}

	// Objetos____________________________________________________________
	public void jB_entrarPerformed(ActionEvent evt) {
		FormGeraMinuta FormGera = new FormGeraMinuta(jT_usuario.getText());
		FormGera.setVisible(rootPaneCheckingEnabled);
		dispose();

	}

	public void jT_usuarioPerformed(ActionEvent evt) {
		FormGeraMinuta FormGera = new FormGeraMinuta(jT_usuario.getText());
		FormGera.setVisible(rootPaneCheckingEnabled);
		dispose();

	}
}
