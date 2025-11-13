package com.ethan.RPG.repository;

import com.ethan.RPG.entity.Monster;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface MonsterRepository extends JpaRepository<Monster, Long>{
}
