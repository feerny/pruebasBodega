package newProductTest;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

public class productTest {

	@Test
	public void test() throws SQLException {
		NewProduct prod= new NewProduct();
		
		int validacion = prod.clickNuevo( "sopa", "3500", "4000", "500", "30", "50");
		
		assertEquals(2,validacion,0.0001);
	}

}
