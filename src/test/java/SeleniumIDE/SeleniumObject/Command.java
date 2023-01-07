package SeleniumIDE.SeleniumObject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Command {
    private String id;
    private String comment;
    private String command;
    private String target;
    private List<String[]> targets;
    private String value;
}
