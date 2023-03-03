

import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;
import static ru.netology.entity.Country.RUSSIA;
import static ru.netology.entity.Country.USA;


public class Test {


    @org.junit.jupiter.api.Test
    void Test_Message_Ru() {
        LocalizationService localizationService = Mockito.spy(LocalizationServiceImpl.class);
        GeoService geoService = Mockito.spy(GeoServiceImpl.class);

        when(geoService.byIp("172.0.32.11")).
                thenReturn(new Location("Moscow", RUSSIA, "Lenina", 15));

        when(localizationService.locale(RUSSIA)).
                thenReturn("Добро пожаловать");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.0.32.11");

        Assertions.assertEquals("Добро пожаловать", messageSender.send(headers));

    }


    @org.junit.jupiter.api.Test
    void Test_Message_US() {
        LocalizationService localizationService = Mockito.spy(LocalizationServiceImpl.class);
        GeoService geoService = Mockito.spy(GeoServiceImpl.class);

        when(geoService.byIp("96.44.183.149")).
                thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));

        when(localizationService.locale(USA)).
                thenReturn("Welcome");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.183.149");

        Assertions.assertEquals("Welcome", messageSender.send(headers));

    }


    @org.junit.jupiter.api.Test
    void Test_GeoServiceImpl() {
        GeoService geoService = new GeoServiceImpl();
        Location location = Mockito.mock(Location.class);
        when(location.getCountry()).thenReturn(RUSSIA);


        Assertions.assertEquals(location.getCountry(), geoService.byIp("172.0.32.11").getCountry());

    }

    @org.junit.jupiter.api.Test
    void Test_LocalizationServiceImpl() {
        LocalizationService localizationService = new LocalizationServiceImpl();


        Assertions.assertEquals("Добро пожаловать", localizationService.locale(RUSSIA));

    }
}



