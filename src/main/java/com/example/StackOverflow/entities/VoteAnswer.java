package com.example.StackOverflow.entities;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "vote_answers")
public class VoteAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_upvote")
    private boolean isUpvote;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @MapsId("answerId")
    @JoinColumn(name = "answer_id")
    private Answer answer;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;
}
