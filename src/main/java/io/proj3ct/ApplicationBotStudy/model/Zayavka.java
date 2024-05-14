package io.proj3ct.ApplicationBotStudy.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Zayavka")
public class Zayavka {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_zayavka;

    @Column(name = "tariff_name")
    private String tariffName;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "date")
    private Date date;

    @Column(name = "personal_name")
    private String personalName;

    // Конструкторы
    public Zayavka() {
    }

    public Zayavka(String tariffName, String clientName, Date date, String personalName) {
        this.tariffName = tariffName;
        this.clientName = clientName;
        this.date = date;
        this.personalName = personalName;
    }

    // Геттеры и сеттеры
    public Integer getId_zayavka() {
        return id_zayavka;
    }

    public void setId_zayavka(Integer id_zayavka) {
        this.id_zayavka = id_zayavka;
    }

    public String getTariffName() {
        return tariffName;
    }

    public void setTariffName(String tariffName) {
        this.tariffName = tariffName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPersonalName() {
        return personalName;
    }

    public void setPersonalName(String personalName) {
        this.personalName = personalName;
    }
}
