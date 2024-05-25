const urlParams = new URLSearchParams(window.location.search);
const response = urlParams.get("id");
$(document).ready(function(){
    if(response != null){
        load_product_to_cart(response);
    }
})
function load_product_to_cart(id) {
  $.ajax({
    method: "GET",
    url: "http://localhost:8080/user/orderId/" + id,
    headers: {
      "Content-Type": "application/json",
      Authorization: "Bearer " + localStorage.getItem("Token"),
    },
  })
    .done(function (result) {
      createCartTable(result.data);
    })
    .fail(function (jqXHR, textStatus, errorThrown) {
      console.error("AJAX request failed: " + textStatus, errorThrown);
    });
}
var total_price = 0;

function createCartTable(data) {
  var htmlTableCart = "";
  const cartData = data;
  cartData.forEach((item) => {

    htmlTableCart += createCartHtml(item);
  });
  $(".table-bordered tbody").append(htmlTableCart);
  dataLoaded = true;
  totol_price();
}
function createCartHtml(item) {
    total_price += item.price * item.quantity_stock;
  return `<tr>
                    <td class="pro-thumbnail"><a href="#"><img class="img-fluid"
                                src="${
                                  item.images.split(",")[0]
                                }" alt="Product"/></a>
                    </td>
                    <td class="pro-title"><a href="#">${item.name}</a></td>
                    <td class="pro-price"><span>$${item.price}.00</span></td>
                    <td class="pro-quantity">
                        <div class="quantity">
                            <div class="cart-plus-minus">
                                <input class="cart-plus-minus-box" value="${
                                  item.quantity_stock
                                }" type="text">
                                <div class="dec qtybutton">-</div>
                                <div class="inc qtybutton">+</div>
                                <div class="dec qtybutton"><i class="fa fa-minus"></i></div>
                                <div class="inc qtybutton"><i class="fa fa-plus"></i></div>
                            </div>
                        </div>
                    </td>
                    <td class="pro-subtotal"><span>$${
                      item.price * item.quantity_stock
                    }.00</span></td>
                    <td class="pro-remove"><a href="#"><i class="ion-trash-b" data-product-id='${JSON.stringify(
                      item
                    )}'></i></a></td>
                </tr>`;
}

function totol_price() {
    $(".total-price").text("");
    $(".total-price").text("$" + total_price);
    $(".total-amount").text("$" + total_price);
  }
