package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.UserData;

@Repository
public interface UserRepository extends JpaRepository<UserData, Integer> {
}
