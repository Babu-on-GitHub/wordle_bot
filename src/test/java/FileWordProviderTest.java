import org.junit.jupiter.api.Test;
import solver.FileWordProvider;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FileWordProviderTest {

    @Test
    void test(){
        try{
            FileWordProvider provider = new FileWordProvider();
            List<char[]> words = new ArrayList<>();
            while (provider.hasNext()){
                words.add(provider.next());
            }
            assertThat(words).hasOnlyElementsOfType(char[].class);
            assertThat(words).allMatch(x-> x.length == 5);
        }catch (FileNotFoundException e){
            System.out.println("FileWordProviderTest could not find the word dictionary. Check the resources");
        }
    }


}
