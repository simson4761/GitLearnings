package com.example.gitLearning

//01. Kotlin's major advantage is its null safety property, you can make a variable accept null values by simply adding this symbol to the end "?"
fun nullable(value: String?) {
    println(value?.length ?: "Value is null!")
}
// Output when value is null: "Value is null!"
// Output when value is "Kotlin": "6"

//02. Using type alias, we can give our own name to any complex data types
typealias IntList = List<Int>
fun alias(numbers: IntList): Int {
    return numbers.sum()
}
// Output when passing listOf(1, 2, 3, 4): "10"

//03. Int, float, string, boolean, double
fun dataTypes() {
    val intVal: Int = 42
    val doubleVal: Double = 42.42
    val stringVal: String = "Kotlin"
    val booleanVal: Boolean = true
    println("Int: $intVal, Double: $doubleVal, String: $stringVal, Boolean: $booleanVal")
}
// Output: "Int: 42, Double: 42.42, String: Kotlin, Boolean: true"

//04. In kotlin you can specify the data type as Any, and kotlin can automatically cast it into a data type using checks
fun smartCast(value: Any) {
    when (value) {
        is Int -> println("Integer: $value")
        is String -> println("String: $value")
        else -> println("Unknown type")
    }
}
// Output when passing 42: "Integer: 42"
// Output when passing "Kotlin": "String: Kotlin"
// Output when passing 3.14: "Unknown type"

//05. You can insert variables inside double quotes using, string template, and raw string can be used if you don't want to treat special characters as special
fun greetUser(name: String) {
    println("Hello, $name!")
}
// Output when passing "Kotlin": "Hello, Kotlin!"

fun rawString() {
    val rawString = """
        This is a raw string.
        It can span multiple lines.
        |Each line can have a prefix.
    """.trimMargin()
    println(rawString)
}
// Output:
// "This is a raw string.
// It can span multiple lines.
// |Each line can have a prefix."

// 06. Private : can be accessed only inside the class/subclass, Protected : can be accessed only within the same class/file, Public : can be accessed from anywhere
class ExampleClass {
    private val privateField = "Private Field"
    protected val protectedField = "Protected Field"
    public val publicField = "Public Field"

    fun printFields() {
        println("Private: $privateField, Protected: $protectedField, Public: $publicField")
    }
}
// Output when calling `printFields`: "Private: Private Field, Protected: Protected Field, Public: Public Field"

// 07. Properties and backing fields, allows us to code getters and setters with custom rule. we can use it to make sure a variable is not getting updated with an invalid value. For example, we can prevent age from being a negative value, without writing an if else logic everywhere
class Person {
    var age: Int = 0
        set(value) {
            field = if (value >= 0) value else 0  // Backing field used
        }

    var sex : Char = 'M'
}
// Output when setting age to -5: "Person's age: 0"

// 08. Instead of passing the variable inside the function as param, in extension function we can attach the function as an extension to the param variable
fun String.reverseText(): String {
    return this.reversed()
}
// Output when calling this function on "Kotlin": "niltok"

// 09. Inline function can be passed as a param to other functions, and will run automatically
inline fun performAction(action: () -> Unit) {
    println("Before action")
    action()  // The code of action will be inserted here directly
    println("After action")
}
// Output when calling `performAction` with a lambda that prints "Hello, Kotlin!":
// "Before action"
// "Hello, Kotlin!"
// "After action"

// 10. So companion objects can be called without ever needing to create an instance of the class that it belongs to
class Db {
    companion object {
        fun connect() {
            println("Connected to database")
        }
    }
}
// Output when calling `Database.connect()`: "Connected to database"

//11. Anonymous object are used when you don't have create a separate class to implement an interface, you can simply
// you can simply create an anonymous object to use a abstract function from an interface
interface Greeter {
    fun greet(name: String)
}

fun anonymousObj() {
    val greeter = object : Greeter {  // Anonymous object
        override fun greet(name: String) {
            println("Hello, $name!")
        }
    }
    greeter.greet("Alice")  // Output: Hello, Alice!
}

// 12. internal modifier is less open than public modifier and more lenient than private access modifier

public val name : String = "Simson"
internal val age : String = "21"
private val height : String = "6ft"

fun test(){

    //Assuming they are in different class and different module/package
    print(name) // no issues
    print(age) // cant access
    print(height) // cant access

    //Assuming they are in different class but same module
    print(name) // no issues
    print(age) // no issues
    print(height) // cant access

    //Assuming they are same class
    print(name) // no issues
    print(age) // no issues
    print(height) // no issues

}

