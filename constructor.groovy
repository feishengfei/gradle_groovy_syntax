class PersonConstructor {
	String name
	Integer age

	PersonConstructor(name, age) {
		this.name = name
		this.age = age
	}

	void show() {
		println 'name:' + name + ', age:' + age
	}
}

class PersonWOConstructor {
	String name
	Integer age

	void show() {
		println 'name:' + name + ', age:' + age
	}
}

person1 = new PersonConstructor('Marie', 1)
person2 = ['Marie', 2] as PersonConstructor
PersonConstructor person3 = ['Marie', 3]

person1.show()
person2.show()
person3.show()

person4 = new PersonWOConstructor()
person5 = new PersonWOConstructor(name:'Marie')
person6 = new PersonWOConstructor(age:1)
person7 = new PersonWOConstructor(name:'Marie', age:2)

person4.show()
person5.show()
person6.show()
person7.show()
