package control;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import pojos.Location;
import pojos.Time;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dam on 12/11/15.
 */
public class XMLParser {

    private File file;
    private DocumentBuilderFactory dbf;
    private DocumentBuilder db;
    private Document doc;

    public XMLParser() throws ParserConfigurationException, IOException, SAXException {
        this.file = new File("src/xml/forecast.xml");
        dbf = DocumentBuilderFactory.newInstance();
        db = dbf.newDocumentBuilder();
        doc = db.parse(file);
        doc.getDocumentElement().normalize();//Formata el XML en caso que esté con espacios
    }
    public XMLParser(File file) throws ParserConfigurationException, IOException, SAXException {
        this.file = file;
        dbf = DocumentBuilderFactory.newInstance();
        db = dbf.newDocumentBuilder();
        doc = db.parse(file);
        doc.getDocumentElement().normalize();//Formata el XML en caso que esté con espacios
    }

    /**
     * Obtiene la lista de nodos <time></time>
     * @return nodeList
     * @link {NodeList}
     * */
    private NodeList getTimesNodeList(){
        return doc.getElementsByTagName("time");
    }
    /**
     * Obtiene la lista de nodos contenidos en <location></location>
     * @return nodeList
     * @link {NodeList}
     * */
    private NodeList getLocationNodeList(){
        return doc.getElementsByTagName("location").item(0).getChildNodes();
    }

    public List<Time> getTimesList(){
        List<Time> timeList = new ArrayList<>();
        Time time;
        NodeList times = getTimesNodeList();
        Element element;

        for (int i = 0; i < times.getLength(); i ++){
            time = new Time();
            if (times.item(i).getNodeType() == Node.ELEMENT_NODE){
                element = (Element) times.item(i);
                if (element.getTagName().equals("time")){
                    time.setDateFrom(element.getAttribute("from"));
                    time.setDateTo(element.getAttribute("to") );
                }
                if (element.hasChildNodes()){
                    NodeList timeChilds = element.getChildNodes();
                    Element element2;
                    for (int j = 0; j < timeChilds.getLength(); j++){
                        element2 = (Element) timeChilds.item(j);
                        if(element2.getTagName().equalsIgnoreCase("symbol"))
                            time.setIdImage(element2.getAttribute("var"));
                        if(element2.getTagName().equalsIgnoreCase("windirection"))
                            time.setWindDirection(element2.getAttribute("name") + " - " + element2.getAttribute("deg") + " " + element2.getAttribute("code"));
                        if(element2.getTagName().equalsIgnoreCase("windspeed")){
                            String kph = mpsToKph(element2.getAttribute("mps"));//obteniendo el formato convertido de mps to kph
                            time.setWindSpeed(kph + "kph");
                        }
                        if(element2.getTagName().equalsIgnoreCase("temperature"))
                            time.setTemperature(element2.getAttribute("value") + " ºC\tmin: " + element2.getAttribute("min") + "\tmax: " + element2.getAttribute("max"));
                        if(element2.getTagName().equalsIgnoreCase("humidity"))
                            time.setHumidity(element2.getAttribute("value") + element2.getAttribute("unit"));
                    }
                    System.out.println();

                }
            }//END IF (ELEMENT)

            timeList.add(time);//Agregando cada time al List<Time>
        }//END FOR

        return timeList;
    }

    public Location getLocation(){
        NodeList nLocation = getLocationNodeList();
        Location location = new Location();
        Element element;

        for (int i = 0; i < nLocation.getLength(); i ++){
            if (nLocation.item(i).getNodeType() == Node.ELEMENT_NODE) {
                element = (Element) nLocation.item(i);
                if (element.getTagName().equals("name"))
                    location.setCity(element.getTextContent());
                if (element.getTagName().equals("country"))
                    location.setCountry(element.getTextContent());
                if (element.getTagName().equals("location"))
                    location.setLocation("latitude: " + element.getAttribute("latitude") + "  longitude: " + element.getAttribute("longitude"));
            }
        }//END FOR

        return location;
    }

    /**
     * Convierte Metros por segundo a Kilómetros por hora
     * @param mps {@link String}
     * @return kph - {@link String}
     * */
    private String mpsToKph(String mps) {
        double rs = (Double.parseDouble(mps) * 2.6);
        return String.format("%.2f", rs);
    }



}
