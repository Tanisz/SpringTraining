package bank.backend;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaBankDao implements BankDao {

    @PersistenceContext
    private EntityManager em;

    //@Transactional
    @Override
    public void addClient(Client client) {
        em.persist(client);
    }

    @Override
    public List<Client> listClients() {
        return em.createQuery("select distinct c from Client c left join fetch c.addresses",Client.class).getResultList();
    }
}
