package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.tourismMySQL.Util.DatabaseUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Form5Controller {
	public Form5Controller() {
		conn = DatabaseUtil.Connect(); // bağlantıyı içine aktardık. 
		}
	   
	    Connection conn=null;
		PreparedStatement query=null; //sorgu ifadesi
		ResultSet brought=null;// getirilen değer
	    String sql;
		 
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button check_btn;

    @FXML
    private Button create_rsv;

    @FXML
    private Button delete_btn;

    @FXML
    private TextField id_txt;

    @FXML
    private Label lbl;

    @FXML
    private Label lbl_price;

    @FXML
    private TextField mail_txt;

    @FXML
    private TextField name_txt;

    @FXML
    private TextField pass_txt;

    @FXML
    private TextField person_txt;

    @FXML
    private AnchorPane personel_details;

    @FXML
    private TextField phone_txt;

    @FXML
    private Label pkg1;

    @FXML
    private Label pkg2;

    @FXML
    private Label pkg3;

    @FXML
    private ComboBox<String> select_cmb;

    @FXML
    private TableColumn<Table_reservation, String> table_id;

    @FXML
    private TableColumn<Table_reservation, String> table_name;

    @FXML
    private TableColumn<Table_reservation, String> table_package;

    @FXML
    private TableColumn<Table_reservation, String> table_person;

    @FXML
    private TableColumn<Table_reservation,String> table_phone;

    @FXML
    private TableColumn<Table_reservation, Double> table_price;
    

    @FXML
    private TableColumn<Table_reservation,LocalDate> table_date;

    @FXML
    private Button update_btn;

    @FXML
    private TextField username_txt;

    @FXML
    private Button view_btn;
    @FXML
    private TableView<Table_reservation> tableview_reserv;
    @FXML
    private DatePicker dateFinish;


    
   
    
    private final String[]  packages= { "Package1", "Package2","Package3" };//PAKETLER VE FİYATLARI
    private final double[] prices = {145.0, 290.0, 385.0};
    
  
    
    @FXML
    void check_btn_Click(ActionEvent event) {
    	String selectpackage=select_cmb.getValue();
        int person=Integer.parseInt(person_txt.getText());
        double packageprices = 0.0;
        for (int i = 0; i < packages.length; i++) {
            if (packages[i].equals(selectpackage)) {
            	packageprices = prices[i];
            
            
                break;
            }
        }
        double toplamUcret = packageprices * person;
        lbl_price.setText(  toplamUcret + " $");
    }
    
    public void FetchValues (TableView table) {//değerleri getirmek için yazdığımız fonksiyon
        sql="select *  from reservation";
        
        ObservableList<Table_reservation>tableList=FXCollections.observableArrayList();
        try {
            query=conn.prepareStatement(sql);
            ResultSet brought=query.executeQuery();
            while(brought.next()) {
                tableList.add(new Table_reservation( brought.getString("name"),brought.getString("id"),brought.getString("phone"),brought.getString("package"),brought.getString("person"),brought.getDouble("price"),brought.getDate("date")));
            }
             table_name.setCellValueFactory(new PropertyValueFactory<>("name"));
             table_id.setCellValueFactory(new PropertyValueFactory<>("id"));
             table_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
             table_package.setCellValueFactory(new PropertyValueFactory<>("packages"));
             table_person.setCellValueFactory(new PropertyValueFactory<>("person"));
             table_price.setCellValueFactory(new PropertyValueFactory<>("price"));
             table_date.setCellValueFactory(new PropertyValueFactory<>("date"));
             tableview_reserv.setItems(tableList);

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
    void create_rsv_Click(ActionEvent event) {
    	sql = "insert into reservation(name,id,phone,package,person,price,date) values(?,?,?,?,?,?,?)";
    	try {
    		

    		LocalDate tarih2=dateFinish.getValue();
   		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd"); // fixed format string
   	        String formattedString =tarih2.format(formatter); 
    		
    		query = conn.prepareStatement(sql);
    		query.setString(1, name_txt.getText().trim());
			query.setString(2, id_txt.getText().trim());
			query.setString(3, phone_txt.getText().trim());
			query.setString(4, select_cmb.getSelectionModel().getSelectedItem().trim());
			query.setString(5, person_txt.getText().trim());
			query.setInt(6, Integer.parseInt((lbl_price.getText().replaceAll("[^\\d]", ""))));//integer olmayan tüm değerleri kaldırır.
			query.setString(7, formattedString);
			query.executeUpdate();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Message");
			alert.setHeaderText(null);
			alert.setContentText("Your reservation has been created successfully!");
			alert.showAndWait();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage().toString());
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setHeaderText(null);
			alert.setContentText("Your reservation could not be created!");
			alert.showAndWait();
		
		}
    	
    	
    	
    	
    }

   
    
    
    @FXML
    void delete_btn_Click(ActionEvent event) {
    	sql="delete from signup where Username=? and Password=?";	
	    try {
	    	query = conn.prepareStatement(sql);
			query.setString(2,DatabaseUtil.MD5Sifrele(pass_txt.getText().trim()));
			query.setString(1,username_txt.getText().trim());
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
    void select_cmb_Action(ActionEvent event) {

    }

    @FXML
    void update_btn_Click(ActionEvent event) {
    	sql="update signup set Password=?,Email=?,Phone=? where Username=?";	
        try {
        	query = conn.prepareStatement(sql);
    		query.setString(1,DatabaseUtil.MD5Sifrele(pass_txt.getText().trim()));
    		query.setString(2,mail_txt.getText().trim());
    		query.setString(3,phone_txt.getText().trim());
    		query.setString(4,username_txt.getText().trim());
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
    void view_btn_Click(ActionEvent event) {
    	FetchValues(tableview_reserv);

    }

    @FXML
    void initialize() {
    	
    	select_cmb.getItems().addAll(packages);
    
    	
    	
    }

	public double[] getPrices() {
		return prices;
	}

}
