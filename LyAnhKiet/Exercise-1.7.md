Exercise 1.7

Consider the following three example classes. These are OO faults taken from Joshua Bloch’s Effective Java, Second Edition. Answer the following questions about each.

## Vehicle, Truck, CloneTest
```Java
// Vehicle.java

public class Vehicle implements Cloneable
{
   private int x;

   public Vehicle(int y) { x = y;}

   @Override public Object clone() {
      Object result = new Vehicle(this.x);
      // Location "A"
      return result;
   }
   @Override public boolean equals (Object o) {
       if (!(o instanceof Vehicle)) return false;
       Vehicle v = (Vehicle) o;
       return v.x == this.x;
   }
}
```
```Java
// Truck.java

public class Truck extends Vehicle
{
   private int y;

   public Truck(int z) { super(z); y = z;}

   @Override public Object clone() {
      Object result = super.clone();
      // Location "B"
      ((Truck) result).y = this.y;  // throws ClassCastException
      return result;
   }
   @Override public boolean equals (Object o) {
       if (!(o instanceof Truck)) return false;
       Truck t = (Truck) o;
       return super.equals(t) && t.y == this.y;
   }
   // other methods omitted
}
```
```Java
// CloneTest.java

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;

public class CloneTest
{
   // this test passes
   @Test public void cloneSuper() {
      Vehicle v = new Vehicle(4);
      Vehicle w = (Vehicle) v.clone();
      assertFalse(v == w);
      assertEquals(v.getClass(), w.getClass());
      assertTrue(v.equals(w));
   }

   // this test fails!
   @Test public void cloneSub() {
      Truck s = new Truck(4);
      Truck t = (Truck) s.clone();
      assertFalse(s == t);
      assertEquals(s.getClass(), t.getClass());
      assertTrue(s.equals(t));
   }
}
```
## BigDecimalTest
```Java
// BigDecimalTest.java

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
import java.math.*;

public class BigDecimalTest {
  private BigDecimal x;
  private BigDecimal y;

  Set <BigDecimal> tree;
  Set <BigDecimal> hash;

  @Before public void setUp() {
     x = new BigDecimal("1.0");
     y = new BigDecimal("1.00");
     // Fact:  !x.equals(y), but x.compareTo(y) == 0

     tree = new TreeSet <BigDecimal> ();
     hash = new HashSet <BigDecimal> ();
  }

  // this test fails!
  @Test public void inconsistentSets() {
     tree.add(x); tree.add(y);
     // TreeSet uses compareTo(), so tree now has 1 element

     hash.add(x); hash.add(y);
     // HashSet uses equals(), so hash now has 2 elements

     assertEquals(tree, hash);   // hence this assertion cannot possibly be true
  }
}  
```
## Point, ColorPoint, PointTest
```Java
// Point.java

public class Point
{
   private int x; private int y;
   public Point(int x, int y) { this.x=x; this.y=y; }

   @Override public boolean equals(Object o) {
      // Location A
      if (!(o instanceof Point)) return false;
      Point p = (Point) o;
      return (p.x == this.x) && (p.y == this.y);
   }
}
```
```Java
// ColorPoint.java

public class ColorPoint extends Point
{
   private Color color;
   // Fault: Superclass instantiable; subclass state extended

   public ColorPoint(int x, int y, Color color) {
      super (x,y);
      this.color=color;
   }

   @Override public boolean equals(Object o) {
      // Location B
      if (!(o instanceof ColorPoint)) return false;
      ColorPoint cp = (ColorPoint) o;
      return (super.equals(cp) &&  (cp.color == this.color));
   }
}

enum Color { RED, WHITE, BLUE }
```
```Java
// PointTest.java

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;

public class PointTest {
   private Point p  = new Point(1,2);
   private ColorPoint cp1   = new ColorPoint(1,2,Color.RED);
   private ColorPoint cp2   = new ColorPoint(1,2,Color.BLUE);

   // this test fails!
   @Test public void symmetry() {
      assertEquals(p.equals(cp1), cp1.equals(p));
   }

   // this test passes
   @Test public void transitivity() {
      if (cp1.equals(p) && p.equals(cp2)) {
         assertTrue(cp1.equals(cp2));
      }
   }
}
```

(a) Explain what is wrong with the given code. Describe the fault precisely by proposing a modification to the code.

(b) If possible, give a test case that does not execute the fault. If not, briefly explain why not.

(c) If possible, give a test case that executes the fault, but does notresult in an error state. If not, briefly explain why not.

(d) If possible give a test case that results in an error, but not a failure. If not, briefly explain why not. Hint: Don’t forget about the program counter.

(e) In the given code, describe the first error state. Be sure to describe the complete state.

(f) Implement your repair and verify that the given test now produces the expected output. Submit a screen printout or other evidence that your new program works.

a)
#### Vehicle.java, Truck.java, CloneTest.java

- The fault is in method Truck.clone(). One of possible modification is:
```Java
@Override public Object clone() {
      Object result = new Vehicle(this.y);
      return result;
   }
```

#### BigDecimalTest.java
 The fault in is the inconsistency between methods compareTo() and equals(). One of possible modification is:  
 - Override one of them  
 - Overide methods in HashSet or TreeSet  

#### Point.java, ColorPoint.java, PointTest.java 
The fault in is the inconsistency between superclass and subclass. One of possible modification is:  
 - Write same.

b)

- Test case, which does not execute the fault in (Vehicle.java, Truck.java, CloneTest.java), does not uses Truck.equals()

- No test case that can not execute the fault in (BigDecimalTest.java)

- No test case that can not execute the fault in (Point.java, ColorPoint.java, PointTest.java)

c)

- Test case, which does execute the fault in (Vehicle.java, Truck.java, CloneTest.java) and does not create result in an error state, does not test subclass

- Test case, which does execute the fault in (BigDecimalTest.java) and does not create result in an error state, test with 1.00 and 2.00

- No test case that can execute the fault in (Point.java, ColorPoint.java, PointTest.java) and does not create result in an error state

