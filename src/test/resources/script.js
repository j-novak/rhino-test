var foo = root.foo;
var fooResult = foo.split('|');
print(fooResult);

// takes the first value of the inner collection explicitly and do split on it
var objectMap_foo_0 = root.objectMap['foo'][0];
var objectMap_foo_0_split = objectMap_foo_0.split('|');
print(objectMap_foo_0_split);

// should return first value in the inner collection as a default and do split on it
var objectMap_foo = root.objectMap['foo'];
// call custom method (length() in this case)
print(objectMap_foo.length());
var objectMap_foo_split = objectMap_foo.split('|');
print(objectMap_foo_split);