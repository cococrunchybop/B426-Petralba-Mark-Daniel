# Activity: Inheritance with Pen

## Objective

Apply inheritance by moving the shared data of `Ballpen` into a parent class `Pen`, then creating another child class of your choice.

## Instructions

Use your `Ballpen` class from the encapsulation activity as the starting point.

### Part 1: Create the parent class `Pen`

1. Create a new class named `Pen` with these private data members:
   - `String brand`
   - `String color`
   - `double weightInGrams`

2. Add a parameterized constructor for all three data members. Use `this`.

3. Add the getters and setters.

4. Add this method:

   ```java
   public void write() {
       System.out.println("Writing with a pen.");
   }
   ```

5. Override `toString()` to display the brand, color, and weight.

### Part 2: Update the `Ballpen` class

6. Make `Ballpen` extend `Pen`.

7. Keep only `pointSize` in `Ballpen`. Remove `brand`, `color`, and `weightInGrams`, because they are now inherited.

8. Update the constructor. Use `super(...)` to pass the brand, color, and weight to `Pen`.

9. Remove the getters and setters of the inherited fields from `Ballpen`.

10. Override `write()` so it prints `"Writing smoothly with a ballpen."`

11. Override `toString()` using `super.toString()`. Add the type and the point size.

### Part 3: Create your own child class

12. Choose ONE class to create. It must extend `Pen`.

| Class | Extra private data member |
|---|---|
| `Marker` | `boolean permanent` |
| `Pencil` | `String hardness` (example: "2B") |
| `Highlighter` | `String inkType` (example: "Neon") |

13. Your class must have:
    - A parameterized constructor that uses `super(...)`
    - A getter and setter for the extra data member
    - An overridden `write()` with its own message
    - An overridden `toString()` that uses `super.toString()`

### Part 4: Update the `Activity15_Petralba` class

14. Create one `Ballpen` object and one object of your own class.

15. For each object, print it using `System.out.println(object)`, then call `write()`.

## Sample Output (Marker)

```
=== Ballpen ===
Type: Ballpen
Brand: Pilot
Color: Black
Weight in Grams: 20.0
Point Size: 1.0
Writing smoothly with a ballpen.

=== Marker ===
Type: Marker
Brand: Pentel
Color: Red
Weight in Grams: 35.0
Permanent: Yes
Writing boldly with a marker.
```

## Stretch Goal (Optional)

Create a class `GelPen` that extends `Ballpen`. Add a `boolean waterproof` data member. Check that it can use the `pointSize` from `Ballpen` and the `brand` from `Pen`.
