package com.test;

import java.util.Map;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

public class JsMap extends ScriptableObject {

  private Map<String, JsMapValue> innerMap;

  public void setInnerMap(Map<String, JsMapValue> innerMap) {
    this.innerMap = innerMap;
  }

  @Override
  public Object get(String name, Scriptable start) {
    if (!this.innerMap.containsKey(name)) {
      return Context.getUndefinedValue();
    }
    return this.innerMap.get(name);
  }

  @Override
  public Object getDefaultValue(Class<?> typeHint) {
    return this.innerMap.toString();
  }

  @Override
  public String getClassName() {
    return "JsMap";
  }
}
