package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.tourismMySQL.Util.DatabaseUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import java.sql.*;

public class Form3Controller {
	Connection conn = null;
	PreparedStatement query = null; // sorgu ifadesi
	ResultSet brought = null;// getirilen değer
	String sql;

	public Form3Controller() {
		conn = DatabaseUtil.Connect(); // bağlantıyı içine aktardık.

	}

	@FXML
	private AnchorPane anchor3;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button back_btn;

	@FXML
	private ComboBox<String> combo1;

	@FXML
	private Button create_btn;

	@FXML
	private TextField create_country;

	@FXML
	private TextField create_id;

	@FXML
	private TextField create_mail;

	@FXML
	private TextField create_name;

	@FXML
	private TextField create_pass;

	@FXML
	private TextField create_phone;

	@FXML
	private TextField create_username;

	@FXML
	void back_btn_Click(ActionEvent event) throws IOException {
		new SceneSwitch(anchor3, "Form2.fxml");
	}

	@FXML
	void combo1_Click(ActionEvent event) {

	}

	@FXML
	void create_btn_Click(ActionEvent event) {
		sql = "insert into signup(Username,Password,Name,Gender,Id,Email,Phone,Country) values(?,?,?,?,?,?,?,?)";
		try {
			
			query = conn.prepareStatement(sql);
			query = conn.prepareStatement(sql);
			query.setString(1, create_username.getText().trim());
			query.setString(2, DatabaseUtil.MD5Sifrele(create_pass.getText().trim()));
			query.setString(3, create_name.getText().trim());
			query.setString(4, combo1.getSelectionModel().getSelectedItem().trim());
			query.setString(5, create_id.getText().trim());
			query.setString(6, create_mail.getText().trim());
			query.setString(7, create_phone.getText().trim());
			query.setString(8, create_country.getText().trim());
			query.executeUpdate();
			System.out.println("kullanıcı ekleme başarılı");
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Message");
			alert.setHeaderText(null);
			alert.setContentText("Signup is completed succesfully!");
			alert.showAndWait();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage().toString());
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setHeaderText(null);
			alert.setContentText("Signup is failed!");
			alert.showAndWait();

		}

	}

	@FXML
	void initialize() {
		String[] array = { "Male", "Female" };
		combo1.getItems().addAll(array);

	}

}
