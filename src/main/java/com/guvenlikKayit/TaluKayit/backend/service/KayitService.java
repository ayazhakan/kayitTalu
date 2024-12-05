package com.guvenlikKayit.TaluKayit.backend.service;

import com.guvenlikKayit.TaluKayit.backend.model.GirisCikisKayit;
import com.guvenlikKayit.TaluKayit.backend.repository.KayitRepository;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;
import java.util.List;

@Service
public class KayitService implements CrudListener<GirisCikisKayit> {

    private final KayitRepository kayitRepository;

    @Autowired
    public KayitService(KayitRepository kayitRepository) {
        this.kayitRepository = kayitRepository;
    }

    @Override
    public List<GirisCikisKayit> findAll() {
        return kayitRepository.findAll(Sort.by(Sort.Order.desc("id")));
    }

    @Override
    public GirisCikisKayit add(GirisCikisKayit girisCikisKayit) {
        LocalDate tarih = LocalDate.now();
        girisCikisKayit.setTarih(tarih);
        return kayitRepository.save(girisCikisKayit);
    }

    @Override
    public GirisCikisKayit update(GirisCikisKayit girisCikisKayit) {
        return kayitRepository.save(girisCikisKayit);
    }

    @Override
    public void delete(GirisCikisKayit girisCikisKayit) {
        kayitRepository.delete(girisCikisKayit);
    }
}