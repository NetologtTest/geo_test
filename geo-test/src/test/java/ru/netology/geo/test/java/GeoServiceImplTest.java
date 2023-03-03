package ru.netology.geo.test.java;

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


public class GeoServiceImplTest {


    @org.junit.jupiter.api.Test
    void GeoServiceImplTest() {
        GeoService geoService = new GeoServiceImpl();
        Assertions.assertEquals(RUSSIA, geoService.byIp("172.0.32.11").getCountry());

    }
}