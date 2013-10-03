package com.vectorsf.jvoiceframework.testapp.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * This class provides an interface that is used to store key-value pairs of Genesys CTI data vector, 
 * as well as other control information, such as if the value has been updated during the call or if it is 
 * necessary that data is read when call starts.
 * 
 * @author antonio.mateo@servexternos.isban.es, yerayangel.castellano@servexternos.isban.es
 * @see CTIDataVectorItem
 */
@Component("ctiData")
@Scope("session")
public class CTIDataVector {
	
	private static final long serialVersionUID = -2975216216233134928L;
	public static final String CONTEXT_KEY = "CTI_DATA_VECTOR";
	
	
	public static final String CTI_EXTENDED_DATAVECTOR_SEPARATOR_FIELD = ";";
	
	public static final String CTI_APPLICATION_KEY = "APPLICATION";
	public static final String CTI_SERVICE_KEY = "SERVICE";
	public static final String CTI_LANGUAGE_KEY = "IDIOMA";
	
	public static final String CTI_CONN_ID_KEY = "ConnID";
	public static final String CTI_ANI_KEY = "Ani";
	public static final String CTI_DNIS_KEY = "DNIS";
	public static final String CTI_VDN_KEY = "VDN";
	
	public static final String CTI_PERSON_CODE_KEY = "CODPERS";
	public static final String CTI_PERSON_TYPE_KEY = "TIPOPERS";
	public static final String CTI_CUST_IDENTIFICATION_KEY = "CODDOCU";
	public static final String CTI_CUST_IDENTIFICATION_TYPE_KEY = "TIPDOCU";
	public static final String CTI_SECURITY_LEVEL_KEY = "IDENTIFICACION";
	public static final String CTI_PERSON_NAME_KEY = "NOMBRE";
	public static final String CTI_PERSON_SURNAME_KEY = "APELLIDO1";
	
	public static final String CTI_SEGMENT_KEY = "SEGMENTO";
	public static final String CTI_SOLICITED_OPERATION_KEY = "OPESOLICITADA";
	public static final String CTI_AGENT_IDENTIFICATION_KEY = "sourceAgentNameTransfer";	
	public static final String CTI_MAIN_TRANSFER_REASON_KEY = "mainTransferReasonName";
	public static final String CTI_SECOND_TRANSFER_REASON_KEY = "secondTransferReasonName";
	
	public static final String CTI_PROCEDENCIA_KEY = "Procedencia";
	public static final String CTI_PROCEDENCIA_DIRECTA_VALUE = "02Directa";
	public static final String CTI_PROCEDENCIA_IVR_VALUE = "01Ivr";
	public static final String CTI_PROCEDENCIA_TRANSFERIDA_VALUE = "03Transferida";
	
	// CTI Data Vector
	protected HashMap<String, CTIDataVectorItem> ctiData = null;
	// CTI Call Info
	protected String callID = null;
//	protected String connID = null;
//	protected String ani = null;
//	protected String dnis = null;
	
