package grabber.service.api;

import grabber.model.Wine;

@Deprecated
public interface JsonDeserializerService {

    Wine deserializeJsonNodeToWineObject(Object object);
}
