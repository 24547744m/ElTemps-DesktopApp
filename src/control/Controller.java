package control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;
import org.xml.sax.SAXException;
import pojos.Time;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    // iconos del tiempo openweathermap => http://openweathermap.org/weather-conditions
    //link para agregar imagenes al imageView http://stackoverflow.com/questions/15661500/javafx-listview-item-with-an-image-button
    // http://docs.oracle.com/javafx/2/ui_controls/list-view.htm#CEGGEDBF http://java-buddy.blogspot.com.es/2013/05/implement-javafx-listview-for-custom.html

    private List<Time> objTimesList;
    private List<String> stringTimesList;
    private ObservableList<String> observableTimesList;

//    private Location objLocation;
    private XMLParser parser;

    @FXML private ListView<String> lvItems;

    public void initialize() throws SAXException, IOException, ParserConfigurationException {
        parser = new XMLParser();
        objTimesList = parser.getTimesList();
        if (objTimesList.size() != 0){
            setStringTimes(objTimesList, false);//
            observableTimesList = FXCollections.observableList(stringTimesList);//Seteando la Lista de Strings en el objeto ObservableList

            lvItems.setItems(observableTimesList);
            /*** Para tratar cada row del ListView ***/
            lvItems.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
                @Override
                public ListCell<String> call(ListView<String> param) {
                    return new TimeListCell();
                }
            });
            /*****************************************/
//            lvItems.autosize();
        }
    }

    /**
     * Obtiene las fechas de cada objeto Time y las guarda en una lista de Strings
     * opcion de imprimir 2do parametro
     * */
    private void setStringTimes(List<Time> times, boolean print){
        stringTimesList = new ArrayList<>();
        Time time;
        for (int j = 0; j < times.size(); j++){
            time = times.get(j);
            stringTimesList.add(time.getIdImage()+"#" + time.getDateFrom() + " - " + time.getDateTo());

            if (print)
                System.out.println(time.getIdImage() + " " + time.getDateFrom() + " " + time.getTemperature());
        }
    }

//    private void setListViewItems(List<Time> objTimes){
//        Time time;
//        Item item;
//        for (int i = 0; i < objTimes.size(); i++){
//            time = objTimes.get(i);
//            item = new Item(new ImageView("img/" + time.getIdImage() + ".png"), time.getDateFrom() + " - " + time.getDateTo());
//            this.itemsList.add(item);
//        }
//    }


    private ImageView newImageView(String idImage){
        ImageView imageView = new ImageView("img/" + idImage + ".png");
        System.out.println("id: " + idImage);
        return imageView;
    }


    /**
     * Clase que trata cada row del ListView
     * */
    private class TimeListCell extends ListCell<String>{

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null){
                setText(item.substring( (item.indexOf("#")+1), item.length() ));
                setGraphic( newImageView(item.substring(0, item.indexOf("#"))) );
            }

        }//END METHOD
    }


}