// 13.When expression is a great alternative to writing exhaustive if-else ladders , but only drawback might
// be that you have to write a else condition (there are some exceptions) compulsorily
fun whenNumbers(number: Int): String {
    return when {
        number < 0 -> "Negative"
        number == 0 -> "Zero"
        number in 1..10 -> "Between 1 and 10"
        number > 10 -> "Greater than 10"
        else -> "Unknown"
    }
}

// 14. Similar to anonymous objects , lambda functions can be defined and used without a function name
//
val greet: () -> String = { "Hello, Kotlin!" }

fun greetMain() {
    println(greet())  // Output: Hello, Kotlin!
}

// 15. Lambda it-> , here "it" refers to the current element that we are iterating , through.
// If there is only one element expected , then no need to rename(but you can) ,if there is more than one
// then you have to rename

fun sampleLamdaInt(){
    val numbers = listOf(1, 2, 3)

    numbers.forEach { it -> // lambda it
        println(it)
    }
}

// 16. Scope functions : let, run, apply, with, also.
// 16.1 : let : this block only executes when the value is not null
// 16.2 : run : this is block of code , that's not reusable, its similar to an inline function
// 16.3 : apply : this block is similar to run block but focuses on modifying object
// 16.4 : with : compact way you put multiple action in a inline function without repeating the param inside the body
// 16.5 : also : similar to run , but wont alter the original value
fun scopeFunctions(){
    // Let
    val name: String? = "Alice"
    name?.let {
        println("Hi, $it!")
    }

    // Run
    val score = 95
    val message = score.run {
        if (this > 90) "Great job!" else "Keep trying!"
    }

    // Apply
    val student = StringBuilder("Student")
    student.apply {
        append(" is learning Kotlin!")
    }

    //  With
    val teacher = StringBuilder("Teacher")
    val result = with(teacher) {
        append(" is teaching Kotlin!")
        append(" And doing a great job!")
        toString()
    }

    // Also
    val exam = "Math Test"
    exam.also {
        println("I’m preparing for $it")
    }
    // Output: I’m preparing for Math Test , but value of exam wont change

}

// 17 : lambda expression , is interchangeable with lambda functions (refer to question 15)

//18 : Lambdas With Receivers, are lambda functions where we can modify the properties of the object
fun lambdaWithExpression(){
    val person = Person()

    val myCar = Person().apply {
       age = 69
    }
}

// 19 : kotlin collections , these are inbuilt fucntions provided by kotlin
fun kotlinCollections() {
    val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    // filter : as the name suggest it filters based on the condition
    val evenNumbers = numbers.filter { it % 2 == 0 }
    println("Even numbers: $evenNumbers")  // Output: Even numbers: [2, 4, 6, 8, 10]

    // map , is used to paste all the values to a new variable with a custom condition
    val doubledEvenNumbers = evenNumbers.map { it * 2 }
    println("Doubled even numbers: $doubledEvenNumbers")  // Output: Doubled even numbers: [4, 8, 12, 16, 20]

    // distinct : will remove duplicates!
    val uniqueDoubledNumbers = doubledEvenNumbers.distinct()
    println("Unique doubled numbers: $uniqueDoubledNumbers")  // Output: Unique doubled numbers: [4, 8, 12, 16, 20]

    // similar to filter , but return type is boolean , great for checking conditions
    val anyGreaterThanFifteen = uniqueDoubledNumbers.any { it > 15 }
    println("Is there any number greater than 15? $anyGreaterThanFifteen")  // Output: true

    // similar to any , but its like a AND condition , and "any" is a OR  condition
    val allLessThanTwentyFive = uniqueDoubledNumbers.all { it < 25 }
    println("Are all numbers less than 25? $allLessThanTwentyFive")  // Output: true

    // sum : return the cumulative sum
    val sumOfNumbers = uniqueDoubledNumbers.sum()
    println("Sum of unique doubled numbers: $sumOfNumbers")  // Output: Sum of unique doubled numbers: 60

    // sortedDescending , sort in descending order
    val sortedNumbersDescending = uniqueDoubledNumbers.sortedDescending()
    println("Sorted numbers in descending order: $sortedNumbersDescending")  // Output: Sorted numbers in descending order: [20, 16, 12, 8, 4]

    // creates a map with respect to the condition provided
    val groupedNumbers = uniqueDoubledNumbers.groupBy { if (it > 15) "Greater than 15" else "Smaller than or equal to 15" }
    println("Grouped numbers: $groupedNumbers")
    // Output: Grouped numbers: {Smaller than or equal to 15=[4, 8, 12], Greater than 15=[16, 20]}

    // maxOrNull, minOrNull :  largest number (max) and smallest number (min) from the list
    val maxNumber = uniqueDoubledNumbers.maxOrNull()
    val minNumber = uniqueDoubledNumbers.minOrNull()

    println("Max number: $maxNumber")  // Output: Max number: 20
    println("Min number: $minNumber")  // Output: Min number: 4
}


