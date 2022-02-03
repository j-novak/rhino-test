package com.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.tools.shell.Global;

class Test1 {


  Context cx;
  Scriptable scope;
  File testFile;

  @BeforeEach
  void before() {
    this.cx = Context.enter();
    this.scope = new Global(cx);
//    scope = cx.initStandardObjects();

    URL resource = this.getClass().getClassLoader().getResource("script.js");
    assertNotNull(resource, "File not found!");
    testFile = new File(resource.getFile());
  }

  @AfterEach
  void after() {
    Context.exit();
  }

  @Test
  void test() throws Exception {
    ScriptableObject.defineClass(this.scope, Root.class);
    ScriptableObject.defineClass(this.scope, JsMap.class);
    ScriptableObject.defineClass(this.scope, JsMapValue.class);

    var jsMapValue = (JsMapValue) this.cx.newObject(this.scope, "JsMapValue");
    jsMapValue.setValues(List.of("bar|baz", "one|two"));

    var jsMap = (JsMap) this.cx.newObject(this.scope, "JsMap");
    Map<String, JsMapValue> innerMap = new HashMap<>();
    innerMap.put("foo", jsMapValue);
    jsMap.setInnerMap(innerMap);

    var root = (Root) this.cx.newObject(this.scope, "Root");
    root.setFoo("foo|bar");
    root.setObjectMap(jsMap);

    this.scope.put("root", this.scope, root);
    this.cx.evaluateReader(this.scope, new FileReader(testFile), testFile.getName(), 1, null);
  }
}
