package com.example.interfaces;

import com.example.Album;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Skinner on 2/23/17.
 */
public interface AlbumRepository extends CrudRepository<Album, Long> {

}
