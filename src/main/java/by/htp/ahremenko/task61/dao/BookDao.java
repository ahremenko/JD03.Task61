package java.by.htp.ahremenko.task61.dao;

import java.by.htp.ahremenko.task61.model.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BookDao extends AbstractDao<Book> {
    private static String SQL_SELECT_BY_ID = "SELECT * FROM book AS b WHERE b.id=?";

    public BookDao(Connection connection) {
        super(connection);
        SQL_SELECT_SORTED_PAGE = "SELECT * FROM book ORDER BY %s %s LIMIT ?, ?";
        SQL_INSERT = "INSERT INTO book "
                + "(name, creation_year, genre_id, author_id) "
                + "VALUES (?, ?, ?, ?)";
        SQL_UPDATE = "UPDATE book "
                + "SET name=?, creation_year=?, genre_id=?, author_id=? "
                + "WHERE id=?";
        SQL_SELECT_FILTERED_ENTITIES = "SELECT * FROM book "
                + "WHERE (genre_id=? OR ?='') AND (author_id=? OR ?='') AND (creation_year >= ? OR or ?='') and (creation_year <= ? OR or ?='') )";
    }

    @Override
    public final List<Book> getSortedEntitiesPage(int pageNumber, String sortedField, boolean order, int itemsNumberInPage) throws SQLException {
        List<Book> books = super.getSortedEntitiesPage(pageNumber, sortedField, order, itemsNumberInPage);
        AuthorDao authorDao = new AuthorDao(connection);
        for (Book book: books) {
            book.setAuthor(authorDao.getEntityById(book.getAuthor().getId()));
        }
        GenreDao genreDao = new GenreDao(connection);
        for (Book book: books) {
            book.setGenre(genreDao.getEntityById(book.getGenre().getId()));
        }
        return books;
    }

    @Override
    protected final Book setEntityFields(ResultSet entityTableRow) throws SQLException {
        Book book = new Book();
        book.setId(entityTableRow.getLong("id"));
        AuthorDao authorDao = new AuthorDao(connection);
        book.setAuthor(authorDao.getEntityById(entityTableRow.getLong("id_author")));
        GenreDao genreDao = new GenreDao(connection);
        book.setGenre(genreDao.getEntityById(entityTableRow.getLong("id_genre")));
        book.setCreationYear(entityTableRow.getInt("creation_year"));
        return book;
    }

    @Override
    protected final void setValuesForInsertIntoPrepareStatement(PreparedStatement prepareStatement, Book entity)
            throws SQLException {
        prepareStatement.setString(1, entity.getName());
        prepareStatement.setInt(2, entity.getCreationYear());
        prepareStatement.setLong(3, entity.getGenre().getId());
        prepareStatement.setLong(4, entity.getAuthor().getId());
    }

    @Override
    protected final void setValuesForUpdateIntoPrepareStatement(PreparedStatement prepareStatement,
                                                                Book entity) throws SQLException {
        setValuesForInsertIntoPrepareStatement(prepareStatement, entity);
        prepareStatement.setLong(5, entity.getId());
    }

    public final Book getEntityById(long id) throws SQLException {
        try (PreparedStatement getByIdPrepareStatement =
                     connection.prepareStatement(SQL_SELECT_BY_ID)) {
            getByIdPrepareStatement.setLong(1, id);
            ResultSet entity = getByIdPrepareStatement.executeQuery();
            if (entity.next()) {
                Book book = setEntityFields(entity);
                entity.close();
                return book;
            }
            return null;
        }
    }
}
