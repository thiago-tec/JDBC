package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AtualizacaoUsuario {

	public static void main(String[] args) throws SQLException {

		Scanner entrada = new Scanner(System.in);
		Connection conexao = FabricaConexao.getConexao();
		String sql = "SELECT * FROM pessoas WHERE nome like ?";

		System.out.print("pesquise o nome: ");
		String pesquisa = entrada.nextLine();

		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, "%" + pesquisa + "%");
		ResultSet resultado = stmt.executeQuery();

		List<Pessoa> pessoas = new ArrayList<>();

		while (resultado.next()) {
			int codigo = resultado.getInt("codigo");
			String nome = resultado.getString("nome");
			pessoas.add(new Pessoa(codigo, nome));

		}

		for (Pessoa pessoa : pessoas) {
			System.out.println(pessoa.getCodigo() + " - " + pessoa.getNome());
		}

		System.out.println("é essa pessoa que gostaria de alterar ?(sim ou não)");
		String resposta = entrada.nextLine();

		if (resposta.equalsIgnoreCase("sim")) {

			System.out.print("qual o novo nome: ");
			String novoNome = entrada.nextLine();

			System.out.print("qual o id: ");
			int codigo = entrada.nextInt();

			String atualizacao = "UPDATE pessoas SET nome = ? WHERE codigo = ?";
			PreparedStatement stmtAtt = conexao.prepareStatement(atualizacao);
			stmtAtt.setString(1, novoNome);
			stmtAtt.setInt(2, codigo);
			stmtAtt.execute();
			stmtAtt.close();
			System.out.println("Atualizada com sucesso !");
			

		} else {
			System.out.println("pessoa não encontrata");
		}

		entrada.close();
		stmt.close();
		conexao.close();

	}

}
