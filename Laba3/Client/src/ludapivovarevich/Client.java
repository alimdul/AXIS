package ludapivovarevich;

import ludapivovarevich.controller.Controller;
import ludapivovarevich.view.MyFrame;

import java.awt.*;

public class Client {
    public static void main(String args[]){
        Controller controller = new Controller();
        MyFrame frame = new MyFrame("Справочник по видам цветов",new Dimension(710,600), controller);
        frame.init();
    }
}
