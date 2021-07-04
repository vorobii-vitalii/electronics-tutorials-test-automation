package dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Link {
    private String text;
    private String href;
}
