class Data {
	private int id
	protected String description
	public static final boolean DEBUG = false
//	private String idStr = IDGenerator.next()
}

class BadPractice {
	private mapping
}

class GoodPractice {
	private Map<String, String> mapping
}

class Person01 {
	private String name
	private int age
	String toString() {
		"name:" + name + ",age:" + age
	}
}

class Person02 {
	final String name
	final int age
	Person02(String name, int age) {
		this.name = name
		this.age = age
	}
	String toString() {
		"name:" + name + ",age:" + age
	}
}

class Person03 {
	String name
	void name(String name) {
		this.name = "Wonder$name"
	}
	String wonder() {
		this.name
	}
}

class PseudoProperties {
	// a pseudo property "name"
	void setName(String name) {}
	String getName() {}

	// a pseudo read-only property "age"
	int getAge() { 42 }

	// a pseudo write-only property "groovy"
	void setGroovy(boolean groovy) {  }
}

println Data.class
def d = new Data()

p01 = new Person01()
p01.name = "Frank"
p01.age = 1
println "p01:" + p01.toString()

p02 = new Person02("Frank", 2)
println "p02:" + p02.toString()

p03 = new Person03()
p03.name('Frank')
println "p03:" + p03.wonder()

//assert p01.properties.keySet().containsAll(['name', 'age']) //private doesn't support
assert p02.properties.keySet().containsAll(['name', 'age'])
assert p03.properties.keySet().containsAll(['name'])

println p01.properties.keySet()
println p02.properties.keySet()
println p03.properties.keySet()

def p = new PseudoProperties()
p.name = 'Foo'
assert p.age == 42
p.groovy = true
