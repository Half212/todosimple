package com.aquilesleite.todosimple.repositories;

import com.aquilesleite.todosimple.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepo extends JpaRepository<User,Long> {

    
}
