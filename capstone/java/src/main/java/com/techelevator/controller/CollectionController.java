package com.techelevator.controller;

import com.techelevator.dao.CardDao;
import com.techelevator.dao.CollectionDao;
import com.techelevator.dao.UserDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.CardDto;
import com.techelevator.model.CollectionDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@CrossOrigin
@PreAuthorize("isAuthenticated()")
@RestController
@RequestMapping("/collection")
public class CollectionController {
    private CollectionDao collectionDao;
    private CardDao cardDao;
    private UserDao userDao;

    CollectionController(CollectionDao collectionDao, CardDao cardDao, UserDao userDao) {
        this.collectionDao = collectionDao;
        this.cardDao = cardDao;
        this.userDao = userDao;
    }


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/magic", method = RequestMethod.GET)
    public List<CollectionDto> getMagicCollections()  {
        try {
            return collectionDao.getMagicCollections();
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/pokemon", method = RequestMethod.GET)
    public List<CollectionDto> getPokemonCollections()  {
        try {
            return collectionDao.getPokemonCollections();
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/{collectionId}", method = RequestMethod.GET)
    public CollectionDto getCollection(@PathVariable int collectionId)  {
        try {
            return collectionDao.getCollectionById(collectionId);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/{collectionId}", method = RequestMethod.DELETE)
    public void deleteCollection(@PathVariable int collectionId, Principal principal){
        int userId = userDao.getUserByUsername(principal.getName()).getId();

        try {
            int authorId = collectionDao.getCollectionAuthor(collectionId);
            if (userId != authorId) {
                throw new DaoException("You are not authorized to delete this!");
            }
            collectionDao.deleteCollection(collectionId);

        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    };

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/{collectionId}/add-card", method = RequestMethod.POST)
    public CollectionDto addCard(@PathVariable int collectionId, @Valid @RequestBody CardDto card) {
        CollectionDto updatedCollection = null;
        try {
            if (card == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Card was null and not added to collection.");
            }
            int newCardId = cardDao.addCard(card);
            updatedCollection = collectionDao.getCollectionById(collectionId);

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
            cardDao.removeCard(cardId);
            updatedCollection = collectionDao.getCollectionById(collectionId);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return updatedCollection;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public CollectionDto createCollection(@Valid @RequestBody CollectionDto collection, Principal principal) {
        CollectionDto newCollectionDto = null;
        int authorId = userDao.getUserByUsername(principal.getName()).getId();
        try {
            if (collection == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CollectionDto was not created.");
            }
            newCollectionDto = collectionDao.createCollectionDto(collection, authorId);

        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        return newCollectionDto;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/mine", method = RequestMethod.GET)
    public List<CollectionDto> getMyCollections(Principal principal){
        int userId = userDao.getUserByUsername(principal.getName()).getId();
        System.out.println(principal);
        List<CollectionDto> myCollections = null;
        try {
           myCollections = collectionDao.getMyCollections(userId);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return myCollections;
    };

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/favorite", method = RequestMethod.GET)
    public List<CollectionDto> getFavoriteCollections(Principal principal){
        int userId = userDao.getUserByUsername(principal.getName()).getId();
        List<CollectionDto> favCollections = null;
        try {
            favCollections = collectionDao.getFavoriteCollections(userId);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return favCollections;
    };

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/favorite/{collectionId}", method = RequestMethod.POST)
    public void addCollectionToFavorite(@PathVariable int collectionId, Principal principal) {
        int userId = userDao.getUserByUsername(principal.getName()).getId();
        try {
            collectionDao.addCollectionToFavorite(collectionId, userId);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/favorite/{collectionId}", method = RequestMethod.DELETE)
    public void removeCollectionFromFavorite(@PathVariable int collectionId, Principal principal) {
        int userId = userDao.getUserByUsername(principal.getName()).getId();
        try {
            collectionDao.removeCollectionFromFavorite(collectionId, userId);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
