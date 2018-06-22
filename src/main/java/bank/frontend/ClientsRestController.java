package bank.frontend;

import bank.backend.BankService;
import bank.backend.Client;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
