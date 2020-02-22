package gui;
//A Great GUI
import MonopolyClient.MainClient;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ClientStage extends Stage {

    private LoginPage loginPage;
    private SignUpPage signUpPage;
    private NickNamePage nickNamePage;
    private MainGameDesk mainGameDesk;
    private MainClient client;

    public ClientStage(MainClient client){
        this.client = client;

        this.setTitle("Monopoly");
        this.setResizable(false);
        setLoginPage();
        this.show();
    }

    public void setLoginPage(){
        loginPage = new LoginPage(client);
        this.setScene(loginPage.getScene());
    }

    public void setGameDeskPage(){
        mainGameDesk = new MainGameDesk(client);
        this.setResizable(false);
        this.setScene(mainGameDesk.scene);
    }

/***********************************************************************************************************************/
                                            /**Used by current class**/
/***********************************************************************************************************************/
    public Node findElement(int row, int col, GridPane root) {
        Node result = null;
        for (Node node : root.getChildren()) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
                result = node;
                break;
            }
        }
        return result;
    }
    public void loginFailed() {
        Label loginFail = (Label) this.findElement(0, 0, (GridPane) this.getScene().getRoot());
        loginFail.setText("Incorrect username or password");
        loginFail.setTextFill(Color.RED);
    }

    public void signUpFail() {
        Label signUpFail = (Label) this.findElement(0, 0, (GridPane) this.getScene().getRoot());
        signUpFail.setText("Username has been used");
        signUpFail.setTextFill(Color.RED);
    }

    public void signUpSuccess() {
        Label signUpSuccess = (Label) this.findElement(0, 0, (GridPane) this.getScene().getRoot());
        signUpSuccess.setText("Account created");
        signUpSuccess.setTextFill(Color.GREEN);
    }

    public void nickName() {
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(25, 25, 25, 25));
        Label prompt = new Label();
        Label nickName = new Label("Please choose your nickname");
        TextField nickNameField = new TextField();
        Button submit = new Button("Submit");
        submit.setOnAction(e -> {
            client.send("NickName " + nickNameField.getText());
        });
        root.add(prompt, 0, 0, 2, 1);
        root.add(nickName, 0, 1);
        root.add(nickNameField, 1, 1);
        root.add(submit, 1, 2);
        this.setScene(new Scene(root));
    }

    public void nickNameFail() {
        Label nickNameFail = (Label) this.findElement(0, 0, (GridPane) this.getScene().getRoot());
        nickNameFail.setText("Nickname has already been used");
        nickNameFail.setTextFill(Color.RED);
    }

/***********************************************************************************************************************/
                                            /**Used by MainClient class**/
/***********************************************************************************************************************/

}
