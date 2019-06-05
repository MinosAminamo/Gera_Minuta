/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.form;

import br.com.utilitarios.Consulta;
import br.com.utilitarios.TabelaConsulta;
import br.com.utilitarios.conexao;
import br.com.utilitarios.uiNimbus;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Welington Zanon
 */
public class FormModel extends JFrame {

	public FormModel() {
		initComponents();

	}
	// ______variaveis

	protected String usuario, senha, titulo = "GERA MINUTA AEREA BETA 5.0    ";
	protected conexao Conexao;
	protected JPanel jP_Principal;
	protected JLabel jL_Informativo;

	private void initComponents() {
		Conexao = new conexao();
		setLayout(null); 
		uiNimbus.ui();
		setSize(800, 600);
		setResizable(false);
		setTitle(titulo);
		setLocationRelativeTo(null);

		// iniciando variaveis________________________________________________
		jP_Principal = new JPanel();
		JTable tableContato;
		TabelaConsulta tableModelContato;
		JScrollPane jS_PanelContato;
		// Labels_________________________________________________________________

		jL_Informativo = new JLabel();
		jL_Informativo.setBounds(650, 480, 64, 64);
		jL_Informativo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/img/Ajuda.png")));
		jL_Informativo.setToolTipText("Clique aqui para maiores imformações sobre vetos e premissas.");
		add(jL_Informativo);
		// tabela_______________________________________________________

		tableContato = new JTable();
		jS_PanelContato = new JScrollPane(tableContato);
		tableModelContato = new TabelaConsulta(getListaConsultaContato());
		tableContato.setModel(tableModelContato);
		tableContato.setPreferredScrollableViewportSize(new Dimension(500, 300));
		tableContato.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(jS_PanelContato);
		jS_PanelContato.setBounds(10, 465, 400, 98);

		// painel_______________________________________________________
		add(jP_Principal);
		jP_Principal.setBounds(10, 10, 772, 450);
		jP_Principal.setBackground(new java.awt.Color(204, 204, 204));
		jP_Principal.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

		// comando listener___________________________________________________
		jL_Informativo.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jL_InformativoMouseClicked(evt);
			}
		});

	}
	// Objetos______________________________________________________

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	private void jL_InformativoMouseClicked(java.awt.event.MouseEvent evt) {
		// TODO add your handling code here:
		JOptionPane.showMessageDialog(null,
				"PREMISSA:\n\n"
						+ "Todas as cargas acima de 100kgs devem ser solicitadas a orientação de malha para  TRAFEGO AEREO \n"
						+ "Esta vetado a utilização do aeroporto de VCP (embarque ou destino) para cargas com valor superior a R$60.000,00 \n"
						+ "Cias AZUL  não possui capacidade de embarque de paletes\n"
						+ "Esta vetado a  utilizaçãoda cia AZUL / AVIANCA  com destino/origem GIG\n"
						+ "Esta vetado o embarque de  cargas com destino SDU sem previo alinhamento com o a filial RIO e TRAFEGO AEREO\n"
						+ "Podemos embarcar vulneravel com a GOL. Porem é NECESSÁRIO alinhamento antes do embarque com o TRAFEGO AEREO\n"
						+ "É procedimento assim que a carga estiver liberada/disponivel DEVE ser retirada\n"
						+ "É PROIBIDO  a utilização do surface da cia aerea, bem como o serviço de coleta e entrega dos mesmos\n"
						+ "É procedimento a utilização de sacas de lona,lacre de aço, caixa palete ou strech preto para garantir a integridade da carga\n"
						+ "Esta vetado o embarque de cargas perigosas. Exceções com a autorização da equipe MTZ AEREO\n"
						+ "Cosmeticos são considerados carga perigosa, assim apenas podemos embarcar pela TAM e com a autorização do TRAFEGO AEREO\n"
						+ "Os paletes do cliente Motorola devem ser realizadas a pesagem no ato das retirada. Divergencias, ligar para equipe Motorola in house e MTZ Aereo\n"
						+ "Está vetado embarques pela AZUL nas origens BEL, IOS, JDO, MAO, SSA e PMW, para cargas com peso INFERIOR à 30 kg. (Isso vale para qualquer tipo de mercadoria)\n"
						+ "Está suspenso o embarque de vulneráveis pela AZUL (todas as origens)\n"
						+ "Os embarques vulneráveis destino PNZ pela AVIANCA estão vetados \n"
						+ "Os embarques destino STM pela GOL estão vetados por tempo indeterminado\n"
						+ "Os embarques vulneráveis destino MAB pela GOL estão vetados \n"
						+ "Os embarques vulneráveis destino MCP pela GOL estão vetados\n"
						+ "Os embarques destino ATM pela GOL estão vetados");
	}

	private List<Consulta> getListaConsultaContato() {
		List<Consulta> consulta = new ArrayList<Consulta>();
		Consulta emp1 = new Consulta();
		emp1.setNome("Trafego Aéreo");
		emp1.setTelefone("11 9 7637-1976");
		consulta.add(emp1);
		Consulta emp2 = new Consulta();
		emp2.setNome("Clientes especiais");
		emp2.setTelefone("11 9 5767-3454");
		consulta.add(emp2);
		Consulta emp3 = new Consulta();
		emp3.setNome("Soraya (Clientes Services)");
		emp3.setTelefone("11 9 5767-3455");
		consulta.add(emp3);
		Consulta emp4 = new Consulta();
		emp4.setNome("Valdir Ribeiro (Enc. Op)");
		emp4.setTelefone("11 9 8064-1106");
		consulta.add(emp4);
		Consulta emp5 = new Consulta();
		emp5.setNome("Arlita Freitas (Sup. Op)");
		emp5.setTelefone("11 9 8064-2110");
		consulta.add(emp5);
		Consulta emp6 = new Consulta();
		emp6.setNome("Alzira Borges (Sup. Op)");
		emp6.setTelefone("11 9 9233-9936");
		consulta.add(emp6);
		return consulta;
	}

}
