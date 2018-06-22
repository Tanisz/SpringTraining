package bank;

import bank.backend.BankService;
import bank.backend.Client;
import bank.backend.Config;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class BankServiceContextTest {

    @Test
    public void afterAddShouldList() {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        DataSource dataSource = context.getBean(DataSource.class);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute("delete from clients");

        BankService bankService = context.getBean(BankService.class);
        bankService.addClient("John Doe");
        bankService.addClient("Jane Doe");
        bankService.addClient("Jack Doe");

        List<Client> clients = bankService.listClient();
        assertEquals(Arrays.asList("John Doe","Jane Doe","Jack Doe"),
                clients.stream().map(c -> c.getName()).collect(Collectors.toList())
        );

    }
}
