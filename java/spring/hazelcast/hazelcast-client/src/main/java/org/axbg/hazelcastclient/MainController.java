package org.axbg.hazelcastclient;

import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/main")
public class MainController {
    @Autowired
    private HazelcastInstance client;

    @GetMapping
    public ResponseEntity<List<String>> readMessage() {
        return ResponseEntity.ok(client.getList("data"));
    }

    @PostMapping
    public ResponseEntity<Boolean> sendMessage(@RequestBody MessageData data) {
        client.getList("data").add(data.getMessage());
        return ResponseEntity.ok(Boolean.TRUE);
    }
}
