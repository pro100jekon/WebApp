<%@ attribute name="element" type="java.util.List" required="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" uri="/WEB-INF/tag/editItem" %>
<div class="album py-5 bg-light">
    <div class="container">
        <div class="row">
            <c:forEach var="i" items="${element}">
                <div class="col-md-4">
                    <div class="card mb-4 box-shadow">
                        <div class="card-body">
                            <p class="card-text">
                                ${i.toHtml()}
                            </p>
                            <div class="d-flex justify-content-between align-items-center">
                                <small class="text-capitalize"><a href="<ct:editItem  entity="${i}"/>${i.id}">Edit</a></small>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>