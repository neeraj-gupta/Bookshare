package demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cmpe275Project.Cmpe275ProjectApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Cmpe275ProjectApplication.class)
@WebAppConfiguration
public class Cmpe275ProjectApplicationTests {

	@Test
	public void contextLoads() {
	}

}