	public CTIDataVector() {
		ctiData = new HashMap<String, CTIDataVectorItem>();
		
		//The following parameters are marked as necessary to read at the beggining.
		ctiData.put(CTIDataVector.CTI_APPLICATION_KEY, new CTIDataVectorItem(null, false, true));
		ctiData.put(CTIDataVector.CTI_CONN_ID_KEY, new CTIDataVectorItem(null, false, true));
		ctiData.put(CTIDataVector.CTI_ANI_KEY, new CTIDataVectorItem(null, false, true));
		ctiData.put(CTIDataVector.CTI_DNIS_KEY, new CTIDataVectorItem(null, false, true));
		ctiData.put(CTIDataVector.CTI_VDN_KEY, new CTIDataVectorItem(null, false, true));
		ctiData.put(CTIDataVector.CTI_LANGUAGE_KEY, new CTIDataVectorItem(null, false, true));
		
		//Splitter SanUK to RBS Retail and RBS BB
		ctiData.put(CTIDataVector.CTI_CUST_IDENTIFICATION_KEY, new CTIDataVectorItem(null, false, true));
		
		//Although "Procedencia" parameter is the first parameter to be read, it is established as
		//non-necessary-to-read because it will be read 'outside' the scope of ctiDataVector.
		//Setting it to true would cause the request of the get operation to Genesys twice.
		ctiData.put(CTIDataVector.CTI_PROCEDENCIA_KEY, new CTIDataVectorItem(null, false, false));
		
		
		//The following parameters are marked as NOT necessary to read at the beggining.
		ctiData.put(CTIDataVector.CTI_PERSON_CODE_KEY, new CTIDataVectorItem(null, false, false));
		ctiData.put(CTIDataVector.CTI_PERSON_TYPE_KEY, new CTIDataVectorItem(null, false, false));
		ctiData.put(CTIDataVector.CTI_CUST_IDENTIFICATION_TYPE_KEY, new CTIDataVectorItem(null, false, false));
		ctiData.put(CTIDataVector.CTI_SECURITY_LEVEL_KEY, new CTIDataVectorItem(null, false, false));
		ctiData.put(CTIDataVector.CTI_PERSON_NAME_KEY, new CTIDataVectorItem(null, false, false));
		ctiData.put(CTIDataVector.CTI_PERSON_SURNAME_KEY, new CTIDataVectorItem(null, false, false));
		ctiData.put(CTIDataVector.CTI_SERVICE_KEY, new CTIDataVectorItem(null, false, false));
		ctiData.put(CTIDataVector.CTI_SEGMENT_KEY, new CTIDataVectorItem(null, false, false));
		ctiData.put(CTIDataVector.CTI_SOLICITED_OPERATION_KEY, new CTIDataVectorItem(null, false, false));
		ctiData.put(CTIDataVector.CTI_AGENT_IDENTIFICATION_KEY, new CTIDataVectorItem(null, false, false));
		ctiData.put(CTIDataVector.CTI_MAIN_TRANSFER_REASON_KEY, new CTIDataVectorItem(null, false, false));
		ctiData.put(CTIDataVector.CTI_SECOND_TRANSFER_REASON_KEY, new CTIDataVectorItem(null, false, false));
		
	}
	
	/**
	 * 
	 * @param key represents the name of the CTI parameter
	 * @return true/false
	 * For a given key, this method returns if its value was modified after the creation of
	 * ctiDataVector when call starts.
	 */
	public boolean isModified(String key){
		if((ctiData!=null)&&(ctiData.get(key)!=null))
			return ctiData.get(key).isModified();
		else
			return false;
	}
	
	public boolean isReadAtBegin(String key){
		if((ctiData!=null)&&(ctiData.get(key)!=null))
			return ctiData.get(key).isReadAtBegin();
		else 
			return false;
	}
	
	public CTIDataVector(CTIDataVector ctiDataVector) {
		this.callID = ctiDataVector.getCallID();
		this.ctiData = ctiDataVector.getCTIHashMap();
	}
	
	public String getCallID() {
		return callID;
	}
	public void setCallID(String callID) {
		this.callID = callID;
	}
	public HashMap<String, CTIDataVectorItem> getCTIHashMap(){
		return ctiData;
	}
	public String getCTIValue(String key) {
		if(ctiData.get(key)!=null)
			return (String)((CTIDataVectorItem)ctiData.get(key)).getValue();
		else
			return null;
	}
	/** 
	 * <MARQUEE BEHAVIOR="ALTERNATE" SCROLLDELAY=50 WIDTH =400> <FONT
     * COLOR="FF0000"> <B>DO NOT USE THIS METHOD <B></FONT> </MARQUEE>
     * <P>
	 * It is developed to be invoked in some controlled scenarios.
	 * <b>Using it may imply CTI parameters are not updated correctly.</b> 
	 * This method is used only when the call starts, and the CTI data coming from Genesys 
	 * (through Nortel-Genesys driver) is stored under the ctiDataVector object.
	 * 
	 * There are getters and setter for every CTI parameter. Use them instead.
	 * @param key to be set/updated in ctiDataVector object
	 * @param value associated to the given key
	 * @deprecated
	 */
	@Deprecated
	public void setCTIValue(String key, String value) {
		//This method is used only when the call starts, and the CTI data coming from Genesys 
		//(through Nortel-Genesys driver) is stored under the ctiDataVector object.
		//DO NOT USE THIS METHOD
		CTIDataVectorItem item = ctiData.get(key);
		item.setValue(value);
		ctiData.put(key, item);
	}
	
