var paramsDelim = "|";
var traceDelim = "&";

function insertDialogueTrace(dialogueID, recAvailable, recDetected, retry, dialogueReturnCode, userInput, recParams) {

	if (userInput == undefined){
		userInput = "";
	}	
	if (recParams == undefined){
		recParams = "";
	}

	inputInfo += traceDelim + "DIALOGUE" + paramsDelim + 
							dialogueID + paramsDelim + 
							recAvailable + paramsDelim + 
							recDetected + paramsDelim + 
							retry + paramsDelim + 
							dialogueReturnCode + paramsDelim + 
							userInput + paramsDelim + 
							recParams;	
	return ("");
}

function insertSpeechTrace(speechID, type, text) {
	
	if (type == undefined){
		type = "";
	}	
	if (text == undefined){
		text = "";
	}
	
	inputInfo += traceDelim + "SPEECH" + paramsDelim + 
									speechID + paramsDelim + 
									type + paramsDelim + 
									text;
	return ("");
}