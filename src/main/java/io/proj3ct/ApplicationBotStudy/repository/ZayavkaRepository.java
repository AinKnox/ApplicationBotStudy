package io.proj3ct.ApplicationBotStudy.repository;

import io.proj3ct.ApplicationBotStudy.model.Zayavka;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZayavkaRepository extends JpaRepository<Zayavka, Integer> {
}