	public Set<String> getAllCTIActiveKeys() {
		return ctiData.keySet();
	}
	
	public String getApplication() {
		if(ctiData.get(CTIDataVector.CTI_APPLICATION_KEY)!=null)
			return ctiData.get(CTIDataVector.CTI_APPLICATION_KEY).getValue();
		else
			return null;
	}
	
	public void setApplication(String application) {

		CTIDataVectorItem item = ctiData.get(CTIDataVector.CTI_APPLICATION_KEY);
		item.setValue(application);
		item.setModified(true);
		this.ctiData.put(CTIDataVector.CTI_APPLICATION_KEY, item);
		
	}
	
	public String getService() {
		if(ctiData.get(CTIDataVector.CTI_SERVICE_KEY)!=null)
			return ctiData.get(CTIDataVector.CTI_SERVICE_KEY).getValue();
		else
			return null;
	}
	
	public void setService(String service) {
		CTIDataVectorItem item = ctiData.get(CTIDataVector.CTI_SERVICE_KEY);
		item.setValue(service);
		item.setModified(true);
		this.ctiData.put(CTIDataVector.CTI_SERVICE_KEY, item);
	}
	public String getLanguage() {
		if(ctiData.get(CTIDataVector.CTI_LANGUAGE_KEY)!=null)
			return ctiData.get(CTIDataVector.CTI_LANGUAGE_KEY).getValue();
		else
			return null;
	}
	public void setLanguage(String language) {
		CTIDataVectorItem item = ctiData.get(CTIDataVector.CTI_LANGUAGE_KEY);
		item.setValue(language);
		item.setModified(true);
		this.ctiData.put(CTIDataVector.CTI_LANGUAGE_KEY, item);		
	}
	
	public String getConnID() {
		if(ctiData.get(CTIDataVector.CTI_CONN_ID_KEY)!=null)
			return ctiData.get(CTIDataVector.CTI_CONN_ID_KEY).getValue();
		else
			return null;
	}
	
	public void setConnID(String connID) {
		CTIDataVectorItem item = ctiData.get(CTIDataVector.CTI_CONN_ID_KEY);
		item.setValue(connID);
		item.setModified(true);
		this.ctiData.put(CTIDataVector.CTI_CONN_ID_KEY, item);
	}
	
	public String getANI() {
		if(ctiData.get(CTIDataVector.CTI_ANI_KEY)!=null)
			return ctiData.get(CTIDataVector.CTI_ANI_KEY).getValue();
		else
			return null;
	}
	
	public void setANI(String ani) {
		CTIDataVectorItem item = ctiData.get(CTIDataVector.CTI_ANI_KEY);
		item.setValue(ani);
		item.setModified(true);
		this.ctiData.put(CTIDataVector.CTI_ANI_KEY, item);
	}
	
	public String getDNIS() {
		if(ctiData.get(CTIDataVector.CTI_DNIS_KEY)!=null)
			return ctiData.get(CTIDataVector.CTI_DNIS_KEY).getValue();
		else
			return null;
	}
	
	public void setDNIS(String dnis) {
		CTIDataVectorItem item = ctiData.get(CTIDataVector.CTI_DNIS_KEY);
		item.setValue(dnis);
		item.setModified(true);
		this.ctiData.put(CTIDataVector.CTI_DNIS_KEY, item);
	}
	
	public String getVDN() {
		if(ctiData.get(CTIDataVector.CTI_VDN_KEY)!=null)
			return ctiData.get(CTIDataVector.CTI_VDN_KEY).getValue();
		else 
			return null;
	}
	
