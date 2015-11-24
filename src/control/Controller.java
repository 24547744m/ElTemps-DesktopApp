package control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import org.xml.sax.SAXException;
import pojos.Item;
import pojos.Time;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    // iconos del tiempo openweathermap => http://openweathermap.org/weather-conditions 
    //link para agregar imagenes al imageView http://stackoverflow.com/questions/15661500/javafx-listview-item-with-an-image-button
    // http://docs.oracle.com/javafx/2/ui_controls/list-view.htm#CEGGEDBF http://java-buddy.blogspot.com.es/2013/05/implement-javafx-listview-for-custom.html


    private List<Time> objTimes;
//    private Location objLocation;
    private List<Item> itemsList;//Lista de objetos de tipo -> Item(ImageView, Time)
    private ObservableList<Item> itemObservableList;
    private XMLParser parser;

    @FXML private ListView<Item> lvItems;

    public void initialize() throws SAXException, IOException, ParserConfigurationException {
        itemsList = new ArrayList<Item>();
        parser = new XMLParser();
        this.objTimes = parser.getTimesList();
//        printTimes(objTimes);
        if (objTimes != null){
            setListViewItems(objTimes);
            itemObservableList = FXCollections.observableList(itemsList);//
            lvItems.setItems(itemObservableList);
            lvItems.autosize();
        }
    }


    private void printTimes(List<Time> objTimes){
        Time time;
        for (int i = 0; i < objTimes.size(); i++){
            time = objTimes.get(i);
            System.out.println(time.getIdImage() + " " + time.getDateFrom() + " " + time.getTemperature());
        }
    }

    private void setListViewItems(List<Time> objTimes){
        Time time;
        Item item;
        for (int i = 0; i < objTimes.size(); i++){
            time = objTimes.get(i);
            item = new Item(new ImageView("img/" + time.getIdImage() + ".png"), time.getDateFrom() + " - " + time.getDateTo());
            this.itemsList.add(item);
        }
    }





}
