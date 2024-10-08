package pe.edu.upeu.calcfx;

import atlantafx.base.theme.PrimerLight;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CalcFxApplication extends Application {
<<<<<<< HEAD
	ConfigurableApplicationContext configurableApplicationContext;
	Parent parent;
=======
		ConfigurableApplicationContext configurableApplicationContext;
		Parent parent;
>>>>>>> 968eea5a0e24814f95c05c4ef5da9e3a700872a3
	public static void main(String[] args) {
		System.out.println("Hola estas funcionando");
		launch(args);
		//SpringApplication.run(CalcFxApplication.class, args);
	}

	@Override
	public void init() throws Exception {

		SpringApplicationBuilder builder = new
				SpringApplicationBuilder(CalcFxApplication.class);
		builder.application().setWebApplicationType(WebApplicationType.NONE);
		configurableApplicationContext =
				builder.run(getParameters().getRaw().toArray(new String[0]));


<<<<<<< HEAD
		//FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/calc.fxml"));
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/tictactoe.fxml"));
=======

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/calc.fxml"));
>>>>>>> 968eea5a0e24814f95c05c4ef5da9e3a700872a3
		fxmlLoader.setControllerFactory(configurableApplicationContext::getBean);
		parent= fxmlLoader.load();
	}

	@Override
	public void start(Stage stage) throws Exception {
		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getBounds();
<<<<<<< HEAD
		//Scene scene = new Scene(parent,bounds.getWidth()/2, bounds.getHeight()/2);
		Scene scene = new Scene(parent);
		// Obtener el tamaño preferido del contenido
		double preferredWidth = parent.prefWidth(-1); // -1 significa que no se restringe el cálculo
		double preferredHeight = parent.prefHeight(-1);
		// Configurar el tamaño mínimo basado en el tamaño preferido
=======
	//Scene scene = new Scene(parent,bounds.getWidth()/2, bounds.getHeight()/2);
		Scene scene = new Scene(parent);
	// Obtener el tamaño preferido del contenido
		double preferredWidth = parent.prefWidth(-1); // -1 significa que no se restringe el cálculo
		double preferredHeight = parent.prefHeight(-1);
	// Configurar el tamaño mínimo basado en el tamaño preferido
>>>>>>> 968eea5a0e24814f95c05c4ef5da9e3a700872a3
		stage.setMinWidth(preferredWidth);
		stage.setMinHeight(preferredHeight);


<<<<<<< HEAD
		//Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
=======
		Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
>>>>>>> 968eea5a0e24814f95c05c4ef5da9e3a700872a3
		//scene.getStylesheets().add(getClass().getResource("/css/bootstrap3.css").toExternalForm());
		scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
		stage.setScene(scene);
		stage.setTitle("Spring Java-FX");
		stage.show();
	}


}
