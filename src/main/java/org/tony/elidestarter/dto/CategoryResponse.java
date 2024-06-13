package org.tony.elidestarter.dto;

import com.yahoo.elide.annotation.Include;
import com.yahoo.elide.annotation.ReadPermission;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Include(name = "categories")
@ReadPermission(expression = "Prefab.Role.All")
@Schema(description = "A Category model description", title = "CategoryResponse")
public class CategoryResponse {

  @Id
  private String id;
  private String label;
  private String description;
}
