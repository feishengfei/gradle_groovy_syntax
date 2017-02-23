/* Method definition */

def someMethod() {
	'method called'
}

String anotherMethod() {
	'another method called'
}

def thirdMethod(param1) {
	"$param1 passed"
}

static String fourthMethod(String param1) {
	"$param1 passed"
}

println "method1:" + someMethod()
println "method2:" + anotherMethod()
println "method3:" + thirdMethod("hello")
println "method4:" + fourthMethod('hello')

/* Named arguments */
def foo(Map args) {
	"${args.name}: ${args.age}"
}

println 'foo:' + foo(name: 'Marie', age: 1)

/* Default arguments */

def foo(String par1, Integer par2 = 1) {
	[name:par1, age:par2]
}

assert foo('Marie').age == 1

/* Varargs */

def foo(Object... args) {
	args.length
}

assert foo() == 0
assert foo(1) == 1
assert foo(1, 2) == 2
//assert foo(null) == null

def bar(Object[] args) {
	args.length
}

assert bar() == 0
assert bar(1) == 1
assert bar(1, 2) == 2
//assert bar(null) == null

def foo01(Object... args) {1}
def foo02(Object x) {2}
