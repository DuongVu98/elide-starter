package org.tony.elidestarter.config;

import com.yahoo.elide.core.datastore.DataStoreTransaction;
import com.yahoo.elide.datastores.noop.NoopDataStore;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.tony.elidestarter.dto.CategoryResponse;

@Slf4j
public class CategoryDataStore extends NoopDataStore {

  private Set<CategoryResponse> categories = new HashSet<>();

  public CategoryDataStore(Collection<Class> entityClasses) {
    super(entityClasses);
    init();
  }

  void init() {
    log.info("Initializing CategoryDataStore");
    Faker faker = new Faker();
    categories = IntStream.range(0, 10)
        .mapToObj(i -> CategoryResponse.builder()
            .id(String.valueOf(i))
            .label(faker.commerce().brand())
            .description(faker.lorem().paragraph())
            .build()
        )
        .collect(Collectors.toSet());
  }

  @Override
  public DataStoreTransaction beginReadTransaction() {
    return new CategoryDataStoreTransaction(categories);
  }
}
