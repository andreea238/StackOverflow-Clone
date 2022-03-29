package com.example.StackOverflow.repositories;

import com.example.StackOverflow.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITagRepo extends JpaRepository<Tag, Long> {
}
