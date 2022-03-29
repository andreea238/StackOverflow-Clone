package com.example.StackOverflow.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "votes")
    private int votes;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
