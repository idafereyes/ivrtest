package com.vectorsf.jvoiceframework.testapp.model;

import java.io.Serializable;

/**
 * This is class is used to wrap the value, modified status and the need of perform a get of
 * a CTI parameter when the call starts.
 * 
 * @author yerayangel.castellano@servexternos.isban.es
 * @since 09/03/2012
 * @see CTIDataVector
 *
 */
public class CTIDataVectorItem implements Serializable{
	
	
	private String value;
	private boolean modified=false;
	private boolean readAtBegin = false;
	


	public CTIDataVectorItem(){
		this.value=null;
		this.modified=false;
		this.readAtBegin=false;
	}
	
	/**
	 * Constructor of class CTIDataVectorItem.
	 * The instance objects of this class will be handled exclusively by CTIDataVector class Hashmap attribute.
	 * The key of the mentioned hashmap is the name of the parameter, and the item attribute of this class is the value.
	 * 
	 * @param item Value of the CTI parameter. 
	 * @param modified It is a control flag used to check if the item has been modified during the call in order to update the CTI value when call is transferred to a representative.
	 * @param readAtBegin Determines if is need to get the value of this parameter from CTI at the beginning of the call.
	 * @see CTIDataVector
	 */
	public CTIDataVectorItem (String value, boolean modified, boolean readAtBegin){
		this.value=value;
		this.modified=modified;
		this.readAtBegin=readAtBegin;
	}
	
	private static final long serialVersionUID = 1L;
/**
 * 
 * @return
 */
	public String getValue() {
		return value;
	}
	public void setValue(String item) {
		this.value = item;
	}
	
	public boolean isModified() {
		return modified;
	}
	public void setModified(boolean modified) {
		this.modified = modified;
	}
	
	public boolean isReadAtBegin() {
		return readAtBegin;
	}
	public void setReadAtBegin(boolean readAtBegin) {
		this.readAtBegin = readAtBegin;
	}
	

}
