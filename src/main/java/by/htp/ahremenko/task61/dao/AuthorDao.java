package java.by.htp.ahremenko.task61.dao;

import java.by.htp.ahremenko.task61.model.Author;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorDao extends AbstractDao<Author> {

    private static String SQL_SELECT_BY_ID = "SELECT * FROM author AS a WHERE a.id=?";

    public AuthorDao(Connection connection) {
        super(connection);

        SQL_SELECT_SORTED_PAGE = "SELECT * FROM author ORDER BY %s %s LIMIT ?, ?";
        SQL_INSERT = "INSERT INTO autho "
                + "(name) "
                + "VALUES (?)";
        SQL_UPDATE = "UPDATE author "
                + "SET name=? "
                + "WHERE id=?";
        SQL_SELECT_FILTERED_ENTITIES = "SELECT * FROM author "
                + "WHERE (name=? OR ?='')";
    }

    public final Author getEntityById(long id) throws SQLException {
        try (PreparedStatement getByIdPrepareStatement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            getByIdPrepareStatement.setLong(1, id);
            ResultSet entity = getByIdPrepareStatement.executeQuery();
            if (entity.next()) {
                Author author = setEntityFields(entity);
                entity.close();
                return author;
            }
            return null;
        }
    }

    @Override
    protected final void setValuesForInsertIntoPrepareStatement(PreparedStatement prepareStatement, Author author)
            throws SQLException {
        int parametrIndex=1;
        prepareStatement.setString(parametrIndex++, author.getName());
    }

    @Override
    protected final void setValuesForUpdateIntoPrepareStatement(PreparedStatement prepareStatement, Author author)
            throws SQLException {
        setValuesForInsertIntoPrepareStatement(prepareStatement, author);
        prepareStatement.setLong(2, author.getId());

    }

    @Override
    protected final Author setEntityFields(ResultSet entityTableRow) throws SQLException {
        Author author = new Author();
        author.setId(entityTableRow.getLong("id"));
        author.setName(entityTableRow.getString("name"));
        return author;
    }
}
