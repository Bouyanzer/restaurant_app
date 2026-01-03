package com.bouyanzer.client;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    boolean existsByEmail(String email);

    Optional<Client> findByEmail(String email);
}
