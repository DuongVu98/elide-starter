package org.tony.elidestarter.config;

import com.yahoo.elide.core.RequestScope;
import com.yahoo.elide.core.datastore.DataStoreIterable;
import com.yahoo.elide.core.request.EntityProjection;
import com.yahoo.elide.datastores.noop.NoopTransaction;
import java.io.Serializable;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.tony.elidestarter.dto.CategoryResponse;

@RequiredArgsConstructor
public class CategoryDataStoreTransaction extends NoopTransaction {

  private final Set<CategoryResponse> categories;

  @Override
  public <T> DataStoreIterable<T> loadObjects(EntityProjection entityProjection,
      RequestScope scope) {
    if (entityProjection.getType().getCanonicalName()
        .equals(CategoryResponse.class.getCanonicalName())) {
      return () -> (Set<T>) categories;
    } else {
      return null;
    }
  }

  @Override
  public <T> T loadObject(EntityProjection projection, Serializable id, RequestScope scope) {
    return (T) categories.stream().filter(c -> id.equals(c.getId())).findFirst().get();
  }
}
