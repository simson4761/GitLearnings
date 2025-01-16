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

//11. Anonymous object are used when you dont have create a seperate class to implement an interface, you can simply
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

// 15. Lamda it-> , here "it" refers to the current element that we are iterating , through.
// If there is only one element expected , then no need to rename(but you can) ,if there is more than one
// then you have to rename

fun sampleLamdaInt(){
    val numbers = listOf(1, 2, 3)

    numbers.forEach { it -> // lamda it
        println(it)
    }
}
