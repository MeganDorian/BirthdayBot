package phoenix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import phoenix.entities.TemplateMessage;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class TemplateService {

    @PersistenceContext
    private EntityManager entityManager;

    public TemplateService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List <TemplateMessage> templateSelectAll () {
        Query query = entityManager.createNativeQuery("select * from bdays.templates", TemplateMessage.class);
        return query.getResultList();
    }

    public TemplateMessage templateSearchId (int id) {
        return entityManager.find(TemplateMessage.class, id);
    }

}
