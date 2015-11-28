package control;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import org.xml.sax.SAXException;
import pojos.Time;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class Controller {
    public Label lblWindDirection;
    public Label lblWindSpeed;
    public Label lblHumidity;
    public Label lblClouds;
    public Label lblTemperature;
    public ImageView ivLarge;
    public Label lblTemperatureLarge;

    // iconos del tiempo openweathermap => http://openweathermap.org/weather-conditions
    //link para agregar imagenes al imageView http://stackoverflow.com/questions/15661500/javafx-listview-item-with-an-image-button
    // http://docs.oracle.com/javafx/2/ui_controls/list-view.htm#CEGGEDBF http://java-buddy.blogspot.com.es/2013/05/implement-javafx-listview-for-custom.html

    private List<Time> objTimesList;
    private List<String> stringTimesList;
    private ObservableList<Time> observableTimesList;

//    private Location objLocation;
    private XMLParser parser;

    @FXML private ListView<Time> lvItems;

    public void initialize() throws SAXException, IOException, ParserConfigurationException {
//        lblHumidity.set

        parser = new XMLParser();
        objTimesList = parser.getTimesList();
        if (objTimesList.size() != 0){
//            setStringTimes(objTimesList, false);//
            observableTimesList = FXCollections.observableList(objTimesList);//Seteando la Lista de Strings en el objeto ObservableList

            lvItems.setItems(observableTimesList);
            /*** Para tratar cada row del ListView ***/
            lvItems.setCellFactory(new Callback<ListView<Time>, ListCell<Time>>() {
                @Override
                public ListCell<Time> call(ListView<Time> param) {
                    return new TimeListCell();
                }
            });
            /*****************************************/

            lvItems.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Time>() {
                @Override
                public void changed(ObservableValue<? extends Time> observable, Time oldValue, Time newValue) {
//                    System.out.println(observable.getValue().getDateFrom());
                    Time time = observable.getValue();
                    String temp = time.getTemperature();
                    lblTemperatureLarge.setText(temp.substring(0, temp.indexOf("C")+1));
                    lblWindDirection.setText(time.getWindDirection());
                    lblWindSpeed.setText(time.getWindSpeed());
                    lblClouds.setText(time.getClouds());
                    lblHumidity.setText(time.getHumidity());
                    lblTemperature.setText(time.getTemperature());
//                    ivLarge = getImageView(time.getIdImage());
                    ivLarge.setImage(new Image("img/" + time.getIdImage() + ".png"));
                }
            });

//            lvItems.autosize();
        }
    }

    /**
     * Devuelve un ImageView recibiendo como parámetro el id de la imagen
     * */
    private ImageView getImageView(String idImage){
        return new ImageView("img/" + idImage + ".png");
    }

    public void closeProgram(ActionEvent ae) {
        Platform.exit();

    }


    /**
     * Clase que trata cada row del ListView
     * */
    private class TimeListCell extends ListCell<Time>{

        @Override
        protected void updateItem(Time time, boolean empty) {
            super.updateItem(time, empty);
            if (time != null){
//                System.out.println(time.getDateFrom());
                setText(time.getDateFrom());
                setGraphic(getImageView(time.getIdImage()));
            }

        }//END METHOD



    }


}
