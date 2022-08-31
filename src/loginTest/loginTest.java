package loginTest;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;


public class loginTest {

	@Test
	public void test() throws SQLException  {
		LoginController con = new LoginController();
		
		int validacion = con.ingresarBtn("felipe","felipe19");
		
		assertEquals(2,validacion,0.0001);
		
		
	}

}
