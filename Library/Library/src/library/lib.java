package library;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Optional;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import static library.book.deletek;
import static library.book.loadk;
import static library.book.savek;
import static library.pf.add;
import static library.pf.book;
import static library.pf.checkl;
import static library.pf.checks;
import static library.pf.delb;
import static library.pf.deletedir;
import static library.pf.nextp;
import static library.pf.prep;
import static library.pf.read;
import static library.pf.resetpass;
import static library.pf.searchau;
import static library.pf.searchn;
import static library.pf.searchreld;
import static library.user.deleteu;
import static library.user.loadu;
import static library.user.saveu;
import static org.bouncycastle.asn1.cms.CMSObjectIdentifiers.data;
import static org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers.data;

public class lib extends Application {

    ////////////////////////////////////////////////////
    //Start of username formatter
    Pattern pattern = Pattern.compile("[a-zA-Z]*");
    UnaryOperator<TextFormatter.Change> filter = c -> {
        if (pattern.matcher(c.getControlNewText()).matches()) {
            return c;
        } else {
            return null;
        }
    };
    TextFormatter<String> formatter = new TextFormatter<>(filter);
    //end of username formatter
    //////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////
    Stage window;
    Scene wel, login, signup, home, addbooks, del, view, edit, resetp, book, reader, edit2,scene1,scene2,scene3,scene4;
    LinkedList<user> data = new LinkedList<>();

    //LinkedList<book> books = new LinkedList<>();
    Label holhi;
    DatePicker siddob;
    DatePicker reddob;
    Image main;
    ImageView iv8;

    @Override
    public void start(Stage primaryStage) throws IOException, InterruptedException, Exception {
        //read("C:\\Users\\Kerlos\\Documents\\NetBeansProjects\\Library\\PMS.pdf");
        
        loadu(data);
      //  loadk(data.);

        /////////////////////////////////////////////////////////////////////
        window = primaryStage;

        //BackgroundImage allback = new BackgroundImage(new Image(lib.class.getResource("back.png").toExternalForm(), 40, 40, false, false),
        //        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
        //        BackgroundSize.DEFAULT);        
        //Background ivb=new Background(allback);
        //////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////
        //Start of welcome scene
        Pane root1 = new Pane();
        Button webent = new Button("               Enter            ");
        webent.setLayoutX(270);
        webent.setLayoutY(230);
        BackgroundImage myBI1 = new BackgroundImage(new Image(lib.class.getResource("Miky.gif").toExternalForm(), 400, 250, false, false),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        root1.setBackground(new Background(myBI1));
        webent.setOnAction(e -> window.setScene(login));
        Label welwel = new Label("Welcome");
        welwel.setLayoutX(6);
        welwel.setLayoutY(50);
        welwel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 40));
        welwel.setTextFill(Color.web("#000000"));
        root1.getChildren().addAll(webent, welwel);
        wel = new Scene(root1, 400, 250, Color.LIGHTGRAY);
        //end of welcome scene
        //////////////////////////////////////////////////////////////////////////
        //start of the login scene
        Button lobcna = new Button("Create a new account");
        lobcna.setLayoutX(50);
        lobcna.setLayoutY(200);
        Button loblogin = new Button("Login");
        loblogin.setLayoutX(150);
        loblogin.setLayoutY(150);
        Text lotwel = new Text("Welcome");
        lotwel.setLayoutX(170);
        lotwel.setLayoutY(40);
        lotwel.setScaleX(4);
        lotwel.setScaleY(4);
        Label lolun = new Label("Username");
        lolun.setLayoutX(50);
        lolun.setLayoutY(85);
        TextField lotun = new TextField();
        lotun.setLayoutX(120);
        lotun.setLayoutY(80);

