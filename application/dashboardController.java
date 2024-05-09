package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;

import javafx.scene.chart.BarChart;

import javafx.scene.control.TableColumn;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;

import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;

import javafx.scene.control.TableView;

import javafx.scene.control.DatePicker;

public class dashboardController implements Initializable {
	   @FXML
	    private AnchorPane main_form;

	    @FXML
	    private Button close;

	    @FXML
	    private Button minimize;

	    @FXML
	    private Label username_txt;

	    @FXML
	    private Button home_btn;

	    @FXML
	    private Button addStudent_btn;

	    @FXML
	    private Button AvailableCourse_btn;

	    @FXML
	    private Button StudentGrade_btn;

	    @FXML
	    private Button logout_btn;

	    @FXML
	    private AnchorPane home_form;

	    @FXML
	    private Label home_totalEnrolled;

	    @FXML
	    private Label home_totalfemale;

	    @FXML
	    private Label home_totalMale;

	    @FXML
	    private BarChart<?, ?> home_totalEnrolledChart;

	    @FXML
	    private AreaChart<?, ?> home_totalFemaleChart;

	    @FXML
	    private LineChart<?, ?> home_totalMaleChart;

	    @FXML
	    private AnchorPane addStudent_form;

	    @FXML
	    private TextField addStudent_search;

	    @FXML
	    private TableView<AddStudentController> addStudent_tableView;

	    @FXML
	    private TableColumn<AddStudentController, String> addStudent_col_studentNum;

	    @FXML
	    private TableColumn<AddStudentController, String> addStudent_col_year;

	    @FXML
	    private TableColumn<AddStudentController, String> addStudent_col_courses;

	    @FXML
	    private TableColumn<AddStudentController, String> addStudent_col_firstname;

	    @FXML
	    private TableColumn<AddStudentController, String> addStudent_col_lastname;

	    @FXML
	    private TableColumn<AddStudentController, String> addStudent_col_gender;

	    @FXML
	    private TableColumn<AddStudentController, String> addStudent_col_dob;

	    @FXML
	    private TableColumn<AddStudentController, String> addStudent_col_status;

	    @FXML
	    private TextField addStudent_studentNum;

	    @FXML
	    private ComboBox<?> addStudent_year;

	    @FXML
	    private ComboBox<?> addStudent_courses;

	    @FXML
	    private TextField addStudent_firstname;

	    @FXML
	    private TextField addStudent_lastname;

	    @FXML
	    private ComboBox<?> addStudent_gender;

	    @FXML
	    private DatePicker addStudent_dob;

	    @FXML
	    private ComboBox<?> addStudent_status;

	    @FXML
	    private ImageView addStudent_imageView;

	    @FXML
	    private Button addStudent_insertBtn;

	    @FXML
	    private Button addStudent_clearBtn;

	    @FXML
	    private Button addStudent_addBtn;

	    @FXML
	    private Button addStudent_updateBtn;

	    @FXML
	    private Button addStudent_deleteBtn;

	    @FXML
	    private AnchorPane available_form;

	    @FXML
	    private TextField available_courses;

	    @FXML
	    private TextField available_description;

	    @FXML
	    private TextField available_degree;

	    @FXML
	    private Button available_addBtn;

	    @FXML
	    private Button available_updateBtn;

	    @FXML
	    private Button available_clearBtn;

	    @FXML
	    private Button available_deletebtn;

	    @FXML
	    private TableView<courseData> available_tableView;

	    @FXML
	    private TableColumn<courseData, String> available_col_courses;

	    @FXML
	    private TableColumn<courseData, String> available_col_description;

	    @FXML
	    private TableColumn<courseData, String> available_col_degree;

	    @FXML
	    private AnchorPane studentGrade_form;

	    @FXML
	    private TextField studentGrade_StudentNum;

	    @FXML
	    private Label studentGrade_Year;

	    @FXML
	    private Label studentGrade_Courses;

	    @FXML
	    private TextField studentGrade_firstSem;

	    @FXML
	    private TextField studentGrade_secondSem;

	    @FXML
	    private Button studentGrade_clearbtn;

	    @FXML
	    private Button studentGrade_updatebtn;

	    @FXML
	    private TableView<AddStudentController> studentGrade_TableView;

	    @FXML
	    private TableColumn<AddStudentController, String> studentGrade_col_StudentNum;

	    @FXML
	    private TableColumn<AddStudentController, String> studentGrade_col_Year;

	    @FXML
	    private TableColumn<AddStudentController, String> studentGrade_col_Courses;

	    @FXML
	    private TableColumn<AddStudentController, String> studentGrade_col_firstSem;

	    @FXML
	    private TableColumn<AddStudentController, String> studentGrade_col_secondSem;

	    @FXML
	    private TableColumn<AddStudentController, String> studentGrade_col_final;

	    @FXML
	    private TextField studentGrade_Search;
	    
	    private Image image;
	    
	    private Connection connect;
	    private PreparedStatement prepare;
	    private Statement statement;
	    private ResultSet result;
	    
	    private double x = 0;
	    private double y = 0;
	    
	    public void displayUsername() {
	    	username_txt.setText(getData.username);
	    }
	    
	    public void defaultNav() {
	    	home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
	    }
	    public void close() {
	    	System.exit(0);
	    }
	    
