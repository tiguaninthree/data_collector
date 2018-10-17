package grabber.service.api;

import grabber.model.Wine;

public interface JsonDeserializerService {

    Wine deserializeJsonNodeToWineObject(Object object);
}
