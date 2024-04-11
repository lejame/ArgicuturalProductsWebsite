var dataLoaded = false;
$(document).ready(function () {
  if (dataLoaded === false) {
    loadData();
  }
});
function loadData() {
  $.ajax({
    method: "GET",
    url: "http://localhost:8080/product",
    headers: {
      "Content-Type": "application/json",
      Authorization: "Bearer " + localStorage.getItem("Token"),
    },
  })
    .done(function (result) {
      displayProducts(result.data);
      var data = JSON.stringify(result.data);
      localStorage.setItem("ProductList", data);
      dataLoaded = true; // Set dataLoaded to true after loading data
    })
    .fail(function (jqXHR, textStatus, errorThrown) {
      console.error("AJAX request failed: " + textStatus, errorThrown);
    });
}
function displayProducts(products) {
  var htmlItem = "";
  for (var i = 0; i < products.length; i++) {
    var product = products[i];
    htmlItem += createProductHTML(product);
  }
  $("#product_list tbody").empty().append(htmlItem);
}
function createProductHTML(product) {
  return `
            <tr>
                <td><img class="tbl-thumb" src="${product.images.split(",")[0]}"
                        alt="Product Image"></td>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>${product.category}</td>
                <td>${product.rate}</td>
                <td>${product.quantity}</td>
                <td><textarea name="" id="" cols="30" rows="3" cols="7">${
                  product.description
                }</textarea></td>
                <td>
                    <div class="d-flex justify-content-center">
                        <button type="button"
                            class="btn btn-outline-success dropdown-toggle dropdown-toggle-split"
                            data-bs-toggle="dropdown" aria-haspopup="true"
                            aria-expanded="false" data-display="static">
                            <span class="sr-only"><i
                                    class="ri-settings-3-line"></i></span>
                        </button>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="#">Edit</a>
                            <a class="dropdown-item" href="#">Delete</a>
                        </div>
                    </div>
                </td>
            </tr>`;
}
