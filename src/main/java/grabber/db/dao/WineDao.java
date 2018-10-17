package grabber.db.dao;

import grabber.model.Wine;

import java.util.List;

public interface WineDao {

    void create(Wine wine);

    List<Wine> findByWineName(String name);

    void saveImage(Wine wine);

}
