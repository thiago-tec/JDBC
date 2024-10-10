package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsultarPessoa2 {

	public static void main(String[] args) throws SQLException {

		Scanner entrada = new Scanner(System.in);
		Connection conexao = FabricaConexao.getConexao();
		String sql = "SELECT * FROM pessoas WHERE nome like ?";

		System.out.print("pesquise o nome: ");
		String pesquisa = entrada.nextLine();

		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, "%" + pesquisa + "%");//para passar algum caracter especial no comando sql tem q fazer dessa forma
		ResultSet resultado = stmt.executeQuery();
		System.out.println();

		List<Pessoa> pessoas = new ArrayList<>();

		while (resultado.next()) {
			int codigo = resultado.getInt("codigo");
			String nome = resultado.getString("nome");
			pessoas.add(new Pessoa(codigo, nome));

		}

		for (Pessoa pessoa : pessoas) {
			System.out.println(pessoa.getCodigo() + " - " + pessoa.getNome());
		}

		entrada.close();
		stmt.close();
		conexao.close();

	}

}
