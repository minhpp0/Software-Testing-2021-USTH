```Java
import org.junit.*;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import static org.junit.Assume.*;

import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import java.util.*;

@RunWith (Theories.class)
public class ListTheoryTest
{
   @DataPoints
   public static String[] string = {"ant", "bat", "cat"};

   @DataPoints
   public static List[] lists = {
      Arrays.asList ("ant", "bat"),
      Arrays.asList ("bat", "cat", "dog", "elk"),
      Arrays.asList ("Snap", "Crackle", "Pop")
   };


   @Theory
   public void removeThenAddDoesNotChangeList
                   (List<String> list, String string)  // Parameters!
   {
      assumeTrue (list != null);            // Assume
      assumeTrue (list.contains (string));  // Assume
      List<String> copy = new ArrayList<String>(list);   // Act
      copy.remove (string);                       
      copy.add (string);
      assertTrue (list.equals (copy));      // Assert
    }
}
```