package com.guvenlikKayit.TaluKayit.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="kargoKayit")
public class KargoKayit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String gelenTarih;
    String gonderenFirma;
    String gondericiIsim;
    String kargoIcerik;
    String kargoTakipNo;
    String aliciIsim;
    String teslimTarihi;
    String aciklama;
}
