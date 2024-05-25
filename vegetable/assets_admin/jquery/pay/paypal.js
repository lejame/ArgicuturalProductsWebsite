$(document).ready(function () {
  cartList();
  loadCartBill();
  paypal();
  clickAppVoucher();
});

function cartList() {
  var dataCart = localStorage.getItem("cart");
  var data = JSON.parse(dataCart);
  var htmlListCart = "";

  var count = 0;
  if (data) {
    for (var i = 0; i < data.length; i++) {
      count += data[i].soluong * data[i].price;
      htmlListCart += `<div class="single-cart-item">
                            <div class="cart-img">
                                <a><img src="${data[i].images.split(",")[0]}"
                                        alt="" height="50" wight="30"></a>
                            </div>
                            <div class="cart-text">
                                <h5 class="title"><a>${data[i].name}</a></h5>
                                <div class="cart-text-btn">
                                    <div class="cart-qty">
                                        <span>${data[i].soluong}×</span>
                                        <span class="cart-price">$${
                                          data[i].price
                                        }</span>
                                    </div>
                                    <button type="button"><i
                                            class="ion-trash-b"></i></button>
                                </div>
                            </div>
                        </div>`;
    }
  }
  var htmlInsert = ` <div class="cart-price-total d-flex justify-content-between">
                      <h5>Total :</h5>
                      <h5>$${count}</h5>
                      </div>
                      <div class="cart-links d-flex justify-content-center">
                      <a class="obrien-button white-btn" href="cart.html">View
                          cart</a>
                      <a class="obrien-button white-btn"
                          href="checkout.html">Checkout</a>
                    </div>`;
  console.log(data.length);

  $(".list-cart").append(htmlListCart, htmlInsert);
  $(".cart-item_count").text(data.length);
}
var total_price = 0;
function loadCartBill() {
  var cart = localStorage.getItem("cart");
  var dataCart = JSON.parse(cart);
  var html = "";
  var count = 0;
  html += `<tr class="cart_item">`;
  for (i = 0; i < dataCart.length; i++) {
    count += dataCart[i].soluong * dataCart[i].price;
    html += `<tr>`;
    html += `<td class="cart-product-name">${dataCart[i].name}<strong  class="product-quantity">×${dataCart[i].soluong}</strong></td>
      <td>________ <strong  class="product-quantity">1×${dataCart[i].price}</strong></td>`;
    html += `</tr>`;
  }
  html += `</tr>`;
  total_price = count;
  $(".your-order-table tbody").html(html);
  $(".amount").text("$" + count);
}
function paypal() {
  $(".btn-place-order").click(function (e) {
    e.preventDefault();
    var emailAddress = $(".email_").val();
    console.log(emailAddress);
    var phone = $(".phone_").val();
    var description = $(".description_").val();
    var lastName = $(".lastName_").val();
    var firstName = $(".firstName_").val();
    var town = $(".town_").val();
    var address = $(".address_").val();

    var products = localStorage.getItem("cart");

    products = JSON.parse(products);

    var count = 0;
    for (i = 0; i < products.length; i++) {
      count += products[i].price * products[i].soluong;
    }
    var total = count;

    const newPaypal = {
      price: total,
      description: description,
      product: products,
      phone: phone,
      lastName: lastName,
      firstName: firstName,
      town: town,
      emailAddress: emailAddress,
      address: address,
      email: localStorage.getItem("email"),
    };

    const formData = JSON.stringify(newPaypal); // Convert the object to a JSON string
    console.log(formData);
    localStorage.removeItem("cart");
    $.ajax({
      url: "http://localhost:8080/paypal",
      method: "POST",
      data: formData,
      contentType: "application/json", // Set the content type to JSON
    }).done(function (result) {

      location.href = result;
    });
    // var cart = [];
    // localStorage.setItem("cart",cart);

  });
}
function clickAppVoucher() {
  $(".form-apply-discount").submit(function (e) {
    e.preventDefault();
    var code = $(".discount-code").val();

    $.ajax({
      method: "GET",
      url: "http://localhost:8080/discount/" + code + "/" + total_price,
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + localStorage.getItem("Token"),
      },
    }).done(function (result) {
      $(".amount").text("$" + result.data);
      $(".discout_code").text(100 - (result.data * 100) / total_price + "%");
    });
  });
}
