package co.simplon;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootTest
class LesPromosSimplonApplicationTests {

	@Test
	void contextLoads() {
		ConfigurableApplicationContext context = SpringApplication.run(LesPromosSimplonApplication.class);
		assertTrue(context.isRunning());
		context.close();
	}

}
