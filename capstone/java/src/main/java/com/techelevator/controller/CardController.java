package com.techelevator.controller;

import com.techelevator.dao.CardDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.CardDto;
import com.techelevator.model.CollectionDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@CrossOrigin
//@PreAuthorize("isAuthenticated()")
@RestController
@RequestMapping("/card/")
public class CardController {

    private CardDao cardDao;

    public CardController(CardDao cardDao) {
        this.cardDao = cardDao;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "{cardId}", method = RequestMethod.GET)
    public CardDto getCard(@PathVariable int cardId, Principal principal)  {
        try {
            // Gives us the current user
            // System.out.println(principal.toString());
            return cardDao.getCardById(cardId);
        } catch (DaoException e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
