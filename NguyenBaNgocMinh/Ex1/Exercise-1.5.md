Ex 1.5

a) findLast: The for loop's condition is incorrect. The condition I >= 0 is used to iterate through all of the elements in the array.
lastZero: We loop through an array from the final element to the first element to find the last index of an element (not from the first element to the last element)
countPositive: The condition of if statement is wrong. Positive elements are elements greater than 0 (not >=)
oddOrPos: The remainder divided by two must be non-zero in order to establish whether a number is odd or not (not equal to 1)

b) Test cases that does not execute the fault: findLast: x = [1, 0, 2], y = 0 lastZero: x = [1, 0, 2] countPositive: x = [-1, 1, 2] oddOrPos: x = [0, 1, 2]

c) Test cases that execute the fault: findLast: x = [0, 1, 2], y = 0 lastZero: x = [0, 1, 0, 2] countPositive: x = [0, 1, 2] oddOrPos: x = [-1, 0, 1]

(d) If possible give a test case that results in an error, but not a failure. If not, briefly explain why not. Hint: Don’t forget about the program counter.

(e) For the given test case, describe the first error state. Be sure to describe the complete state.

(f) f) Find last index of element public int findLast(int[] x, int y) { for (int i = x.length - 1; i >= 0; i--) { if (x[i] == y) { return i; } } return -1; }

Find last index of zero public static int lastZero(int[] x) { for (int i = x.length - 1; i >= 0; i--) { if (x[i] == 0) { return i; } } return -1; }

Count positive elements public int countPositive(int[] x) { int count = 0; for (int i = 0; i < x.length; i++) { if (x[i] > 0) { count++; } } return count; }

Count odd or positive elements public int oddOrPos(int[] x) { int count = 0; for (int i = 0; i < x.length; i++) { if (x[i] % 2 != || 0 x[i] > 0) { count++; } } return count; }