	public void setVDN(String VDN) {
		CTIDataVectorItem item = ctiData.get(CTIDataVector.CTI_VDN_KEY);
		item.setValue(VDN);
		item.setModified(true);
		this.ctiData.put(CTIDataVector.CTI_VDN_KEY, item);
	}
	
	public String getPersonCode() {
		if(ctiData.get(CTIDataVector.CTI_PERSON_CODE_KEY)!=null)
			return ctiData.get(CTIDataVector.CTI_PERSON_CODE_KEY).getValue();
		else
			return null;
	}
	
	public void setPersonCode(String personCode) {
		CTIDataVectorItem item = ctiData.get(CTIDataVector.CTI_PERSON_CODE_KEY);
		item.setValue(personCode);
		item.setModified(true);
		this.ctiData.put(CTIDataVector.CTI_PERSON_CODE_KEY, item);
	}
	
	public String getPersonType() {
		if(ctiData.get(CTIDataVector.CTI_PERSON_TYPE_KEY)!=null)
			return ctiData.get(CTIDataVector.CTI_PERSON_TYPE_KEY).getValue();
		else
			return null;
	}
	
	public void setPersonType(String personType) {
		CTIDataVectorItem item = ctiData.get(CTIDataVector.CTI_PERSON_TYPE_KEY);
		item.setValue(personType);
		item.setModified(true);
		this.ctiData.put(CTIDataVector.CTI_PERSON_TYPE_KEY, item);
	}
	
	public String getSecurityLevel() {
		if(ctiData.get(CTIDataVector.CTI_SECURITY_LEVEL_KEY)!=null)
			return ctiData.get(CTIDataVector.CTI_SECURITY_LEVEL_KEY).getValue();
		else
			return null;
	}
	public void setSecurityLevel(String securityLevel) {
		CTIDataVectorItem item = ctiData.get(CTIDataVector.CTI_SECURITY_LEVEL_KEY);
		item.setValue(securityLevel);
		item.setModified(true);
		this.ctiData.put(CTIDataVector.CTI_SECURITY_LEVEL_KEY, item);
	}
	
	public String getCustIdentification() {
		if(ctiData.get(CTIDataVector.CTI_CUST_IDENTIFICATION_KEY)!=null)
			return ctiData.get(CTIDataVector.CTI_CUST_IDENTIFICATION_KEY).getValue();
		else
			return null;
	}
	
	public void setCustIdentification(String custIdentification) {
		CTIDataVectorItem item = ctiData.get(CTIDataVector.CTI_CUST_IDENTIFICATION_KEY);
		item.setValue(custIdentification);
		item.setModified(true);
		this.ctiData.put(CTIDataVector.CTI_CUST_IDENTIFICATION_KEY, item);
	}
	
	public String getCustIdentificationType() {
		if(ctiData.get(CTIDataVector.CTI_CUST_IDENTIFICATION_TYPE_KEY)!=null)
			return ctiData.get(CTIDataVector.CTI_CUST_IDENTIFICATION_TYPE_KEY).getValue();
		else
			return null;
	}
	
	public void setCustIdentificationType(String custIdentificationType) {
		CTIDataVectorItem item = ctiData.get(CTIDataVector.CTI_CUST_IDENTIFICATION_TYPE_KEY);
		item.setValue(custIdentificationType);
		item.setModified(true);
		this.ctiData.put(CTIDataVector.CTI_CUST_IDENTIFICATION_TYPE_KEY, item);
	}
	
	public String getPersonName() {
		if(ctiData.get(CTIDataVector.CTI_PERSON_NAME_KEY)!=null)
			return ctiData.get(CTIDataVector.CTI_PERSON_NAME_KEY).getValue();
		else
			return null;
	}
	
	public void setPersonName(String personName) {
		CTIDataVectorItem item = ctiData.get(CTIDataVector.CTI_PERSON_NAME_KEY);
		item.setValue(personName);
		item.setModified(true);
		this.ctiData.put(CTIDataVector.CTI_PERSON_NAME_KEY, item);
	}
	
