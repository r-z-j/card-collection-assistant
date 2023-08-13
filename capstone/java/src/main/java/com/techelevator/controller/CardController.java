package com.techelevator.controller;

import com.techelevator.dao.CardDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.CardDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@PreAuthorize("isAuthenticated()")
@CrossOrigin
@RestController
@RequestMapping("/card")
public class CardController {

    private CardDao cardDao;

    public CardController(CardDao cardDao) {
        this.cardDao = cardDao;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<CardDto> getAllCards()  {
        try {
            return cardDao.getAllCards();
        } catch (DaoException e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/{cardId}", method = RequestMethod.GET)
    public CardDto getCard(@PathVariable int cardId, Principal principal)  {
        try {
            return cardDao.getCardById(cardId);
        } catch (DaoException e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/{cardId}", method = RequestMethod.POST)
    public CardDto updateCard(@PathVariable int cardId, @Valid @RequestBody CardDto card, Principal principal)  {
        try {
            return cardDao.updateCard(cardId, card);
        } catch (DaoException e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
