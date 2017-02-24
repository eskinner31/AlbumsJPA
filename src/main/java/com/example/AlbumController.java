package com.example;

import com.example.interfaces.AlbumRepository;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Skinner on 2/23/17.
 */

@RestController
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumRepository repository;

    /**
     * Create constructor for Album Controller where er can inject the albumrepository
     */

    public AlbumController(AlbumRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public Iterable<Album> getAllAlbums() {
        return repository.findAll();
    }

    @PostMapping("/create")
    public void addAlbum(@RequestBody Album album) {
        repository.save(album);
    }
}
