

import org.mockito.Mockito;

import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;


public class Test {



    public class GeoServiceImpl_test implements GeoService {


        @Override
        public Location byIp(String ip) {
            return new Location("Moscow", Country.RUSSIA, "Lenina", 15);
        }

        @Override
        public Location byCoordinates(double latitude, double longitude) {

            throw new RuntimeException("Not implemented");
        }
    }

    public class LocalizationServiceImpl_test implements LocalizationService {

        public String locale(Country country) {
            switch (country) {
                case RUSSIA:
                    return "Добро пожаловать";
                default:
                    return "Welcome";
            }
        }
    }

    @org.junit.jupiter.api.Test
    void test_MessageSenderImpl() {

        GeoService geoService = Mockito.mock(GeoServiceImpl_test.class);
        LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl_test.class);
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);


        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.0.32.11");
        messageSender.send(headers);


    }


}
