package com.test;

import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.annotations.JSGetter;

public class Root extends ScriptableObject {

  private String foo;
  private JsMap objectMap;

  @JSGetter
  public String getFoo() {
    return foo;
  }

  public void setFoo(String foo) {
    this.foo = foo;
  }

  @JSGetter
  public JsMap getObjectMap() {
    return objectMap;
  }

  public void setObjectMap(JsMap objectMap) {
    this.objectMap = objectMap;
  }

  @Override
  public String getClassName() {
    return "Root";
  }
}
