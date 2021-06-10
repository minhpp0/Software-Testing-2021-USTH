Ex 1.4
The following exercise is intended to encourage you to think of testing in a more rigorous
way than you may be used to. The exercise also hints at the strong relationship between
specification clarity, faults, and test cases.

(a) Write a Java method with the signature import java.util.Vector;

class Vector_test { public static Vector union (Vector a, Vector b) { Vector v = new Vector(); v.addAll(a); v.addAll(b); return v; } public static void main(String[] args) { Vector a = new Vector(); a.add(10); Vector b = new Vector(); b.add(8); Vector v = union(a, b); } }

(b) You may see a variety of flaws and ambiguities in the supplied task after more reflection. In other words, there are several potential for flaws. Make a list of as many possible flaws as you can. One probable flaw is the absence of verification statements, such as ensuring that the two vectors are not empty or have different dimensions.

(c) var a = new Vector(); var b = new Vector(); a.add("name"); var student = new Student("name", 21); b.add(student); var c = union(a, b)

(d) Rewrite the method signature so that it is exact enough to eliminate the flaws and ambiguities that were discovered before. You might want to include examples from your test cases to demonstrate your requirements.

public static Vector union(Vector a, Vector b, boolean inv = False) { if (a.isEmpty() && b.isEmpty()) return Null; else { if (inv) { Vector v = new Vector(); v.addAll(b); v.addAll(a); return v; } else { Vector v = new Vector(); v.addAll(a); v.addAll(b); return v; } } }

