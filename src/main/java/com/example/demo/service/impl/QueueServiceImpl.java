// package com.example.demo.service.impl;

// import com.example.demo.entity.QueuePosition;
// import com.example.demo.repository.QueuePositionRepository;
// import com.example.demo.repository.TokenRepository;
// import com.example.demo.service.QueueService;

// public class QueueServiceImpl implements QueueService {

//     private final QueuePositionRepository queuePositionRepository;
//     private final TokenRepository tokenRepository;

//     // REQUIRED constructor order
//     public QueueServiceImpl(
//             QueuePositionRepository queuePositionRepository,
//             TokenRepository tokenRepository
//     ) {
//         this.queuePositionRepository = queuePositionRepository;
//         this.tokenRepository = tokenRepository;
//     }

//     @Override
//     public QueuePosition updateQueuePosition(Long tokenId, Integer newPosition) {
//         QueuePosition qp = queuePositionRepository.findByToken_Id(tokenId)
//                 .orElseThrow(() -> new RuntimeException("Queue position not found"));

//         qp.setPosition(newPosition);
//         return queuePositionRepository.save(qp);
//     }

//     @Override
//     public QueuePosition getPosition(Long tokenId) {
//         return queuePositionRepository.findByToken_Id(tokenId)
//                 .orElseThrow(() -> new RuntimeException("Queue position not found"));
//     }
// }
