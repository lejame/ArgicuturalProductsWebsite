$(document).ready(function () {
  load_order_of_user();
});

function load_order_of_user() {
  var email = localStorage.getItem("email");
  $.ajax({
    method: "GET",
    url: "http://localhost:8080/user/order/" + email,
    headers: {
      "Content-Type": "application/json",
      Authorization: "Bearer " + localStorage.getItem("Token"),
    },
  })
    .done(function (result) {
      displayAccountOrder(result.data);
    })
    .fail(function (jqXHR, textStatus, errorThrown) {
      console.error("AJAX request failed: " + textStatus, errorThrown);
    });
}
function displayAccountOrder(result) {
  var htmlItem = "";
  for (var i = 0; i < result.length; i++) {
    var data = result[i];
    htmlItem += createHTML(data);
  }
  $(".transaction_history tbody").empty().append(htmlItem);
}

function createHTML(data) {
  return `
    <tr>
        <td>${data.orderId}</td>
        <td>${data.date}</td>
        <td>${data.status}</td>
        <td>${data.total}</td>
        <td><a href="order_carted.html?id=${data.orderId}" class="btn obrien-button-2 primary-color rounded-0 click-check-cart" >View</a></td>
    </tr>`;
}