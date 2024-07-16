package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.tourismMySQL.Util.DatabaseUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class Form6Controller {
	Connection conn = null;
	PreparedStatement query = null; // sorgu ifadesi
	ResultSet brought = null;// getirilen değer
	String sql;

	public Form6Controller() {
		conn = DatabaseUtil.Connect(); // bağlantıyı içine aktardık.

	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField adress_txt;

    @FXML
    private AnchorPane anchor_person;

    @FXML
    private Button create;

    @FXML
    private Button delete;

    @FXML
    private TableColumn<Personel_info, String> e_adress;

    @FXML
    private TableColumn<Personel_info, String> e_gender;

    @FXML
    private TableColumn<Personel_info, String> e_id;

    @FXML
    private TableColumn<Personel_info, String> e_name;

    @FXML
    private TableColumn<Personel_info, String> e_phone;

    @FXML
    private ComboBox<String> gender_cmb;
    @FXML
    private TableView<Personel_info> person_info;

    @FXML
    private TextField id_txt;

    @FXML
    private TextField name_txt;

    @FXML
    private TextField phone_txt;

    @FXML
    private Button show;

    @FXML
    private Button update;

    @FXML
    void create_Click(ActionEvent event) {
    	sql = "insert into admin(employeeName,employeeId,employeeGender,employeePhone,employeeAdress) values(?,?,?,?,?)"; // yeni çalışanların bilgilerini veritabanına ekleyeceğimşz kod
		try {
			
		
			query = conn.prepareStatement(sql);
			query.setString(1, name_txt.getText().trim());
			query.setString(2, id_txt.getText().trim());
			query.setString(3, gender_cmb.getSelectionModel().getSelectedItem().trim());
			query.setString(4, phone_txt.getText().trim());
			query.setString(5, adress_txt.getText().trim());
		
		
			query.executeUpdate();
			System.out.println("Personnel registration creation successful");
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Message");
			alert.setHeaderText(null);
			alert.setContentText("Personnel registration creation successful!");
			alert.showAndWait();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage().toString());
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setHeaderText(null);
			alert.setContentText(" failed!");
			alert.showAndWait();

		}

	}
    public void FetchValues (TableView table) {//değerleri getirmek için yazdığımız fonksiyon
        sql="select*  from admin";
        
        ObservableList<Personel_info>tableList=FXCollections.observableArrayList();
        try {
            query=conn.prepareStatement(sql);
            ResultSet brought=query.executeQuery();
            while(brought.next()) {
              tableList.add(new Personel_info( brought.getString("employeeName"),brought.getString("employeeId"),brought.getString("employeeGender"),brought.getString("employeePhone"),brought.getString("employeeAdress")));
            }
             e_name.setCellValueFactory(new PropertyValueFactory<>("name"));
             e_id.setCellValueFactory(new PropertyValueFactory<>("id"));
             e_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
             e_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
             e_adress.setCellValueFactory(new PropertyValueFactory<>("adress"));
             person_info.setItems(tableList);
           

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage().toString());
            Alert alert = new Alert(AlertType.ERROR);//hata olursa uygulama içinde alert penceresi belirecek
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Failed");
            alert.showAndWait();
        }




    }
    
   
    

    @FXML
    void delete_Click(ActionEvent event) {
    	sql="delete from admin where employeeName=? and employeeId=?";	// işten çıkmak isteyen çalışanların bilgilerini veritabanından sileceğimiz kod
	    try {
	    	query = conn.prepareStatement(sql);
			query.setString(1,name_txt.getText().trim());
			query.setString(2,id_txt.getText().trim());
			query.executeUpdate();
			System.out.println("Delete is completed succesfully..");
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Message");
			alert.setHeaderText(null);
			alert.setContentText("Delete is completed succesfully!");
			alert.showAndWait();

		} 
	    catch (Exception e) {
			// TODO: handle exception
	    	System.out.println(e.getMessage().toString());
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setHeaderText(null);
			alert.setContentText("Delete is failed! Make sure you fill out all the information and it is correct!!");
			alert.showAndWait();
	    }
    

    }

    @FXML
    void gender_cmb_Click(ActionEvent event) {

    }

    @FXML
    void show_Click(ActionEvent event) {
    	FetchValues(person_info);

    

    }

    @FXML
    void update_Click(ActionEvent event) {
    	sql="update admin set employeeId=?,employeeGender=?,employeePhone=?,employeeAdress=? where employeeName=?";	//çalışan bilgilerini güncelleyeceğimiz kod
        try {
        	query = conn.prepareStatement(sql);

			query = conn.prepareStatement(sql);
			query.setString(1, id_txt.getText().trim());
			query.setString(2, gender_cmb.getSelectionModel().getSelectedItem().trim());
			query.setString(3, phone_txt.getText().trim());
			query.setString(4, adress_txt.getText().trim());;
			query.setString(5, name_txt.getText().trim());;
    		query.executeUpdate();
    		System.out.println("Update is completed succesfully..");
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Message");
    		alert.setHeaderText(null);
    		alert.setContentText("Update is completed succesfully!");
    		alert.showAndWait();

    	} 
        catch (Exception e) {
    		// TODO: handle exception
        	System.out.println(e.getMessage().toString());
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("ERROR");
    		alert.setHeaderText(null);
    		alert.setContentText("Update is failed! Make sure you fill out all the information and it is correct!!");
    		alert.showAndWait();
    	}

    }

    @FXML
    void initialize() {
    	String[] array = { "Male", "Female" };
    	gender_cmb.getItems().addAll(array);
    
       
    }

}
