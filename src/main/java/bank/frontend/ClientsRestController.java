package bank.frontend;

import bank.backend.BankService;
import bank.backend.Client;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientsRestController {

    private BankService bankService;

    public ClientsRestController(BankService bankService) {
        this.bankService = bankService;
    }

    @RequestMapping("/clients")
    public List<Client> listClients() {
        return bankService.listClient();

    }

    @RequestMapping(value="/clients", method = RequestMethod.POST)
    public void addClient(@RequestBody Client client) {
        bankService.addClient(client);

    }

    @RequestMapping("/clients/{id}")
    public Client getClient(@PathVariable long id) {
        return bankService.listClient().stream().filter(c->c.getId() == id).findFirst().get();
    }
}