        Label lolpass = new Label("Password");
        lolpass.setLayoutX(50);
        lolpass.setLayoutY(125);
        PasswordField loppass = new PasswordField();
        loppass.setLayoutX(120);
        loppass.setLayoutY(120);
        Label lolerr = new Label("");
        lolerr.setLayoutX(50);
        lolerr.setLayoutY(227);
        lolerr.setTextFill(Color.web("#990033"));
        Button lobforget = new Button("Forgot Your Password?");
        lobforget.setOnAction(e -> {
            reddob.setValue(Optional.ofNullable(siddob.getValue()).orElse(LocalDate.now()));
            lotun.setText("");
            loppass.setText("");
            lolerr.setText("");
            window.setScene(resetp);
        });
        lobforget.setStyle("-fx-background-color: transparent;");
        lobforget.setLayoutX(60);
        lobforget.setLayoutY(175);
        Image image = new Image(lib.class.getResource("logim.png").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setX(250);
        imageView.setY(50);
        imageView.setFitHeight(196.5);
        imageView.setFitWidth(142);
        loblogin.setOnAction(e -> {
            if (checkl(data, lotun.getText(), loppass.getText()) == true) {
                holhi.setText("Hi, " + data.get(pf.index).name);
                lotun.setText("");
                loppass.setText("");
                lolerr.setText("");
                window.setScene(home);
            } else {
                lolerr.setText("Wrong Username or Password");
            }
        }
        );
        lobcna.setOnAction(e -> {
            primaryStage.setScene(signup);
            siddob.setValue(LocalDate.now());
            lolerr.setText("");
            lotun.setText("");
            loppass.setText("");
        });
        Pane root = new Pane();
        primaryStage.getIcons().add(new Image(lib.class.getResource("icon.png").toExternalForm()));
        root.getChildren().addAll(lobcna, lotwel, lolun, lotun, lolpass, loppass, loblogin, lolerr, imageView, lobforget);
        login = new Scene(root, 400, 250);
        //// end of login scene
        //////////////////////////////////////////////////////////////////////////
        //Start of signup scene
        Pane root2 = new Pane();
        Image b = new Image(lib.class.getResource("back.png").toExternalForm());
        ImageView ivb = new ImageView(b);
        ivb.setFitHeight(35);
        ivb.setFitWidth(35);
        Button sibback = new Button("", ivb);
        //sibback.setBackground(ivb);       
        sibback.setStyle("-fx-background-color: transparent;");
        sibback.setLayoutX(5);
        sibback.setLayoutY(205);
        Label silreg = new Label("Register");
        silreg.setScaleX(2);
        silreg.setScaleY(2);
        silreg.setLayoutX(35);
        silreg.setLayoutY(10);
        Label silun = new Label("Username");
        silun.setLayoutX(10);
        silun.setLayoutY(55);
        Label silpass = new Label("Password");
        silpass.setLayoutX(10);
        silpass.setLayoutY(115);
        TextField situn = new TextField();
        situn.setLayoutX(70);
        situn.setLayoutY(50);
        situn.setTextFormatter(formatter);
        PasswordField sippass = new PasswordField();
        sippass.setLayoutX(70);
        sippass.setLayoutY(110);
        Label sildob = new Label("Birthday");
        sildob.setLayoutX(10);
        sildob.setLayoutY(175);
        siddob = new DatePicker();
        siddob.setLayoutX(70);
        siddob.setLayoutY(170);
        Button sibsi = new Button("Sign up");
        sibsi.setLayoutX(70);
        sibsi.setLayoutY(215);
        Label silerr = new Label("");
        silerr.setLayoutX(70);
        silerr.setLayoutY(197);
        silerr.setTextFill(Color.web("#990033"));

        sibsi.setOnAction(e -> {
            siddob.setValue(Optional.ofNullable(siddob.getValue()).orElse(LocalDate.now()));
            //String bd = siddob.getValue().format( DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            if (checks(data, situn.getText()) == false) {
                if (!situn.getText().isEmpty() && !sippass.getText().isEmpty()) {
                    window.setScene(login);
                    add(data, situn.getText(), sippass.getText(), siddob.getValue().toString());
                    //System.out.println(siddob.getValue().toString());
                    situn.setText("");
                    sippass.setText("");
                    silerr.setText("");
                    deleteu();
                    deletek();
                    saveu(data);
                    savek(data);
                } else {
                    silerr.setText("Enter Valid Values");
                }
            } else {
                silerr.setText("Username Already Exists");
            }
        });
        sibback.setOnAction(e -> {
            situn.setText("");
            sippass.setText("");
            silerr.setText("");
            window.setScene(login);
        });
        Image image2 = new Image(lib.class.getResource("sigim.png").toExternalForm());
        ImageView imageView2 = new ImageView(image2);
        imageView2.setX(240);
        imageView2.setY(30);
        imageView2.setFitHeight(196.5);
        imageView2.setFitWidth(142);
        root2.getChildren().addAll(silun, silpass, situn, silreg, sippass, sibsi, siddob, sildob, silerr, sibback, imageView2);
        signup = new Scene(root2, 400, 250);
        //end of signup scene
        //////////////////////////////////////////////////////////////////////////
        //Start of reset loppass scene
        Pane root8 = new Pane();
        BackgroundImage myBI6 = new BackgroundImage(new Image(lib.class.getResource("resetp.jpg").toExternalForm(), 400, 250, false, false),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        root8.setBackground(new Background(myBI6));
        Label relre = new Label("Reset Your Password");
        relre.setLayoutX(60);
        relre.setLayoutY(20);
        relre.setScaleX(2);
        relre.setScaleY(2);
        relre.setTextFill(Color.web("#FFFFFF"));
        Label relun = new Label("Username");
        relun.setLayoutX(10);
        relun.setLayoutY(60);
        TextField retun = new TextField();
        retun.setLayoutX(70);
        retun.setLayoutY(55);
        relun.setTextFill(Color.web("#FFFFFF"));
        Label reldob = new Label("Birthday");
        reldob.setLayoutX(10);
        reldob.setLayoutY(105);
        reldob.setTextFill(Color.web("#FFFFFF"));
        reddob = new DatePicker();
        reddob.setLayoutX(70);
        reddob.setLayoutY(100);
        Label relnp = new Label("New Password");
        relnp.setLayoutX(10);
        relnp.setLayoutY(155);
        relnp.setTextFill(Color.web("#FFFFFF"));
        PasswordField repnp = new PasswordField();
        repnp.setLayoutX(95);
        repnp.setLayoutY(150);
        Label relerr = new Label("");
        relerr.setLayoutX(90);
        relerr.setLayoutY(220);
        relerr.setTextFill(Color.web("#990033"));
        Button rebre = new Button("Reset");
        rebre.setLayoutX(110);
        rebre.setLayoutY(190);
        Image b1 = new Image(lib.class.getResource("back.png").toExternalForm());
        ImageView ivb1 = new ImageView(b1);
        ivb1.setFitHeight(35);
        ivb1.setFitWidth(35);
        Button rebback = new Button("", ivb1);
        //rebback.setBackground(ivb);
        rebback.setStyle("-fx-background-color: transparent;");
        rebback.setLayoutX(5);
        rebback.setLayoutY(205);
        rebback.setOnAction(e -> {
            window.setScene(login);
            relerr.setText("");
            retun.setText("");
            repnp.setText("");
        });
        rebre.setOnAction(e -> {
            if (resetpass(data, reddob.getValue().toString(), retun.getText(), repnp.getText())) {
                window.setScene(login);
                relerr.setText("");
                retun.setText("");
                repnp.setText("");
                deleteu();
                deletek();
                saveu(data);
                savek(data);
            } else {
                relerr.setText("Wrong Birthday or Username");
            }
        });
        root8.getChildren().addAll(retun, relre, reldob, relun, reddob, rebre, rebback, repnp, relnp, relerr);
        resetp = new Scene(root8, 400, 250);
        //end of reset loppass scene
        //////////////////////////////////////////////////////////////////////////
        //Start of home scene
        Pane root3 = new Pane();
        holhi = new Label();
        holhi.setLayoutX(150);
        holhi.setLayoutY(20);
        holhi.setScaleX(2);
        holhi.setScaleY(2);
        Image i3 = new Image(lib.class.getResource("logout.png").toExternalForm());
        ImageView iv2 = new ImageView(i3);
        iv2.setFitHeight(30);
        iv2.setFitWidth(30);
        Button hoblogout = new Button("Logout", iv2);
        hoblogout.setOnAction(e -> {

            window.setScene(login);
        });
        hoblogout.setStyle("-fx-background-color: transparent;");
        hoblogout.setLayoutX(10);
        hoblogout.setLayoutY(210);
        Image i4 = new Image(lib.class.getResource("Add.png").toExternalForm());
        ImageView iv3 = new ImageView(i4);
        iv3.setFitHeight(50);
        iv3.setFitWidth(50);
        Button hobadd = new Button("", iv3);
        hobadd.setOnAction(e -> window.setScene(addbooks));
        hobadd.setTooltip(new Tooltip("Add Books"));
        hobadd.setStyle("-fx-background-color: transparent;");
        hobadd.setLayoutX(40);
        hobadd.setLayoutY(65);
        Image i5 = new Image(lib.class.getResource("del.png").toExternalForm());
        ImageView iv4 = new ImageView(i5);
        iv4.setFitHeight(50);
        iv4.setFitWidth(50);
        Button hobdel = new Button("", iv4);
        hobdel.setOnAction(e -> window.setScene(del));
        hobdel.setTooltip(new Tooltip("Delete Books"));
        hobdel.setStyle("-fx-background-color: transparent;");
        hobdel.setLayoutX(40);
        hobdel.setLayoutY(135);
        Image i6 = new Image(lib.class.getResource("edit.png").toExternalForm());
        ImageView iv5 = new ImageView(i6);
        iv5.setFitHeight(50);
        iv5.setFitWidth(50);
        Button hobeb = new Button("", iv5);
        hobeb.setOnAction(e -> window.setScene(edit));
        hobeb.setTooltip(new Tooltip("Edit Books"));
        hobeb.setStyle("-fx-background-color: transparent;");
        hobeb.setLayoutX(300);
        hobeb.setLayoutY(135);
        Image i7 = new Image(lib.class.getResource("view.png").toExternalForm());
        ImageView iv6 = new ImageView(i7);
        iv6.setFitHeight(50);
        iv6.setFitWidth(50);
        Button hobvb = new Button("", iv6);
        hobvb.setOnAction(e -> window.setScene(view));
        hobvb.setTooltip(new Tooltip("View Books"));
        hobvb.setStyle("-fx-background-color: transparent;");
        hobvb.setLayoutX(300);
        hobvb.setLayoutY(65);
        Image i18 = new Image(lib.class.getResource("vid.png").toExternalForm());
        ImageView iv18 = new ImageView(i18);
        iv18.setFitHeight(30);
        iv18.setFitWidth(30);
        Button hobvid = new Button("", iv18);
        hobvid.setOnAction(e -> window.setScene(scene1));
        hobvid.setTooltip(new Tooltip("Videos"));
        hobvid.setStyle("-fx-background-color: transparent;");
        hobvid.setLayoutX(350);
        hobvid.setLayoutY(210);
        BackgroundImage myBI = new BackgroundImage(new Image(lib.class.getResource("homeb.jpg").toExternalForm(), 400, 250, false, false),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        root3.setBackground(new Background(myBI));
        root3.getChildren().addAll(hoblogout, holhi, hobadd, hobdel, hobeb, hobvb,hobvid);
        home = new Scene(root3, 400, 250);
        //end of home scene
        //////////////////////////////////////////////////////////////////////////
        //Start of add books scene
        Pane root4 = new Pane();
        Image b2 = new Image(lib.class.getResource("back.png").toExternalForm());
        ImageView ivb2 = new ImageView(b2);
        ivb2.setFitHeight(35);
        ivb2.setFitWidth(35);
        Button adbback = new Button("", ivb2);
        //adbback.setBackground(ivb);
        adbback.setStyle("-fx-background-color: transparent;");
        adbback.setLayoutX(5);
        adbback.setLayoutY(205);

        Label adlab = new Label("Add Book");
        adlab.setLayoutX(30);
        adlab.setLayoutY(5);
        adlab.setScaleX(2);
        adlab.setScaleY(2);
        Label adlbn = new Label("Book Name");
        adlbn.setLayoutX(5);
        adlbn.setLayoutY(40);
        TextField adtbn = new TextField();
        adtbn.setLayoutX(80);
        adtbn.setLayoutY(35);

        Label adlau = new Label("Author");
        adlau.setLayoutX(5);
        adlau.setLayoutY(80);
        TextField adtau = new TextField();
        adtau.setLayoutX(80);
        adtau.setLayoutY(75);
        Label adlrel = new Label("Release Date");
        adlrel.setLayoutX(5);
        adlrel.setLayoutY(120);
        TextField adtrel = new TextField();
        adtrel.setLayoutX(80);
        adtrel.setLayoutY(115);
        Label adlpos = new Label("Book Path");
        adlpos.setLayoutX(5);
        adlpos.setLayoutY(160);
        TextField adtpos = new TextField();
        adtpos.setLayoutX(80);
        adtpos.setLayoutY(155);
        adtpos.setEditable(false);
        Button adbab = new Button("Add Book");
        adbab.setLayoutX(120);
        adbab.setLayoutY(200);
        Image browse = new Image(lib.class.getResource("browse.png").toExternalForm());
        ImageView iv7 = new ImageView(browse);
        iv7.setFitHeight(30);
        iv7.setFitWidth(30);
        Button adbbr = new Button("", iv7);
        adbbr.setStyle("-fx-background-color: transparent;");
        adbbr.setLayoutX(220);
        adbbr.setLayoutY(149);
        Label adlerr = new Label();
        adlerr.setLayoutX(80);
        adlerr.setLayoutY(230);
        adlerr.setTextFill(Color.web("#990033"));
        FileChooser adfbook = new FileChooser();
        //Exception except = new Exception("Encapsulated");

        adfbook.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        adbbr.setOnAction(e -> {
            try {
                File selectedFile = adfbook.showOpenDialog(window);
                adtpos.setText(selectedFile.getAbsolutePath());
            } catch (Exception ex) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Wrong Extension");
                alert.setHeaderText(null);
                alert.setContentText("Sorry PDF and EPUB Only");
                alert.showAndWait();
                //System.out.println("shit");
            }

        });
        adbab.setOnAction(e -> {
            if (!adtpos.getText().isEmpty() && !adtbn.getText().isEmpty()) {
                window.setScene(home);
                data.get(pf.index).books.add(new book(adtbn.getText(), adtau.getText(), adtrel.getText(), adtpos.getText()));
                adtbn.setText("");
                adtrel.setText("");
                adtau.setText("");
                adtpos.setText("");
                adlerr.setText("");
                deleteu();
                deletek();
                saveu(data);
                savek(data);
            } else {
                adlerr.setText("Book's Name and Path Are Requied");
            }
        });
        adbback.setOnAction(e -> {
            adtbn.setText("");
            adtrel.setText("");
            adtau.setText("");
            adtpos.setText("");
            adlerr.setText("");
            window.setScene(home);
        });
        BackgroundImage myBI2 = new BackgroundImage(new Image(lib.class.getResource("addbooks.jpg").toExternalForm(), 400, 250, false, false),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        root4.setBackground(new Background(myBI2));
        root4.getChildren().addAll(adlbn, adlab, adlau, adlrel, adlpos, adtbn, adtau, adtrel, adtpos, adbbr, adbab, adbback, adlerr);
        addbooks = new Scene(root4, 400, 250);
        //end of add books scene
        //////////////////////////////////////////////////////////////////////////
        //Start of delete book scene
        Pane root5 = new Pane();
        Image b3 = new Image(lib.class.getResource("back.png").toExternalForm());
        ImageView ivb3 = new ImageView(b3);
        ivb3.setFitHeight(35);
        ivb3.setFitWidth(35);
        Button delbback = new Button("", ivb3);
        //delbback.setBackground(ivb);
        delbback.setStyle("-fx-background-color: transparent;");
        delbback.setLayoutX(3);
        delbback.setLayoutY(205);

        Label delldel = new Label("Delete Book : ");
        delldel.setLayoutX(40);
        delldel.setLayoutY(1);
        delldel.setScaleX(2);
        delldel.setScaleY(2);
        delldel.setTextFill(Color.web("#FFFFFF"));
        Label dellse = new Label("  search :");
        dellse.setLayoutX(1);
        dellse.setLayoutY(35);
        dellse.setTextFill(Color.web("#FFFFFF"));
        dellse.setWrapText(false);
        dellse.setAlignment(Pos.BASELINE_LEFT);
        Label dellb1 = new Label("1 :");
        dellb1.setLayoutX(55);
        dellb1.setLayoutY(100);
        dellb1.setTextFill(Color.web("#FFFFFF"));
        dellb1.setWrapText(false);
        Button delbb1 = new Button();
        delbb1.setLayoutX(70);
        delbb1.setLayoutY(97);
        delbb1.setOnAction(e -> {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            //alert.setTitle("Confirmation Dialog");
            //alert.setHeaderText("Look, a Confirmation Dialog");
            alert.setContentText("Are you sure?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                book(data, delbb1.getText());
                delb(data, pf.book);
                pf.book = 0;
                delbb1.setText("");
                deleteu();
                deletek();
                saveu(data);
                savek(data);
            }
        });
        delbb1.setStyle("-fx-background-color: transparent;");
        delbb1.setTextFill(Color.web("#FFFFFF"));
        Label dellb2 = new Label("2 :");
        dellb2.setLayoutX(55);
        dellb2.setLayoutY(125);
        dellb2.setTextFill(Color.web("#FFFFFF"));
        dellb2.setWrapText(false);
        Button delbb2 = new Button();
        delbb2.setLayoutX(70);
        delbb2.setLayoutY(122);
        delbb2.setOnAction(e -> {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            //alert.setTitle("Confirmation Dialog");
            //alert.setHeaderText("Look, a Confirmation Dialog");
            alert.setContentText("Are you sure?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                book(data, delbb2.getText());
                delb(data, pf.book);
                pf.book = 0;
                delbb2.setText("");
                deleteu();
                deletek();
                saveu(data);
                savek(data);
            }
        });
        delbb2.setStyle("-fx-background-color: transparent;");
        delbb2.setTextFill(Color.web("#FFFFFF"));
        Label dellb3 = new Label("3 :");
        dellb3.setLayoutX(55);
        dellb3.setLayoutY(150);
        dellb3.setTextFill(Color.web("#FFFFFF"));
        dellb3.setWrapText(false);
        Button delbb3 = new Button();
        delbb3.setLayoutX(70);
        delbb3.setLayoutY(142);
        delbb3.setOnAction(e -> {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            //alert.setTitle("Confirmation Dialog");
            //alert.setHeaderText("Look, a Confirmation Dialog");
            alert.setContentText("Are you sure?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                book(data, delbb3.getText());
                delb(data, pf.book);
                pf.book = 0;
                delbb3.setText("");
                deleteu();
                deletek();
                saveu(data);
                savek(data);
            }
        });
        delbb3.setStyle("-fx-background-color: transparent;");
        delbb3.setTextFill(Color.web("#FFFFFF"));
        Label dellb4 = new Label("4 :");
        dellb4.setLayoutX(55);
        dellb4.setLayoutY(175);
        dellb4.setTextFill(Color.web("#FFFFFF"));
        dellb4.setWrapText(false);
        Button delbb4 = new Button();
        delbb4.setLayoutX(70);
        delbb4.setLayoutY(172);
        delbb4.setOnAction(e -> {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            //alert.setTitle("Confirmation Dialog");
            //alert.setHeaderText("Look, a Confirmation Dialog");
            alert.setContentText("Are you sure?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                book(data, delbb4.getText());
                delb(data, pf.book);
                pf.book = 0;
                delbb4.setText("");
                deleteu();
                deletek();
                saveu(data);
                savek(data);
            }
        });
        delbb4.setStyle("-fx-background-color: transparent;");
        delbb4.setTextFill(Color.web("#FFFFFF"));
        Label dellb5 = new Label("5 :");
        dellb5.setLayoutX(55);
        dellb5.setLayoutY(200);
        dellb5.setTextFill(Color.web("#FFFFFF"));
        dellb5.setWrapText(false);
        Button delbb5 = new Button();
        delbb5.setLayoutX(70);
        delbb5.setLayoutY(197);
        delbb5.setOnAction(e -> {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            //alert.setTitle("Confirmation Dialog");
            //alert.setHeaderText("Look, a Confirmation Dialog");
            alert.setContentText("Are you sure?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                book(data, delbb5.getText());
                delb(data, pf.book);
                pf.book = 0;
                delbb5.setText("");
                deleteu();
                deletek();
                saveu(data);
                savek(data);
            }
        });
        delbb5.setStyle("-fx-background-color: transparent;");
        delbb5.setTextFill(Color.web("#FFFFFF"));
        //l7.setPrefSize(300, 600);
        TextField deltse = new TextField("");
        deltse.setLayoutX(60);
        deltse.setLayoutY(30);
        Button delbns = new Button("Name");
        delbns.setLayoutX(60);
        delbns.setLayoutY(65);
        delbns.setOnAction(e -> {
            searchn(data, pf.index, deltse.getText(), delbb1, delbb2, delbb3, delbb4, delbb5);
        });
        Button delbaus = new Button("Author");
        delbaus.setLayoutX(110);
        delbaus.setLayoutY(65);
        delbaus.setOnAction(e -> {
            searchau(data, pf.index, deltse.getText(), delbb1, delbb2, delbb3, delbb4, delbb5);
        });
        Button delbrels = new Button("Release Date");
        delbrels.setLayoutX(165);
        delbrels.setLayoutY(65);
        delbrels.setOnAction(e -> {
            searchreld(data, pf.index, deltse.getText(), delbb1, delbb2, delbb3, delbb4, delbb5);
        });
        delbback.setOnAction(e -> {
            window.setScene(home);
            deltse.setText("");
            delbb5.setText("");
            delbb4.setText("");
            delbb3.setText("");
            delbb2.setText("");
            delbb1.setText("");
        });
        root5.getChildren().addAll(dellse, dellb1, dellb2, dellb3, dellb4, dellb5, deltse, delbns, delbaus, delbrels, delldel, delbback, delbb1, delbb2, delbb3, delbb4, delbb5);
        BackgroundImage myBI3 = new BackgroundImage(new Image(lib.class.getResource("del.jpg").toExternalForm(), 450, 260, false, false),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        root5.setBackground(new Background(myBI3));
        del = new Scene(root5, 400, 250);
        //end of delete book scene
        //////////////////////////////////////////////////////////////////////////
        //Start of read book scene
        Pane root6 = new Pane();
        Image b4 = new Image(lib.class.getResource("back.png").toExternalForm());
        ImageView ivb4 = new ImageView(b4);
        ivb4.setFitHeight(35);
        ivb4.setFitWidth(35);
        Button vibback = new Button("", ivb4);
        vibback.setStyle("-fx-background-color: transparent;");
        vibback.setLayoutX(3);
        vibback.setLayoutY(205);
        Label vilread = new Label("Read Book : ");
        vilread.setLayoutX(40);
        vilread.setLayoutY(1);
        vilread.setScaleX(2);
        vilread.setScaleY(2);
        Label vilse = new Label("  search :");
        vilse.setLayoutX(1);
        vilse.setLayoutY(35);
        vilse.setWrapText(false);
        vilse.setAlignment(Pos.BASELINE_LEFT);
        Label vilb1 = new Label("1 : ");
        vilb1.setLayoutX(50);
        vilb1.setLayoutY(100);
        vilb1.setWrapText(false);
        Button vibb1 = new Button();
        vibb1.setLayoutX(70);
        vibb1.setLayoutY(97);
        vibb1.setOnAction(e -> {
            book(data, vibb1.getText());
            read(data.get(pf.index).books.get(pf.book).posb, Integer.toString(pf.book));
            window.setScene(reader);
            window.setResizable(true);
            window.setHeight(700);
            window.setWidth(800);
            pf.loc = 0;
            //window.setFullScreen(true);

        });
        vibb1.setStyle("-fx-background-color: transparent;");
        Label vilb2 = new Label("2 : ");
        vilb2.setLayoutX(50);
        vilb2.setLayoutY(125);
        vilb2.setWrapText(false);
        Button vibb2 = new Button();
        vibb2.setLayoutX(70);
        vibb2.setLayoutY(122);
        vibb2.setOnAction(e -> {
            book(data, vibb2.getText());
            read(data.get(pf.index).books.get(pf.book).posb, Integer.toString(pf.book));
            window.setScene(reader);
            window.setResizable(true);
            window.setHeight(500);
            window.setWidth(800);
            pf.loc = 0;
            //window.setFullScreen(true);

        });
        vibb2.setStyle("-fx-background-color: transparent;");
        Label vilb3 = new Label("3 : ");
        vilb3.setLayoutX(50);
        vilb3.setLayoutY(150);
        vilb3.setWrapText(false);
        Button vibb3 = new Button();
        vibb3.setLayoutX(70);
        vibb3.setLayoutY(147);
        vibb3.setOnAction(e -> {
            book(data, vibb3.getText());
            read(data.get(pf.index).books.get(pf.book).posb, Integer.toString(pf.book));
            window.setScene(reader);
            window.setResizable(true);
            window.setHeight(500);
            window.setWidth(800);
            pf.loc = 0;
            //window.setFullScreen(true);

        });
        vibb3.setStyle("-fx-background-color: transparent;");
        Label vilb4 = new Label("4 : ");
        vilb4.setLayoutX(50);
        vilb4.setLayoutY(175);
        vilb4.setWrapText(false);
        Button vibb4 = new Button();
        vibb4.setLayoutX(70);
        vibb4.setLayoutY(172);
        vibb4.setOnAction(e -> {
            book(data, vibb4.getText());
            read(data.get(pf.index).books.get(pf.book).posb, Integer.toString(pf.book));
            window.setScene(reader);
            window.setResizable(true);
            window.setHeight(500);
            window.setWidth(800);
            pf.loc = 0;
            //window.setFullScreen(true);

        });
        vibb4.setStyle("-fx-background-color: transparent;");
        Label vilb5 = new Label("5 : ");
        vilb5.setLayoutX(50);
        vilb5.setLayoutY(200);
        vilb5.setWrapText(false);
        Button vibb5 = new Button();
        vibb5.setLayoutX(70);
        vibb5.setLayoutY(197);
        vibb5.setOnAction(e -> {
            book(data, vibb5.getText());
            read(data.get(pf.index).books.get(pf.book).posb, Integer.toString(pf.book));
            window.setScene(reader);
            window.setResizable(true);
            window.setHeight(500);
            window.setWidth(800);
            pf.loc = 0;
            //window.setFullScreen(true);

        });
        vibb5.setStyle("-fx-background-color: transparent;");
        TextField vitse = new TextField("");
        vitse.setLayoutX(60);
        vitse.setLayoutY(30);
        Button vibns = new Button("Name");
        vibns.setLayoutX(60);
        vibns.setLayoutY(65);
        vibns.setOnAction(e -> {
            searchn(data, pf.index, vitse.getText(), vibb1, vibb2, vibb3, vibb4, vibb5);
        });
        Button vibaus = new Button("Author");
        vibaus.setLayoutX(110);
        vibaus.setLayoutY(65);
        vibaus.setOnAction(e -> {
            searchau(data, pf.index, vitse.getText(), vibb1, vibb2, vibb3, vibb4, vibb5);
        });
        Button vibrels = new Button("Release Date");
        vibrels.setLayoutX(165);
        vibrels.setLayoutY(65);
        vibrels.setOnAction(e -> {
            searchreld(data, pf.index, vitse.getText(), vibb1, vibb2, vibb3, vibb4, vibb5);
        });
        vibback.setOnAction(e -> {
            window.setScene(home);
            vitse.setText("");
            vibb5.setText("");
            vibb4.setText("");
            vibb3.setText("");
            vibb2.setText("");
            vibb1.setText("");
        });
        root6.getChildren().addAll(vilse, vilb1, vilb2, vilb3, vilb4, vilb5, vitse, vibns, vibaus, vibrels, vilread, vibback, vibb1, vibb2, vibb3, vibb4, vibb5);
        BackgroundImage myBI4 = new BackgroundImage(new Image(lib.class.getResource("read.jpg").toExternalForm(), 400, 250, false, false),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        root6.setBackground(new Background(myBI4));
        view = new Scene(root6, 400, 250);
        
        //end of read book scene
        //////////////////////////////////////////////////////////////////////////
        //Start of edit book scene
        Pane root7 = new Pane();
        Image b5 = new Image(lib.class.getResource("back.png").toExternalForm());
        ImageView ivb5 = new ImageView(b5);
        ivb5.setFitHeight(35);
        ivb5.setFitWidth(35);
        Button edbback = new Button("", ivb5);
        //edbback.setBackground(ivb);
        edbback.setStyle("-fx-background-color: transparent;");
        edbback.setLayoutX(3);
        edbback.setLayoutY(205);
        edbback.setOnAction(e -> window.setScene(home));
        Label edled = new Label("Edit Book : ");
        edled.setLayoutX(40);
        edled.setLayoutY(1);
        edled.setScaleX(2);
        edled.setScaleY(2);
        Label edlse = new Label("  search :");
        edlse.setLayoutX(1);
        edlse.setLayoutY(35);
        edlse.setWrapText(false);
        edlse.setAlignment(Pos.BASELINE_LEFT);
        Label edlb1 = new Label("1 : ");
        edlb1.setLayoutX(50);
        edlb1.setLayoutY(100);
        edlb1.setWrapText(false);
        Button edbb1 = new Button();
        edbb1.setLayoutX(70);
        edbb1.setLayoutY(97);
        edbb1.setStyle("-fx-background-color: transparent;");
        edbb1.setOnAction(e -> {
            book(data, edbb1.getText());
            window.setScene(edit2);
        });
        Label edlb2 = new Label("2 : ");
        edlb2.setLayoutX(50);
        edlb2.setLayoutY(125);
        edlb2.setWrapText(false);
        Button edbb2 = new Button();
        edbb2.setLayoutX(70);
        edbb2.setLayoutY(122);
        edbb2.setStyle("-fx-background-color: transparent;");
        edbb2.setOnAction(e -> {
            book(data, edbb2.getText());
            window.setScene(edit2);
        });
        Label edlb3 = new Label("3 : ");
        edlb3.setLayoutX(50);
        edlb3.setLayoutY(150);
        edlb3.setWrapText(false);
        Button edbb3 = new Button();
        edbb3.setLayoutX(70);
        edbb3.setLayoutY(142);
        edbb3.setStyle("-fx-background-color: transparent;");
        edbb3.setOnAction(e -> {
            book(data, edbb3.getText());
            window.setScene(edit2);
        });
        Label edlb4 = new Label("4 : ");
        edlb4.setLayoutX(50);
        edlb4.setLayoutY(175);
        edlb4.setWrapText(false);
        Button edbb4 = new Button();
        edbb4.setLayoutX(70);
        edbb4.setLayoutY(172);
        edbb4.setStyle("-fx-background-color: transparent;");
        edbb4.setOnAction(e -> {
            book(data, edbb4.getText());
            window.setScene(edit2);
        });
        Label edlb5 = new Label("5 : ");
        edlb5.setLayoutX(50);
        edlb5.setLayoutY(200);
        edlb5.setWrapText(false);
        Button edbb5 = new Button();
        edbb5.setLayoutX(70);
        edbb5.setLayoutY(197);
        edbb5.setStyle("-fx-background-color: transparent;");
        edbb5.setOnAction(e -> {
            book(data, edbb5.getText());
            window.setScene(edit2);
        });
        TextField edtse = new TextField("");
        edtse.setLayoutX(60);
        edtse.setLayoutY(30);
        Button edbns = new Button("Name");
        edbns.setLayoutX(60);
        edbns.setLayoutY(65);
        edbns.setOnAction(e -> {
            searchn(data, pf.index, edtse.getText(), edbb1, edbb2, edbb3, edbb4, edbb5);
        });
        Button edbaus = new Button("Author");
        edbaus.setLayoutX(110);
        edbaus.setLayoutY(65);
        edbaus.setOnAction(e -> {
            searchau(data, pf.index, edtse.getText(), edbb1, edbb2, edbb3, edbb4, edbb5);
        });
        Button edbrels = new Button("Release Date");
        edbrels.setLayoutX(165);
        edbrels.setLayoutY(65);
        edbrels.setOnAction(e -> {
            searchreld(data, pf.index, edtse.getText(), edbb1, edbb2, edbb3, edbb4, edbb5);
        });
        root7.getChildren().addAll(edlse, edlb1, edlb2, edlb3, edlb4, edlb5, edtse, edbns, edbaus, edbrels, edled, edbback, edbb1, edbb2, edbb3, edbb4, edbb5);
        BackgroundImage myBI5 = new BackgroundImage(new Image(lib.class.getResource("editb.jpg").toExternalForm(), 450, 260, false, false),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        root7.setBackground(new Background(myBI5));
        edit = new Scene(root7, 400, 250);
        //end of edit book scene
        //////////////////////////////////////////////////////////////////////////
        //Start of reader scene
        Pane root9 = new Pane();
        Image next = new Image(lib.class.getResource("next.png").toExternalForm());
        ImageView nextiv = new ImageView(next);
        nextiv.setFitHeight(35);
        nextiv.setFitWidth(35);
        Button prebne = new Button("", nextiv);
        prebne.setLayoutX(450);
        prebne.setLayoutY(450);
        prebne.setStyle("-fx-background-color: transparent;");
        Image pre = new Image(lib.class.getResource("previous.png").toExternalForm());
        ImageView preiv = new ImageView(pre);
        preiv.setFitHeight(35);
        preiv.setFitWidth(35);
        Button prebpre = new Button("", preiv);
        prebpre.setLayoutX(400);
        prebpre.setLayoutY(450);
        prebpre.setStyle("-fx-background-color: transparent;");

        Image b6 = new Image(lib.class.getResource("back.png").toExternalForm());
        ImageView ivb6 = new ImageView(b6);
        ivb5.setFitHeight(35);
        ivb5.setFitWidth(35);
        Button prebback = new Button("", ivb6);
        prebback.setStyle("-fx-background-color: transparent;");
        prebback.setLayoutX(3);
        prebback.setLayoutY(205);
        prebback.setOnAction(e -> {
            window.setScene(view);
            window.setHeight(400);
            window.setWidth(370);
            window.setResizable(false);
            deletedir(new File(Integer.toString(pf.book)));
            pf.loc = 0;
            pf.pages = 0;
            pf.book = 0;
        });
        main = new Image(new File(pf.book + "/" + pf.loc + ".png").toURI().toString());
        iv8 = new ImageView(main);
        iv8.setFitHeight(400);
        iv8.setFitWidth(400);
        iv8.setLayoutX(200);
        iv8.setLayoutY(20);
        prebne.setOnAction(e -> {
            nextp(main, iv8);
        });
        prebpre.setOnAction(e -> {
            prep(main, iv8);
        });
        //v.setSpacing(10);
        //v.setPadding( new Insets(10));
        root9.getChildren().addAll(prebne, prebpre, prebback, iv8);
        reader = new Scene(root9, 400, 250);
        //End of reader scene
        //////////////////////////////////////////////////////////////////////////
        //Start of edit2 books scene
        Pane root10 = new Pane();
        Image b7 = new Image(lib.class.getResource("back.png").toExternalForm());
        ImageView ivb7 = new ImageView(b7);
        ivb7.setFitHeight(35);
        ivb7.setFitWidth(35);
        Button ed2bback = new Button("", ivb7);
        //ed2bback.setBackground(ivb);
        ed2bback.setStyle("-fx-background-color: transparent;");
        ed2bback.setLayoutX(5);
        ed2bback.setLayoutY(205);

        //Label ed2lab = new Label("");
        //ed2lab.setLayoutX(30);
        //ed2lab.setLayoutY(5);
        //ed2lab.setScaleX(2);
        //ed2lab.setScaleY(2);
        Label ed2lbn = new Label("Book Name");
        ed2lbn.setLayoutX(5);
        ed2lbn.setLayoutY(40);
        TextField ed2tbn = new TextField();
        ed2tbn.setLayoutX(80);
        ed2tbn.setLayoutY(35);

        Label ed2lau = new Label("Author");
        ed2lau.setLayoutX(5);
        ed2lau.setLayoutY(80);
        TextField ed2tau = new TextField();
        ed2tau.setLayoutX(80);
        ed2tau.setLayoutY(75);
        Label ed2lrel = new Label("Release Date");
        ed2lrel.setLayoutX(5);
        ed2lrel.setLayoutY(120);
        TextField ed2trel = new TextField();
        ed2trel.setLayoutX(80);
        ed2trel.setLayoutY(115);
        Label ed2lpos = new Label("Book Path");
        ed2lpos.setLayoutX(5);
        ed2lpos.setLayoutY(160);
        TextField ed2tpos = new TextField();
        ed2tpos.setLayoutX(80);
        ed2tpos.setLayoutY(155);
        ed2tpos.setEditable(false);
        Button ed2bab = new Button("Edit Book");
        ed2bab.setLayoutX(120);
        ed2bab.setLayoutY(200);
        Image browse2 = new Image(lib.class.getResource("browse.png").toExternalForm());
        ImageView iv9 = new ImageView(browse2);
        iv9.setFitHeight(30);
        iv9.setFitWidth(30);
        Button ed2bbr = new Button("", iv9);
        ed2bbr.setStyle("-fx-background-color: transparent;");
        ed2bbr.setLayoutX(220);
        ed2bbr.setLayoutY(149);
        Label ed2lerr = new Label();
        ed2lerr.setLayoutX(80);
        ed2lerr.setLayoutY(230);
        ed2lerr.setTextFill(Color.web("#990033"));
        FileChooser ed2fbook = new FileChooser();
        //Exception except = new Exception("Encapsulated");

        ed2fbook.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        ed2bbr.setOnAction(e -> {
            try {
                File selectedFile2 = ed2fbook.showOpenDialog(window);
                ed2tpos.setText(selectedFile2.getAbsolutePath());
            } catch (Exception ex) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Wrong Extension");
                alert.setHeaderText(null);
                alert.setContentText("Sorry PDF Only");
                alert.showAndWait();
                //System.out.println("shit");
            }

        });
        ed2bab.setOnAction(e -> {
            if (!ed2tpos.getText().isEmpty() && !ed2tbn.getText().isEmpty()) {
                window.setScene(home);
                data.get(pf.index).books.get(pf.book).author = ed2tau.getText();
                data.get(pf.index).books.get(pf.book).name = ed2tbn.getText();
                data.get(pf.index).books.get(pf.book).posb = ed2tpos.getText();
                ed2tbn.setText("");
                ed2trel.setText("");
                ed2tau.setText("");
                ed2tpos.setText("");
                ed2lerr.setText("");
                pf.book = 0;
                deleteu();
                deletek();
                saveu(data);
                savek(data);
            } else {
                ed2lerr.setText("Book's Name and Path Are Requied");
            }
        });
        ed2bback.setOnAction(e -> {
            ed2tbn.setText("");
            ed2trel.setText("");
            ed2tau.setText("");
            ed2tpos.setText("");
            ed2lerr.setText("");
            window.setScene(home);
        });
        BackgroundImage myBI7 = new BackgroundImage(new Image(lib.class.getResource("editb.jpg").toExternalForm(), 400, 250, false, false),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        root10.setBackground(new Background(myBI7));
        root10.getChildren().addAll(ed2lbn, ed2lau, ed2lrel, ed2lpos, ed2tbn, ed2tau, ed2trel, ed2tpos, ed2bbr, ed2bab, ed2bback, ed2lerr);
        edit2 = new Scene(root10, 400, 250);
        //end of edit2 books scene
        //////////////////////////////////////////////////////////////////////////
        //Start video shit
        Pane rot1 = new Pane();
        Image b19 = new Image(lib.class.getResource("back.png").toExternalForm());
        ImageView ivb19 = new ImageView(b19);
        ivb19.setFitHeight(35);
        ivb19.setFitWidth(35);
        Button vidback = new Button("", ivb19);
        //ed2bback.setBackground(ivb);
        vidback.setStyle("-fx-background-color: transparent;");
        vidback.setLayoutX(10);
        vidback.setLayoutY(370); 
        vidback.setOnAction(e-> window.setScene(home));
        //ImageView im=new ImageView("javafxapplication59/incre1.jpg");
        BackgroundImage im = new BackgroundImage(new Image(lib.class.getResource("incre1.jpg").toExternalForm(), 650, 410, false, false),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        rot1.setBackground(new Background(im));
        ImageView inc1=new ImageView(new Image(lib.class.getResource("play.png").toExternalForm(),60,60,true,true));
        Button incbtn1=new Button("",inc1);
        incbtn1.setLayoutX(550);
        incbtn1.setLayoutY(60);
        incbtn1.setBackground(Background.EMPTY);
        incbtn1.setOnAction(e-> window.setScene(scene2));       
        ImageView inc2=new ImageView(new Image(lib.class.getResource("play.png").toExternalForm(),60,60,true,true));
        Button incbtn2=new Button("",inc2);
        incbtn2.setLayoutX(550);
        incbtn2.setLayoutY(160);
        incbtn2.setBackground(Background.EMPTY);
        incbtn2.setOnAction(e-> window.setScene(scene3));        
        ImageView inc3=new ImageView(new Image(lib.class.getResource("play.png").toExternalForm(),60,60,true,true));
        Button incbtn3=new Button("",inc3);
        incbtn3.setLayoutX(550);
        incbtn3.setLayoutY(260);
        incbtn3.setBackground(Background.EMPTY);
        incbtn3.setOnAction(e-> window.setScene(scene4));           
        rot1.getChildren().addAll(incbtn1,incbtn2,incbtn3,vidback); 
        scene1= new Scene(rot1, 650, 410);
        //End of video shit
        //////////////////////////////////////////////////////////////////////////
        Group rot2=new Group();
        ImageView arrow=new ImageView(new Image(lib.class.getResource("arrow2.png").toExternalForm(),35,35,true,true));
        Button arrowbtn1=new Button("",arrow);
        arrowbtn1.setLayoutX(30);
        arrowbtn1.setLayoutY(360);
        arrowbtn1.setRotate(180);
        arrowbtn1.setBackground(Background.EMPTY);
        arrowbtn1.setOnAction(e-> window.setScene(scene1));
       
        WebView webview1 = new WebView();
        webview1.setLayoutX(0);
        webview1.setLayoutY(-45);
        webview1.setPrefSize(650,455);
        webview1.getEngine().load("https://drive.google.com/open?id=14Gorjlg9INJmBObf_0lw0F_i7yokZoOs");
        rot2.getChildren().addAll(webview1,arrowbtn1);  
        scene2= new Scene(rot2,650,410); 
        //////////////////////////////////////////////////////////////////////////
        Group rot3=new Group();
        ImageView arrow1=new ImageView(new Image(lib.class.getResource("arrow2.png").toExternalForm(),35,35,true,true));
        Button arrowbtn2=new Button("",arrow1);
        arrowbtn2.setLayoutX(30);
        arrowbtn2.setLayoutY(360);
        arrowbtn2.setRotate(180);
        arrowbtn2.setBackground(Background.EMPTY);
        arrowbtn2.setOnAction(e-> window.setScene(scene1));
       
        WebView webview2 = new WebView();
        webview2.setLayoutX(0);
        webview2.setLayoutY(-45);
        webview2.setPrefSize(690,475);
        webview2.getEngine().load("https://drive.google.com/file/d/1siFFPb5ILu2KoMuLbu0vPlWPYPtCVm4_/view?usp=sharing");
        rot3.getChildren().addAll(webview2,arrowbtn2);  
        scene3= new Scene(rot3,650,410);
        //////////////////////////////////////////////////////////////////////////
        Group rot4=new Group();
        ImageView arrow2=new ImageView(new Image(lib.class.getResource("arrow2.png").toExternalForm(),35,35,true,true));
        Button arrowbtn3=new Button("",arrow2);
        arrowbtn3.setLayoutX(30);
        arrowbtn3.setLayoutY(360);
        arrowbtn3.setRotate(180);
        arrowbtn3.setBackground(Background.EMPTY);
        arrowbtn3.setOnAction(e-> window.setScene(scene1));
       
        WebView webview3 = new WebView();
        webview3.setLayoutX(0);
        webview3.setLayoutY(-45);
        webview3.setPrefSize(700,475);
        webview3.getEngine().load("https://drive.google.com/file/d/1uvSViBrWqdcIPa4jDfBRTneTem9rB5MG/view?usp=sharing");
        rot4.getChildren().addAll(webview3,arrowbtn3);  
        scene4= new Scene(rot4,650,410); 
        //////////////////////////////////////////////////////////////////////////
        primaryStage.setTitle("Library");
        primaryStage.setScene(wel);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");

        launch(args);
    }

}
