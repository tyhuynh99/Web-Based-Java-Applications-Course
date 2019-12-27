







<div class="card" style="width: 18rem;float: left; margin: 20px 20px 20px 55px; text-align: center">
    <img class="card-img-top" src="img/portfolio/01-thumbnail.jpg" alt="Card image cap">
    <div class="card-body" style="background-color: white">
        <h5 class="card-title">${dto.getName()}</h5>
        <p class="card-text">${dto.getDes()}</p>
        Price: <i class="fa fa-dollar"></i><label class="price"><b>${dto.getPrice()}$</b></label>
        <c:if test="${dto.isStatus() eq 'true'}" var="checkStock">
            <form action="MainController" method="POST">
                <div style="text-align: left">
                    Quantity:
                    <select name="quantity"> 
                        <c:forEach begin="1" end="${dto.getQuantity()}" varStatus="counter">
                            <option value="${counter.count}">${counter.count}</option>
                        </c:forEach>
                    </select><br/>
                    <c:if test="${not empty requestScope.LIST_PET }" var="checkPet">
                        Choose your pet:
                        <select name="petId">
                            <c:forEach items="${requestScope.LIST_PET}" var="petDto">
                                <option value="${petDto.getId()}">
                                    ${petDto.getName()}
                                </option>
                            </c:forEach>
                        </select><br/>
                    </c:if>
                    <c:if test="${!checkPet}">
                        Please add pet before shopping
                    </c:if>
                </div>
                <input type="hidden" name="accId" value="${dto.getId()}"/>
                <button <c:if test="${!checkPet}">disabled</c:if> class="btn_addcart" name="action" value="Add accessory to cart"  ><i class="fas fa-shopping-cart" style="color: black"></i> Add to cart</button>
            </form>
        </c:if>
        <c:if test="${!checkStock}">
            <br/>
            <label style="color: red; font-size: 20px;"><b>OUT OF STOCK !</b></label>
        </c:if>
    </div>
</div>
