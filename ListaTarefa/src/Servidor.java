

import java.io.*;
import java.net.*;
import java.util.ArrayList;

class Servidor {
	public static void main(String argv[]) throws Exception {
		
		
		String resposta;
		ArrayList<String> tarefas = new ArrayList<>();
		int count = 0;
		
		
		ServerSocket socketRecepcao = new ServerSocket(6789);

		while(true) { 
			Socket socketConexao = socketRecepcao.accept();
			BufferedReader doCliente = new BufferedReader(new InputStreamReader(socketConexao.getInputStream()));
			DataOutputStream paraCliente = new DataOutputStream(socketConexao.getOutputStream());
			
			paraCliente.writeBytes("1) Adicionar item na lista;2) Mostrar lista;3) Remover item da lista"+'\n');
			

			/////////Opcao escolhida pelo Cliente/////////
			resposta = doCliente.readLine();
			//////////////////////////////////////////////
			
			if(resposta.equalsIgnoreCase("1")) 
			{
				doCliente = new BufferedReader(new InputStreamReader(socketConexao.getInputStream()));
				paraCliente = new DataOutputStream(socketConexao.getOutputStream());
				paraCliente.writeBytes("Digite a nova tarefa:"+'\n');
				resposta = doCliente.readLine();
				
				tarefas.add(count,resposta);
				paraCliente.writeBytes(tarefas.get(count)+'\n');
				count ++;
				
			}else if (resposta.equalsIgnoreCase("2")){
				doCliente = new BufferedReader(new InputStreamReader(socketConexao.getInputStream()));
				paraCliente = new DataOutputStream(socketConexao.getOutputStream());

				String itens = "";
				for(int i  = 0; i<tarefas.size(); i++){
					itens+=(i+1)+". "+tarefas.get(i)+";";
				}

				paraCliente.writeBytes(itens+'\n');
				
			}else if (resposta.equalsIgnoreCase("3")){
				doCliente = new BufferedReader(new InputStreamReader(socketConexao.getInputStream()));
				paraCliente = new DataOutputStream(socketConexao.getOutputStream());
				paraCliente.writeBytes("Digite o numero da tarefa para ser removida:"+'\n');
				String itens = "";
				for(int i  = 0; i<tarefas.size(); i++){
					itens+=(i+1)+". "+tarefas.get(i)+";";
				}

				paraCliente.writeBytes(itens+'\n');
				resposta = doCliente.readLine();

				int index = Integer.valueOf(resposta);

				String tarefaRemovida = tarefas.get(index-1);
				tarefas.remove(index-1);

				paraCliente.writeBytes("A tarefa: "+tarefaRemovida+" foi removida"+'\n');
				
			}
			//////////////////
			
		    
			
		}
	}
}

