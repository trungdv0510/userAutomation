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
public class TestCase {
    private String id;
    private String version;
    private String name;
    private String url;
    private List<Test> tests;
    private List<Suite> suites;
    private List<String> urls;
    private List<String> plugins;
}
