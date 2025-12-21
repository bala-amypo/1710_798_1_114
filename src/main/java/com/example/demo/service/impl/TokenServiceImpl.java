package com.example.demo.service.impl;

import com.example.demo.entity.QueuePosition;
import com.example.demo.entity.ServiceCounter;
import com.example.demo.entity.Token;
import com.example.demo.entity.TokenLog;
import com.example.demo.repository.QueuePositionRepository;
import com.example.demo.repository.ServiceCounterRepository;
import com.example.demo.repository.TokenLogRepository;
import com.example.demo.repository.TokenRepository;
import com.example.demo.service.TokenService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;
    private final ServiceCounterRepository serviceCounterRepository;
    private final TokenLogRepository tokenLogRepository;
    private final QueuePositionRepository queuePositionRepository;

    // REQUIRED constructor order (VERY IMPORTANT)
    public TokenServiceImpl(
            TokenRepository tokenRepository,
            ServiceCounterRepository serviceCounterRepository,
            TokenLogRepository tokenLogRepository,
            QueuePositionRepository queuePositionRepository
    ) {
        this.tokenRepository = tokenRepository;
        this.serviceCounterRepository = serviceCounterRepository;
        this.tokenLogRepository = tokenLogRepository;
        this.queuePositionRepository = queuePositionRepository;
    }

    @Override
    public Token issueToken(Long counterId) {

        ServiceCounter counter = serviceCounterRepository.findById(counterId)
                .orElseThrow(() -> new RuntimeException("Counter not found"));

        if (!Boolean.TRUE.equals(counter.getIsActive())) {
            throw new RuntimeException("Counter not active");
        }

        Token token = new Token();
        token.setTokenNumber(UUID.randomUUID().toString());
        token.setServiceCounter(counter);
        token.setStatus("WAITING");
        token.setIssuedAt(LocalDateTime.now());

        Token savedToken = tokenRepository.save(token);

        QueuePosition qp = new QueuePosition();
        qp.setToken(savedToken);
        qp.setPosition(1);
        qp.setUpdatedAt(LocalDateTime.now());
        queuePositionRepository.save(qp);

        TokenLog log = new TokenLog();
        log.setToken(savedToken);
        log.setLogMessage("Token issued");
        tokenLogRepository.save(log);

        return savedToken;
    }

    @Override
    public Token updateStatus(Long tokenId, String status) {

        Token token = tokenRepository.findById(tokenId)
                .orElseThrow(() -> new RuntimeException("Token not found"));

        String current = token.getStatus();

        if ("WAITING".equals(current) && "SERVING".equals(status) ||
            "SERVING".equals(current) && "COMPLETED".equals(status)) {

            token.setStatus(status);

            if ("COMPLETED".equals(status)) {
                token.setCompletedAt(LocalDateTime.now());
            }

            Token updated = tokenRepository.save(token);

            TokenLog log = new TokenLog();
            log.setToken(updated);
            log.setLogMessage("Status changed to " + status);
            tokenLogRepository.save(log);

            return updated;
        }

        throw new RuntimeException("Invalid status transition");
    }

    @Override
    public Token getToken(Long tokenId) {
        return tokenRepository.findById(tokenId)
                .orElseThrow(() -> new RuntimeException("Token not found"));
    }
}