	public String getPersonSurname() {
		if(ctiData.get(CTIDataVector.CTI_PERSON_SURNAME_KEY)!=null)
			return ctiData.get(CTIDataVector.CTI_PERSON_SURNAME_KEY).getValue();
		else
			return null;
	}
	
	public void setPersonSurname(String personSurname) {
		CTIDataVectorItem item = ctiData.get(CTIDataVector.CTI_PERSON_SURNAME_KEY);
		item.setValue(personSurname);
		item.setModified(true);
		this.ctiData.put(CTIDataVector.CTI_PERSON_SURNAME_KEY, item);
	}
	public String getSegment() {
		if(ctiData.get(CTIDataVector.CTI_SEGMENT_KEY)!=null)
			return ctiData.get(CTIDataVector.CTI_SEGMENT_KEY).getValue();
		else
			return null;
	}
	public void setSegment(String segment) {
		CTIDataVectorItem item = ctiData.get(CTIDataVector.CTI_SEGMENT_KEY);
		item.setValue(segment);
		item.setModified(true);
		this.ctiData.put(CTIDataVector.CTI_SEGMENT_KEY, item);
	}
	public String getSolicitedOperation() {
		if(ctiData.get(CTIDataVector.CTI_SOLICITED_OPERATION_KEY)!=null)
			return ctiData.get(CTIDataVector.CTI_SOLICITED_OPERATION_KEY).getValue();
		else
			return null;
	}
	
	public void setSolicitedOperation(String solicitedOperation) {
		CTIDataVectorItem item = ctiData.get(CTIDataVector.CTI_SOLICITED_OPERATION_KEY);
		item.setValue(solicitedOperation);
		item.setModified(true);
		this.ctiData.put(CTIDataVector.CTI_SOLICITED_OPERATION_KEY, item);
	}
	
	public String getMainTransferReason() {
		if(ctiData.get(CTIDataVector.CTI_MAIN_TRANSFER_REASON_KEY)!=null)
			return ctiData.get(CTIDataVector.CTI_MAIN_TRANSFER_REASON_KEY).getValue();
		else
			return null;
	}
	
	public void setMainTransferReason(String mainTransferReason) {
		CTIDataVectorItem item = ctiData.get(CTIDataVector.CTI_MAIN_TRANSFER_REASON_KEY);
		item.setValue(mainTransferReason);
		item.setModified(true);
		this.ctiData.put(CTIDataVector.CTI_MAIN_TRANSFER_REASON_KEY, item);
	}
	
	public String getSecondTransferReason() {
		if(ctiData.get(CTIDataVector.CTI_SECOND_TRANSFER_REASON_KEY)!=null)
			return ctiData.get(CTIDataVector.CTI_SECOND_TRANSFER_REASON_KEY).getValue();
		else
			return null;
	}
	
	public void setSecondTransferReason(String secondTransferReason) {
		CTIDataVectorItem item = ctiData.get(CTIDataVector.CTI_SECOND_TRANSFER_REASON_KEY);
		item.setValue(secondTransferReason);
		item.setModified(true);
		this.ctiData.put(CTIDataVector.CTI_SECOND_TRANSFER_REASON_KEY, item);
	}
	
	public String getAgentIdentification() {
		if(ctiData.get(CTIDataVector.CTI_AGENT_IDENTIFICATION_KEY)!=null)
			return ctiData.get(CTIDataVector.CTI_AGENT_IDENTIFICATION_KEY).getValue();
		else
			return null;
	}
	
	public void setAgentIdentification(String agentIdentification) {
		CTIDataVectorItem item = ctiData.get(CTIDataVector.CTI_AGENT_IDENTIFICATION_KEY);
		item.setValue(agentIdentification);
		item.setModified(true);
		this.ctiData.put(CTIDataVector.CTI_AGENT_IDENTIFICATION_KEY, item);
	}
	
	public String getProcedencia() {
		if(ctiData.get(CTIDataVector.CTI_PROCEDENCIA_KEY)!=null)
			return ctiData.get(CTIDataVector.CTI_PROCEDENCIA_KEY).getValue();
		else
			return null;
	}
	
