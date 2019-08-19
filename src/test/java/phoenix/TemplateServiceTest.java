package phoenix;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import phoenix.entities.TemplateMessage;
import phoenix.services.TemplateService;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class TemplateServiceTest {


    @Test
    public void shouldReturnRequiredRowsSelectedById() {
        EntityManager entityManager = Mockito.mock(EntityManager.class);


        TemplateMessage templateMessage = new TemplateMessage();
        templateMessage.setId(1);
        templateMessage.setTemplate("Happy Birthday");

        Mockito.when(entityManager.find(TemplateMessage.class, 1)).thenReturn(templateMessage);

        TemplateService templateService = new TemplateService(entityManager);
        TemplateMessage message = templateService.templateSearchId(1);

        Mockito.verify(entityManager, Mockito.times(1)).find(TemplateMessage.class, 1);
        Assert.assertEquals(templateMessage, message);
    }

    @Test
    public void shouldReturnAllRows() {
        EntityManager entityManager = Mockito.mock(EntityManager.class);
        Query query = Mockito.mock(Query.class);

        List<TemplateMessage> filled = fillList();
        Mockito.when(entityManager.createNativeQuery("select * from bdays.templates",
                TemplateMessage.class)).thenReturn(query);
        Mockito.when(query.getResultList()).thenReturn(filled);

        TemplateService templateService = new TemplateService(entityManager);
        List templateList= templateService.templateSelect();

        Mockito.verify(entityManager, Mockito.times(1)).createNativeQuery
                ("select * from bdays.templates", TemplateMessage.class);
        Mockito.verify(query, Mockito.times(1)).getResultList();

        Assert.assertEquals(filled, templateList);
    }

    private List<TemplateMessage> fillList() {
        List<TemplateMessage> list = new ArrayList<>();
        TemplateMessage templateMessage = new TemplateMessage();
        templateMessage.setId(1);
        templateMessage.setTemplate("Happy Birthday");
        list.add(templateMessage);
        templateMessage = new TemplateMessage();
        templateMessage.setId(2);
        templateMessage.setTemplate("Congratulations");
        list.add(templateMessage);
        templateMessage = new TemplateMessage();
        templateMessage.setId(3);
        templateMessage.setTemplate("С Днем Рождения");
        list.add(templateMessage);
        templateMessage = new TemplateMessage();
        templateMessage.setId(4);
        templateMessage.setTemplate("Поздравляю");
        list.add(templateMessage);
        return list;
    }

}
