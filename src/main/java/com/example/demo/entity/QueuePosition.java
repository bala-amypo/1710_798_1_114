package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class QueuePosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "token_id", unique = true)
    private Token token;

    private Integer position;

    private LocalDateTime updatedAt;

    public QueuePosition() {}

    public Long getId() {
        return id;
    }

    public Token getToken() {
        return token;
    }

    public Integer getPosition() {
        return position;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
