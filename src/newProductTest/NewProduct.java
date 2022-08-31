package newProductTest;

import java.sql.SQLException;
import java.sql.Statement;

import coneccion.Conexion;


public class NewProduct {
	int vali=1;
	String estadoCliente,query;
    int codigoProducto;
    //consulta de cadena
    Conexion con = new Conexion();
	
    public int clickNuevo(String idNombre,String idPcom,String idPven,String idCbodega,String idCmin,String idCmax) throws SQLException {
        //Recuperar datos del formulario
        //Validar Datos
        //Preparar la insercion
        String nom2 = idNombre;
        String Pcom2 = idPcom;
        String Pven2 = idPven;
        String Cbo2 = idCbodega;
        String Cmin2 = idCmin;
        String Cmax2 = idCmax;
      
        if(nom2==null || nom2.isEmpty())
        	System.out.println("Debe ingresar un producto valido");
        else if(Pcom2==null || Pcom2.isEmpty())
        	System.out.println("Debe ingresar precio de compra valido");
        else if(Pven2==null || Pven2.isEmpty())
        	System.out.println("Debe ingresar precio de venta valido");
        else if(Cbo2==null || Cbo2.isEmpty())
        	System.out.println("Debe ingresar cantidad en bodega");
        else if(Cmin2==null || Cmin2.isEmpty())
        	System.out.println("Debe ingresar una cantidad minima");
        else if(Cmax2==null || Cmax2.isEmpty())
        	System.out.println("Debe ingresar una cantidad maxima");
        else{
            String query1 = "insert into productos (nombre,pCompra,pVenta,cBodega,cMinima,cmaxPer,estado)values ('"+nom2+"','"+Double.parseDouble(Pcom2)+"','"+Double.parseDouble(Pven2)+"','"+Integer.parseInt(Cbo2)+"','"+Integer.parseInt(Cmin2)+"','"+Integer.parseInt(Cmax2)+"','A')";
            con.conectar();
            try (Statement stm = con.getCon().createStatement()){
                int rest = stm.executeUpdate(query1);
                if(rest != 0){
                	System.out.println("producto Registrado con exito");
                	vali=2;
                   
                }
                else{
                	System.out.println("Error al grabar los datos por favor verifique");
                	vali=1;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            con.desconectar();
        }
        
        
       return vali;

    }

}
