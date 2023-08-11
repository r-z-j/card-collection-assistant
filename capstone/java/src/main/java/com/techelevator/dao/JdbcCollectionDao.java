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
public class JdbcCollectionDao implements CollectionDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcCollectionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @ResponseBody
    @Override
    @GetMapping
    public CollectionDto getCollectionById(int collectionId) {
        CollectionDto collection = null;
        String sql = "SELECT * FROM collection WHERE collection_id = ?";
        String cardListSql = "SELECT * FROM card " +
        "JOIN card_collection ON card.card_id  = card_collection.card_id " +
        "JOIN collection ON card_collection.collection_id = collection.collection_id " +
        "WHERE collection.collection_id = ?";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, collectionId);
            if (result.next()) {
                collection = mapRowToCollection(result);
            }

            List<CardDto> cardList = new ArrayList<>();
            result = jdbcTemplate.queryForRowSet(cardListSql, collectionId);
            while (result.next()) {
                CardDto card = mapRowToCard(result);
                cardList.add(card);
            }
            collection.setCardList(cardList);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return collection;

    }

    @Override
    public CollectionDto createCollectionDto(CollectionDto collection) {
        CollectionDto newCollectionDto = null;
        String sql = "INSERT INTO collection(collection_name, author_id, game_type_id) VALUES (?, ?, ?) RETURNING collection_id;";

        try {
            int newCollectionId = jdbcTemplate.queryForObject(sql,
                    int.class,
                    collection.getCollectionName(),
                    collection.getAuthorId(),
                    collection.getGameTypeId());
            newCollectionDto = getCollectionById(newCollectionId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
return newCollectionDto;
    }

    @Override
    public CollectionDto updateCollectionById(int collectionId) {
        return null;
    }

    @Override
    public CollectionDto addCardToCollectionById(int collectionId, CardDto card) {
        return null;
    }

    @Override
    public CollectionDto removeCardFromCollectionById(int collectionId, CardDto card) {
        return null;
    }

    @Override
    public List<CollectionDao> getCollectionsByUserId(int userId) {
        return null;
    }

    @Override
    public List<CardDto> getCardsInCollectionById(int collectionId) {
        return null;
    }

    private CollectionDto mapRowToCollection(SqlRowSet rs) {
        CollectionDto collection = new CollectionDto();
        collection.setCollectionId(rs.getInt("collection_id"));
        collection.setCollectionName(rs.getString("collection_name"));
        collection.setAuthorId(rs.getInt("author_id"));
        collection.setGameTypeId(rs.getInt("game_type_id"));
        return collection;
    }

    private CardDto mapRowToCard(SqlRowSet rs) {
        CardDto card = new CardDto();
        card.setCardId(rs.getInt("card_id"));
        card.setCardApiId(rs.getString("card_api_id"));
        card.setCardName(rs.getString("card_name"));
        card.setGameTypeId(rs.getInt("game_type_id"));
        card.setUserPrice(BigDecimal.valueOf(rs.getInt("user_price")));
        card.setConditionId(rs.getInt("condition_id"));
        card.setQuantity(rs.getInt("quantity"));

        return card;
    }

}
