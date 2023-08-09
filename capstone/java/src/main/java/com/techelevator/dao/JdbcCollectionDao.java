package com.techelevator.dao;

import com.techelevator.model.CardDto;
import com.techelevator.model.CollectionDto;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.Objects;
import com.techelevator.exception.DaoException;
import com.techelevator.model.RegisterUserDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcCollectionDao implements CollectionDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcCollectionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public CollectionDto getCollectionById(int collectionId) {
        CollectionDto collection = null;
        String sql = "SELECT * FROM collection WHERE collection_id = ?";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, collectionId);
            if (result.next()) {
                collection = mapRowToCollection(result);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return collection;

    }

    @Override
    public CollectionDto createCollectionById(CollectionDao collection) {
        return null;
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
