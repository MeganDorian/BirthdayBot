package phoenix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import phoenix.entities.Birthday;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

@Service
public class BirthdayService {

    @PersistenceContext
    private EntityManager entityManager;

    public BirthdayService(EntityManager entityManager) {
        this.entityManager=entityManager;
    }

    public Birthday selectById(int id) {
        return entityManager.find(Birthday.class, id);
    }

    public List<Birthday> selectByDate (LocalDate date) {
        Query query =entityManager.createNativeQuery("select * from bdays.birthdays where date_of_birth=?1", Birthday.class).
                setParameter(1, date);
        return query.getResultList();
    }

    public List<Birthday> selectAll () {
        Query query = entityManager.createNativeQuery("select * from bdays.birthdays", Birthday.class);
        return query.getResultList();
    }

    @Transactional
    public void insertBirthday(Birthday birthday) {
        entityManager.persist(birthday);
    }
}
