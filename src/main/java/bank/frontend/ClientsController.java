package bank.frontend;

import bank.backend.BankService;
import bank.backend.Client;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ClientsController {

    private BankService bankService;

    public ClientsController(BankService bankService) {
        this.bankService = bankService;
    }

    @ModelAttribute("clients")
    public List<Client> clients() {
        return bankService.listClient();
    }

    //@ResponseBody
    @RequestMapping(value="/", method = RequestMethod.GET)
    public ModelAndView listClients() {
        //View
        ModelAndView modelAndView = new ModelAndView("clients");
        //Model : Liset<Client>
        //modelAndView.addObject("clients",bankService.listClient());

        modelAndView.addObject("client",new Client());

        return modelAndView;
    }

    @RequestMapping(value="/",method = RequestMethod.POST)
    //public String addClient(@ModelAttribute Client client) {
    public String addClient(@Valid Client client, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "clients";
        }
        bankService.addClient(client);

        redirectAttributes.addFlashAttribute("message","Client has successfully created!");

        return "redirect:/";
    }
}
