package abap_test;
 /*
Задача: Написать программу, которая на основании запроса данных с сайта
http://www.cbr.ru/scripts/XML_daily.asp определит самую дорогую и самую
дешевую валюту (с максимальной и минимальной стоимостью 1-ой единицы в
рублях)
 */

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;


public class MaxСurrency {


    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, ParseException {

        String cheapCurrency = null;
        double min = 0.0;
        String expensiveCurrency = null;
        double max = 0.0;

        DecimalFormat df = new DecimalFormat();
        DecimalFormatSymbols sfs = new DecimalFormatSymbols();
        sfs.setDecimalSeparator(',');
        df.setDecimalFormatSymbols(sfs);


        URL url = new URL("http://www.cbr.ru/scripts/XML_daily.asp");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(url.openStream());

        NodeList root = doc.getChildNodes();
        for (int k = 0; k < root.getLength(); k++) {
            if (root.item(k).getNodeName().equals("ValCurs")) {
                NodeList currencies = root.item(k).getChildNodes();
                for (int i = 0; i < currencies.getLength(); i++) {
                    Node currency = currencies.item(i);
                    if (currency.getNodeName().equals("Valute")) {
                        NodeList currencyProperties = currency.getChildNodes();

                        String charCode = null;
                        int nominal = 0;
                        double value = 0;

                        for (int j = 0; j < currencyProperties.getLength(); j++) {
                            if (currencyProperties.item(j).getNodeName().equals("CharCode")) {
                                charCode = currencyProperties.item(j).getTextContent();
                            }

                            if (currencyProperties.item(j).getNodeName().equals("Nominal")) {
                                nominal = Integer.parseInt(currencyProperties.item(j).getTextContent());
                            }

                            if (currencyProperties.item(j).getNodeName().equals("Value")) {
                                value = df.parse(currencyProperties.item(j).getTextContent()).doubleValue();
                            }
                        }

                        double cost = value/nominal;
                        if (cost > max){
                            max = cost;
                            expensiveCurrency = charCode;
                        }

                        if (i == 0 || cost < min) {
                            min = cost;
                            cheapCurrency = charCode;
                        }
                    }
                }
            }
        }

        System.out.println("The most expensive currency " + expensiveCurrency);
        System.out.println("Cheapest currency " + cheapCurrency);
    }
}