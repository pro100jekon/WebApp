<%@ include file="WEB-INF/jspf/taglib.jspf" %>
<html>
<title>
    <fmt:message key="welcome"/>
</title>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <%@ include file="WEB-INF/jspf/style.jspf" %>
</head>
<body>
<%@ include file="WEB-INF/jspf/header.jspf" %>
<div class="album py-5 bg-light">
    <div class="container">
        <form action="showByCriteria">
            <c:set var="categories" value="${applicationScope.ItemService.allCategories}"/>
            <select name="category">
                <option name="None" value="None"><fmt:message key="none"/></option>
                <c:forEach var="category" items="${categories}">
                    <option name="${category}" value="${category.toLowerCase()}">${category}</option>
                </c:forEach>
            </select>
            <select name="sort">
                <option name="None" value="None"><fmt:message key="none"/></option>
                <option name="DateASC" value="DateASC"><fmt:message key="date.asc"/></option>
                <option name="DateDESC" value="DateDESC"><fmt:message key="date.desc"/></option>
                <option name="NameASC" value="NameASC"><fmt:message key="name.asc"/></option>
                <option name="NameDESC" value="NameDESC"><fmt:message key="name.desc"/></option>
                <option name="PriceASC" value="PriceASC"><fmt:message key="price.asc"/></option>
                <option name="PriceDESC" value="PriceDESC"><fmt:message key="price.desc"/></option>
                <option name="WeightASC" value="WeightASC"><fmt:message key="weight.asc"/></option>
                <option name="WeightDESC" value="WeightDESC"><fmt:message key="weight.desc"/></option>
            </select>
            <label for="left"><fmt:message key="price.range"/></label>
            <input id="left" name="left" value="${param.left}" type="number">
            <input id="right" name="right" value="${param.right}" type="number">
            <c:set var="colors" value="${ItemService.allColors}"/>
            <select name="color">
                <option name="None" value="None"><fmt:message key="none"/></option>
                <c:forEach var="color" items="${colors}">
                    <option name="${color}" value="${color.toLowerCase()}">${color}</option>
                </c:forEach>
            </select>
            <input type="submit" value="Filter">
        </form>
        <div class="row">
            <c:forEach var="i" items="${requestScope.items}">
                <input id="${i.id}" type="hidden">
                <div class="col-md-4">
                    <div class="card mb-4 box-shadow">
                        <c:if test="${i.imageURLs.size() > 0}">
                            <div id="carousel${i.id}" class="carousel slide" data-ride="carousel">
                                <ol class="carousel-indicators">
                                    <li data-target="#carousel${i.id}" data-slide-to="0" class="active"></li>
                                    <c:set var="counter" value="0"/>
                                    <c:forEach var="img" items="${i.imageURLs}">
                                        <c:if test="${img != i.imageURLs.get(0)}">
                                            <c:set var="counter" value="${counter + 1}"/>
                                            <li data-target="#carousel${i.id}" data-slide-to="${counter}"></li>
                                        </c:if>
                                    </c:forEach>
                                </ol>
                                <div class="carousel-inner">
                                    <div class="carousel-item active">
                                        <img style="margin-top: 5px; max-width: 350px; object-fit: contain; height: 200px; display:block; margin-left: auto; margin-right: auto"
                                             src="img/${i.imageURLs.get(0)}" height="200" width="350">
                                    </div>
                                    <c:forEach var="img" items="${i.imageURLs}">
                                        <c:if test="${img != i.imageURLs.get(0)}">
                                            <div class="carousel-item">
                                                <img style="margin-top: 5px; max-width: 100%; object-fit: contain; height: 200px; display: block; margin-left: auto; margin-right: auto"
                                                     src="img/${img}" height="200" width="350">
                                            </div>
                                        </c:if>
                                    </c:forEach>
                                </div>
                                <a class="carousel-control-prev" href="#carousel${i.id}" role="button"
                                   data-slide="prev">
                                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                    <span class="sr-only"><fmt:message key="prev"/></span>
                                </a>
                                <a class="carousel-control-next" href="#carousel${i.id}" role="button"
                                   data-slide="next">
                                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                    <span class="sr-only"><fmt:message key="next"/></span>
                                </a>
                            </div>
                        </c:if>
                        <div class="card-body">
                            <p class="card-text">
                                    ${i.name}<br>
                                <fmt:message key="color"/>: ${i.color}<br>
                                <fmt:message key="size"/>: ${i.size}<br>
                                <fmt:message key="date"/>: ${i.date}<br>
                                <fmt:message key="weight"/>: ${i.weight}<br>
                            </p>
                            <div class="d-flex justify-content-between align-items-center">
                                <h4 class="text-capitalize">${i.price} UAH</h4>
                                <a id="add${i.id}" href="#" class="btn btn-primary my-2">
                                    <fmt:message key="add.to.cart"/></a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<%@ include file="WEB-INF/jspf/footer.jspf" %>
<script>
    $(document).ready(function () {
        $('a[id^="add"]').on('click', function () {
            var id = this.id;
            $.get("addToCartAjax", {itemId: id}, function (text) {
                var res = text.split('%');
                $('#alert').text(res[0]);
                $('#totalPrice').html('<fmt:message key="cart"/>: ' + res[1] + ' UAH');
            });
            return false;
        });
    });
</script>
</body>
</html>
