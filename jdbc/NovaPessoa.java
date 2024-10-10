package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class NovaPessoa {

	public static void main(String[] args) throws SQLException {
		
		
		Scanner entrada = new Scanner(System.in);
		Connection conexao = FabricaConexao.getConexao();
		
		System.out.print("informe o nome: ");
		String nome = entrada.nextLine();
		
		String sql = "INSERT INTO PESSOAS (nome) VALUES (?)";
		PreparedStatement stmt = conexao.prepareStatement(sql); //o preparedStatement seleciona o comando sql e onde tem o ? ele usa como parametro
		stmt.setString(1, nome); // aqui vc seleciona a quantidade de parametro  e depois o nome da varialvel que recebe o parametro
		
		stmt.execute(); // não pode esquecer de executar
		System.out.println("pessoa incluida com sucesso!");	
		entrada.close();
		
	}
}
