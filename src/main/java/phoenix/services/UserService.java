package phoenix.services;

import org.springframework.stereotype.Service;
import phoenix.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Service
public class UserService {

    @PersistenceContext
    private EntityManager entityManager;


    public UserService(EntityManager entityManager) {
        this.entityManager=entityManager;
    }

    public User getById(int id) {
        return entityManager.find(User.class, id);
    }

    public User getByLogin(String login) {
        User user=null;
        try {
            Query query = entityManager.createNativeQuery("select * from bdays.users where login=?1", User.class)
                    .setParameter(1, login);

            user = (User) query.getSingleResult();
        } catch (Exception e) {
            System.err.println(e.getStackTrace().toString());
        }
        return user;
    }
}
