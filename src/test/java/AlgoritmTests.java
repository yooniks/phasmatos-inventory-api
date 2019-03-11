import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Test;

public class AlgoritmTests {

  @Test
  public void fillBorders() {
    List<Integer> borders = new ArrayList<>();
    for (int i = 0; i < 100; i ++) {
      int copied = i;
      if (i % 9 == 0) {
        if (i != 0) copied--;
        borders.add(copied);
      }
    }
    System.out.println(borders.stream().map(String::valueOf).collect(Collectors.joining(",")));
  }

}
