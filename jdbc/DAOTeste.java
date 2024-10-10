package jdbc;

public class DAOTeste {

	public static void main(String[] args) {
		
		DAO dao = new DAO();
		
//		String sql = "INSERT INTO pessoas (nome) VALUES (?)";
		
//		System.out.println(dao.cmSql(sql, "João"));
		
		String sql2 = "DELETE FROM pessoas WHERE nome = ?";
		
		System.out.println(dao.cmSql(sql2, "João"));
		
		dao.close();

	}

}
