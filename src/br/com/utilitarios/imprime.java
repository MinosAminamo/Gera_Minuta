package br.com.utilitarios;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.print.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class imprime implements Printable {

	String CompaniaAerea, Usuario = "", Origem, Destino, Peso, QtdVolume, Conta, TextoFedex = "", TipoServico,
			Adicionais, embalagem, conteudo, notafiscal;
	String servico1 = "", servico2 = "", servico3 = "", servico4 = "", servico5 = "", aeroporto = "", Obs = "";
	BufferedImage image;

	/*
	 * public static void main(String[] args) {
	 * 
	 * Imprime example1 = new Imprime(); System.exit(0); }
	 */
	/**
	 * Constructor: Example1
	 * <p>
	 *
	 */
	// public String A;
	public imprime(String CompaniaAerea, String Origem, String Destino, String Peso, String QtdVolume,
			String Adicionais, String TipoServico, String usuario, String embalagem, String conteudo,
			String NotaFiscal) {
		// System.out.println("As variaveis s�o
		// "+CompaniaAerea+Destino+Peso+QtdVolume);
		this.CompaniaAerea = CompaniaAerea;
		this.Destino = Destino;
		this.Origem = Origem;
		this.Peso = Peso;
		this.QtdVolume = QtdVolume;
		this.TipoServico = TipoServico;
		this.Adicionais = Adicionais;
		this.Usuario = usuario;
		this.embalagem = embalagem;
		this.conteudo = conteudo;
		this.notafiscal = NotaFiscal;
		if (this.CompaniaAerea.equals("INT")) {
			this.servico1 = "VOO CERTO";
			this.servico2 = "EXPRESS";
			this.servico3 = "STANDARD";
			this.servico4 = "DOC";
			this.servico5 = "DEZ HORAS";
			this.Conta = "14121911594516";
		}
		if (this.CompaniaAerea.equals("GOL")) {
			this.servico1 = "VOO CERTO";
			this.servico2 = "EXPRESS";
			this.servico3 = "STANDARD";
			this.servico4 = "DOC";
			this.servico5 = "DEZ HORAS";
			this.Conta = "56522";
		}
		if (this.CompaniaAerea.equals("TAM")) {
			this.servico1 = "PROXIMO VOO";
			this.servico2 = "PROXIMO DIA";
			this.servico3 = "CONVENCIONAL";
			this.servico4 = "PRE PAGO";
			this.servico5 = "BAGAGEM PORTA A PORTA";
			this.Conta = "01400150";
			this.Obs = "Tomador CNPJ: 10.970.887/0002-85";
		}
		if (this.CompaniaAerea.equals("AVI")) {
			this.servico1 = "PROXIMO VOO";
			this.servico2 = "PROXIMO DIA";
			this.servico3 = "CONVENCIONAL";
			this.servico4 = "PRE PAGO";
			this.servico5 = "BAGAGEM PORTA A PORTA";
			this.Conta = "01400150";
			this.Obs = "Tomador CNPJ: 10.970.887/0002-85";
		}
		if (this.CompaniaAerea.equals("AZU")) {
			this.servico1 = "PROXIMO VOO";
			this.servico2 = "AZUL CARGO AMANHÃ";
			this.servico3 = "CONVENCIONAL(STANDARD)";
			this.servico4 = "PRE PAGO";
			this.servico5 = "BAGAGEM PORTA A PORTA";
			this.Conta = "01400150";
			this.Obs = "Tomador CNPJ: 10.970.887/0002-85";
		}
		if (this.CompaniaAerea.equals("AZU") && (this.Destino.equals("CPQ  INTERNACI"))) {
			this.servico1 = "PROXIMO VOO";
			this.servico2 = "AZUL CARGO AMANHÃ";
			this.servico3 = "CONVENCIONAL(STANDARD)";
			this.servico4 = "PRE PAGO";
			this.servico5 = "BAGAGEM PORTA A PORTA";
			this.Conta = "14121911594516";
			this.Obs = "Tomador CNPJ: 10.970.887/0002-85";
		}

		// --- Create a printerJob object
		PrinterJob printJob = PrinterJob.getPrinterJob();

		// --- Set the printable class to this one since we
		// --- are implementing the Printable interface
		printJob.setPrintable(this);

		// --- Show a print dialog to the user. If the user
		// --- click the print button, then print otherwise
		// --- cancel the print job
		if (printJob.printDialog()) {
			try {
				printJob.print();
			} catch (Exception PrintException) {
				PrintException.printStackTrace();
			}
		}
	}

	/**
	 * Method: print
	 * <p>
	 *
	 * This class is responsible for rendering a page using the provided
	 * parameters. The result will be a grid where each cell will be half an
	 * inch by half an inch.
	 *
	 * @param g
	 *            a value of type Graphics
	 * @param pageFormat
	 *            a value of type PageFormat
	 * @param page
	 *            a value of type int
	 * @return a value of type int
	 */
	@Override
	public int print(Graphics g, PageFormat pageFormat, int page) {

		conexao print = new conexao();
		print.conecta();
		Date Data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");

		Graphics2D g2d;
		try {
			// Image Imagem = new
			// ImageIcon("../img/minuta"+CompaniaAerea+".png").getImage();
			image = ImageIO.read(getClass().getResource("/br/com/img/minuta" + CompaniaAerea + ".png"));
		} catch (IOException ex) {
			Logger.getLogger(imprime.class.getName()).log(Level.SEVERE, null, ex);

		}

		Font titleFont = new Font("Arial", 0, 5);
		Font titleFont2 = new Font("Arial", 0, 7);
		Font titleFont3 = new Font("Tahoma", Font.BOLD, 8);
		Font titleFont4 = new Font("Tahoma", Font.BOLD, 12);
		Font titleFont5 = new Font("Arial", 0, 12);
		Font titleFont6 = new Font("Arial", 0, 8);
		// --- Validate the page number, we only print the first page
		if (page == 0) { // --- Create a graphic2D object a set the default
			// parameters

			g2d = (Graphics2D) g;
			g2d.setColor(Color.black);
			{
				try {

					String DataAtual = formatador.format(Data);

					g2d.drawImage(image, 15, 10, 565, 820, null);

					print.executeSQL("select  t1.*, t2.cidade_nome,t3.estado_sigla from filiais "
							+ "as t1 inner join cidade as t2  on t1.filial_cidade = t2.cidade_id inner join estado"
							+ " as t3  on t1.filial_estado = t3.estado_id where FILIAIS_SIGLA ='" + Destino + "'");
					print.resultset.first();
					int i = 1;
					int A = 0;
					while (i < 3) {

						g2d.setFont(titleFont);
						g2d.drawString(servico1, 56, 52 + A);
						g2d.drawString(servico2, 164, 52 + A);
						g2d.drawString(servico3, 276, 52 + A);
						g2d.drawString(servico4, 393, 52 + A);
						g2d.drawString(servico5, 483, 52 + A);
						g2d.drawString("CONTROLE", 470, 20 + A);
						g2d.drawString("DÉBITO EM C/C", 464, 63 + A);
						g2d.drawString("Nº", 464, 69 + A);

						g2d.drawString("REMETENTE", 20, 77 + A);
						g2d.drawString("DESTINATARIO", 305, 77 + A);
						g2d.drawString("CNPJ", 20, 104 + A);
						g2d.drawString("INSC.ESTADUAL", 152, 104 + A);
						g2d.drawString("CNPJ", 305, 104 + A);
						g2d.drawString("INSC.ESTADUAL", 467, 104 + A);
						g2d.drawString("ENDEREÇO", 20, 131 + A);
						g2d.drawString("ENDEREÇO", 305, 131 + A);
						g2d.drawString("FONE", 20, 158 + A);
						g2d.drawString("FONE", 305, 158 + A);
						g2d.drawString("BAIRRO", 92, 158 + A);
						// g2d.drawString("BAIRRO" , 20, 185+A);
						g2d.drawString("CIDADE", 20, 185 + A);
						g2d.drawString("UF", 187, 185 + A);
						g2d.drawString("BAIRRO", 381, 158 + A);

						// g2d.drawString("BAIRRO" , 305, 185+A);
						g2d.drawString("CIDADE", 305, 185 + A);
						g2d.drawString("UF", 465, 185 + A);
						g2d.drawString("NOME DO CONTATO", 20, 212 + A);

						g2d.drawString("AEROPORTO DE DESTINO", 242, 212 + A);

						g2d.drawString("Próprio - n apólice", 418, 250 + A);
						g2d.drawString("Seguradora:", 390, 258 + A);
						g2d.drawString("Sem Seguro - não declare valor mercadoria.", 418, 264 + A);
						g2d.drawString("Com Seguro " + CompaniaAerea + " - Informe Valor da Mercadoria.", 418, 272 + A);

						g2d.drawString("Data:", 22, 372 + A);
						g2d.drawString("Nome:", 133, 372 + A);

						g2d.drawString("Recebido pela " + CompaniaAerea + " em:", 260, 406 + A);
						g2d.drawString("Nome", 427, 406 + A);

						g2d.setFont(titleFont2);
						g2d.drawString("FRETE PAGO", 43, 67 + A);
						g2d.drawString("FRETE A COBRAR", 153, 67 + A);
						g2d.drawString("ENTREGA A DOMICÍLIO", 261, 67 + A);
						g2d.drawString("RETIRA NO AEROPORTO", 377, 67 + A);

						g2d.drawString(Conta, 513, 69 + A);

						g2d.drawString(TextoFedex, 80, 287 + A);

						g2d.drawString("QTD. VOLS:", 20, 242 + A);
						g2d.drawString("PESO(KG)", 72, 242 + A);
						g2d.drawString("EMBALAGEM/DESCRIÇÃO", 135, 242 + A);
						g2d.drawString("DECLARAÇÃO DE CONTEÚDO", 260, 242 + A);
						g2d.drawString("SEGURO", 465, 242 + A);
						g2d.drawString("1884000001101", 515, 251 + A);
						g2d.drawString("MAPFRE", 503, 258 + A);
						g2d.drawString(Adicionais, 70, 289 + A);
						g2d.drawString("Conhecimento Nº:", 378, 360 + A);
						g2d.drawString("Emitido por:", 378, 385 + A);

						g2d.drawString("ADICIONAIS:", 20, 287 + A);
						g2d.drawString("Notas Fiscais:", 20, 313 + A);
						g2d.drawString("Valor da Mercadoria:", 20, 342 + A);
						g2d.drawString(
								"AUTORIZO O EMBARQUE DOS VOLUMES RELACIONADOS, CONFORME ESPECIFICAÇÕES DESTA MINUTA",
								18, 356 + A);

						g2d.setFont(titleFont3);
						g2d.drawString("MINUTA DE DESPACHO MD -", 115, 27 + A);
						g2d.drawString("ELETRONICA", 140, 37 + A);

						g2d.drawString("HORÁRIO DE CHEGADA NA LOJA", 65, 397 + A);
						g2d.drawString("RECEBIMENTO:", 378, 397 + A);
						g2d.drawString("USO DA " + CompaniaAerea, 448, 343 + A);

						g2d.setFont(titleFont4);
						g2d.drawString("", 243, 55 + A);
						g2d.drawString("X", 360, 67 + A);

						g2d.drawString("X", 394, 254 + A);

						if (TipoServico.equals("Convencional ")) {
							g2d.drawString("X", 244, 54 + A);
						}
						if (TipoServico.equals("Proximo Dia  ")) {
							g2d.drawString("X", 136, 54 + A);
						}

						g2d.drawString(aeroporto, 245, 226 + A);

						g2d.drawString(QtdVolume, 36, 265 + A);
						g2d.drawString(Peso, 79, 265 + A);
						g2d.drawString(embalagem, 125, 265 + A);
						g2d.drawString(conteudo, 248, 265 + A);

						g2d.setFont(titleFont5);

						g2d.drawString(print.resultset.getString("FILIAL_NOME"), 305, 91 + A);

						g2d.drawString(print.resultset.getString("FILIAL_cnpj"), 305, 119 + A);

						g2d.drawString(print.resultset.getString("FILIAL_INSC"), 467, 119 + A);

						g2d.drawString(print.resultset.getString("FILIAL_LOGRADOURO"), 305, 146 + A);

						g2d.drawString(print.resultset.getString("ESTADO_SIGLA"), 465, 201 + A);
						g2d.drawString(Obs, 26, 225 + A);

						g2d.setFont(titleFont6);

						// g2d.drawString("Jrd. ROSICLER", 20, 201+A);
						g2d.drawString(print.resultset.getString("FILIAL_BAIRRO"), 381, 173 + A);
						// g2d.drawString(print.resultset.getString("DES_Bairro"),
						// 305, 201+A);

						g2d.drawString(print.resultset.getString("FILIAL_TELEFONE"), 305, 173 + A);

						g2d.drawString(print.resultset.getString("FILIAL_OBSERVACAO"), 242, 225 + A);

						g2d.drawString(print.resultset.getString("CIDADE_NOME"), 305, 201 + A);
						g2d.drawString(DataAtual, 40, 372 + A);
						g2d.drawString(Usuario, 160, 372 + A);
						A = 419;
						i++;

						// g2d.drawString("Ologradouro
						// �"+print.resultset.getString("Des_logradouro"),
						// 150, 70);
						// g2d.drawString("O CNPJ
						// �"+print.resultset.getString("Des_CNPJ"),
						// 150, 170);
					}
					print.executeSQL(
							"select  t1.*, t2.cidade_nome,t3.estado_sigla from filiais as t1 inner join cidade as t2  on t1.filial_cidade = t2.cidade_id inner join estado as t3  on t1.filial_estado = t3.estado_id where FILIAIS_SIGLA like '"
									+ Origem + "%'");
					print.resultset.first();
					i = 1;
					A = 0;
					while (i < 3) {

						g2d.setFont(titleFont5);
						g2d.drawString(notafiscal, 70, 312 + A);

						g2d.drawString(print.resultset.getString("FILIAL_NOME"), 20, 91 + A);
						g2d.drawString(print.resultset.getString("FILIAL_cnpj"), 20, 119 + A);

						g2d.drawString(print.resultset.getString("FILIAL_INSC"), 152, 119 + A);

						g2d.drawString(print.resultset.getString("FILIAL_LOGRADOURO"), 20, 146 + A);

						g2d.drawString(print.resultset.getString("ESTADO_SIGLA"), 187, 201 + A);

						g2d.setFont(titleFont6);
						g2d.drawString(print.resultset.getString("FILIAL_BAIRRO"), 92, 173 + A);
						// g2d.drawString("Jrd. ROSICLER", 20, 201+A);

						// g2d.drawString(print.resultset.getString("DES_Bairro"),
						// 305, 201+A);
						g2d.drawString(print.resultset.getString("FILIAL_TELEFONE"), 20, 173 + A);

						g2d.drawString(print.resultset.getString("CIDADE_NOME"), 20, 201 + A);

						A = 419;
						i++;

						// g2d.drawString("Ologradouro
						// �"+print.resultset.getString("Des_logradouro"),
						// 150, 70);
						// g2d.drawString("O CNPJ
						// �"+print.resultset.getString("Des_CNPJ"),
						// 150, 170);
					}
				} catch (SQLException erro) {
				}

			}

			return (PAGE_EXISTS);
		} else {
			return (NO_SUCH_PAGE);
		}

	}

}