	    public void minimize() {
	    	Stage stage = (Stage)main_form.getScene().getWindow();
	    	stage.setIconified(true);
	    }
	    //method for logout;
	    public void logout() {
	    	try {
	    		
	    		Alert alert = new Alert(AlertType.CONFIRMATION);
	    		alert.setTitle("Confirmation Message");
	            alert.setHeaderText(null);
	            alert.setContentText("Are you sure you want to logout?");

	            Optional<ButtonType> option = alert.showAndWait();
	            
	            if(option.get().equals(ButtonType.OK)) {
	            	
	            	logout_btn.getScene().getWindow().hide();
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
	            else {
	            	return ;
	            }
	    	
	    	}catch(Exception e) {e.printStackTrace();}
	    }
	    
	    //method for switching to different form;
	    public void switchForm(ActionEvent event) {
	    	if(event.getSource() == home_btn) {
	    		home_form.setVisible(true);
	    		addStudent_form.setVisible(false);
	    		available_form.setVisible(false);
	    		studentGrade_form.setVisible(false);
	    		
	    		  home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
	              addStudent_btn.setStyle("-fx-background-color:transparent");
	              AvailableCourse_btn.setStyle("-fx-background-color:transparent");
	              StudentGrade_btn.setStyle("-fx-background-color:transparent");
	              
	              homeDisplayMaleEnrolled();
		      	  homeDisplayFemaleEnrolled();
		      	  homeDisplayTotalEnrolledStudents();
			      homeDisplayEnrolledMaleChart();
			      homeDisplayFemaleEnrolledChart();
			      homeDisplayTotalEnrolledChart();
		    		
	    		
	    	}else if(event.getSource()==addStudent_btn) {
	    		home_form.setVisible(false);
	    		addStudent_form.setVisible(true);
	    		available_form.setVisible(false);
	    		studentGrade_form.setVisible(false);
	    		
	    		home_btn.setStyle("-fx-background-color:transparent");
	            addStudent_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
	            AvailableCourse_btn.setStyle("-fx-background-color:transparent");
	            StudentGrade_btn.setStyle("-fx-background-color:transparent");
	            
	            addStudentsShowListData();
	            addStudentsYearList();
	            addStudentsGenderList();
	            addStudentsStatusList();
	            addStudentCourseList();
	            addStudentsSearch();
	            
	    	}else if(event.getSource() == AvailableCourse_btn) {
	    		home_form.setVisible(false);
	    		addStudent_form.setVisible(false);
	    		available_form.setVisible(true);
	    		studentGrade_form.setVisible(false);
	    		
	    		home_btn.setStyle("-fx-background-color:transparent");
	            addStudent_btn.setStyle("-fx-background-color:transparent");
	            AvailableCourse_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
	            StudentGrade_btn.setStyle("-fx-background-color:transparent");
	            
	           
	            
	            availableCourseShowListData();
	    		
	    	}else if(event.getSource() == StudentGrade_btn){
	    		home_form.setVisible(false);
	    		addStudent_form.setVisible(false);
	    		available_form.setVisible(false);
	    		studentGrade_form.setVisible(true);
	    		
	    		home_btn.setStyle("-fx-background-color:transparent");
	            addStudent_btn.setStyle("-fx-background-color:transparent");
	            AvailableCourse_btn.setStyle("-fx-background-color:transparent");
	            StudentGrade_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
	    		
	            studentGradesShowListData();
	            studentGradesSelect();
	    	}
	    }
	    
//-------------------------HOME FORM------------------------------------------------	    -
	   
	    
	    public void homeDisplayTotalEnrolledStudents() {
	    	
	    	String sql ="SELECT count(id) FROM student where status='Enrolled'";
	    	
	    	connect= database.connectdb();
	    	int countEnrolled=0;
	    	try {
	    		prepare = connect.prepareStatement(sql);
	    		result=prepare.executeQuery();
	    		if(result.next()) {
	    			countEnrolled= result.getInt("COUNT(id)");
	    		}
	    		home_totalEnrolled.setText(String.valueOf(countEnrolled));
	    	}catch(Exception e) {e.printStackTrace();}
	    }
	    
	    public void homeDisplayFemaleEnrolled() {
	    	String sql = "SELECT count(id) from student where gender='female' and status='Enrolled'";
	    	
	    	connect= database.connectdb();
	    	int countFemale=0;
	    	try {
	    		prepare=connect.prepareStatement(sql);
	    		result=prepare.executeQuery();
	    		if(result.next()) {
	    			countFemale=result.getInt("Count(id)");
	    		}
	    		home_totalfemale.setText(String.valueOf(countFemale));
	    	}catch(Exception e) {e.printStackTrace();}
	    }
	    
	    public void homeDisplayMaleEnrolled() {
	    	String sql="SELECT Count(id) from student where gender='male' and status='Enrolled'";
	    	
	    	connect=database.connectdb();
	    	int countMale=0;
	    	try {
	    		prepare=connect.prepareStatement(sql);
	    		
	    		result= prepare.executeQuery();
	    		if(result.next()) {
	    			countMale=result.getInt("count(id)");
	    		}
	    		home_totalMale.setText(String.valueOf(countMale));
	    	}catch(Exception e) {e.printStackTrace();}
	    }
	    
	    public void homeDisplayTotalEnrolledChart() {

	        home_totalEnrolledChart.getData().clear();

	        String sql = "SELECT Date, COUNT(id) FROM student WHERE status = 'Enrolled' GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 5";

	        connect = database.connectdb();

	        try {
	            XYChart.Series chart = new XYChart.Series();

	            prepare = connect.prepareStatement(sql);
	            result = prepare.executeQuery();

	            while (result.next()) {
	                chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
	            }

	            home_totalEnrolledChart.getData().add(chart);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	    }

	    public void homeDisplayFemaleEnrolledChart() {

	        home_totalFemaleChart.getData().clear();

	        String sql = "SELECT Date, COUNT(id) FROM student WHERE status = 'Enrolled' and gender = 'Female' GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 5";

	        connect = database.connectdb();

	        try {
	            XYChart.Series chart = new XYChart.Series();

	            prepare = connect.prepareStatement(sql);
	            result = prepare.executeQuery();

	            while (result.next()) {
	                chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
	            }

	            home_totalFemaleChart.getData().add(chart);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	    }

	    public void homeDisplayEnrolledMaleChart() {

	        home_totalMaleChart.getData().clear();

	        String sql = "SELECT Date, COUNT(id) FROM student WHERE status = 'Enrolled' and gender = 'Male' GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 5";

	        connect = database.connectdb();

	        try {
	            XYChart.Series chart = new XYChart.Series();

	            prepare = connect.prepareStatement(sql);
	            result = prepare.executeQuery();

	            while (result.next()) {
	                chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
	            }

	            home_totalMaleChart.getData().add(chart);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	    }

	   
	    
//---------------------------------ADD_STUDENT FORM -------------------------------------
	    
	    // method for fetching the sql data to table in addStudent_tableView;
	    public ObservableList<AddStudentController> addStudentListData(){
	    	
	    	ObservableList<AddStudentController> listStudents = FXCollections.observableArrayList();
	    	
	    	String sql ="SELECT * FROM student";
	    	
	    	connect=database.connectdb();
	    	
	    	try {
	    		AddStudentController studentD;
	    		prepare=connect.prepareStatement(sql);
	    		result = prepare.executeQuery();
	    		
	    		while(result.next()) {
	    			studentD = new AddStudentController(result.getInt("studentNum"),
	    					result.getString("year"),
	    					result.getString("course"),
	    					result.getString("firstName"),
	    					result.getString("lastName"),
	    					result.getString("gender"),
	    					result.getDate("birth"),
	    					result.getString("status"),
	    					result.getString("image"));
	    			listStudents.add(studentD);
	    		}
	    	}catch(Exception e) {e.printStackTrace();}
	    	return listStudents;
	    }
	    
	    
	    //method to set the fetched data from sql to table;
	    private ObservableList<AddStudentController> addStudentListD;
	    public void addStudentsShowListData() {
	    	addStudentListD =addStudentListData();
	    	
	    	addStudent_col_studentNum.setCellValueFactory(new PropertyValueFactory<>("studentNum"));
	    	addStudent_col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
	    	addStudent_col_courses.setCellValueFactory(new PropertyValueFactory<>("course"));
	    	addStudent_col_firstname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
	    	addStudent_col_lastname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
	    	addStudent_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
	    	addStudent_col_dob.setCellValueFactory(new PropertyValueFactory<>("birth"));
	    	addStudent_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

	    	addStudent_tableView.setItems(addStudentListD);
	    }
	    // method to set the label when some data is selected in table;
	    public void addStudentsSelect() {
	    	AddStudentController studentD = addStudent_tableView.getSelectionModel().getSelectedItem();
	    	int num = addStudent_tableView.getSelectionModel().getSelectedIndex();
	    	
	    	
	    	if((num - 1) < -1) {return;}
	    	
	    	addStudent_studentNum.setText(String.valueOf(studentD.getStudentNum()));
	    	addStudent_firstname.setText(String.valueOf(studentD.getFirstName()));
	    	addStudent_lastname.setText(String.valueOf(studentD.getLastName()));
	    	addStudent_dob.setValue(LocalDate.parse(String.valueOf(studentD.getBirth())));
	    	
	    	
	    	String uri = "file:"+studentD.getImage();
	    	
	    	image=new Image(uri,108,125,false,true);
	    	addStudent_imageView.setImage(image);
	    	
	    	getData.path = studentD.getImage();

	    	
	    	
	    	}
	    
	    
	 // List of ComboBox year in addStudent_form
	    private String[] yearList = {"First Year","Second Year","Third Year","Final Year"};
	    
	    // method for setting the yearList to the ComboBox;
	    public void addStudentsYearList() {
	    	List<String> yearL = new ArrayList<>();
	    	
	    	for(String data : yearList) {
	    		yearL.add(data);
	    	}
	    	ObservableList onList = FXCollections.observableArrayList(yearL);
	    	addStudent_year.setItems(onList);
	    }
	    
	    //list of gender in addStudent_form
	    private String[] genderList= {"Male","Female","Others"};
	    
	    //method for setting the genderList to the comboBox;
	    public void addStudentsGenderList() {
	    	List<String> genderL = new ArrayList<>();
	    	for(String data : genderList) {
	    		genderL.add(data);
	    	}
	    	ObservableList onList = FXCollections.observableArrayList(genderL);
	    	addStudent_gender.setItems(onList);
	    }
	    
	    
	    private String[] statusList= {"Enrolled","Not-Enrolled","Inactive"};
	    
	    //method for setting the statusList to the comboBox;
	    public void addStudentsStatusList() {
	    	List<String> statusL = new ArrayList<>();
	    	for(String data : statusList) {
	    		statusL.add(data);
	    	}
	    	ObservableList onList = FXCollections.observableArrayList(statusL);
	    	addStudent_status.setItems(onList);
	    }
	    
	    // code for adding course to the combobox fetching from the available course;
	    public void addStudentCourseList() {
	    	String courseList ="SELECT course from course";
	    	connect=database.connectdb();
	    	
	    	try {
	    		ObservableList listC = FXCollections.observableArrayList();
	    		prepare=connect.prepareStatement(courseList);
	    		result=prepare.executeQuery();
	    		while(result.next()) {
	    			listC.add(result.getString("course"));
	    		}
	    		addStudent_courses.setItems(listC);
	    		
	    	}catch(Exception e) {e.printStackTrace();}
	    }
	    
	    // code to insert image from local Disk to database;
	    public void addStudentInsertImage() {
	    	FileChooser open = new FileChooser();
	    	open.setTitle("Open Image File");
	    	open.getExtensionFilters().add(new ExtensionFilter("Image File","*jpg" , "*png") );
	    	
	    	File file = open.showOpenDialog(main_form.getScene().getWindow());
	    	
	    	if(file != null) {
	    		image = new Image(file.toURI().toString(),108,125,false,true);
	    		addStudent_imageView.setImage(image);
	    		
	    		getData.path=file.getAbsolutePath();
	    	}
	    }
	    
	    
	    // code to add new students Data to database;
	    
	    public void addStudentsAdd() {

	        String insertData = "INSERT INTO student "
	                + "(studentNum,year,course,firstName,lastName,gender,birth,status,image,Date) "
	                + "VALUES(?,?,?,?,?,?,?,?,?,?)";

	        connect = database.connectdb();

	        try {
	            Alert alert;

	            if (addStudent_studentNum.getText().isEmpty()
	                    || addStudent_year.getSelectionModel().getSelectedItem() == null
	                    || addStudent_courses.getSelectionModel().getSelectedItem() == null
	                    || addStudent_firstname.getText().isEmpty()
	                    || addStudent_lastname.getText().isEmpty()
	                    || addStudent_gender.getSelectionModel().getSelectedItem() == null
	                    || addStudent_dob.getValue() == null
	                    || addStudent_status.getSelectionModel().getSelectedItem() == null
	                    || getData.path == null || getData.path == "") {
	                alert = new Alert(AlertType.ERROR);
	                alert.setTitle("Error Message");
	                alert.setHeaderText(null);
	                alert.setContentText("Please fill all blank fields");
	                alert.showAndWait();
	            } else {
	                // CHECK IF THE STUDENTNUMBER IS ALREADY EXIST
	                String checkData = "SELECT studentNum FROM student WHERE studentNum = '"
	                        + addStudent_studentNum.getText() + "'";

	                statement = connect.createStatement();
	                result = statement.executeQuery(checkData);

	                if (result.next()) {
	                    alert = new Alert(AlertType.ERROR);
	                    alert.setTitle("Error Message");
	                    alert.setHeaderText(null);
	                    alert.setContentText("Student #" + addStudent_studentNum.getText() + " was already exist!");
	                    alert.showAndWait();
	                } else {
	                    prepare = connect.prepareStatement(insertData);
	                    prepare.setString(1, addStudent_studentNum.getText());
	                    prepare.setString(2, (String) addStudent_year.getSelectionModel().getSelectedItem());
	                    prepare.setString(3, (String) addStudent_courses.getSelectionModel().getSelectedItem());
	                    prepare.setString(4, addStudent_firstname.getText());
	                    prepare.setString(5, addStudent_lastname.getText());
	                    prepare.setString(6, (String) addStudent_gender.getSelectionModel().getSelectedItem());
	                    prepare.setString(7, String.valueOf(addStudent_dob.getValue()));
	                    prepare.setString(8, (String) addStudent_status.getSelectionModel().getSelectedItem());

	                    String uri = getData.path;
	                    uri = uri.replace("\\", "\\\\");
	                    prepare.setString(9, uri);

	                    Date date = new Date();
	                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
	                    prepare.setString(10, String.valueOf(sqlDate));

	                    prepare.executeUpdate();

	                    String insertStudentGrade = "INSERT INTO student_grade "
	                            + "(studentNum,year,course,first_sem,second_sem,final) "
	                            + "VALUES(?,?,?,?,?,?)";

	                    prepare = connect.prepareStatement(insertStudentGrade);
	                    prepare.setString(1, addStudent_studentNum.getText());
	                    prepare.setString(2, (String) addStudent_year.getSelectionModel().getSelectedItem());
	                    prepare.setString(3, (String) addStudent_courses.getSelectionModel().getSelectedItem());
	                    prepare.setString(4, "0");
	                    prepare.setString(5, "0");
	                    prepare.setString(6, "0");

	                    prepare.executeUpdate();

	                    alert = new Alert(AlertType.INFORMATION);
	                    alert.setTitle("Information Message");
	                    alert.setHeaderText(null);
	                    alert.setContentText("Successfully Added!");
	                    alert.showAndWait();

	                    // TO UPDATE THE TABLEVIEW
	                    addStudentsShowListData();
	                    // TO CLEAR THE FIELDS
	                    addStudentClear();
	                }
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	       

	    //code to clear the label :
	    public void addStudentClear() {
	    	addStudent_studentNum.setText("");
	    	addStudent_year.getSelectionModel().clearSelection();
	    	addStudent_courses.getSelectionModel().clearSelection();
	    	addStudent_firstname.setText("");
	    	addStudent_lastname.setText("");
	    	addStudent_gender.getSelectionModel().clearSelection();
	    	addStudent_dob.setValue(null);
	    	addStudent_status.getSelectionModel().clearSelection();
	    	addStudent_imageView.setImage(null);
	    	
	    	getData.path="null";
	    }
	    
	    //code to update the existing data of student in database:
	    public void addStudentsUpdate() {

	        String uri = getData.path;
	        uri = uri.replace("\\", "\\\\");

	        String updateData = "UPDATE student SET "
	                + "year = '" + addStudent_year.getSelectionModel().getSelectedItem()
	                + "', course = '" + addStudent_courses.getSelectionModel().getSelectedItem()
	                + "', firstName = '" + addStudent_firstname.getText()
	                + "', lastName = '" + addStudent_lastname.getText()
	                + "', gender = '" + addStudent_gender.getSelectionModel().getSelectedItem()
	                + "', birth = '" + addStudent_dob.getValue()
	                + "', status = '" + addStudent_status.getSelectionModel().getSelectedItem()
	                + "', image = '" + uri + "' WHERE studentNum = '"
	                + addStudent_studentNum.getText() + "'";

	        connect = database.connectdb();

	        try {
	            Alert alert;
	            if (addStudent_studentNum.getText().isEmpty()
	                    || addStudent_year.getSelectionModel().getSelectedItem() == null
	                    || addStudent_courses.getSelectionModel().getSelectedItem() == null
	                    || addStudent_firstname.getText().isEmpty()
	                    || addStudent_lastname.getText().isEmpty()
	                    || addStudent_gender.getSelectionModel().getSelectedItem() == null
	                    || addStudent_dob.getValue() == null
	                    || addStudent_status.getSelectionModel().getSelectedItem() == null
	                    || getData.path == null || getData.path == "") {
	                alert = new Alert(AlertType.ERROR);
	                alert.setTitle("Error Message");
	                alert.setHeaderText(null);
	                alert.setContentText("Please fill all blank fields");
	                alert.showAndWait();
	            } else {

	                alert = new Alert(AlertType.CONFIRMATION);
	                alert.setTitle("Confirmation Message");
	                alert.setHeaderText(null);
	                alert.setContentText("Are you sure you want to UPDATE Student #" + addStudent_studentNum.getText() + "?");
	                Optional<ButtonType> option = alert.showAndWait();

	                if (option.get().equals(ButtonType.OK)) {
	                    statement = connect.createStatement();
	                    statement.executeUpdate(updateData);

	                    alert = new Alert(AlertType.INFORMATION);
	                    alert.setTitle("Information Message");
	                    alert.setHeaderText(null);
	                    alert.setContentText("Successfully Updated!");
	                    alert.showAndWait();

	                    // TO UPDATE THE TABLEVIEW
	                    addStudentsShowListData();
	                    // TO CLEAR THE FIELDS
	                    addStudentClear();

	                } else {
	                    return;
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    //code to delete student data from database:
	    public void addStudentsDelete() {

	        String deleteData = "DELETE FROM student WHERE studentNum = '"
	                + addStudent_studentNum.getText() + "'";

	        connect = database.connectdb();

	        try {
	            Alert alert;
	            if (addStudent_studentNum.getText().isEmpty()
	                    || addStudent_year.getSelectionModel().getSelectedItem() == null
	                    || addStudent_courses.getSelectionModel().getSelectedItem() == null
	                    || addStudent_firstname.getText().isEmpty()
	                    || addStudent_lastname.getText().isEmpty()
	                    || addStudent_gender.getSelectionModel().getSelectedItem() == null
	                    || addStudent_dob.getValue() == null
	                    || addStudent_status.getSelectionModel().getSelectedItem() == null
	                    || getData.path == null || getData.path == "") {
	                alert = new Alert(AlertType.ERROR);
	                alert.setTitle("Error Message");
	                alert.setHeaderText(null);
	                alert.setContentText("Please fill all blank fields");
	                alert.showAndWait();
	            } else {
	                alert = new Alert(AlertType.CONFIRMATION);
	                alert.setTitle("Confirmation Message");
	                alert.setHeaderText(null);
	                alert.setContentText("Are you sure you want to DELETE Student #" + addStudent_studentNum.getText() + "?");

	                Optional<ButtonType> option = alert.showAndWait();

	                if (option.get().equals(ButtonType.OK)) {

	                    statement = connect.createStatement();
	                    statement.executeUpdate(deleteData);

	                    String checkData = "SELECT studentNum FROM student_grade "
	                            + "WHERE studentNum = '" + addStudent_studentNum.getText() + "'";

	                    prepare = connect.prepareStatement(checkData);
	                    result = prepare.executeQuery();

	                    // IF THE STUDENT NUMBER IS EXIST THEN PROCEED TO DELETE
	                    if (result.next()) {
	                        String deleteGrade = "DELETE FROM student_grade WHERE "
	                                + "studentNum = '" + addStudent_studentNum.getText() + "'";

	                        statement = connect.createStatement();
	                        statement.executeUpdate(deleteGrade);

	                    }// IF NOT THEN NVM

	                    alert = new Alert(AlertType.INFORMATION);
	                    alert.setTitle("Information Message");
	                    alert.setHeaderText(null);
	                    alert.setContentText("Successfully Deleted!");
	                    alert.showAndWait();

	                    // TO UPDATE THE TABLEVIEW
	                    addStudentsShowListData();
	                    // TO CLEAR THE FIELDS
	                    addStudentClear();

	                } else {
	                    return;
	                }

	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	    }
	    
	    // code to search student in tableView:
	    public void addStudentsSearch() {

	        FilteredList<AddStudentController> filter = new FilteredList<>(addStudentListD, e -> true);

	        addStudent_search.textProperty().addListener((Observable, oldValue, newValue) -> {

	            filter.setPredicate(predicateStudentData -> {

	                if (newValue == null || newValue.isEmpty()) {
	                    return true;
	                }

	                String searchKey = newValue.toLowerCase();

	                if (predicateStudentData.getStudentNum().toString().contains(searchKey)) {
	                    return true;
	                } else if (predicateStudentData.getYear().toLowerCase().contains(searchKey)) {
	                    return true;
	                } else if (predicateStudentData.getCourse().toLowerCase().contains(searchKey)) {
	                    return true;
	                } else if (predicateStudentData.getFirstName().toLowerCase().contains(searchKey)) {
	                    return true;
	                } else if (predicateStudentData.getLastName().toLowerCase().contains(searchKey)) {
	                    return true;
	                } else if (predicateStudentData.getGender().toLowerCase().contains(searchKey)) {
	                    return true;
	                } else if (predicateStudentData.getBirth().toString().contains(searchKey)) {
	                    return true;
	                } else if (predicateStudentData.getStatus().toLowerCase().contains(searchKey)) {
	                    return true;
	                } else {
	                    return false;
	                }
	            });
	        });

	        SortedList<AddStudentController> sortList = new SortedList<>(filter);

	        sortList.comparatorProperty().bind(addStudent_tableView.comparatorProperty());
	        addStudent_tableView.setItems(sortList);

	    }
	    
	    
	    
	    
//-------------------------- AVAILABLE_COURSE FORM -------------------------   
	    
	    
	    
	    //fetching available courses from database to variable listData
	    public ObservableList<courseData> availableCourseListData(){
	    	ObservableList<courseData> listData = FXCollections.observableArrayList();
	    	
	    	String sql ="SELECT * from course";
	    	
	    	connect = database.connectdb();
	    	
	    	try {
	    		courseData courseD;
	    		prepare = connect.prepareStatement(sql);
	    		result = prepare.executeQuery();
	    		while(result.next()) {
	    			courseD = new courseData(result.getString("course"), result.getString("description"), result.getString("degree"));
	    			listData.add(courseD);
	    		}
	    	}catch(Exception e) {e.printStackTrace();}
	    	return listData;
	    }
	    
	    
	    // setting the fetched available course to table View;
	    private ObservableList<courseData> availableCourseList;
	    public void availableCourseShowListData() {
	    	availableCourseList = availableCourseListData();
	    	available_col_courses.setCellValueFactory(new PropertyValueFactory<>("course"));
	    	available_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
	    	available_col_degree.setCellValueFactory(new PropertyValueFactory<>("degree"));
	    	
	    	available_tableView.setItems(availableCourseList);
	    }
	    
	    // set the label according the selected course from table;
	    public void availableCourseSelect() {
	    	courseData courseD =available_tableView.getSelectionModel().getSelectedItem();
	    	
	    	int num = available_tableView.getSelectionModel().getSelectedIndex();
	    	
	    	while((num -1 )< -1){
	    		return;
	    	}
	    	available_courses.setText(courseD.getCourse());
	    	available_description.setText(courseD.getDescription());
	    	available_degree.setText(courseD.getDegree());
	    }
	    
	    
	  //Adding courses to the database :
	    public void availableCourseAdd() {
	    	String insertData ="insert into course(course,description,degree) Values (?,?,?)";
	    	
	    	connect = database.connectdb();
	    	try{
	    		Alert alert;
	    		if(available_courses.getText().isEmpty() 
	    				|| available_description.getText().isEmpty()
	    				|| available_degree.getText().isEmpty()) {
	    			alert = new Alert(AlertType.ERROR);
	    			alert.setTitle("Error Message");
	    			alert.setHeaderText(null);
	    			alert.setContentText("please fill all blank fields");
	    			alert.showAndWait();
	    		}else {
	    			String checkData ="Select course from course where course ='"+available_courses.getText()+"'";
	    			statement = connect.createStatement();
	    			result=statement.executeQuery(checkData);
	    			if(result.next()) {
	    				alert = new Alert(AlertType.ERROR);
		    			alert.setTitle("Error Message");
		    			alert.setHeaderText(null);
		    			alert.setContentText("course: "+available_courses.getText() + "was already exist!");
		    			alert.showAndWait();
	    			}else {
	    			prepare = connect.prepareStatement(insertData);
	    			prepare.setString(1, available_courses.getText());
	    			prepare.setString(2, available_description.getText());
	    			prepare.setString(3, available_degree.getText());
	    			
	    			prepare.executeUpdate();
	    			alert = new Alert(AlertType.INFORMATION);
	    			alert.setTitle("Information Message");
	    			alert.setHeaderText(null);
	    			alert.setContentText("Successfully Added");
	    			alert.showAndWait();
	    			
	    			availableCourseShowListData();
	    			
	    			availableCourseClear();
	    			}
	    		}
	    	}catch(Exception e) {e.printStackTrace();}
	    }
	    
	    //clearing the label:
	    public void availableCourseClear() {
	    	available_courses.setText("");
	    	available_description.setText("");
	    	available_degree.setText("");
	    }
	    
	    //Updating the existing course:
	    public void availableCourseUpdate() {
	    	String updateData ="UPDATE course SET description='"
	    				+available_description.getText()+"',degree='"
	    				+available_degree.getText()+"' "
	    				+ "WHERE course='"+available_courses.getText()+"'";
	    	connect=database.connectdb();
	    	try {
	    		Alert alert;
	    		if(available_courses.getText().isEmpty() 
	    				|| available_description.getText().isEmpty()
	    				|| available_degree.getText().isEmpty()) {
	    			alert = new Alert(AlertType.ERROR);
	    			alert.setTitle("Error Message");
	    			alert.setHeaderText(null);
	    			alert.setContentText("please fill all blank fields");
	    			alert.showAndWait();
	    		}else {
	    			alert = new Alert(AlertType.CONFIRMATION);
	    			alert.setTitle("Confirmation Message");
	    			alert.setHeaderText(null);
	    			alert.setContentText("Are you sure you want to UPDATE: "+available_courses.getText()+" ?");
	    			Optional<ButtonType> option = alert.showAndWait();
	    			 
	    			if(option.get().equals(ButtonType.OK)) {
	    				statement = connect.createStatement();
		    			statement.executeUpdate(updateData);
		    			alert = new Alert(AlertType.INFORMATION);
		    			alert.setTitle("Information Message");
		    			alert.setHeaderText(null);
		    			alert.setContentText("Sucessfully Updated");
		    			alert.showAndWait();
		    			
		    			availableCourseShowListData();
		    			
		    			availableCourseClear();
	    			}else return;
	    			
	    		}
	    		
	    	}catch(Exception e) {e.printStackTrace();}
	    }
	    //deleting the existing course:
	    public void availableCourseDelete() {
	    	String deleteData = "DELETE FROM course WHERE course ='"
	    			+available_courses.getText()+"'";
	    	connect = database.connectdb();
	    	try {
	    		Alert alert;
	    		if(available_courses.getText().isEmpty() 
	    				|| available_description.getText().isEmpty()
	    				|| available_degree.getText().isEmpty()) {
	    			alert = new Alert(AlertType.ERROR);
	    			alert.setTitle("Error Message");
	    			alert.setHeaderText(null);
	    			alert.setContentText("Please Select the course to be deleted");
	    			alert.showAndWait();
	    		}else {
		    		alert=new Alert(AlertType.CONFIRMATION);
		    		alert.setTitle("Confirmation Message");
		    		alert.setHeaderText(null);
		    		alert.setContentText("Are you sure you want to DELETE couse: "+available_courses.getText()+" ?");
		    		Optional<ButtonType> option = alert.showAndWait();
		    		if(option.get().equals(ButtonType.OK)) {
		    			statement=connect.createStatement();
		    			statement.executeUpdate(deleteData);
		    			alert = new Alert(AlertType.INFORMATION);
		    			alert.setTitle("Information Message");
		    			alert.setHeaderText(null);
		    			alert.setContentText("Sucessfully Deleted");
		    			alert.showAndWait();
		    			
		    			availableCourseShowListData();
		    			
		    			availableCourseClear();
		    			}
	    		}
		    	}catch(Exception e) {e.printStackTrace();}
	    	
	    	}
	    
	    
//-----------------------------STUDENT_GRADE FORM-------------------------------------------
	    	
	    // code for Student_grade start from here;
	    
	    
	    public ObservableList<AddStudentController> studentGradesListData() {

	        ObservableList<AddStudentController> listData = FXCollections.observableArrayList();

	        String sql = "SELECT * FROM student_grade";

	        connect = database.connectdb();

	        try {
	        	AddStudentController studentD;

	            prepare = connect.prepareStatement(sql);
	            result = prepare.executeQuery();

	            while (result.next()) {
	                studentD = new AddStudentController(result.getInt("studentNum"),
	                         result.getString("year"),
	                         result.getString("course"),
	                         result.getDouble("first_sem"),
	                         result.getDouble("second_sem"),
	                         result.getDouble("final"));

	                listData.add(studentD);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return listData;
	    }

	    
	    private ObservableList<AddStudentController> studentGradesList;

	    public void studentGradesShowListData() {
	        studentGradesList = studentGradesListData();

	        studentGrade_col_StudentNum.setCellValueFactory(new PropertyValueFactory<>("studentNum"));
	        studentGrade_col_Year.setCellValueFactory(new PropertyValueFactory<>("year"));
	        studentGrade_col_Courses.setCellValueFactory(new PropertyValueFactory<>("course"));
	        studentGrade_col_firstSem.setCellValueFactory(new PropertyValueFactory<>("firstSem"));
	        studentGrade_col_secondSem.setCellValueFactory(new PropertyValueFactory<>("secondSem"));
	        studentGrade_col_final.setCellValueFactory(new PropertyValueFactory<>("finals"));
//	        WE NEED TO FIX THE DELETE ON ADD STUDENT FORM 
	        studentGrade_TableView.setItems(studentGradesList);

	    }
	    
	 // code to select StudentGrade Data to labels for update or delete

	    public void studentGradesSelect() {

	        AddStudentController studentD = studentGrade_TableView.getSelectionModel().getSelectedItem();
	        int num = studentGrade_TableView.getSelectionModel().getSelectedIndex();

	        if ((num - 1) < -1) {
	            return;
	        }

	        studentGrade_StudentNum.setText(String.valueOf(studentD.getStudentNum()));
	        studentGrade_Year.setText(studentD.getYear());
	        studentGrade_Courses.setText(studentD.getCourse());
	        studentGrade_firstSem.setText(String.valueOf(studentD.getFirstSem()));
	        studentGrade_secondSem.setText(String.valueOf(studentD.getSecondSem()));
	    }
	    // code for searching data in tableView:
	    
	    public void studentGradesSearch() {

	        FilteredList<AddStudentController> filter = new FilteredList<>(studentGradesList, e -> true);

	        studentGrade_Search.textProperty().addListener((Observable, oldValue, newValue) -> {

	            filter.setPredicate(predicateStudentData -> {

	                if (newValue.isEmpty() || newValue == null) {
	                    return true;
	                }
	                String searchKey = newValue.toLowerCase();

	                if (predicateStudentData.getStudentNum().toString().contains(searchKey)) {
	                    return true;
	                } else if (predicateStudentData.getYear().toLowerCase().contains(searchKey)) {
	                    return true;
	                } else if (predicateStudentData.getCourse().toLowerCase().contains(searchKey)) {
	                    return true;
	                } else if (predicateStudentData.getFirstSem().toString().contains(searchKey)) {
	                    return true;
	                } else if (predicateStudentData.getSecondSem().toString().contains(searchKey)) {
	                    return true;
	                } else if (predicateStudentData.getFinals().toString().contains(searchKey)) {
	                    return true;
	                } else {
	                    return false;
	                }
	            });
	        });

	        SortedList<AddStudentController> sortList = new SortedList<>(filter);

	        sortList.comparatorProperty().bind(studentGrade_TableView.comparatorProperty());
	        studentGrade_TableView.setItems(sortList);

	    }
	    
	    //code for grade update to database;
	    public void studentGradesUpdate() {
	        double finalCheck1 = 0;
	        double finalCheck2 = 0;

	        String checkData = "SELECT * FROM student_grade WHERE studentNum = '"
	                + studentGrade_StudentNum.getText() + "'";

	        connect = database.connectdb();

	        double finalResult = 0;

	        try {

	            prepare = connect.prepareStatement(checkData);
	            result = prepare.executeQuery();

	            if (result.next()) {
	                finalCheck1 = result.getDouble("first_sem");
	                finalCheck2 = result.getDouble("second_sem");
	            }

	            if (finalCheck1 == 0 || finalCheck2 == 0) {
	                finalResult = 0;
	            } else { //LIKE (X+Y)/2 AVE WE NEED TO FIND FOR FINALS
	                finalResult = ((Double.parseDouble(studentGrade_firstSem.getText())
	                        + Double.parseDouble(studentGrade_secondSem.getText())) / 2);
	               
	            }

	            String updateData = "UPDATE student_grade SET "
	                    + " year = '" + studentGrade_Year.getText()
	                    + "', course = '" + studentGrade_Courses.getText()
	                    + "', first_sem = '" + studentGrade_firstSem.getText()
	                    + "', second_sem = '" + studentGrade_secondSem.getText()
	                    + "', final = '" + finalResult + "' WHERE studentNum = '"
	                    + studentGrade_StudentNum.getText() + "'";

	            Alert alert;

	            if (studentGrade_StudentNum.getText().isEmpty()
	                    || studentGrade_Year.getText().isEmpty()
	                    || studentGrade_Courses.getText().isEmpty()) {
	                alert = new Alert(AlertType.ERROR);
	                alert.setTitle("Error Message");
	                alert.setHeaderText(null);
	                alert.setContentText("Please fill all blank fields");
	                alert.showAndWait();

	            } else {

	                alert = new Alert(AlertType.CONFIRMATION);
	                alert.setTitle("Confirmation Message");
	                alert.setHeaderText(null);
	                alert.setContentText("Are you sure you want to UPDATE Student #" + studentGrade_StudentNum.getText() + "?");
	                Optional<ButtonType> option = alert.showAndWait();

	                if (option.get().equals(ButtonType.OK)) {
	                    statement = connect.createStatement();
	                    statement.executeUpdate(updateData);

	                    alert = new Alert(AlertType.INFORMATION);
	                    alert.setTitle("Information Message");
	                    alert.setHeaderText(null);
	                    alert.setContentText("Successfully Updated!");
	                    alert.showAndWait();

	                    // TO UPDATE THE TABLEVIEW
	                    studentGradesShowListData();
	                } else {
	                    return;
	                }

	            }// NOT WE ARE CLOSER TO THE ENDING PART  :) LETS PROCEED TO DASHBOARD FORM 
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    public void studentGradesClear() {
	        studentGrade_StudentNum.setText("");
	        studentGrade_Year.setText("");
	        studentGrade_Courses.setText("");
	        studentGrade_firstSem.setText("");
	        studentGrade_secondSem.setText("");
	    }


//++++++++++++++++++++ END OF CODE++++++++++++++++++++++++++
	    
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			displayUsername();
			defaultNav();
			
			
			homeDisplayMaleEnrolled();
	      	homeDisplayFemaleEnrolled();
	      	homeDisplayTotalEnrolledStudents();
	      	homeDisplayEnrolledMaleChart();
		    homeDisplayFemaleEnrolledChart();
		    homeDisplayTotalEnrolledChart();
			
			addStudentsShowListData();
			addStudentsYearList();
			addStudentsGenderList();
			addStudentsStatusList();
			addStudentCourseList();
			
			availableCourseShowListData();
			
			studentGradesShowListData();
		}

}
