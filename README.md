# Types of design patterns

	* Creational
	* Structural
	* Behavioural

	
#Creational Patterns


## Singleton

* Only one instance
* Single point of access for a resource

* Uses:
	* Network Manager
	* Database Access
	* Logging
	* Utility classes


```kotlin
class Singleton private constructor() {
    private object HOLDER {
        val INSTANCE = Singleton()

        companion object {
            val instance: Singleton by lazy { HOLDER.INSTANCE }
            //Your CODE
        }
    }
}
```

```kotlin
object Singleton {
	//Your CODE
}
```

## Factory Method

* Provides a way to access functionality without caring about implementation
* Separation of concerns
* Allow for testability

## Abstract Factory


* Provides a way to access functionality without caring about implementation
* One level of abstraction over the factory pattern
* Separation of concerns
* Allows for testability

## Builder

Used when we have multiple parameters to initialize

```kotlin
val dialog = AlertDialog.Builder(context)
	setTitle()
	setMessage()
	setPositiveButton()
	setNegativeButton()
	create()
```
Used by many developers, implemented by few

For many parameters, it's impractical to build all constructor, 5 parameter combinations -> 120 constructors

Kotlin solves this problem partially with named parameters. Does not work with java code

Optional parameters

```kotlin
class Component private constructor(builder: Builder){
	var param1
	var param2
	var param3
	
	class Builder{
		var param1
		var param2
		var param3
		
		fun setParam1(param1) = apply {this. param1 = param1}
		fun setParam2(param2) = apply {this. param2 = param2}
		fun setParam3(param3) = apply {this. param3 = param3}
		fun build() = Component(this)
	}
	
	init{
		param1 = builder.param1
		param2 = builder.param2
		param3 = builder.param3
	}
	
}
```

## Lazy initialization

Very useful technique for memory management

Initialize a resource when it's needed, not when declared

Lazy vs eager initialization

```kotlin
class Window{
	val box = ALextBox()
	
	fun showMessage(message:String){
		box.setMessage(message)
		box.build()
		box.show()
	}
	
}


class Window{
	val box = ALextBox
	
	fun showMessage(message:String){
		if(box == null)
			box = AlextBox()
		box.setMessage(message)
		box.build()
		box.show()
	}
	
}

```

Kotlin has built in lazy initialization

```kotlin
class Window{
	val box by lazy { ALextBox() }
	
	fun showMessage(message:String){
		box.setMessage(message)
		box.show()
	}

}
```

Can only be used with val properties

Kotlin allows for late initialization


```kotlin
class Window{
	lateinit var box : ALextBox
	
	fun showMessage(message:String){
		box.AlertBox()
		box.setMessage(message)
		box.show()
	}

}
```
Can be used with var properties

Crash if variable not initialized before use 

## Prototype

Lets you copy existing objects

Without depending on their classes

Only reliant on interface

The copied object must provide the copy functionality

#Structural Patterns

## Adapter

Converts the interface of a class into another interface the client expects

Convert data from one format into another

Used extensively in Android

## Bridge

Having classes with multiple orthogonal train exponentially increases the sis of the inheritance tree

Split into multiple interfaces/classes

Associate them using a "bridge" reference

## Facade

Provide a simple interface to a complex functionality

Removes the need for complex object/memory management

Simplifies client implementation

## Decorator

Attach new behaviour to an object

Without altering existing code

Overriding behaviour

## Composite

Compose objects into tree structures

Works when the core functionality can be represented as a tree

Manipulate many objects as a single one

## Proxy

Provide some functionality beside and/or after calling an object

Similar to facade, except the proxy has the same interface

#Behavioural patterns

## Observer

Define a subscription mechanism

Notify multiple objets simultaneously

One-to-many relationship

## Chain of responsibility

Define a chain of handlers to process a request.

Each handler contains a reference to the next handler.

Each handler decides to process the request AND / OR pass it on.

Request can be of different types.

## Command

A request is wrapped in an object that contains all request info

The command object is passed to the correct handler

## Strategy

A class behaviour or algorithm can be changed at run time

Objects contain algorithm logic

Context object that can handle algorithm objects

User when we want to be able to add functionality without changing program structure

##State

An object changes its behaviour based on an internal state

At any moment, there's finite number of states a program can be in

State can be encapsulated in an object

## Visitor

Separation between an algorithm and the object they operator on

2 concepts: visitor and element(visitable)

The element accepts visitor type object