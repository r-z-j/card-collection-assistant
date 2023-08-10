package com.techelevator.controller;

import com.techelevator.dao.CollectionDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.CollectionDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/collection/")
public class CollectionController {
    private CollectionDao collectionDao;

    CollectionController(CollectionDao collectionDao) {
        this.collectionDao = collectionDao;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public CollectionDto getCollection(@PathVariable int id)  {
        try {
            return collectionDao.getCollectionById(id);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public CollectionDto create(@Valid @RequestBody CollectionDto collection) {
        CollectionDto newCollectionDto = null;
        try {
            if (collection == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CollectionDto was not created.");
            }

            newCollectionDto = collectionDao.createCollectionDto(collection);

        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "CollectionDto was not created.");
        }
        return newCollectionDto;
    }


}
