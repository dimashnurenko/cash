package dmitry.shnurenko.cash;

import dmitry.shnurenko.cash.view.mainwindow.MainWindow;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * The class represents entry point of application.
 *
 * @author Dmitry Shnurenko.
 */
public class Run {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");

        MainWindow mainWindow = context.getBean(MainWindow.class);

        mainWindow.show();
    }
}
