package com.sinosoft.aspect.softphone.soap.agent;

import java.io.Serializable;
import java.rmi.RemoteException;

public class ConcertoFault extends RemoteException implements Serializable {

	private int concertoFaultCode;
	private String concertoFaultString;
	private Object __equalsCalc;
	private boolean __hashCodeCalc;
	
	public ConcertoFault() {
		__equalsCalc = null;
		__hashCodeCalc = false;
	}

	public ConcertoFault(int concertoFaultCode, String concertoFaultString) {
		__equalsCalc = null;
		__hashCodeCalc = false;
		this.concertoFaultCode = concertoFaultCode;
		this.concertoFaultString = concertoFaultString;
	}

	public int getConcertoFaultCode() {
		return concertoFaultCode;
	}

	public void setConcertoFaultCode(int concertoFaultCode) {
		this.concertoFaultCode = concertoFaultCode;
	}

	public String getConcertoFaultString() {
		return concertoFaultString;
	}

	public void setConcertoFaultString(String concertoFaultString) {
		this.concertoFaultString = concertoFaultString;
	}

	public synchronized boolean equals(Object obj) {
		if (!(obj instanceof ConcertoFault))
			return false;
		ConcertoFault other = (ConcertoFault) obj;
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (__equalsCalc != null) {
			return __equalsCalc == obj;
		} else {
			__equalsCalc = obj;
			boolean _equals = concertoFaultCode == other.getConcertoFaultCode()
					&& (concertoFaultString == null
							&& other.getConcertoFaultString() == null || concertoFaultString != null
							&& concertoFaultString.equals(other
									.getConcertoFaultString()));
			__equalsCalc = null;
			return _equals;
		}
	}

	public synchronized int hashCode() {
		if (__hashCodeCalc)
			return 0;
		__hashCodeCalc = true;
		int _hashCode = 1;
		_hashCode += getConcertoFaultCode();
		if (getConcertoFaultString() != null)
			_hashCode += getConcertoFaultString().hashCode();
		__hashCodeCalc = false;
		return _hashCode;
	}

}
