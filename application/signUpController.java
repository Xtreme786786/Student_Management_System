package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class signUpController implements Initializable {
    @FXML
    private TextField sign_username;

    @FXML
    private PasswordField sign_password;
    @FXML
    private TextField collegeCode ;
    @FXML
    private Button back;
    @FXML
    private Button signUpbtn;
    
    private Connection connect;
    private PreparedStatement prepare;;
    private double x=0;
    private double y=0;
    
    public void back() throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
		Scene scene = new Scene(root,600,400);
		Stage primaryStage = new Stage();
		
		root.setOnMousePressed((MouseEvent event) -> {
			x= event.getSceneX();
			y=event.getSceneY();
			});
		
		root.setOnMouseDragged((MouseEvent event) -> {
			primaryStage.setX(event.getScreenX() -x);
			primaryStage.setY(event.getScreenY()-y);
			
			primaryStage.setOpacity(.8);
			
		});
		
		root.setOnMouseReleased((MouseEvent event) ->{
			primaryStage.setOpacity(1);
		});
		
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		
		
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
    }
    public void Sign_Up() {
    	Alert alert;
    	connect=database.connectdb();
    	String query ="INSERT INTO admin(username,password) VALUES (?,?)";
    	try {
    		prepare = connect.prepareStatement(query);
    		prepare.setString(1,sign_username.getText());
    		prepare.setString(2, sign_password.getText());
    		
    		if(collegeCode.getText().equals("@123")) {
    			prepare.executeUpdate();
    			
    			
    			if(sign_username.getText().isEmpty() || sign_password.getText().isEmpty() || collegeCode.getText().isEmpty()){
        			alert= new Alert(AlertType.ERROR);
        			alert.setTitle("Error Message");
        			alert.setHeaderText(null);
        			alert.setContentText("Please fill all the blank fields");
        			alert.showAndWait();
        		}else {
    				 alert = new Alert(AlertType.INFORMATION);
        			 alert.setTitle("Information message");
        			 alert.setHeaderText(null);
        			 alert.setContentText("Successfull Added! ");
        			 alert.showAndWait();
        			 
        			 signUpbtn.getScene().getWindow().hide();
        		}
    		}else {
    			alert= new Alert(AlertType.ERROR);
    			alert.setTitle("Error Message");
    			alert.setHeaderText(null);
    			alert.setContentText("College Code is Incorrect!");
    			alert.showAndWait();
    		}
    	
    		
    	
    	}catch(Exception e) {e.printStackTrace();}
    	
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
