package hello.servlet.web.frontcontroller;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter@Setter
public class ModelView {
    //뷰의 논리적 이름
    private String viewName;
    private Map<String, Object> model = new HashMap<>();



    public ModelView(String viewName) {
        this.viewName = viewName;
    }


}
