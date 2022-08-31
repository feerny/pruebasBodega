import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application{
	
	@Override
	public void start(Stage stage) throws Exception{
		try {
			 
			FXMLLoader loader = new FXMLLoader(getClass().getResource("vista/vista.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			//stage.getIcons().add(new Image("vista/Gaming_5000x3125"));
			stage.setTitle("login");
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			 e.printStackTrace();
		}
	}

	 public static void main(String[] args) throws Exception{
		    launch(args);
		}

}
