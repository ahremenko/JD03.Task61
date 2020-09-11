package java.by.htp.ahremenko.task61.dao;

import java.by.htp.ahremenko.task61.model.Genre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreDao extends AbstractDao<Genre> {

        private static String SQL_SELECT_BY_ID = "SELECT * FROM genre AS g WHERE g.id=?";

        public GenreDao(Connection connection) {
            super(connection);

            SQL_SELECT_SORTED_PAGE = "SELECT * FROM genre ORDER BY %s %s LIMIT ?, ?";
            SQL_INSERT = "INSERT INTO genre "
                    + "(name) "
                    + "VALUES (?)";
            SQL_UPDATE = "UPDATE genre "
                    + "SET name=? "
                    + "WHERE id=?";
            SQL_SELECT_FILTERED_ENTITIES = "SELECT * FROM genre "
                    + "WHERE (name=? OR ?='')";
        }

        public final Genre getEntityById(long id) throws SQLException {
            try (PreparedStatement getByIdPrepareStatement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
                getByIdPrepareStatement.setLong(1, id);
                ResultSet entity = getByIdPrepareStatement.executeQuery();
                if (entity.next()) {
                    Genre genre = setEntityFields(entity);
                    entity.close();
                    return genre;
                }
                return null;
            }
        }

        @Override
        protected final void setValuesForInsertIntoPrepareStatement(PreparedStatement prepareStatement, Genre genre)
                throws SQLException {
            int parametrIndex=1;
            prepareStatement.setString(parametrIndex++, genre.getName());
        }

        @Override
        protected final void setValuesForUpdateIntoPrepareStatement(PreparedStatement prepareStatement, Genre genre)
                throws SQLException {
            setValuesForInsertIntoPrepareStatement(prepareStatement, genre);
            prepareStatement.setLong(2, genre.getId());

        }

        @Override
        protected final Genre setEntityFields(ResultSet entityTableRow) throws SQLException {
            Genre genre = new Genre();
            genre.setId(entityTableRow.getLong("id"));
            genre.setName(entityTableRow.getString("name"));
            return genre;
        }
    }
}
