import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            Login login = new Login();

            login.setVisible(true);
            login.setDefaultCloseOperation(3);
            try{
                LocalDateTime current = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                String formatted = current.format(formatter);
                login.clock.setFont(new Font("Montserrat",Font.BOLD,27));
                login.clock.setForeground(Color.BLUE);
                while (true){
                    Calendar calendar = Calendar.getInstance();
                    String hour = (calendar.getTime().getHours() > 9)  ? "" + calendar.getTime().getHours() + "" : "0" + calendar.getTime().getHours() ;
                    String minute = (calendar.getTime().getMinutes() > 9) ?
                            "" + calendar.getTime().getMinutes() + ""
                            : "0" + calendar.getTime().getMinutes();
                    String second = (calendar.getTime().getSeconds() > 9) ?
                            "" + calendar.getTime().getSeconds() + ""
                            : "0" + calendar.getTime().getSeconds();
                    login.clock.setText(formatted + "      "+ hour + ":" + minute + ":" + second);
                    Thread.sleep(1000);

                }
            } catch(InterruptedException e ) {
                e.printStackTrace();
            }


        } catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
