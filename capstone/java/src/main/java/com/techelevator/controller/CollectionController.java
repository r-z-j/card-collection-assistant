package com.techelevator.controller;

import com.techelevator.dao.CardDao;
import com.techelevator.dao.CollectionDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.CardDto;
import com.techelevator.model.CollectionDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;

@CrossOrigin
//@PreAuthorize("isAuthenticated()")
@RestController
@RequestMapping("/collection/")
public class CollectionController {
    private CollectionDao collectionDao;
    private CardDao cardDao;

    CollectionController(CollectionDao collectionDao, CardDao cardDao) {
        this.collectionDao = collectionDao;
        this.cardDao = cardDao;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "{collectionId}", method = RequestMethod.GET)
    public CollectionDto getCollection(@PathVariable int collectionId, Principal principal)  {
        try {
            // Gives us the current user
            // System.out.println(principal.toString());
            return collectionDao.getCollectionById(collectionId);
        } catch (DaoException e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/{collectionId}/add-card", method = RequestMethod.POST)
    public CollectionDto addCard(@PathVariable int collectionId, @Valid @RequestBody CardDto card) {
        CollectionDto updatedCollection = null;
        try {
            if (card == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CollectionDto was not created.");
            }
            int newCardId = cardDao.addCard(card);
            updatedCollection = collectionDao.addCardToCollectionById(collectionId, newCardId);

        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        return updatedCollection;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/{collectionId}/card/{cardId}", method = RequestMethod.DELETE)
    public CollectionDto removeCard(@PathVariable int collectionId, @PathVariable int cardId) {
        CollectionDto updatedCollection = null;
        try {
           int numberOfRows = collectionDao.removeCardFromCollectionById(collectionId, cardId);
           updatedCollection = collectionDao.getCollectionById(collectionId);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        return updatedCollection;
    }



    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public CollectionDto create(@Valid @RequestBody CollectionDto collection) {
        CollectionDto newCollectionDto = null;
        System.out.println(collection.toString());
        try {
            if (collection == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CollectionDto was not created.");
            }

            newCollectionDto = collectionDao.createCollectionDto(collection);

        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        return newCollectionDto;
    }


}
