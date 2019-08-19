package phoenix;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import phoenix.entities.Birthday;
import phoenix.entities.TemplateMessage;
import phoenix.services.TemplateService;
import phoenix.telegram.MessageConcat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MessageConcatTest {

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowExceptionBecauseNoTemplatesFound() {
        TemplateService templateService = Mockito.mock(TemplateService.class);
        Random random = Mockito.mock(Random.class);

        Birthday birthday = new Birthday(1, "megan", LocalDate.now());

        Mockito.when(templateService.templateSelect()).thenReturn(Collections.emptyList());

        MessageConcat messageConcat = new MessageConcat(templateService, random);
        messageConcat.birthdayAndTemplateConcat(birthday.toString());

        Mockito.verify(templateService).templateSelect();
    }

    @Test
    public void shouldReturnNewString() {
        TemplateService templateService = Mockito.mock(TemplateService.class);
        TemplateMessage templateMessage = Mockito.mock(TemplateMessage.class);
        Random random = Mockito.mock(Random.class);

        Birthday birthday = new Birthday(1, "megan", LocalDate.now());

        List<TemplateMessage> templateMessages = fillTemplate();
        Mockito.when(templateService.templateSelect()).thenReturn(templateMessages);
        Mockito.when(templateMessage.getTemplate()).thenReturn("Happy Birthday");
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(0);

        String result = birthday.toString().concat(templateMessage.getTemplate());
        MessageConcat messageConcat = new MessageConcat(templateService, random);
        String concat = messageConcat.birthdayAndTemplateConcat(birthday.toString());

        Mockito.verify(templateService).templateSelect();

        Assert.assertEquals(result, concat);
    }

    private List<TemplateMessage> fillTemplate() {
        List<TemplateMessage> list = new ArrayList<>();
        list.add(new TemplateMessage(1, "Happy Birthday"));
        list.add(new TemplateMessage(2, "С Днем Рождения"));
        list.add(new TemplateMessage(3, "Поздравляем"));
        list.add(new TemplateMessage(4, "Congratulations"));
        return list;
    }
}
