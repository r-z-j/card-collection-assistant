package com.techelevator.dao;

import com.techelevator.model.CardDto;
import com.techelevator.model.CollectionDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import com.techelevator.exception.DaoException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

            List<String> cardApiList = new ArrayList<>();
            result = jdbcTemplate.queryForRowSet(cardListSql, collectionId);
            while (result.next()) {
                cardApiList.add(result.getString("card_api_id"));
            }
            collection.setCardApiList(cardApiList);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return collection;

    }

//    @Override
//    public City createCity(City city) {
//        City newCity = null;
//        String sql = "INSERT INTO city (city_name, state_abbreviation, population, area) " +
//                "VALUES (?, ?, ?, ?) RETURNING city_id;";
//        try {
//            int newCityId = jdbcTemplate.queryForObject(sql, int.class,
//                    city.getCityName(), city.getStateAbbreviation(), city.getPopulation(), city.getArea());
//
//            newCity = getCityById(newCityId);
//        } catch (CannotGetJdbcConnectionException e) {
//            throw new DaoException("Unable to connect to server or database", e);
//        } catch (DataIntegrityViolationException e) {
//            throw new DaoException("Data integrity violation", e);
//        }
//        return newCity;
//    }
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
        CollectionDto collection = new CollectionDto( rs.getInt("collection_id"),
                rs.getString("collection_name"),
                rs.getInt("author_id"),
                rs.getInt("game_type_id") );
        return collection;
    }

}
