package com.fairverse.mie_api.user1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface User1Repository extends JpaRepository<User1, Long> {

    @Query("Select u From User1 u where u.mail =?1")
    Optional<User1> findUser1ByMail(String mail);
}
