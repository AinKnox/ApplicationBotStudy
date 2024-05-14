package io.proj3ct.ApplicationBotStudy.service;

import io.proj3ct.ApplicationBotStudy.model.Zayavka;
import io.proj3ct.ApplicationBotStudy.repository.ZayavkaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ZayavkaService {

    @Autowired
    private ZayavkaRepository zayavkaRepository;

    public void saveZayavka(String tariffName, String clientName, Date date, String personalName) {
        Zayavka zayavka = new Zayavka(tariffName, clientName, date, personalName);
        zayavkaRepository.save(zayavka);
    }
}
