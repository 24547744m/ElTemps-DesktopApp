package control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import org.xml.sax.SAXException;
import pojos.Item;
import pojos.Location;
import pojos.Time;

import javax.swing.text.html.ImageView;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private List<Time> objTimes;
//    private Location objLocation;
//    private ObservableList<Time> observableList;
    private List<Item> itemsList;
    private ObservableList<Item> itemObservableList;
    private XMLParser parser;

    @FXML private ListView<Time> lvItems;
//    @FXML private

    public void initialize() throws ParserConfigurationException, SAXException, IOException {
        itemsList = new ArrayList<>();
        System.out.println("HOLAAAAAAAAAAAAAa");
        objTimes = getXMLTimes();


//        observableList = FXCollections.observableList(objTimes);
//        lvItems.setItems(observableList);
    }


    private void setListViewItems(){
        Time time;
        Item item;
        for (int i = 0; i < objTimes.size(); i++){
            time = objTimes.get(i);
//            item = new Item(new ImageView(), time);
//            itemsList.add();

        }


    }

    private List<Time> getXMLTimes() throws IOException, SAXException, ParserConfigurationException {
        parser = new XMLParser();
        this.objTimes = parser.getTimesList();
        return this.objTimes;
    }

    private ImageView newImageView(){
//        ImageView iv = new ImageView();



        return null;
    }



}
