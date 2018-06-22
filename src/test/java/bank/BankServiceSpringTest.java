package bank;

import bank.backend.Address;
import bank.backend.BankService;
import bank.backend.Client;
import bank.backend.Config;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
@Sql(statements = "delete from clients")
@Sql(statements = "delete from logs")
public class BankServiceSpringTest {

    @Autowired
    private BankService bankService;

    @Test
    public void afterAddShouldList() {

        bankService.addClient("John Doe");
        bankService.addClient("Jane Doe");
        bankService.addClient("Jack Doe");

        List<Client> clients = bankService.listClient();
        assertEquals(Arrays.asList("John Doe","Jane Doe","Jack Doe"),
                clients.stream().map(c -> c.getName()).collect(Collectors.toList())
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyName() {

        bankService.addClient("   ");

    }

    @Test
    public void saveWithAddress() {
        bankService.addClient("John Doe",new Address("Budapest","Fő utca 30."),
                new Address("Győr","Duna utca 3"),
                new Address("Békéscsaba","Jókai u. 45"));

        Client client = bankService.listClient().get(0);
        System.out.println(client.getAddresses().get(0).getCity());

    }
}