// 20 : Map and Destructuring, map is a key value pair data structure, destructing allows you to
// define more than a single variable in a single declaration

fun mapAndDestructuringExample() {
    data class Person(val name: String, val age: Int)
    // Step 1: Create a map with key-value pairs
    val studentsGrades = mapOf(
        "Alice" to "A",
        "Bob" to "B",
        "Charlie" to "A",
        "David" to "C"
    )

    // Step 2: Print the map
    println("Students and their grades: $studentsGrades")
    // Output: Students and their grades: {Alice=A, Bob=B, Charlie=A, David=C}

    val person : Person = Person("simson",21)

    // Destructuring the person object into 'name' and 'age' variables
    val (name, age) = person
}

// 21: Sequences in Kotlin , main advantage is lazy evaluation , which means instead of iterating over
// entire list, it only does action on when needed , thus saving resources
fun sequenceKotlin() {
    val numbers = (1..1_000_000).asSequence()  // create a sequence

    val result = numbers
        .map { it * 2 }    // Double each number
        .filter { it % 5 == 0 }  // Keep only numbers divisible by 5

    println(result.take(10).toList())  // Output the first 10 elements
}

// 22 : Kotlin Generics , allows you to work with any type of data type without specifying type
fun <T> genericFunction(list: List<T>) : T {
    return list.first()
}

// 23 : Generics: Functions and Erasure , in jvm during runtime it can differentiate between date types
// every data types list is just a list.

// 24 : Generics: Reified Parameters in Kotlin , reified allow you to access the date type even after
// erasure
inline fun <reified T> isOfType(value: Any): Boolean {
    return value is T
}

fun reified() {
    println(isOfType<String>("Hello")) // true
    println(isOfType<Int>("Hello"))    // false
}

// 25 : Generic covariance , allows all type of data types , but still expects a sub type , also its read only
open class Animal {
    fun sound() = "Some sound"
}

class Dog : Animal() {
    fun bark() = "Bowww"
}

class Cage<out T>(private val animal: T) {
    fun getAnimal(): T = animal
}

fun covariance() {
    val dogCage: Cage<Dog> = Cage(Dog())
    val animalCage: Cage<Animal> = dogCage // Covariance allows this
    println(animalCage.getAnimal().sound())// Output: Some sound
    println(dogCage.getAnimal().bark())// Output: Bowww
}

// 26 ; Generic contravariance , instead of subtype it even allows supertypes // aka parent class
fun contravariance() {

    val dogCage: Cage<Dog> = Cage(Dog())
    val animalCage: Cage<Animal> = dogCage


    val animal: Animal = animalCage.getAnimal()

    // You cannot call `bark()` directly because `animal` is an Animal
    // But you can cast it back to Dog if you're sure:
    if (animal is Dog) {
        println(animal.bark()) // Output: "Bowww"
    }
}

//27 : Generics: Use-Site Variance , allows you to read from a class without specified type and not modify
class Box<T>(private val item: T) {
    fun getItem(): T = item
}

fun printAnimalSound(box: Box<out Animal>) {
    val animal: Animal = box.getItem() // Can safely read Animal or its subclass
    println(animal.sound()) // Prints sound of the animal
}

// 28 : Calling Java from Kotlin : java and kotlin are fully interoperable, so we can use it directly
//public class ExampleJavaFunction {
//    public static String greet(String name) {
//        return "Hello, " + name + "!";
//    }
//} here instead of pasting this function in a kotlin file , which im using now
// I have to create a separate java file and call it from there
fun kotlin() {
    // Call the Java function directly using the class name
//    val greeting = ExampleJavaFunction.greet("Kotlin")
//    println(greeting)  // Output: Hello, Kotlin!
}

// 29 Calling Kotlin Functions from Java : we have to do the exact opposite to the
// previous instructions mentioned
fun greet(name: String): String {
    return "Hello, $name!"
}

// Java class that calls Kotlin functions
/*public class Main {
    public static void main(String[] args) {
        // Creating an object of the Kotlin class
        ExampleKotlinFunction kotlinFunction = new ExampleKotlinFunction();

        // Calling Kotlin function greet() from Java
        String greeting = kotlinFunction.greet("Java");

        // Calling Kotlin function greetWithDefault() from Java (using default argument)
        String defaultGreeting = kotlinFunction.greetWithDefault("Java");

        // Printing the greetings
        System.out.println(greeting);  // Output: Hello, Java!
        System.out.println(defaultGreeting);  // Output: Hello, Java!
    }
} */