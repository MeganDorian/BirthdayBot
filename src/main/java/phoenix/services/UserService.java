package phoenix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import phoenix.entities.Birthday;
import phoenix.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
public class UserService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public PasswordEncoder encoder;

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

    @Transactional
    public List<User> getAllUsers() {
        return entityManager.createNativeQuery("select * from bdays.users", User.class).getResultList();
    }

    @Transactional
    public void insertUser(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        entityManager.persist(user);
    }

    @Transactional
    public void deleteUser(int id) {
        entityManager.createNativeQuery("DELETE from bdays.users where id=?1")
                .setParameter(1, id).executeUpdate();
    }
}
