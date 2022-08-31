package controller;

import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class HomeController {
	
    @FXML
    private MenuItem IdVentas;
    
    @FXML
    private MenuItem idClientes;
    

    @FXML
    private MenuItem idSalir;
    
    @FXML
    private MenuItem idPrductos;
    
    @FXML
    void VentasMenuClick (ActionEvent event) throws IOException{
    	
    	Parent root = (new FXMLLoader(getClass().getResource("/vista/registro.fxml"))).load();
    	Scene scene = new Scene(root);
    	Stage teatro = new Stage();
    	teatro.setTitle("Home");
    	teatro.setScene(scene);
        
    	teatro.show();
    }
    

    @FXML
    void clickClientes(ActionEvent event)throws IOException {
    	Parent root = (new FXMLLoader(getClass().getResource("/vista/clientes.fxml"))).load();
    	Scene scene = new Scene(root);
    	Stage teatro = new Stage();
    	teatro.setTitle("Home");
    	teatro.setScene(scene);
        
    	teatro.show();
    }
    

    @FXML
    void clickProductos(ActionEvent event)throws IOException {
    	Parent root = (new FXMLLoader(getClass().getResource("/vista/productos.fxml"))).load();
    	Scene scene = new Scene(root);
    	Stage teatro = new Stage();
    	teatro.setTitle("Home");
    	teatro.setScene(scene);
        
    	teatro.show();
    }
    
    @FXML
    void clickSalir(ActionEvent event)throws IOException {
    	JOptionPane.showMessageDialog(null,"cerro sesion!!");
        //Stage stage = (Stage) idSalir.getScene().getWindow();
       // stage.close();
        //Crear la nueva ventana
        System.out.println("entro");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/vista.fxml"));
        System.out.println("oe");
        Parent root = loader.load();
        Stage teatro = new Stage();

        Scene scene = new Scene(root);
        teatro = new Stage();
        //stage.close();
        teatro.setTitle("Home");
        teatro.setScene(scene);
        
        teatro.show();

    }
    
    
    
    
}
