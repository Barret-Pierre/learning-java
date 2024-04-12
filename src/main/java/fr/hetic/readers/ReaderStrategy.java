package fr.hetic.readers;

import fr.hetic.models.File;

import java.sql.SQLException;
import java.util.List;

public interface ReaderStrategy {
    List<File> getFile() throws SQLException;
}
