<%@ page language="java" contentType="application/vxml; charset=UTF-8" pageEncoding="UTF-8" %><vxml version="2.1" xmlns="http://www.w3.org/2001/vxml" xml:lang="es-ES">
    <meta http-equiv="Expires" content="0"/>
    <form>
        <block>
            <var name="ani" expr="session.connection.remote.uri" />
            <var name="dnis" expr="session.connection.local.uri" />
            <var name="_eventId" expr="'success'" />
            <submit next="${flowExecutionUrl}" namelist="_eventId ani dnis" method="post" />
        </block>
    </form>
</vxml>