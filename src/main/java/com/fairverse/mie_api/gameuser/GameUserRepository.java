package com.fairverse.mie_api.gameuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameUserRepository extends JpaRepository<GameUser, Long> {

    @Query("Select g From GameUser g where g.email = ?1")
    Optional<GameUser> findGameUserByEmail(String email);

    @Query("Select g FROM GameUser g WHERE g.username=?1")
    Optional<GameUser> findGameUserByUsername(String username);
}
