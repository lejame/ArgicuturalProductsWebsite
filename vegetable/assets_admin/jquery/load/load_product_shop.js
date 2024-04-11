var dataLoaded = false;
// const
$(document).ready(function () {
  if (!dataLoaded) {
    loadData();
    cartList();
    handleCartButtonClick();
    handleCartButtonClickDelete();
    addCartList();
  }
});

var clickAddCart = false;
function handleCartButtonClick() {
  $("body").on("click", ".btn-cart", function () {
    var carts = [];
    var dataCart = localStorage.getItem("cart");
    if (dataCart) {
      carts = JSON.parse(dataCart);
    }
    var isExistProduct = false;
    var data = $(this).attr("data-product");
    var datapasre = JSON.parse(data);
    for (var i = 0; i < carts.length; i++) {
      if (datapasre.id === carts[i].id) {
        carts[i].soluong += 1;
        isExistProduct = true;
      }
    }
    if (isExistProduct == false) {
      datapasre.soluong = 1;
      carts.push(datapasre);
    }
    var dataJsonCart = JSON.stringify(carts);
    localStorage.setItem("cart", dataJsonCart);
    createElementCart();
  });
}

function createElementCart() {
  $(".list-cart").text("");
  $(".cart-item_count").empty();
  cartList();
}

function handleCartButtonClickDelete() {
  if (clickAddCart) {
    $("body").on("click", ".ion-trash-b", function () {
      var data = $(this).attr("data-product-id");
      var carts = [];
      var dataCart = localStorage.getItem("cart");
      if (dataCart) {
        carts = JSON.parse(dataCart);
      }
      var datapasre = JSON.parse(data);
      console.log(datapasre);
      var newCarts = [];
      for (var i = 0; i < carts.length; i++) {
        if (datapasre.id !== carts[i].id) {
          newCarts.push(carts[i]);
          console.log(newCarts);
        }
      }
      carts = newCarts;
      var dataJsonCart = JSON.stringify(carts);
      localStorage.setItem("cart", dataJsonCart);
      createElementCart();
    });
  }
}

// load data
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
      dataLoaded = true;
    })
    .fail(function (jqXHR, textStatus, errorThrown) {
      console.error("AJAX request failed: " + textStatus, errorThrown);
    });
}
function createElementCart() {
  $(".cart-item_count").empty();
  $(".list-cart").html("");
  cartList();
}
function displayProducts(products) {
  var htmlItem = "";
  for (var i = 0; i < products.length; i++) {
    var product = products[i];
    htmlItem += createProductHTML(product);
  }
  $("#list-product").append(htmlItem);
}
function cartList() {
  clickAddCart = true;
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
                                            class="ion-trash-b" data-product-id='${JSON.stringify(
                                              data[i]
                                            )}'></i></button>
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
function createProductHTML(product) {
  var rate = product.rate || 0;
  var htmlRating = "";
  for (var j = 0; j < 5; j++) {
    htmlRating +=
      j < rate ? '<i class="fa fa-star"></i>' : '<i class="fa fa-star-o"></i>';
  }

  var oldPrice = product.old_price || "";
  var htmlOldPrice = oldPrice
    ? '<span class="old-price"><del>' + oldPrice + "$</del></span>"
    : "";
  const returnStringHere = `<div class="col-custom product-area col-lg-4 col-md-6 col-sm-6">`;
  var images = product.images.split(",");
  var htmlImages = `<img src="${images[0]}" alt="" class="product-image-1 w-100" height="300" wight="300"><br>
                    <img src="${images[1]}" alt="" class="product-image-2 position-absolute w-100" height="300" wight="300">`;

  return `${returnStringHere}
              <div class="single-product position-relative">
                <div class="product-image" data=>
                  <a class="d-block" href="product-details.html?data=${btoa(unescape(encodeURIComponent(JSON.stringify(product))))}">
                  ${htmlImages}
                  </a>
                </div>
                <div class="product-content">
                  <div class="product-rating">
                  ${htmlRating}
                  </div>
                  <div class="product-title">
                      <h4 class="title-2"> <a href="product-details.html">${
                        product.name
                      }</a>
                      </h4>
                  </div>
                  <div class="price-box">
                      <span class="regular-price ">${product.price}$</span>
                      ${htmlOldPrice}
                  </div>
              </div>
              <div class="add-action d-flex position-absolute">
                  <a  title="Add To cart" class="btn-cart" data-product='${JSON.stringify(
                    product
                  )}'>
                      <i class="ion-bag "></i>
                  </a>
                  <a href="compare.html" title="Compare">
                      <i class="ion-ios-loop-strong"></i>
                  </a>
                  <a href="wishlist.html" title="Add To Wishlist">
                      <i class="ion-ios-heart-outline"></i>
                  </a>
                  <a id="reviewproduct-${
                    product.id
                  }" href="#" title="Quick View" data-bs-toggle="modal" data-bs-target="#reviewModal">
                      <i class="ion-eye"></i>
                  </a>
              </div>
          </div>
        </div>`;
}
// Modal event handling
$(document).on("click", "[id*='reviewproduct-']", function () {
  var productId = this.id.split("-")[1];
  console.log();
  var dataJson = localStorage.getItem("ProductList");
  if (dataJson) {
    var productList = JSON.parse(dataJson);
    for (var i = 0; i < productList.length; i++) {
      if (productList[i].id === parseInt(productId)) {
        var foundProduct = productList[i];
        CreateReviewModal(foundProduct);
        break;
      }
    }
  }
});

