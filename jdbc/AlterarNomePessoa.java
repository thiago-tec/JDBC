package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AlterarNomePessoa {

	public static void main(String[] args) throws SQLException {

		Scanner entrada = new Scanner(System.in);
		Connection conexao = FabricaConexao.getConexao();

		System.out.print("informe o codigo da pessoa: ");
		int codigo = entrada.nextInt();
		
		String select = "SELECT codigo, nome FROM pessoas WHERE  codigo = ?";
		String update = "UPDATE pessoa SET nome = ? WHERE codigo = ?";
		PreparedStatement stmt = conexao.prepareStatement(select);
		stmt.setInt(1, codigo);
		ResultSet resultado = stmt.executeQuery();
		
		if(resultado.next()) {
			Pessoa p = new Pessoa(resultado.getInt(1), resultado.getString(2));
			System.out.println("O nome selecionado é " + p.getNome());
			
			System.out.println("Informe o novo nome: ");
			String novoNome = entrada.next();
			
			stmt.close();
			stmt = conexao.prepareStatement(update);
			stmt.setString(1, novoNome);
			stmt.setInt(2, codigo);
			stmt.execute();
			System.out.println("pessoa alterada.");
			
			entrada.close();
			
		}
		
		conexao.close();
		entrada.close();
	}

}
