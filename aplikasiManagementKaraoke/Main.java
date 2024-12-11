package aplikasiManagementKaraoke;


import aplikasiManagementKaraoke.config.Database;
import aplikasiManagementKaraoke.views.Menus;
import aplikasiManagementKaraoke.views.TerminalView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "aplikasiManagementKaraoke")
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        Menus terminalView = context.getBean(TerminalView.class);
        terminalView.run();
    }

    @Bean
    Database database() {
        Database database = new Database("management_karaoke", "root", "", "localhost", "3306");
        database.setup();
        return database;
    }
}