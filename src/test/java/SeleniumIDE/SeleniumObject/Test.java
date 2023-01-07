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
public class Test {
    private String id;
    private String name;
    private List<Command> commands;
}
