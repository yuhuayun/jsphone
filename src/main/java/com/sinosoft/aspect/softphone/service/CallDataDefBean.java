package com.sinosoft.aspect.softphone.service;

class CallDataDefBean {

  private final int fieldOrder;
  private final String fieldLabel;
  private final String fieldType;

  public CallDataDefBean(int order, String label, String type) {
    this.fieldOrder = order;
    this.fieldLabel = label;
    this.fieldType = type;
  }

  public int getFieldOrder() {
    return this.fieldOrder;
  }

  public String getFieldLabel() {
    return this.fieldLabel;
  }

  public String getFieldType() {
    return this.fieldType;
  }

  public String toString() {
    StringBuffer sb = new StringBuffer();

    sb.append("FIELD= { ORDER: ").append(this.fieldOrder);
    sb.append(" LABEL: ").append(this.fieldLabel);
    sb.append(" TYPE: ").append(this.fieldType).append(" }");

    return sb.toString();
  }
}