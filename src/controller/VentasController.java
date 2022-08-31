package controller;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import coneccion.Conexion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class VentasController {

	
	Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    String dato, query;
    Conexion conect = new Conexion();


    @FXML
    private Button btnGuardar;

    @FXML
    private ComboBox<String> cmbClient;

    @FXML
    private ComboBox<String> cmbProduct;

    @FXML
    private TextField txtCant;

    @FXML
    void clickGuardar(ActionEvent  event) throws SQLException {
    	
    	System.out.println("entro click");
        String cliente = cmbClient.getValue();
        String producto = cmbProduct.getValue();
        String cantidad = txtCant.getText();
        if (cliente==null || cliente.isEmpty()) {
            Alert alerta = new Alert(AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setTitle("Validacion de datos");
            alerta.setContentText("Por favor seleccione un cliente");
            alerta.showAndWait();
        }
        else if(producto==null || producto.isEmpty()){
            Alert alerta = new Alert(AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setTitle("Validacion de datos");
            alerta.setContentText("Por favor seleccione un producto");
            alerta.showAndWait();
        }
        else if(cantidad==null || cantidad.isEmpty() || !esValido(cantidad)){
            Alert alerta = new Alert(AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setTitle("Validacion de datos");
            alerta.setContentText("Por favor ingrese una cantidad");
            alerta.showAndWait();
        }
        else{
            //Conectarme a la base de datos
            conect.conectar();
            try (Statement stm = conect.getCon().createStatement()){
                String[] tmp = cliente.split(" ");
                int clie = Integer.parseInt(tmp[0]);
                tmp = producto.split(" ");
                int prod = Integer.parseInt(tmp[0]);
                int cant = Integer.parseInt(cantidad);
                query = "INSERT INTO ventas(cliente,producto,cantidad) VALUES ("+clie+","+prod+","+cant+")";
                int resu = stm.executeUpdate(query);
                if(resu!=0){
                	JOptionPane.showMessageDialog(null,"producto vendido con exito");

                    System.out.println("Datos Insertados con exito");
                    txtCant.clear();
                }
                else{
                    System.out.println("Error al registrar la venta");
                }
                
            } catch (Exception e) {
            }
            
        }
        
        
    }
    
    private boolean esValido(String valor){
            boolean sw = false;
            try{
                int dato = Integer.parseInt(valor);
                sw= dato>0;
            }
            catch (NumberFormatException e){
                sw = false;
            }
            return sw;
    } 
    
    @FXML
    void initialize() throws IOException, SQLException{
        //Declaro variable
        
        ResultSet rst;
        //Conectarme a la base de datos        
        conect.conectar();
        System.out.println("Voy bien antes del combo");
        //Preparar para recuperar datos de la base de datos. Tabla clientes
        query = "SELECT id,nombre,apellido from clientes order by apellido,nombre";
        try (Statement stm = conect.getCon().createStatement()){ //Preparo el area para las consultas
            System.out.println("Voy bien de la consulta combo");
            rst = stm.executeQuery(query);
            System.out.println("Voy bien dentro combo");
            while (rst.next()) {
                dato = String.format("%d %s %s", rst.getInt("id"), rst.getString("nombre"), rst.getString("apellido"));
                cmbClient.getItems().add(dato);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //Preparar para recuperar datos de la base de datos. Tabla Productos
        query = "SELECT codigo,nombre from productos order by nombre";
        try (Statement stm = conect.getCon().createStatement()){ //Preparo el area para las consultas
            rst = stm.executeQuery(query);
            while (rst.next()) {
                dato = String.format("%d %s", rst.getInt("codigo"), rst.getString("nombre"));
                cmbProduct.getItems().add(dato);
            }
        } catch (Exception e) {
        }
        
    }
}
