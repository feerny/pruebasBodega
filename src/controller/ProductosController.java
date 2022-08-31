package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import coneccion.Conexion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ProductosController {
	
    @FXML
    private Button btnBorrar;

    @FXML
    private Button btnNuevo;

    @FXML
    private Button btntActualizar;

    @FXML
    private ImageView buscarImg;

    @FXML
    private TextField idCbodega;

    @FXML
    private TextField idCmax;

    @FXML
    private TextField idCmin;

    @FXML
    private TextField idNombre;

    @FXML
    private TextField idPcom;

    @FXML
    private TextField idPven;

    @FXML
    private TextField txtEntrada;
    
	String estadoCliente,query;
    int codigoProducto;
    //consulta de cadena
    Conexion con = new Conexion();
    
    @FXML
    void buscarImgClick(MouseEvent  event) throws SQLException {
        //1. Recuperar el dato que se excriba en txtIdentifi
        //2. Validar que se escribio algo
        //3. consultar en la base de datos por un registro con esa identificacion
            //==> Se encuentra
                    //Desplegar los datos
                    //Activar los botones Eliminar y Actualizar
                    //Desactivar el boton nuevo
            //==> No se encuentra
                    //notifico
        String iden = idNombre.getText();
        if (iden==null || iden.isEmpty()) {
            txtEntrada.setText("Digite una identificación valida para poder buscar");
        }
        else{
            con.conectar();
            try(Statement stm = con.getCon().createStatement()){
                String ident = idNombre.getText();
                ResultSet rta = stm.executeQuery("select * from productos where nombre = '"+ident+"'");
                if(rta.next()){
                	codigoProducto = rta.getInt("codigo");
                	idPcom.setText(rta.getDouble("pCompra")+"");
                    estadoCliente = rta.getString("estado");
                	idPven.setText(rta.getDouble("pVenta")+"");
                	idCbodega.setText(rta.getInt("cBodega")+"");
                	idCmin.setText(rta.getInt("cMinima")+"");
                	idCmax.setText(rta.getInt("cmaxPer")+"");
                    if (estadoCliente.equalsIgnoreCase("A")) {
                        btnBorrar.setText("Borrar");
                    } else {
                        btnBorrar.setText("Recuperar");
                    }
      
                    btnBorrar.setDisable(false);
                    btntActualizar.setDisable(false);
                    txtEntrada.clear();
                }
                else
                    txtEntrada.setText("No se encontro registro que coincida con la identificación");
            }
            con.desconectar();
        }

    }

    @FXML
    void clickActualizar(ActionEvent event) throws SQLException {
        //Recuperar los valores suministrados
        //Preparar la consulta a utilizar (Actualizar todo los campos)
        //Ejecuta la consulta
        
        String nom = idNombre.getText();
        String Pcom = idPcom.getText();
        String Pven = idPven.getText();
        String Cbo = idCbodega.getText();
        String Cmin = idCmin.getText();
        String Cmax = idCmax.getText();
        if(nom==null || nom.isEmpty())
            txtEntrada.setText("Debe ingresar un producto valido");
        else if(Pcom==null || Pcom.isEmpty())
            txtEntrada.setText("Debe ingresar precio de compra");
        else if(Pven==null || Pven.isEmpty())
            txtEntrada.setText("Debe ingresar precio de venta");
        else if(Cbo==null || Cbo.isEmpty())
            txtEntrada.setText("Debe ingresar cantidad en bodega");
        else if(Cmin==null || Cmin.isEmpty())
            txtEntrada.setText("Debe ingresar una cantidad minima");
        else if(Cmax==null || Cmax.isEmpty())
            txtEntrada.setText("Debe ingresar una cantidad maxima");
        else{
            String query1 = "UPDATE productos set nombre = '"+nom+"', pCompra = '"+Double.parseDouble(Pcom) +"' , pVenta = '"+Double.parseDouble(Pven)+"', cBodega = '"+Integer.parseInt(Cbo)+"',cMinima ='"+Integer.parseInt(Cmin)+"',cmaxPer ='"+Integer.parseInt(Cmax)+"' where codigo = "+codigoProducto;
            con.conectar();
            System.out.println("voy bien");
            try(Statement stm = con.getCon().createStatement()){
            //Ejecuta comando de accion
            int res = stm.executeUpdate(query1);
            if(res!=0){
                txtEntrada.setText("Registro Actualizado con exito");
            }else
                txtEntrada.setText("Error al Actualizar registro");
            restaurarDatos();
            } 
            con.desconectar();
        }

    }

    @FXML
    void clickBorrar(ActionEvent event) throws SQLException {

        //Recuperar el texto del boton
        //Preparar la consulta a utilizar (Borrar --> estado=I, recuperar--> estado =A)
        //Ejecuta la consulta
        String acc = btnBorrar.getText();
        if ("Borrar".equalsIgnoreCase(acc)) {
            query = "UPDATE productos set estado = 'I' where codigo="+codigoProducto;
        } else {
            query = "UPDATE productos set estado = 'A' where codigo="+codigoProducto;
        }
        con.conectar();
        try(Statement stm = con.getCon().createStatement()){
        //Ejecuta comando de accion
        int res = stm.executeUpdate(query);
        if(res!=0){
            txtEntrada.setText("Registro Recuperado/Borrado con exito");
        }else
            txtEntrada.setText("Error al recuperar/Borrar registro");
        restaurarDatos();
        }
        con.desconectar();
    }
    
    private void restaurarDatos(){
        
        idNombre.clear();
        idPcom.clear();
        idPven.clear();
        idCbodega.clear();
        idCmin.clear();
        idCmax.clear();
        btnBorrar.setDisable(true);
        btnBorrar.setText("Borrar");
        btntActualizar.setDisable(true);
    }

    @FXML
    void clickNuevo(ActionEvent event) throws SQLException {
        //Recuperar datos del formulario
        //Validar Datos
        //Preparar la insercion
        String nom2 = idNombre.getText();
        String Pcom2 = idPcom.getText();
        String Pven2 = idPven.getText();
        String Cbo2 = idCbodega.getText();
        String Cmin2 = idCmin.getText();
        String Cmax2 = idCmax.getText();
        
        if(nom2==null || nom2.isEmpty())
            txtEntrada.setText("Debe ingresar un producto valido");
        else if(Pcom2==null || Pcom2.isEmpty())
            txtEntrada.setText("Debe ingresar precio de compra");
        else if(Pven2==null || Pven2.isEmpty())
            txtEntrada.setText("Debe ingresar precio de venta");
        else if(Cbo2==null || Cbo2.isEmpty())
            txtEntrada.setText("Debe ingresar cantidad en bodega");
        else if(Cmin2==null || Cmin2.isEmpty())
            txtEntrada.setText("Debe ingresar una cantidad minima");
        else if(Cmax2==null || Cmax2.isEmpty())
            txtEntrada.setText("Debe ingresar una cantidad maxima");
        else{
            String query1 = "insert into productos (nombre,pCompra,pVenta,cBodega,cMinima,cmaxPer,estado)values ('"+nom2+"','"+Double.parseDouble(Pcom2)+"','"+Double.parseDouble(Pven2)+"','"+Integer.parseInt(Cbo2)+"','"+Integer.parseInt(Cmin2)+"','"+Integer.parseInt(Cmax2)+"','A')";
            con.conectar();
            try (Statement stm = con.getCon().createStatement()){
                int rest = stm.executeUpdate(query1);
                if(rest != 0){
                    txtEntrada.setText("producto Registrado con exito");
                    restaurarDatos();
                }
                else{
                    txtEntrada.setText("Error al grabar los datos por favor verifique");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            con.desconectar();
        }

    }
    
    @FXML
    void initialize(){ // Metodo de javafx que sirve para inicializar combox y demas en java apenas se abra una ventana
        btnBorrar.setDisable(true);
        btntActualizar.setDisable(true);
    }

}
