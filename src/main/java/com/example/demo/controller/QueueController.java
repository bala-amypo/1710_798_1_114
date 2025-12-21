package com.example.demo.controller;

import com.example.demo.entity.QueuePosition;
import com.example.demo.service.QueueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/queue")
public class QueueController {

    private final QueueService queueService;

    public QueueController(QueueService queueService) {
        this.queueService = queueService;
    }

    @PutMapping("/position/{tokenId}/{newPosition}")
    public ResponseEntity<QueuePosition> updatePosition(
            @PathVariable Long tokenId,
            @PathVariable Integer newPosition
    ) {
        return ResponseEntity.ok(
                queueService.updateQueuePosition(tokenId, newPosition)
        );
    }

    @GetMapping("/position/{tokenId}")
    public ResponseEntity<QueuePosition> getPosition(@PathVariable Long tokenId) {
        return ResponseEntity.ok(queueService.getPosition(tokenId));
    }
}
