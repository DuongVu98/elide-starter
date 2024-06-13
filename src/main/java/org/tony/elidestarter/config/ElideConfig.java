package org.tony.elidestarter.config;

import com.yahoo.elide.ElideSettingsBuilderCustomizer;
import com.yahoo.elide.core.audit.Slf4jLogger;
import com.yahoo.elide.core.dictionary.EntityDictionary;
import com.yahoo.elide.core.filter.dialect.RSQLFilterDialect;
import com.yahoo.elide.datastores.multiplex.MultiplexManager;
import com.yahoo.elide.jsonapi.JsonApiSettingsBuilderCustomizer;
import com.yahoo.elide.spring.api.OpenApiDocumentCustomizer;
import com.yahoo.elide.spring.datastore.config.DataStoreBuilderCustomizer;
import io.swagger.v3.oas.models.info.Info;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.tony.elidestarter.dto.CategoryResponse;

@Configuration
public class ElideConfig {

  @Bean
  @Primary
  public DataStoreBuilderCustomizer dataStoreBuilderCustomizer() {
    return builder -> builder
        .dataStore(new CategoryDataStore(List.of(CategoryResponse.class)))
        .multiplexer(MultiplexManager::new);
  }

  @Bean
  @Primary
  public JsonApiSettingsBuilderCustomizer jsonApiSettingsBuilderCustomizer(
      EntityDictionary entityDictionary) {
    return builder -> builder
        .enabled(true)
        .path("/api")
        .joinFilterDialect(RSQLFilterDialect.builder().dictionary(entityDictionary).build())
        .subqueryFilterDialect(RSQLFilterDialect.builder().dictionary(entityDictionary).build());
  }

  @Bean
  @Primary
  public ElideSettingsBuilderCustomizer elideSettingsBuilderCustomizer() {
    return builder -> builder
        .auditLogger(new Slf4jLogger());
  }

  @Bean
  public OpenApiDocumentCustomizer openApiDocumentCustomizer() {
    return openApi -> {
      Info info = new Info()
          .title("My Title");
      openApi.setInfo(info);
    };
  }
}
