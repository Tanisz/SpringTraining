package bank;

import bank.backend.BankDao;
import bank.backend.BankService;
import bank.backend.Client;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class BankServiceTest {

    @Test
    public void testAddClient() {
        //Given
        BankDaoStub bankDao = new BankDaoStub();
        BankService bankService = new BankService(null,bankDao);

        //When
        bankService.addClient("John Doe");

        //Then
        assertEquals("John Doe",bankDao.getClient().getName());
    }

    public static class BankDaoStub implements BankDao {

        private Client client;

        @Override
        public void addClient(Client client) {
            this.client = client;
        }

        @Override
        public List<Client> listClients() {
            return null;
        }

        public Client getClient() {
            return client;
        }
    }
}
