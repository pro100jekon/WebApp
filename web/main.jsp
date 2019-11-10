<%@ include file="WEB-INF/jspf/taglib.jspf" %>
<html>
<title>
    Welcome to the shop!
</title>
<head>
    <%@ include file="WEB-INF/jspf/style.jspf" %>
</head>
<body>
<%@ include file="WEB-INF/jspf/header.jspf" %>
<div class="album py-5 bg-light">
    <div class="container">
        <div class="container">
            <form action="sort">
                <select class="custom-select col-4" name="mode">
                    <option value="date">Date</option>
                    <option value="name">Name</option>
                    <option value="price">Price</option>
                    <option value="weight">Weight</option>
                </select>
                <label class="check-box" for="checkbox">Reverse</label>
                <input id="checkbox" name="desc" type="checkbox" value="true">
                <input class="btn btn-outline-primary" type="submit" value="Sort">
            </form>
            <form action="showByCriteria">
                <div class="form-row">
                    <div class="row col-3">Show items by price range:</div>
                    <div class="col-3"><input class="form-control" name="left" type="number" value="${param.left}" required></div>
                    <div class="col-3"><input class="form-control" name="right" type="number" value="${param.right}" required></div>
                    <input class="btn btn-outline-primary" type="submit" value="Show">
                    <c:set var="colors" value="${ItemService.allColors}"/>
                    <div class="dropdown">
                        <button class="btn btn-outline-primary dropdown-toggle" type="button"
                                id="dropdownMenuButton"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Colors
                        </button>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                            <c:forEach var="color" items="${colors}">
                                <a class="dropdown-item" href="showByCriteria?color=${color}&category=">${color}</a>
                            </c:forEach>
                        </div>
                        <a href="main" class="btn btn-outline-primary">Reset</a>
                    </div>
                </div>
            </form>
        </div>
        <div class="row">
            <c:forEach var="i" items="${sessionScope.items}">
                <input id="${i.id}" type="hidden">
                <div class="col-md-4">
                    <div class="card mb-4 box-shadow">
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
                                <span class="sr-only">Previous</span>
                            </a>
                            <a class="carousel-control-next" href="#carousel${i.id}" role="button"
                               data-slide="next">
                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                <span class="sr-only">Next</span>
                            </a>
                        </div>
                        <div class="card-body">
                            <p class="card-text">
                                    ${i.name}<br>
                                Color: ${i.color}<br>
                                Size: ${i.size}<br>
                                Date: ${i.date}<br>
                                Weight: ${i.weight}<br>
                            </p>
                            <div class="d-flex justify-content-between align-items-center">
                                <h4 class="text-capitalize">${i.price} UAH</h4>
                                <a id="add${i.id}" href="#" class="btn btn-primary my-2">Add to cart</a>
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
                $('#totalPrice').html(res[1]);
            });
            return false;
        });
    });
</script>
</body>
</html>
