package myPack;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.MalformedURLException;

public class Controller {
    @FXML
    private ImageView imgv;
    Image img;
    Double h,w;
    // declared buttons
    @FXML
    Button zom = new Button();
    @FXML Button save = new Button();
    @FXML Button dlt = new Button();
    @FXML Button init = new Button();
    @FXML Button rtt = new Button();

    File file;
    Label name = new Label();
    private String filepath;
    Glow glow = new Glow();
    private boolean zoomactive = false;

    @FXML
    void openFile() throws MalformedURLException {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image files", "*.png"));
        file = fc.showOpenDialog(new Stage());
        img = new Image(file.toURI().toURL().toExternalForm());
        Main.mystaticimg=img;
        zom.setDisable(false);
        save.setDisable(false);
        dlt.setDisable(false);
        init.setDisable(false);
        rtt.setDisable(false);
/*
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(-1);
        bhView.setEffect(colorAdjust);

        Main.stage.setScene(new Scene(new VBox(colorView, bhView)));*/
        imgv.setImage(img);
         h = imgv.getFitHeight();
         w = imgv.getFitWidth();
        imgv.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {

                if (!zoomactive) {
                    imgv.setFitHeight(img.getHeight());
                    imgv.setFitWidth(img.getWidth());
                    System.out.println("not zooming");
                    zoomactive=true;
                }else{
                    imgv.setFitWidth(w);
                    imgv.setFitHeight(h);

                    System.out.println("zooming");
                    zoomactive=false;
                }
            }
        });
    }
    @FXML
public void zoom(){
        zoomactive=true;
        imgv.setFitWidth(imgv.getFitWidth()*2);
        imgv.setFitHeight(imgv.getFitHeight()*2);
        if(imgv.getFitWidth()<4000)imgv.setFitWidth(imgv.getFitWidth()*2);
    if(imgv.getFitHeight()<4000)imgv.setFitHeight(imgv.getFitHeight()*2);
if(imgv.getFitHeight()==1000)
     zom.setDisable(true);
}
public void delete(){
        file.delete();
        imgv.setImage(null);
    zom.setDisable(true);
    save.setDisable(true);
    dlt.setDisable(true);
    init.setDisable(true);
    rtt.setDisable(true);
}
public void initilize(){
        imgv.setFitHeight(h);
        imgv.setFitWidth(w);
}
public void rotate(){
    if( imgv!= null){
        imgv.setRotate(imgv.getRotate()-90);
    }

}

        public void save(){
        File outputFile = file;
        BufferedImage bImage = SwingFXUtils.fromFXImage(imgv.snapshot(null,null), null);
        try{
            ImageIO.write(bImage,"png",outputFile);
            System.out.println("saved");
        }catch(Exception ignored){
            System.out.println("nothing happened");

    }
}
public void glow(){
    Glow glow = new Glow();
    glow.setLevel(0.9);
    imgv.setPreserveRatio(true);
    imgv.setEffect(glow);

}
public void filtr(){
    SepiaTone sepiaTone = new SepiaTone();
    sepiaTone.setLevel(0.3);
    imgv.setPreserveRatio(true);
    imgv.setEffect(sepiaTone);
}
    public void black_white(){
        ColorAdjust grayscale = new ColorAdjust();
        grayscale.setSaturation(-1);
        imgv.setEffect(grayscale);
    }

}
