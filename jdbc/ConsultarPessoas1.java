package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConsultarPessoas1 {
	
	public static void main(String[] args) throws SQLException {
		
		Connection conexao = FabricaConexao.getConexao();
		String sql = "SELECT * FROM pessoas";
		
		Statement stmt = conexao.createStatement();
		ResultSet resultado = stmt.executeQuery(sql);//pega o resultado o banco
		
		List<Pessoa> pessoas = new ArrayList<>();
		
		
		while(resultado.next()) { // fazendo iteração com o metodo next do resultset
			int codigo = resultado.getInt("codigo") ;//pode pegar por indice tbm (1)
			String nome = resultado.getString("nome");
			pessoas.add(new Pessoa(codigo, nome)); //jogando os dados para um objeto 
						
		}
		
		for (Pessoa pessoa : pessoas) { //mostrando os dados q foi para o objeto
			System.out.println(pessoa.getCodigo() + " - "+ pessoa.getNome());
		}
		
		stmt.close();
		conexao.close();
		
	}

}
