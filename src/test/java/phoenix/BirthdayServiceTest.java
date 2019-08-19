package phoenix;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import phoenix.entities.Birthday;
import phoenix.services.BirthdayService;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BirthdayServiceTest {

    @Test
        public void shouldReturnRequiredRowsSelectedById () {
        EntityManager entityManager = Mockito.mock(EntityManager.class);

        Birthday birthday = new Birthday(1, "megan",LocalDate.of(1980,3,14));
        Mockito.when(entityManager.find(Birthday.class, 1)).thenReturn(birthday);
        BirthdayService birthdayService = new BirthdayService(entityManager);
        Birthday returnBday = birthdayService.selectById(1);

        Assert.assertEquals(birthday, returnBday);
        Mockito.verify(entityManager, Mockito.times(1)).find(Birthday.class, 1);
    }

    @Test
    public void shouldReturnRequiredRowsSelectedByDate() {
        EntityManager entityManager = Mockito.mock(EntityManager.class);

        List<Birthday> birthdays = new ArrayList<>();
        birthdays.add(new Birthday(4, "dorian", LocalDate.of(2001, 6, 27)));

        Query query =Mockito.mock(Query.class);
        Mockito.when(entityManager.createNativeQuery("select * from bdays.birthdays where date_of_birth=?1",
                Birthday.class)).thenReturn(query);
        Mockito.when(query.setParameter(1, LocalDate.of(2001,6,27))).thenReturn(query);
        Mockito.when(query.getResultList()).thenReturn(birthdays);

        BirthdayService birthdayService = new BirthdayService(entityManager);
        List<Birthday> birthdayList=birthdayService.selectByDate(LocalDate.of(2001, 6, 27));

        Assert.assertEquals(birthdays, birthdayList);
        Mockito.verify(entityManager, Mockito.times(1)).createNativeQuery
                ("select * from bdays.birthdays where date_of_birth=?1", Birthday.class);
        Mockito.verify(query, Mockito.times(1)).setParameter(1, LocalDate.of(2001,6,27));
        Mockito.verify(query, Mockito.times(1)).getResultList();

    }

}
