package com.guvenlikKayit.TaluKayit.backend.repository;

import com.guvenlikKayit.TaluKayit.backend.model.GirisCikisKayit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KayitRepository extends JpaRepository<GirisCikisKayit, Long> {
}
