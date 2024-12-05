package com.guvenlikKayit.TaluKayit.backend.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "kayitlar")
public class GirisCikisKayit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String aracPlaka;
    private String cikisSaati;
    private String donusSaati;
    private String gidilenYer;
    private String aracKullanicisi;
    private LocalDate tarih;
}
