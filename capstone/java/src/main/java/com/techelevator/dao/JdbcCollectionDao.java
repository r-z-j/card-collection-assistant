package com.techelevator.dao;

import com.techelevator.model.CardDto;
import com.techelevator.model.CollectionDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import com.techelevator.exception.DaoException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCollectionDao implements CollectionDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcCollectionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public CollectionDto getCollectionById(int collectionId) {
        CollectionDto collection = null;
        String sql = "SELECT * FROM collection WHERE collection_id = ?";
        String cardListSql = "SELECT * FROM card " +
        "JOIN collection ON card.collection_id = collection.collection_id " +
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
    public int getCollectionAuthor(int collectionId) {
        int authorId = 0;
        String sql = "SELECT author_id FROM collection WHERE collection_id = ?";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, collectionId);
            if (result.next()) {
                authorId = result.getInt("author_id");
            }
            if( authorId == 0 ) {
                throw new DaoException("User not found");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DaoException e) {
            throw new DaoException(e.getMessage());
        }
        return authorId;
    }

    @Override
    public CollectionDto createCollectionDto(CollectionDto collection, int authorId) {
        CollectionDto newCollectionDto = null;
        String sql = "INSERT INTO collection(collection_name, author_id, game_type_id) VALUES (?, ?, ?) RETURNING collection_id;";

        try {
            collection.setAuthorId(authorId);
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
    public void deleteCollection(int collectionId) {
        String sql = "DELETE FROM card WHERE collection_id = ?;" +
        "DELETE FROM favorite_collection WHERE collection_id = ?;" +
        "DELETE FROM collection WHERE collection_id = ?;";
        try {
            jdbcTemplate.update(sql, collectionId, collectionId, collectionId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    @Override
    public List<CollectionDto> getMagicCollections() {
        List<CollectionDto> magicCollections = new ArrayList<>();
        String sql = "SELECT collection_id FROM collection WHERE game_type_id = 1";

        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
            while (result.next()) {
                int collectionId = result.getInt("collection_id");
                magicCollections.add(getCollectionById(collectionId));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return magicCollections;
    }

    @Override
    public List<CollectionDto> getPokemonCollections() {
        List<CollectionDto> pokemonCollections = new ArrayList<>();
        String sql = "SELECT collection_id FROM collection WHERE game_type_id = 2";

        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
            while (result.next()) {
                int collectionId = result.getInt("collection_id");
                pokemonCollections.add(getCollectionById(collectionId));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return pokemonCollections;
    }

    @Override
    public List<CollectionDto> getMyCollections(int userId) {
        List<CollectionDto> myCollections = new ArrayList<>();
        String sql = "SELECT collection_id FROM collection WHERE author_id = ?; ";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);
            while (result.next()) {
                int collectionId = result.getInt("collection_id");
                myCollections.add(getCollectionById(collectionId));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return myCollections;
    }

    @Override
    public List<CollectionDto> getFavoriteCollections(int userId) {
        List<CollectionDto> favCollections = new ArrayList<>();
        String sql = "SELECT collection_id FROM favorite_collection WHERE user_id = ?; ";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);
            while (result.next()) {
                int collectionId = result.getInt("collection_id");
                favCollections.add(getCollectionById(collectionId));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return favCollections;
    }

    @Override
    public List<CollectionDto> getCollectionsByCardApiId(String cardApiId) {
        List<CollectionDto> cardApiCollection = new ArrayList<>();
        String sql = "SELECT collection.collection_id " +
                "FROM collection " +
                "JOIN card ON collection.collection_id = card.collection_id " +
                "WHERE card_api_id = ?;";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, cardApiId);
            while (result.next()) {
                int collectionId = result.getInt("collection_id");
                cardApiCollection.add(getCollectionById(collectionId));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return cardApiCollection;
    }

    @Override
    public void addCollectionToFavorite(int collectionId, int userId) {
        String sql = "INSERT INTO favorite_collection(user_id, collection_id) VALUES (?, ?);";
        try {
            jdbcTemplate.update(sql, userId, collectionId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    @Override
    public void removeCollectionFromFavorite(int collectionId, int userId) {
        String sql = "DELETE FROM favorite_collection WHERE user_id = ? AND collection_id = ?";
        try {
            jdbcTemplate.update(sql, userId, collectionId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
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
        card.setCollectionId(rs.getInt("collection_id"));
        card.setCardApiId(rs.getString("card_api_id"));
        card.setCardName(rs.getString("card_name"));
        card.setGameTypeId(rs.getInt("game_type_id"));
        card.setUserPrice(BigDecimal.valueOf(rs.getDouble("user_price")));
        card.setConditionId(rs.getInt("condition_id"));
        card.setQuantity(rs.getInt("quantity"));

        return card;
    }

}
