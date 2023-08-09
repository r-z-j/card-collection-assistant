package com.techelevator.controller;

import com.techelevator.dao.CollectionDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.CollectionDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
public class CollectionController {
    private CollectionDao collectionDao;

    CollectionController(CollectionDao collectionDao) {
        this.collectionDao = collectionDao;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "collection/{id}", method = RequestMethod.GET)
    public CollectionDto getCollection(@PathVariable int id)  {
        try {
            return collectionDao.getCollectionById(id);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
