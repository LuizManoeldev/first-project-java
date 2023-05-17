/* 
*********************************
POO - Sistema de Controle de Estacionamento
ALUNOS:	LUIZ MANOEL DA CUNHA DANTAS
		MAICON FELIPE DA SILVA SOUZA
*********************************
*/
package Projetos;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Valetinho {

	private JFrame frmValetinho;
	
	private JTextField txtVaga;
	private JTextField txtPlaca;
	private JTextField txtVagaAtual;
	private JTextField txtNovaVaga;

	private JLabel lblVagaAtual;
	private JLabel lblNovaVaga;
	private JLabel lblPlaca;
	private JLabel lblVaga;

	private JButton btnConsultar;
	private JButton btnListagemGeral;
	private JButton btnListarLivres;
	private JButton btnRetirar;
	private JButton btnEstacionar;
	private JButton btnTransferir;
	
	private JTextArea Output;

	private Estacionamento estacionamento;

	private JTextArea listaVagas;
	private JScrollPane scrollPane;
	
	private JLabel txtEscolha;
	private JLabel vagasTotais;
	
	private JButton realizarEstacionar;
	private JButton realizarRetirar;
	private JButton realizarTransferir;
	private JButton realizarConsultar;
	
	
	
	private JButton voltar;
	private JButton EncerrarSalvar;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Valetinho window = new Valetinho();
					window.frmValetinho.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws Exception 
	 */
	public Valetinho() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize() throws Exception {
		frmValetinho = new JFrame();
		frmValetinho.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmValetinho.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 11));
		frmValetinho.getContentPane().setBackground(new Color(192, 192, 192));
		frmValetinho.setTitle("Valetinho");
		
		frmValetinho.getContentPane().setLayout(null); //layout do Painel de conteudo

		//inicializacao da classe Estacionamento
		int vagas = 10;
		estacionamento = new Estacionamento(vagas);

		// Uso de TextArea para quebra de linha
		Output = new JTextArea();
		Output.setWrapStyleWord(true);
		Output.setBackground(new Color(192, 192, 192));
		
		Output.setEditable(false);
		Output.setLineWrap(true);
		Output.setFont(new Font("Serif", Font.PLAIN, 30));
		Output.setVisible(false);
		frmValetinho.getContentPane().add(Output);

		//criacao do textArea junto do scrollPane, so ficarao visiveis ao usar listar geral e listar livres
		scrollPane = new JScrollPane();
		
		frmValetinho.getContentPane().add(scrollPane);
		scrollPane.setVisible(false);
		
		
		// Labels para indica��o dos dados a serem digitados
		lblVaga = new JLabel("VAGA");
		
		frmValetinho.getContentPane().add(lblVaga);

		lblPlaca = new JLabel("PLACA");
		
		frmValetinho.getContentPane().add(lblPlaca);

		// Campo para inserir vaga para estacionamento
		txtVaga = new JTextField();
		txtVaga.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		frmValetinho.getContentPane().add(txtVaga);
		txtVaga.setColumns(10);

		// Placa do carro a ser estacionado
		txtPlaca = new JTextField();
		//formatacao do botao na tela
		txtPlaca.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		
		frmValetinho.getContentPane().add(txtPlaca);
		txtPlaca.setColumns(10);


		txtEscolha = new JLabel("Escolha sua Op\u00E7\u00E3o");
		txtEscolha.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtEscolha.setBounds(245, 11, 174, 26);
		frmValetinho.getContentPane().add(txtEscolha);

		vagasTotais = new JLabel("Total de vagas no estacionamento: " + vagas);
		vagasTotais.setFont(new Font("Tahoma", Font.PLAIN, 16));
		vagasTotais.setBounds(185, 41, 319, 14);
		frmValetinho.getContentPane().add(vagasTotais);

		// Campo para inserir vaga atual do veiculo a ser transferido
		txtVagaAtual = new JTextField();
		txtVagaAtual.setBounds(271, 117, 126, 26);
		txtVagaAtual.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		frmValetinho.getContentPane().add(txtVagaAtual);
		txtVagaAtual.setColumns(10);

		// Vaga de destino para operacao de transferencia
		txtNovaVaga = new JTextField();
		txtNovaVaga.setBounds(271, 154, 126, 26);
		txtNovaVaga.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		frmValetinho.getContentPane().add(txtNovaVaga);
		txtNovaVaga.setColumns(10);

		// Labels para indica��o do dado a ser inserido
		lblVagaAtual = new JLabel("VAGA ATUAL");
		lblVagaAtual.setBounds(175, 124, 76, 13);
		frmValetinho.getContentPane().add(lblVagaAtual);

		lblNovaVaga = new JLabel("NOVA VAGA");
		lblNovaVaga.setBounds(175, 161, 85, 13);
		frmValetinho.getContentPane().add(lblNovaVaga);

		realizarEstacionar = new JButton("Estacionar");
		realizarEstacionar.setBounds(231, 202, 100, 31);
		frmValetinho.getContentPane().add(realizarEstacionar);
		
		realizarConsultar = new JButton("Consultar");
		realizarConsultar.setBounds(231, 202, 100, 31);
		frmValetinho.getContentPane().add(realizarConsultar);
		
		realizarTransferir = new JButton("Transferir");
		realizarTransferir.setBounds(231, 202, 100, 31);
		frmValetinho.getContentPane().add(realizarTransferir);
		
		realizarRetirar = new JButton("Retirar");
		realizarRetirar.setBounds(231, 202, 100, 31);
		frmValetinho.getContentPane().add(realizarRetirar);
		
		txtVaga.setVisible(false);
		txtPlaca.setVisible(false);
		txtVagaAtual.setVisible(false);
		txtNovaVaga.setVisible(false);
		
		lblVaga.setVisible(false);
		lblPlaca.setVisible(false);
		lblVagaAtual.setVisible(false);
		lblNovaVaga.setVisible(false);
		
		realizarRetirar.setVisible(false);
		realizarConsultar.setVisible(false);
		realizarTransferir.setVisible(false);
		realizarEstacionar.setVisible(false);
		
		frmValetinho.setBounds(100, 100, 700, 600);
		Output.setBounds(114, 367, 479, 148);
		scrollPane.setBounds(231, 377, 201, 174);
		
		listaVagas = new JTextArea();
		scrollPane.setViewportView(listaVagas);
		listaVagas.setBackground(new Color(255, 255, 255));
		listaVagas.setEditable(false);
		listaVagas.setVisible(false);
		lblVaga.setBounds(185, 124, 40, 13);
		lblPlaca.setBounds(185, 161, 40, 13);
		txtVaga.setBounds(271, 117, 126, 26);
		txtPlaca.setBounds(271, 154, 126, 26);
		
		realizarEstacionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						//usando label de obrigatoriedade

						//Retirar as strings vazias da placa
						
						
						String tvaga = txtVaga.getText().replaceAll(" ", "");
						String placa = txtPlaca.getText().replaceAll(" ", "");
						placa = placa.toLowerCase();

						if(placa.isEmpty() && tvaga.isEmpty()) {
							Output.setText("");
							Output.setText("Preencha os campos Vaga e Placa, sao obrigatorios!");
							txtVaga.setText("");
							txtPlaca.setText("");
							txtVaga.requestFocus();

						} else if(placa.isEmpty()) {
							Output.setText("");
							Output.setText("Preencha o campo Placa, e obrigatorio!");

							txtPlaca.setText("");
							txtPlaca.requestFocus();
						} else if(tvaga.isEmpty()) {
							Output.setText("");
							Output.setText("Preencha o campo vaga, e obrigatorio!");
							txtVaga.setText("");
							txtVaga.requestFocus();

						} else {
							int vaga = Integer.parseInt(tvaga);
							estacionamento.entrar(placa, vaga);
							Output.setText("");
							Output.setText("O carro ("+ placa +") entrou na vaga "+vaga+"!");
							txtVaga.setText("");
							txtPlaca.setText("");
							txtVaga.requestFocus();
							
						}

					}catch (Exception e1) {
						JOptionPane.showMessageDialog(null,e1.getMessage());
						txtVaga.setText("");
						txtPlaca.setText("");
						txtVaga.requestFocus();
						Output.setText("");
					}
					
					
				}
				
			}
		);
		
		realizarTransferir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String tVagaAtual = txtVagaAtual.getText().replaceAll(" ", "");;
					String tNovaVaga = txtNovaVaga.getText().replaceAll(" ", "");;

					if(tVagaAtual.isEmpty() && tNovaVaga.isEmpty()) {
						Output.setText("");
						Output.setText("Preencha os campos Nova Vaga e Vaga Atual!");
						txtVagaAtual.setText("");
						txtNovaVaga.setText("");
						txtVagaAtual.requestFocus();

					} else if(tVagaAtual.isEmpty()) {
						Output.setText("");
						Output.setText("Preencha o campo Vaga Atual!");
						txtVagaAtual.setText("");
						txtVagaAtual.requestFocus();
					}
					else if(tNovaVaga.isEmpty()) {
						Output.setText("");
						Output.setText("Preencha o campo Nova Vaga!");
						txtNovaVaga.setText("");
						txtNovaVaga.requestFocus();
					}
					else {
						int vagaAtual = Integer.parseInt(tVagaAtual);
						int NovaVaga = Integer.parseInt(tNovaVaga);

						String placatroca = estacionamento.listarGeral()[vagaAtual-1];
						estacionamento.transferir(vagaAtual, NovaVaga);
						Output.setText("");								
						Output.setText("Carro ("+ placatroca  +") transferido da vaga " + vagaAtual + " para a " + NovaVaga);
						txtNovaVaga.setText("");
						txtVagaAtual.setText("");
						txtVagaAtual.requestFocus();
						}

										
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					txtNovaVaga.setText("");
					txtVagaAtual.setText("");
					txtVagaAtual.requestFocus();
				}

				

			}});
		
		realizarConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					String placa = txtPlaca.getText().replaceAll(" ", "");
					placa = placa.toLowerCase();
					
					if (placa.isEmpty()) {
						Output.setText("");
						Output.setText("Digite a placa! Este campo e obrigatorio para consulta!");
						txtPlaca.setText("");
						txtPlaca.requestFocus();

					}
					else {
						int vaga =  estacionamento.consultarPlaca(placa);
						if(vaga == -1) {
							Output.setText("");
							Output.setText("O carro ("+placa + ") � inexistente.");
							txtPlaca.setText("");
							txtPlaca.requestFocus();
						}else {
							Output.setText("");
							Output.setText("O carro ("+placa + ") esta na vaga "+ vaga+".");
							txtPlaca.setText("");
							txtPlaca.requestFocus();
						}
					}


				}catch (Exception E) {
					JOptionPane.showMessageDialog(null,E.getMessage());
					Output.setText("");
					txtPlaca.setText("");
					txtPlaca.requestFocus();
				}
			}});

		
		
		
		realizarRetirar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Output.setText("");
				try {
					String tvaga = txtVaga.getText().replaceAll(" ", "");

					if(tvaga.isEmpty()) {
						Output.setText("Digite a Vaga que deseja desocupar!");
						txtVaga.setText("");
						txtVaga.requestFocus();

					}else {
						String placaSaida = "";
						int vaga = Integer.parseInt(txtVaga.getText()); //.getText() para pegar o conte�do de um JTextField 

						for(int i = 0; i <= vagas ; i++) {
							if (i == (vaga - 1)) { //(vaga - 1) : transcrevendo da linguagem que o usuario entende 
								placaSaida = estacionamento.listarGeral()[i]; // guarda a placa que saiu de determinada vaga
							}
						}

						estacionamento.sair(vaga);
						Output.setText("");
						Output.setText("O carro (" + placaSaida + ") da vaga "+vaga+" foi retirado.");
						txtVaga.setText("");
						txtVaga.requestFocus();

					}
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage());
					txtVaga.setText("");
					txtVaga.requestFocus();
				}

			}});
		
		voltar = new JButton("Voltar");
		voltar.setVisible(false);
		voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnConsultar.setVisible(true);
				btnListagemGeral.setVisible(true);
				btnListarLivres.setVisible(true);
				btnRetirar.setVisible(true);
				btnEstacionar.setVisible(true);
				btnTransferir.setVisible(true);
				
				txtVaga.setVisible(false);
				txtPlaca.setVisible(false);
				
				lblPlaca.setVisible(false);
				lblVaga.setVisible(false);
				
				txtVagaAtual.setVisible(false);
				txtNovaVaga.setVisible(false);
				
				lblVagaAtual.setVisible(false);
				lblNovaVaga.setVisible(false);
				
				realizarEstacionar.setVisible(false);
				realizarTransferir.setVisible(false);
				realizarConsultar.setVisible(false);
				realizarRetirar.setVisible(false);
				voltar.setVisible(false);
				
				Output.setText("");
				Output.setVisible(false);
				
				listaVagas.setVisible(false);
				scrollPane.setVisible(false);
				btnListarLivres.setEnabled(true);
				btnListagemGeral.setEnabled(true);
				txtVaga.setText("");
				txtPlaca.setText("");
				txtNovaVaga.setText("");
				txtVagaAtual.setText("");
				btnListarLivres.setEnabled(true);
				btnListagemGeral.setEnabled(true);
				
				txtEscolha.setVisible(true);
				vagasTotais.setVisible(true);
				
			}
		});
		
		voltar.setBounds(347, 202, 100, 31);
		frmValetinho.getContentPane().add(voltar);

		
		// Botao para realizar opera��o de estacionamento 
		btnEstacionar = new JButton("ESTACIONAR");
		btnEstacionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				txtEscolha.setVisible(false);
				vagasTotais.setVisible(false);
				
				btnListarLivres.setEnabled(true);
				btnListagemGeral.setEnabled(true);

				//fechando todos os bot�es
				btnConsultar.setVisible(false);
				btnListagemGeral.setVisible(false);
				btnListarLivres.setVisible(false);
				btnRetirar.setVisible(false);
				btnEstacionar.setVisible(false);
				btnTransferir.setVisible(false);
				
				lblPlaca.setVisible(true);
				lblVaga.setVisible(true);
				
				txtVaga.setVisible(true);
				txtPlaca.setVisible(true);
				
				realizarEstacionar.setVisible(true);
				voltar.setVisible(true);
				
				listaVagas.setVisible(false);
				scrollPane.setVisible(false);
				txtVaga.requestFocus();
				Output.setVisible(true);
			}
			
		});
		
		//formatacao do botao na tela
		btnEstacionar.setBounds(231, 117, 201, 40);
		frmValetinho.getContentPane().add(btnEstacionar);


				//Botao para realiza��o da opera��o de trasnferencia
		btnTransferir = new JButton("TRANSFERIR");
		btnTransferir.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				txtEscolha.setVisible(false);
				vagasTotais.setVisible(false);
				
				btnConsultar.setVisible(false);
				btnListagemGeral.setVisible(false);
				btnListarLivres.setVisible(false);
				btnRetirar.setVisible(false);
				btnEstacionar.setVisible(false);
				btnTransferir.setVisible(false);
				
				Output.setVisible(true);
				
				txtVagaAtual.setVisible(true);
				txtNovaVaga.setVisible(true);
			
				lblVagaAtual.setVisible(true);
				lblNovaVaga.setVisible(true);
				
				btnListarLivres.setEnabled(true);
				btnListagemGeral.setEnabled(true);
				
				realizarTransferir.setVisible(true);
				voltar.setVisible(true);
				
				listaVagas.setVisible(false);
				scrollPane.setVisible(false);
				
				txtVagaAtual.requestFocus();
				
				txtVagaAtual.setText("");
				txtNovaVaga.setText("");
				
				}});
		//formata��o do botao na tela
		btnTransferir.setBounds(231, 197, 201, 40);
		frmValetinho.getContentPane().add(btnTransferir);

		//Botao para realizar opera��o de consulta
		btnConsultar = new JButton("CONSULTAR");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtEscolha.setVisible(false);
				vagasTotais.setVisible(false);
				
				txtPlaca.setVisible(true);
				lblPlaca.setVisible(true);
				realizarConsultar.setVisible(true);
				voltar.setVisible(true);
				
				btnListarLivres.setEnabled(true);
				btnListagemGeral.setEnabled(true);
				
				btnConsultar.setVisible(false);
				btnListagemGeral.setVisible(false);
				btnListarLivres.setVisible(false);
				btnRetirar.setVisible(false);
				btnEstacionar.setVisible(false);
				btnTransferir.setVisible(false);
				
				listaVagas.setVisible(false);
				scrollPane.setVisible(false);
				
				txtPlaca.requestFocus();
				Output.setVisible(true);				
				
			}
		});
		//formata��o do botao na tela
		btnConsultar.setBounds(231, 237, 201, 40);
		frmValetinho.getContentPane().add(btnConsultar);


		// Botao para listar as vagas gerais
		btnListagemGeral = new JButton("LISTAGEM GERAL");
		btnListagemGeral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Output.setVisible(false);
				btnListarLivres.setEnabled(true);
				listaVagas.setVisible(true);
				scrollPane.setVisible(true);
				listaVagas.setText("");
				String saida = "";
				Output.setText("");
				int contagem = 1;

				for(String s : estacionamento.listarGeral()) {
					saida = s;
					listaVagas.append("  " + contagem + " - " + saida + "\n");
					contagem++;

				}
				listaVagas.setCaretPosition(0);
				btnListagemGeral.setEnabled(false);
			}
		});
		//formata��o do botao na tela
		btnListagemGeral.setBounds(231, 277, 201, 40);
		frmValetinho.getContentPane().add(btnListagemGeral);


		// Botao para listar apenas as vagas livres
		btnListarLivres = new JButton("LISTAR LIVRES");
		btnListarLivres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Output.setVisible(false);
				btnListagemGeral.setEnabled(true);
				listaVagas.setVisible(true);
				scrollPane.setVisible(true);
				listaVagas.setText("");
				Output.setText("");
				listaVagas.append(" - Vagas Livres: \n");


				for(int cont : estacionamento.listarLivres()) {
					int saida = cont;
					listaVagas.append("  " + saida + "\n");					
				}
				listaVagas.setCaretPosition(0);
				btnListarLivres.setEnabled(false);
			}
		});
		//formata��o do botao na tela
		btnListarLivres.setBounds(231, 316, 201, 40);
		frmValetinho.getContentPane().add(btnListarLivres);

		

		//bot�o para saida de um veiculo
		/* 

		 * */
		btnRetirar = new JButton("RETIRAR");
		btnRetirar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtEscolha.setVisible(false);
				vagasTotais.setVisible(false);
				
				lblVaga.setVisible(true);
				txtVaga.setVisible(true);
				realizarRetirar.setVisible(true);
				voltar.setVisible(true);
				
				btnListarLivres.setEnabled(true);
				btnListagemGeral.setEnabled(true);
				
				listaVagas.setVisible(false);
				scrollPane.setVisible(false);
				
				btnConsultar.setVisible(false);
				btnListagemGeral.setVisible(false);
				btnListarLivres.setVisible(false);
				btnRetirar.setVisible(false);
				btnEstacionar.setVisible(false);
				btnTransferir.setVisible(false);
				txtVaga.requestFocus();
				Output.setVisible(true);
							}
		});
		//formata��o do botao na tela
		btnRetirar.setBounds(231, 158, 201, 40);
		frmValetinho.getContentPane().add(btnRetirar);
		
		EncerrarSalvar = new JButton("ENCERRAR E SALVAR");
		EncerrarSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					estacionamento.gravarDados();
					if(estacionamento.listarLivres().size() == 10) {
						JOptionPane.showMessageDialog(null,"O estacionamento est� vazio, logo, o arquivo (placas.csv) ficar� vazio.");
					}
					else {
						JOptionPane.showMessageDialog(null,"As placas atuais foram gravadas em 'placas.csv'");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				frmValetinho.dispose();
			}
		});
		EncerrarSalvar.setForeground(Color.WHITE);
		EncerrarSalvar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		EncerrarSalvar.setBackground(Color.RED);
		EncerrarSalvar.setBounds(10, 520, 142, 31);
		frmValetinho.getContentPane().add(EncerrarSalvar);

	}
}