	public void setProcedencia(String Procedencia) {
		CTIDataVectorItem item = ctiData.get(CTIDataVector.CTI_PROCEDENCIA_KEY);
		item.setValue(Procedencia);
		item.setModified(true);
		this.ctiData.put(CTIDataVector.CTI_PROCEDENCIA_KEY, item);
	}
	
	/**
	 * @param item to be saved in CTIDataVector
	 * @deprecated
	 * <MARQUEE BEHAVIOR="ALTERNATE" SCROLLDELAY=50 WIDTH =400> <FONT
     * COLOR="FF0000"> <B>DO NOT USE THIS METHOD <B></FONT> </MARQUEE>
     * <P>
     * This method is used only for CORE purposes when the call starts. It is invoked only from Dispatcher. 
     * Using it may cause a bad behavior of CTI integration.
     *(i.e.: managing CTI elements that do not exists)
     * To modify the value associated to a given key in the extended CTI vector, use setCustomCTIValue(String key,String value) method.
     * @see setCustomCTIValue(String key, String value)
	 */
	@Deprecated
	public void setCustomCTIValue(String key, CTIDataVectorItem item){
		ctiData.put(key, item);
	}
	
	
	/**
	 * This method is used to update the value of the given key in the extended CTI data vector.
	 * <FONT COLOR="FF0000"><B>Use always this method instead of deprecated setCustomCTIValue(String key, CTIDataVectorItem item)</B></FONT>
	 * @param key to be updated
	 * @param value that will take the given key
	 * @throws CTIException if the given key does not exist in the CTIDataVector.
	 * @since 15/03/2012
	 * @author yerayangel.castellano@servexternos.isban.es
	 */
	public void setCustomCTIValue(String key, String value) throws Exception {
		CTIDataVectorItem item = ctiData.get(key);
		if(item!=null){
			item.setValue(value);
			item.setModified(true);
			ctiData.put(key, item);
		}else{
			throw new Exception("The key "+key+" IS NOT defined in the extended CTI Data Vector through the proper configuration file");	

		}
	}

	/**
	 * Mark as needed to read at begin all parameters that are in CTI data vector.
	 * @since 15/03/2012
	 * @author yerayangel.castellano@servexternos.isban.es
	 */
	public void setReadAtBeginTrueToAllElements() {
		Set<String> allCTIActiveKeys = ctiData.keySet();
		for(String fieldKey : allCTIActiveKeys) {
			// Get Standard CTI fields´
			CTIDataVectorItem item =ctiData.get(fieldKey);
			if(!fieldKey.equalsIgnoreCase(CTIDataVector.CTI_PROCEDENCIA_KEY)){
				item.setReadAtBegin(true);
				ctiData.put(fieldKey, item);
			}
		}
	}
	
	/**
	 * This method returns all CTI vector keys that are needed to be read when call starts.
	 * 
	 * @return an arraylist which contains all CTI vector keys that are needed to be read when call starts.
	 * @since 15/03/2012
	 * @author yerayangel.castellano@servexternos.isban.es
	 */
	public ArrayList<String> getAllReadAtBeginKeys(){
		ArrayList<String> allReadAtBeginElements = new ArrayList();
		Set<String> allCTIActiveKeys = ctiData.keySet();
		for(String fieldKey : allCTIActiveKeys) {
			CTIDataVectorItem item =ctiData.get(fieldKey);
			if(item.isReadAtBegin())
				allReadAtBeginElements.add(fieldKey);
		}
		return allReadAtBeginElements;
	} 
	
	public void retreive(){		
		this.setApplication(APPLICATION_RETAIL);
	}
	
	
	public static final String APPLICATION_RETAIL = "APPLICATION_RETAIL";
	public static final String APPLICATION_PYME_RETAIL = "APPLICATION_PYME_RETAIL";
	public static final String APPLICATION_ULINE_RETAIL = "APPLICATION_ULINE_RETAIL";
}