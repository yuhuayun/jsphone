package com.sinosoft.aspect.softphone.soap.event.Client.Notify;

import java.io.Serializable;

import lombok.Data;

@Data
public class UDRecordState implements Serializable {

	private int agentIndex;
	private int autoRecording;
	private String fileName;
	private int mediaType;
	private int recordingIndex;
	private int recordingRate;
	private int recordingSeq;
	private int recordingState;
	private int recordingStoreId;

}
