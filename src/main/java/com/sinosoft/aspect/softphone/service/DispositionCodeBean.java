package com.sinosoft.aspect.softphone.service;

class DispositionCodeBean {
  private final int id;
  private final String code;
  private final String description;
  private final boolean sale;
  private final boolean callback;
  private final boolean exclusion;

  public DispositionCodeBean(String dispositionCode, int dispositionId, String dispositionDescription, boolean dispositionSale, boolean dispositionCallback, boolean dispositionExclusion) {
    this.code = dispositionCode;
    this.id = dispositionId;
    this.description = dispositionDescription;
    this.sale = dispositionSale;
    this.callback = dispositionCallback;
    this.exclusion = dispositionExclusion;
  }

  public int getDisposition_id() {
    return this.id;
  }

  public String getDisposition_code() {
    return this.code;
  }

  public String getDisposition_description() {
    return this.description;
  }

  public boolean isDisposition_code_sale() {
    return this.sale;
  }

  public boolean isDisposition_code_callback() {
    return this.callback;
  }

  public boolean isDisposition_code_exclusion() {
    return this.exclusion;
  }

  public String toString() {
    StringBuffer sb = new StringBuffer();

    sb.append("DISPOSITION = {");
    sb.append(" CODE: ").append(this.code);
    sb.append(" ID: ").append(this.id);
    sb.append(" DESC: ").append(this.description);
    sb.append(" SALEF: ").append(this.sale);
    sb.append(" CALLBACKF: ").append(this.callback);
    sb.append(" EXCLUSIONF: ").append(this.exclusion);
    sb.append(" }");

    return sb.toString();
  }
}