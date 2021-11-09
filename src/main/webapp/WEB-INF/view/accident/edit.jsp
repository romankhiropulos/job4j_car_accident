<%@ page import="ru.job4j.accident.model.Accident" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<form  action="<c:url value='/update?id=${accident.id}'/>" method='POST'>
<%--    <jsp:useBean id="accident" type="ru.job4j.accident.model.Accident"/>--%>
    <table>
        <tr>
            <td>ДТП:</td>
            <td><input type='text' name='name' value="${accident.name}"></td>
            <td><input type='text' name='address' value="${accident.address}"></td>
            <td><input type='text' name='text' value="${accident.text}"></td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Сохранить" /></td>
        </tr>
    </table>
</form>
</body>
</html>