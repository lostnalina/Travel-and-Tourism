package application;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import com.tourismMySQL.Util.DatabaseUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class Form2Controller {

	public Form2Controller() {

		conn = DatabaseUtil.Connect();
	}

	@FXML
	private AnchorPane anchor1;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button log_btn;

	@FXML
	private TextField pass_text;

	@FXML
	private Button sign_btn;
	
	@FXML
	 private DatePicker dateFinish;

	  @FXML
	  private DatePicker dateStart;

	@FXML
	private TextField user_text;
	Connection conn = null;
	PreparedStatement query = null; // sorgu ifadesi
	ResultSet brought = null;// getirilen değer
	String sql;

	@FXML
	void log_btn_Click(ActionEvent event) {
	    sql = "select * from signup where Username=? and Password=?";
	    try {
	        String username = user_text.getText().trim();
	        String password = pass_text.getText().trim();

	        if (username.equals("admin") && password.equals("admin")) {
	            // Eğer kullanıcı adı ve şifre 'admin' ise Form6'ya geç
	            new SceneSwitch(anchor1, "Form6.fxml");
	        } else {
	            // Diğer kullanıcılar için veritabanı kontrolü yap
	            query = conn.prepareStatement(sql);
	            query.setString(1, username);
	            query.setString(2, DatabaseUtil.MD5Sifrele(password));

	            ResultSet brought = query.executeQuery();

	            if (!brought.next()) {
	                // Kullanıcı adı veya şifre yanlışsa hata mesajı göster
	                Alert alert = new Alert(AlertType.ERROR);
	                alert.setTitle("ERROR");
	                alert.setHeaderText(null);
	                alert.setContentText("Username or password is wrong!");
	                alert.showAndWait();
	            } else {
	                // Kullanıcı adı ve şifre doğruysa Form5'e geç
	                System.out.println("Username:" + brought.getString("Username"));
	                System.out.println("Password:" + brought.getString("Password"));
	                new SceneSwitch(anchor1, "Form5.fxml");
	            }
	        }
	    } catch (Exception e) {
	        // Hata durumunda hata mesajı göster
	        Alert alert2 = new Alert(AlertType.ERROR);
	        alert2.setTitle("ERROR");
	        alert2.setHeaderText(null);
	        alert2.setContentText("Communications link failure");
	        alert2.showAndWait();
	    }
	}

	@FXML
	void sign_btn_Click(ActionEvent event) throws IOException {
		new SceneSwitch(anchor1, "Form3.fxml");
	}

	@FXML
	void initialize() {

	}

}
