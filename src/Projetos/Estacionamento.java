package Projetos;

//Maicon Felipe da Silva Souza e Luiz Manoel da Cunha Dantas

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Estacionamento {
	private String[] placas; //armazena a placa ou "livre"

	public Estacionamento(int n) throws Exception{   

		//n eh o numero de vagas
		if(n <= 0) {
			throw new Exception("O numero de vagas tem que ser maior que zero."); //Objeto Exception
		}

		//Criacao dos arquivos texto historico, placas so sera criado pelo metodo gravarDados.
		FileWriter arquivoHist = new FileWriter("C:/Users/luizm/Desktop/ProjetoFinal/src/Projetos/historico.csv",true);
		arquivoHist.close();
		
		FileWriter arquivoPlacas = new FileWriter("C:/Users/luizm/Desktop/ProjetoFinal/src/Projetos/placas.csv",false);
		arquivoPlacas.close();

		//criacao do array placas com as vagas nao ocupadas tendo a string "livre"
		String[] array = new String[n];
		for(int i = 0; i < n; i++) {
			array[i] = "livre";
		}
		this.placas = array;
	}

	public void entrar(String placa, int vaga) throws Exception{ 

		
		//Retirar as strings vazias da placa
		placa = placa.replaceAll(" ", "");
		placa = placa.toLowerCase();
		
		//convertendo o numero visto pelo usuario para um indice de array
		int indice = vaga - 1;
	
		if(placa.length() == 0) {
			throw new Exception("Digite uma placa! Este campo � obrigat�rio.");
		}
		
		if(vaga < 1 || vaga > this.placas.length) {
			throw new Exception("ERRO: O numero da vaga tem que ser um numero entre 1 e "+this.placas.length+". A vaga de numero " + vaga + " nao existe.");
		}

		if(this.placas[indice] != "livre")
			throw new Exception("ERRO: Voce escolheu uma vaga que ja esta ocupada, escolha uma vaga vazia. Lista de vagas vazias = " + this.listarLivres());

		//ocupa a vaga com a placa obtida
		this.placas[indice] = placa;

		//colocando as informacoes no arquivo historico.csv
		FileWriter arquivo = new FileWriter("C:/Users/luizm/Desktop/ProjetoFinal/src/Projetos/historico.csv",true);

		//Obter a data de entrada formatada
		LocalDateTime agora = LocalDateTime.now();
		DateTimeFormatter formatador;
		formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		String data = agora.format(formatador);

		//escrevendo no arquivo texto de acordo com o formato pedido do historico.csv
		arquivo.write(data + ";" + vaga + ";" + this.placas[indice] + ";" + "entrada" + "\n");
		arquivo.close();
	}

	public void sair(int vaga) throws Exception{

		//convertendo o numero visto pelo usuario para um indice de array
		int indice = vaga - 1;
		
		//parte do c�digo para verificar se estacionamento est� vazio ou nao
		int contagem = 0; 
		for(String vazio : this.placas) {
			if(vazio == "livre") {
				contagem += 1;
			}
			if(contagem == this.placas.length) {
				throw new Exception("O Estacionamento esta vazio, por favor Estacione um ve�culo.");
			}
		}
			

		if(vaga < 1 || vaga > this.placas.length) {
			throw new Exception("ERRO: O numero da vaga tem que ser um numero entre 1 e "+this.placas.length+". A vaga de numero " + vaga + " nao existe.");
		}

		//criacao de array para guardar as vagas que estao ocupadas
		ArrayList<Integer> vagasOcupadas = new ArrayList<>();
		for(int i = 0; i < this.placas.length; i++) {
			if(this.placas[i] != "livre") {
				vagasOcupadas.add(i+1); 
			} 	
		}
		
		//vai usar as vagas ocupadas como uma guia para contornar o erro
		if(this.placas[indice] == "livre")
			throw new Exception("ERRO: Voce escolheu uma vaga vazia, escolha uma vaga ocupada por favor. Lista de vagas Ocupadas = " + vagasOcupadas );

		//colocando os dados dentro do arquivo historico.csv
		FileWriter arquivo2 = new FileWriter("C:/Users/luizm/Desktop/ProjetoFinal/src/Projetos/historico.csv",true);

		LocalDateTime agora = LocalDateTime.now();
		DateTimeFormatter formatador;
		formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		String hoje = agora.format(formatador);

		arquivo2.write(hoje + ";" + vaga + ";" + this.placas[indice] + ";" + "sa�da" + "\n");
		arquivo2.close();

		this.placas[indice] = "livre";
	}

	public int consultarPlaca(String placa) {
		
		//Retirar as strings vazias da placa
		placa = placa.replaceAll(" ", "");
		placa = placa.toLowerCase();
				
		
		int vaga = 0; //inicializando a variavel vaga

		for(int i = 0; i < this.placas.length; i++) { //for vai percorrer todo o array de strings placas		
			if(this.placas[i].equals(placa)) { //se o elemento de determinado indice do array for igual a placa passada
				vaga = i + 1; //sera retornado o indice do array + 1, que vai ser igual a vaga que o usuario consegue compreender
				break; //break para sair do loop for
			} 
			else {
				vaga = -1;
			}	
		}
		return vaga;
	}

	public void transferir(int vaga1, int vaga2) throws Exception{ 

		//convertendo o numero visto pelo usuario para um indice de array
		int indiceOrigem = vaga1 - 1;
		int indiceDestino = vaga2 - 1;

		if(vaga1 < 1 || vaga2 < 1 || vaga1 > this.placas.length || vaga2 > this.placas.length) {
			throw new Exception("ERRO: O numero da vaga de origem e a de destino tem que ser um numero entre 1 e "+this.placas.length+".");
		}

		ArrayList<Integer> vagasOcupadas = new ArrayList<>();
		for(int i=0; i<this.placas.length; i++) {
			if(this.placas[i] != "livre") {
				vagasOcupadas.add((i+1));
			} 
		}

		if(vaga1 == vaga2)
			throw new Exception("ERRO: Voce transferiu uma vaga para a mesma vaga. Vagas vazias = "+this.listarLivres()+ " e Vagas Ocupadas = "+vagasOcupadas+".");

		if(this.placas[indiceOrigem] == "livre" && this.placas[indiceDestino] == "livre")
			throw new Exception("ERR0: a primeira vaga que foi escolhida tem que estar ocupada e a segunda tem que estar vazia. Vagas vazias = "+this.listarLivres()+ " e Vagas Ocupadas = " + vagasOcupadas + ".");

		if(this.placas[indiceOrigem] != "livre" && this.placas[indiceDestino] != "livre")
			throw new Exception("ERRO: a primeira vaga que foi escolhida tem que estar ocupada e a segunda tem que estar vazia. Vagas vazias = "+this.listarLivres()+ " e Vagas Ocupadas = " + vagasOcupadas + ".");

		if(this.placas[indiceOrigem] == "livre" && this.placas[indiceDestino] != "livre")
			throw new Exception("ERR0: a primeira vaga que foi escolhida tem que estar ocupada e a segunda tem que estar vazia. Vagas vazias = "+this.listarLivres()+ " e Vagas Ocupadas = " + vagasOcupadas + ".");

		String troca = null; //variavel de troca
		troca = this.placas[indiceOrigem]; //recebe a placa que ocupa uma vaga
		this.placas[indiceOrigem] = this.placas[indiceDestino]; //ocorre a primeira troca 
		this.placas[indiceDestino] = troca; //trocas concretizadas
	}

	public String[] listarGeral() { 
		return this.placas;
	}

	public ArrayList<Integer> listarLivres(){ 

		ArrayList<Integer> vagasLivres = new ArrayList<>();
		for(int i=0; i<this.placas.length;i++) {
			if(this.placas[i] == "livre") {
				vagasLivres.add(i + 1);
			}	
		}
		return vagasLivres;
	}

	public void gravarDados() throws Exception { 

		//acessando o arquivo placas para gravar a placa de cada vaga ocupada atualmente.
		FileWriter arquivo = new FileWriter(new File("C:/Users/luizm/Desktop/ProjetoFinal/src/Projetos/placas.csv"), false);

		int vaga = 0;
		
		for(int i = 0; i < this.placas.length; i++) { //O for vai percorrer todo o array de strings placas, e quando achar uma placa de uma vaga ocupada, vai gravar(write) no arquivo

			vaga = i + 1; //deixar a vaga legivel ao usuario.

			if(this.placas[i] != "livre") //se estah ocupada 
				arquivo.write(vaga + ";" + this.placas[i] + "\n");	
		}
		
		arquivo.close();
	}

	public void lerDados() throws Exception{ 
		Scanner arquivo = null;
		
		arquivo = new Scanner(new File("C:/Users/luizm/Desktop/ProjetoFinal/src/Projetos/placas.csv"));
		
		File file = new File("C:/Users/luizm/Desktop/ProjetoFinal/src/Projetos/placas.csv");
		
		if (file.length() == 0L) {
			System.out.println("O Arquivo (placas.csv) est� vazio.");
			
			
		}else {
			String inf;
			String placa;
			String[] partes;
			int vaga;
			

			while(arquivo.hasNextLine()) {
				inf = arquivo.nextLine(); //armazena a linha
				partes = inf.split(";"); //divide em partes

				vaga = Integer.parseInt(partes[0]);
				placa = partes[1];
				System.out.println(vaga + " - " + placa);	
			}
			arquivo.close();

			
		}
		
			}
}