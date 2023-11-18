
import java.io.*;
import java.net.*;

public class Cliente {

	public static void main(String argv[]) throws Exception {
		boolean flag = true;
		while (flag) {
			String entrada;
			String respostaServ;

			BufferedReader doUsuario = new BufferedReader(new InputStreamReader(System.in));
			Socket socketCliente = new Socket("127.0.0.1", 6789);
			DataOutputStream paraServidor = new DataOutputStream(socketCliente.getOutputStream());
			BufferedReader doServidor = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));

			respostaServ = doServidor.readLine();

			String[] x = new String[3];
			x = respostaServ.split(";");
			for (int i = 0; i < x.length; i++) {
				if (x[i] != null) {
					System.out.println(x[i]);
				}
			}
			System.out.println();

			/////////////// Escolha de opcao /////////////////////////////

			entrada = doUsuario.readLine();
			paraServidor.writeBytes(entrada + '\n');
			/////////////////////////////////////////////////////////////

			/////////////// Adicionar item /////////////////////////////
			if (entrada.equalsIgnoreCase("1")) {
				doServidor = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
				respostaServ = doServidor.readLine();
				System.out.println(respostaServ);
				entrada = doUsuario.readLine();
				paraServidor.writeBytes(entrada + '\n');

				doServidor = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
				respostaServ = doServidor.readLine();
				System.out.println(respostaServ);
				//////////////////////////////////////////////////////////////////////////////////////////


				/////////////// Mostrar lista /////////////////////////////
			} else if (entrada.equalsIgnoreCase("2")) {
				doServidor = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
				respostaServ = doServidor.readLine();

				String[] lista = new String[10];
				lista = respostaServ.split(";");
				for (int i = 0; i < lista.length; i++) {
					if (lista[i] != null) {
						System.out.println(lista[i]);
					}
				}
				System.out.println();
			//////////////////////////////////////////////////////////////////////////////////////////


			/////////////// Remover Item /////////////////////////////
			}else if (entrada.equalsIgnoreCase("3")){
				doServidor = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
				respostaServ = doServidor.readLine();
				System.out.println(respostaServ);

				doServidor = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
				respostaServ = doServidor.readLine();
				String[] lista = new String[10];
				lista = respostaServ.split(";");
				for (int i = 0; i < lista.length; i++) {
					if (lista[i] != null) {
						System.out.println(lista[i]);
					}
				}
				System.out.println();

				entrada = doUsuario.readLine();
				paraServidor.writeBytes(entrada + '\n');

				doServidor = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
				respostaServ = doServidor.readLine();
				System.out.println(respostaServ);
			//////////////////////////////////////////////////////////////////////////////////////////

			}else if (entrada.equalsIgnoreCase("0")){
				flag = false;
			}

			socketCliente.close();

		
	}
}
}
