package dynamic;

import java.util.LinkedList;
import java.util.List;

public class InsultServiceImpl implements InsultService {
    private List<String> insults = new LinkedList<>();

    public void addInsult(String insult) {
        insults.add(insult);
    }

    public String getInsult() {
        return insults.isEmpty() ? "Empty list" : insults.get(insults.size()-1);
    }

    public String getAllInsults() {
        return insults.toString();
    }
}
