package sg.nus.iss.visa.ssf.workshop11;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Workshop11Application {
	
	private static final Logger logger = LoggerFactory.getLogger(Workshop11Application.class);
		
	private static final String DEFAULT_PORT = "3000";

	public static void main(String[] args) {
		logger.info("Main method started...");

		// initialize spring app
		SpringApplication app = new SpringApplication(Workshop11Application.class);

		// read args array to check for "port" parameter
		DefaultApplicationArguments appArgs = new DefaultApplicationArguments(args);

		List optValues = appArgs.getOptionValues("port");

		String portNumber = null;

		if (optValues == null || optValues.get(0) == null) {
			// read port number from env variables (Case 2)
			portNumber = System.getenv("PORT");

			// assign default port if nothing set (Case 3)
			if (portNumber == null) {
				portNumber = DEFAULT_PORT;
			}
		} else {
			// set port number from command line interface (Case 1)
			portNumber = (String) optValues.get(0);
		}

		if (portNumber != null) {
			app.setDefaultProperties(Collections.singletonMap("server.port", portNumber));
		}

		app.run(args);
	}

}
