package com.test;

import java.util.List;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.annotations.JSFunction;

public class JsMapValue extends ScriptableObject {

  private List<String> values;

  public void setValues(List<String> values) {
    this.values = values;
  }

  @JSFunction
  public Integer length() {
    return this.values.size();
  }

  @Override
  public Object get(int index, Scriptable start) {
    if (values.size() < index) {
      return Context.getUndefinedValue();
    }
    return this.values.get(index);
  }

  @Override
  public Object getDefaultValue(Class<?> typeHint) {
    if (this.values.isEmpty()) {
      return Context.getUndefinedValue();
    }
    return this.values.get(0);
  }

  @Override
  public String getClassName() {
    return "JsMapValue";
  }
}
