package net.discobear.jenova.repository;

import net.discobear.jenova.model.Blockchain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockchainRepository extends JpaRepository<Blockchain, Long> {
}
