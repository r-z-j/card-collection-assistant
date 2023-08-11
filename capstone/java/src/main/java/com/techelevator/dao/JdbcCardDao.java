package com.techelevator.dao;

import com.techelevator.model.CardDto;
import com.techelevator.model.CollectionDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import com.techelevator.exception.DaoException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCardDao implements CardDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcCardDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public CardDto getCardById(int cardId) {
        CardDto card = null;
        String sql = "SELECT * FROM card WHERE card_id = ?";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, cardId);
            if (result.next()) {
                card = mapRowToCard(result);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return card;
    }

    public List<CardDto> getAllCards() {
        List<CardDto> allCards = new ArrayList<>();
        String sql = "SELECT * FROM card";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
            while (result.next()) {
                allCards.add(mapRowToCard(result));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return allCards;
    }

    public int addCard(CardDto card){
        int newCardId;
        String sql = "INSERT INTO card(" +
                "card_api_id, card_name, game_type_id, " +
                "user_price, quantity, condition_id) " +
                "VALUES (?, ?, ?, ?, ?, ?) RETURNING card_id;";
        try {
            newCardId = jdbcTemplate.queryForObject(sql,
                    int.class,
                    card.getCardApiId(),
                    card.getCardName(),
                    card.getGameTypeId(),
                    card.getUserPrice(),
                    card.getQuantity(),
                    card.getConditionId());

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newCardId;
        }

    private CardDto mapRowToCard(SqlRowSet rs) {
        CardDto card = new CardDto();
        card.setCardId(rs.getInt("card_id"));
        card.setCardApiId(rs.getString("card_api_id"));
        card.setCardName(rs.getString("card_name"));
        card.setGameTypeId(rs.getInt("game_type_id"));
        card.setUserPrice(BigDecimal.valueOf(rs.getDouble("user_price")));
        card.setConditionId(rs.getInt("condition_id"));
        card.setQuantity(rs.getInt("quantity"));

        return card;
    }


}
