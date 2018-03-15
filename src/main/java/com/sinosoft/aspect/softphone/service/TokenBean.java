/*
 *
 *
 */
package com.sinosoft.aspect.softphone.service;

/**
 *
 * @author Believe
 *
 */
public class TokenBean {
  private static final long serialVersionUID = -3917611270355394688L;
  private volatile boolean initialized;
  private volatile boolean firstLoad;

  public TokenBean()
  {
    this.initialized = false;
    this.firstLoad = true; }

  public boolean getFirstLoad() {
    return ((this.initialized) && (this.firstLoad));
  }

  public boolean isInitialized() {
    return this.initialized;
  }

  public void initialize() {
    this.firstLoad = true;
    this.initialized = true;
  }

  public void setLoaded() {
    this.firstLoad = false;
  }

  public String toString() {
    return "Initialized: " + this.initialized + ", firstLoad = " + this.firstLoad;
  }
}