package org.tony.elidestarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ElideStarterApplication {

  public static void main(String[] args) {
    SpringApplication.run(ElideStarterApplication.class, args);
  }

}
