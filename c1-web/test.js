var ClassA = function(){
	
}


ClassA.prototype.test = function(){
	console.debug("ClassA.test");
}
ClassA.prototype.testA = function(){
	console.debug("ClassA.testA");
}

/*var F = function() {};
F.prototype = ClassA.prototype;
*/
var ClassB = function() {
}

ClassB.prototype = new ClassA();
//ClassB.prototype = ClassA.prototype;

for(method in ClassA.prototype) {
	console.debug(method);
	ClassB.prototype[method] = ClassA.prototype[method];	
}

ClassB.prototype.test = function(){
	ClassA.prototype.test.call(this);
	console.debug("ClassB.test");
}
//ClassB.constructor.prototype= ClassA.constructor;

var a = new ClassB();
a.test();

console.debug(ClassA.constructor);
console.debug(ClassB.constructor.prototype);
console.debug(ClassB.constructor);
console.debug(a instanceof ClassB);
console.debug(a instanceof ClassA);

