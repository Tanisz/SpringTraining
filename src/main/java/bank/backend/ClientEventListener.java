package bank.backend;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ClientEventListener implements ApplicationListener<ClientHasCreatedEvent> {

    @Override
    public void onApplicationEvent(ClientHasCreatedEvent event) {
        System.out.println("Client has created " + event.getName());
    }
}
