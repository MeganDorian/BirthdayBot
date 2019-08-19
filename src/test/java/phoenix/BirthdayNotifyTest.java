package phoenix;

import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import phoenix.entities.Birthday;
import phoenix.entities.TemplateMessage;
import phoenix.services.BirthdayService;
import phoenix.telegram.BirthdayNotify;
import phoenix.telegram.HappyBirthdayBot;
import phoenix.telegram.MessageConcat;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class BirthdayNotifyTest {
    @Test
    public void shouldNeverNotifyBecauseEmptyList() throws InterruptedException {
        HappyBirthdayBot happyBirthdayBot = Mockito.mock(HappyBirthdayBot.class);
        BirthdayService birthdayService = Mockito.mock(BirthdayService.class);
        MessageConcat messageConcat = Mockito.mock(MessageConcat.class);

        Mockito.when(birthdayService.selectByDate(LocalDate.now())).thenReturn(Collections.emptyList());
        Mockito.when(messageConcat.birthdayAndTemplateConcat(null)).thenReturn(null);

        BirthdayNotify birthdayNotify = new BirthdayNotify(happyBirthdayBot, birthdayService, messageConcat);
        Thread.sleep(1000);

        Mockito.verifyZeroInteractions(happyBirthdayBot);

    }

    @Test
    public void shouldReturnListOfBirthdays() throws InterruptedException {
        HappyBirthdayBot happyBirthdayBot = Mockito.mock(HappyBirthdayBot.class);
        BirthdayService birthdayService = Mockito.mock(BirthdayService.class);
        MessageConcat messageConcat = Mockito.mock(MessageConcat.class);

        Random random = new Random();
        List<Birthday> list = new ArrayList<>(2);
        list.add(new Birthday(6, "melhdor", LocalDate.now()));
        list.add(new Birthday(7, "detel", LocalDate.now()));

        List<TemplateMessage> templates = fillTemplates();
        String msg = list.get(0).toString().concat(templates.get(random.nextInt(templates.size())).getTemplate());
        String msg2 = list.get(1).toString().concat(templates.get(random.nextInt(templates.size())).getTemplate());

        Mockito.when(birthdayService.selectByDate(LocalDate.now())).thenReturn(list);
        Mockito.when(messageConcat.birthdayAndTemplateConcat(list.get(0).toString())).thenReturn(msg);
        Mockito.when(messageConcat.birthdayAndTemplateConcat(list.get(1).toString())).thenReturn(msg2);

        BirthdayNotify birthdayNotify = new BirthdayNotify(happyBirthdayBot, birthdayService, messageConcat);

        Thread.sleep(1000);
        birthdayNotify.init();

        Mockito.verify(happyBirthdayBot, Mockito.times(1)).birthdayNotification(msg);
        Mockito.verify(happyBirthdayBot, Mockito.times(1)).birthdayNotification(msg2);

        Mockito.verify(happyBirthdayBot).birthdayNotification(ArgumentMatchers.eq(msg));
        Mockito.verify(happyBirthdayBot).birthdayNotification(ArgumentMatchers.eq(msg2));
    }

    private List<TemplateMessage> fillTemplates() {
        List<TemplateMessage> templateMessages = new ArrayList<>();
        templateMessages.add(new TemplateMessage(1, "Happy Birthday"));
        templateMessages.add(new TemplateMessage(2, "Congratulations"));
        templateMessages.add(new TemplateMessage(3, "С Днем Рождения"));
        templateMessages.add(new TemplateMessage(4, "Поздравляем"));
        return templateMessages;
    }
}