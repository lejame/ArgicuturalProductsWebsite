var dataLoaded = false;
// const
$(document).ready(function () {
  if (!dataLoaded) {
    loadData();
    handleCartButtonClick();
    cartList();
    handleCartButtonClickDelete();
    loadBlogHome();
    addCartList();
  } else {
    createElementCart();
  }
});

// add product to cart
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
// delete product to cart
var clickDelete = false;
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

function displayProducts(products) {
  var htmlItem = "";
  for (var i = 0; i < 4; i++) {
    var product = products[i];
    htmlItem += createProductHTML(product);
  }
  $("#best_product").append(htmlItem);
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
                                <a><img src="${data[i].images.split(",")[0]}"></
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
  $(".list-cart").append(htmlListCart, htmlInsert);
  $(".cart-item_count").text(data.length);
}
function createProductHTML(product) {
  var htmlLableSoldOut = "";
  if (product.quantity === 0) {
    htmlLableSoldOut += `<div class="label-product">
                            <span class="label-sale position-absolute text-uppercase text-white text-center d-block">Soldout</span>
                        </div>`;
  }
  var rate = product.rate || 0;
  var htmlRating = "";
  for (var j = 0; j < 5; j++) {
    htmlRating +=
      j < rate ? '<i class="fa fa-star"></i>' : '<i class="fa fa-star-o"></i>';
  }

  var oldPrice = product.old_price || "";
  var htmlOldPrice = oldPrice
    ? '<span class="old-price">' + oldPrice + "$</span>"
    : "";

  var images = product.images.split(",");
  var htmlImages = `<img src="${images[0]}" alt="" class="product-image-1 w-100" height="340" wight="340">
                    <img src="${images[1]}" alt="" class="product-image-2 position-absolute w-100" height="340" wight="340">`;

  return `<div class="single-item" style="margin-bottom:20px; margin-left:20px;">
              <div class="single-product position-relative">
                <div class="product-image" data=>
                  <a class="d-block" href="product-details.html">
                  ${htmlImages}
                  </a>
                </div>
                ${htmlLableSoldOut}
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
$(".preview-close").click(function () {
  const modal = $("#preview-modal");
  modal.toggleClass("show");
});

function loadBlogHome() {
  $.ajax({
    method: "GET",
    url: "http://localhost:8080/blog",
    headers: {
      "Content-Type": "application/json",
      Authorization: "Bearer " + localStorage.getItem("Token"),
    },
  })
    .done(function (result) {
      displayBlogs(result.data);
    })
    .fail(function (jqXHR, textStatus, errorThrown) {
      console.error("AJAX request failed: " + textStatus, errorThrown);
    });
}
function displayBlogs(blogs) {
  var htmlItem = "";
  for (var i = 0; i < 3; i++) {
    var blog = blogs[i];
    htmlItem += createBLogHTML(blog);
  }
  $(".col-custom #blog-index-logined").append(htmlItem);
}
function createBLogHTML(blog) {
  return ` <div class="single-blog" style="margin-bottom:10px; margin-left:10px;">
            <div class="single-blog-thumb">
                <a href="blog.html">
                    <img src="${
                      blog.image
                    }" alt="Blog Image" height="330" wight="330">
                </a>
            </div>
            <div class="single-blog-content position-relative">
                <div class="post-date text-center border rounded d-flex flex-column position-absolute">
                    <span>${blog.date.split("/")[0]}</span>
                    <span>${blog.date.split("/")[1]}</span>
                </div>
                <div class="post-meta">
                    <span class="author">Author: ${blog.author}</span>
                </div>
                <h2 class="post-title">
                    <a href="blog.html">${blog.name}</a>
                </h2>
                <p class="desc-content">${blog.content}</p>
            </div>
          </div>`;
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
