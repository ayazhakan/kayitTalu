package com.guvenlikKayit.TaluKayit.backend.service;


import com.guvenlikKayit.TaluKayit.backend.model.KargoKayit;
import com.guvenlikKayit.TaluKayit.backend.repository.KargoKayitRepository;
import com.guvenlikKayit.TaluKayit.backend.repository.KayitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;
import java.util.List;

@Service
public class KargoKayitService implements CrudListener<KargoKayit> {

    private final KargoKayitRepository kargoKayitRepository;

    @Autowired
    public KargoKayitService(KargoKayitRepository kargoKayitRepository) {
        this.kargoKayitRepository = kargoKayitRepository;
    }

    @Override
    public List<KargoKayit> findAll() {
        return kargoKayitRepository.findAll(Sort.by(Sort.Order.desc("id")));
    }

    @Override
    public KargoKayit add(KargoKayit kargoKayit) {
        return kargoKayitRepository.save(kargoKayit);
    }

    @Override
    public KargoKayit update(KargoKayit kargoKayit) {
        return kargoKayitRepository.save(kargoKayit);
    }

    @Override
    public void delete(KargoKayit kargoKayit) {
        kargoKayitRepository.delete(kargoKayit);
    }
}
