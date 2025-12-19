package com.example.demo.service.impl;

import com.example.demo.entity.Token;
import com.example.demo.entity.TokenLog;
import com.example.demo.repository.TokenLogRepository;
import com.example.demo.repository.TokenRepository;
import com.example.demo.service.TokenLogService;

import java.util.List;

public class TokenLogServiceImpl implements TokenLogService {

    private final TokenLogRepository tokenLogRepository;
    private final TokenRepository tokenRepository;

    // REQUIRED constructor order
    public TokenLogServiceImpl(
            TokenLogRepository tokenLogRepository,
            TokenRepository tokenRepository
    ) {
        this.tokenLogRepository = tokenLogRepository;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public TokenLog addLog(Long tokenId, String message) {
        Token token = tokenRepository.findById(tokenId)
                .orElseThrow(() -> new RuntimeException("Token not found"));

        TokenLog log = new TokenLog();
        log.setToken(token);
        log.setLogMessage(message);

        return tokenLogRepository.save(log);
    }

    @Override
    public List<TokenLog> getLogs(Long tokenId) {
        return tokenLogRepository.findByToken_IdOrderByLoggedAtAsc(tokenId);
    }
}
