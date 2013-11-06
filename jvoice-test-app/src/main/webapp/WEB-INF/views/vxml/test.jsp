<%@ page language="java" contentType="application/vxml; charset=UTF-8" pageEncoding="UTF-8" %>
<vxml version="2.1" xmlns="http://www.w3.org/2001/vxml"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xml:lang="es-ES">
  
  <form>
    <block>
        <prompt>Ha seleccionado cuentas de ahorro. Si necesita más información por favor contacte con nuestro servicio de atención al cliente.</prompt>
    </block>
    <block>
        <var name="_eventId" expr="'success'" />
        <submit next="${flowExecutionUrl}" namelist="_eventId" />
    </block>
  </form>
  
</vxml>