$(".preview-close").click(function () {
  const modal = $("#preview-modal");
  modal.toggleClass("show");
});

$("button.btn-list").on("click", function () {
  var button = $(this);
  if (button.hasClass("active")) return;

  // Xóa class "active" từ tất cả các button
  $("button.btn-list").removeClass("active");

  // Thêm class "active" vào button được click
  button.addClass("active");

  // Đổi class của thẻ #list-product
  $("#list-product").removeClass("grid_3").addClass("grid_list");
});

function CreateReviewModal(product) {
  const modal = $("#preview-modal");
  modal.toggleClass("show");
  if (product.old_price === null) {
    $(".preview-oldprice").hide();
  }
  var htmlRating = "";
  for (var j = 0; j < 5; j++) {
    htmlRating +=
      j < product.rate
        ? '<i class="fa fa-star"></i>'
        : '<i class="fa fa-star-o"></i>';
  }
  htmlRating += `<span>${product.review_number} Reviews</span>`;
  $(".preview-oldprice").text(product.old_price + "$");
  $(".preview-productname").text(product.name);
  $(".preview-newprice").text(product.price + "$");
  $(".preview-rating").html(htmlRating);

  const productImages = product.images.split(",");
  $(".preview-thumbnail").attr(
    "src",
    productImages.length >= 2 ? productImages[1] : productImages[0]
  );

  $(".preview-description").text(product.description);
  document
    .getElementById("add-to-cart")
    .setAttribute("data-product", `${JSON.stringify(product)}`);
}

function addCartList() {
  $("#add-to-cart").on("click", function (e) {
    e.preventDefault();
    var product = $("#add-to-cart").attr("data-product");
    product = JSON.parse(product);
    var quantity = parseInt($(".cart-plus-minus-box").val());
    // Thêm sản phẩm vào giỏ hàng
    var carts = [];
    var dataCart = localStorage.getItem("cart");
    if (dataCart) {
      carts = JSON.parse(dataCart);
      var isExistProduct = false;
      for (var i = 0; i < carts.length; i++) {
        if (product.id === carts[i].id) {
          carts[i].soluong += quantity;
          isExistProduct = true;
        }
      }
      if (!isExistProduct) {
        product.soluong = quantity;
        carts.push(product);
      }
    }

    // Cập nhật lại giỏ hàng trong local storage
    var dataJsonCart = JSON.stringify(carts);
    localStorage.setItem("cart", dataJsonCart);
    createElementCart();
  });
}
