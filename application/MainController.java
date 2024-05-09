package application;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainController implements Initializable {

    @FXML
    private AnchorPane main_form;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button loginbtn;
    
    @FXML
    private Button signupbtn;

    @FXML
    private Button close;
    private double x=0;
    private double y=0;
    
    //database tools
    
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
   
    public void loginAdmin() {
    	 String sql = "SELECT * FROM admin WHERE username =? AND password =?";
    	 
    	 connect =database.connectdb();
    	 
    	 try {
    		 
    		 Alert alert;
    		 prepare = connect.prepareStatement(sql);
    		 prepare.setString(1,username.getText());
    		 prepare.setString(2,password.getText());
    		 
    		 result=prepare.executeQuery();
    		 
    		
    		 
    		 if(username.getText().isEmpty() || password.getText().isEmpty()) {
    			 alert = new Alert(AlertType.ERROR);
    			 alert.setTitle("Error message");
    			 alert.setHeaderText(null);
    			 alert.setContentText("Please fill all blank fields");
    			 alert.showAndWait();
    		 }else {
    			 if(result.next()) {
    				 
    				 getData.username=username.getText();
    				 alert = new Alert(AlertType.INFORMATION);
        			 alert.setTitle("Information message");
        			 alert.setHeaderText(null);
        			 alert.setContentText("Successfully Login! ");
        			 alert.showAndWait();
        			 
        			 loginbtn.getScene().getWindow().hide();
        			 
        			 Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        			 
        			 Scene scene = new Scene(root);
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
        				
        			 
        			 primaryStage.setScene(scene);
        			 primaryStage.show();
    			 }else {
    				 alert = new Alert(AlertType.ERROR);
        			 alert.setTitle("Error message");
        			 alert.setHeaderText(null);
        			 alert.setContentText("Wrong username/password");
        			 alert.showAndWait();
    			 }
    		 }
    	 }catch(Exception e) {e.printStackTrace();}
    }
    
    public void SignUp_admin() {
    	try {
    		
    		Parent root = FXMLLoader.load(getClass().getResource("signUp.fxml"));
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
			
			
    	}catch(Exception e) {e.printStackTrace();}
    }
    public void close() {
    	System.exit(0);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
