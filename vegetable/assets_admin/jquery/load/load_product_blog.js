var dataLoaded = false;
// const
$(document).ready(function () {
  if (!dataLoaded) {
    loadData();
    cartList();
  }
});
function loadData() {
  $.ajax({
    method: "GET",
    url: "http://localhost:8080/blog",
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${localStorage.getItem("Token")}`,
    },
  })
    .done(function (result) {
      console.log(result);
      displayBlog(result.data);
      console.log(result.data);
      var data = JSON.stringify(result.data);
      localStorage.setItem("BlogList", data);
      dataLoaded = true;
    })
    .fail(function (jqXHR, textStatus, errorThrown) {
      console.error("AJAX request failed: " + textStatus, errorThrown);
    });
}

function displayBlog(Blog) {
  var htmlItem = "";
  for (var i = 0; i < Blog.length; i++) {
    var blog = Blog[i];
    htmlItem += createBlogHTML(blog);
  }
  $(".list-blog").append(htmlItem);
}

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
function createBlogHTML(blog) {
  
  return `      <div class="col-md-6 col-custom mb-4">
                    <div class="single-blog">
                        <div class="single-blog-thumb">
                            <a href="blog.html">
                                    <img src="${blog.image}" alt="Blog Image" >
                            </a>
                        </div>
                        <div class="single-blog-content position-relative">
                            <div class="post-date text-center border rounded d-flex flex-column position-absolute">
                                <span>${blog.date.split("/")[0]}</span>
                                <span>${blog.date.split("/")[1]}</span>
                            </div>
                            <div class="post-meta">
                                <span class="author">${blog.author}</span>
                            </div>
                            <h2 class="post-title">
                                <a href="blog.html">${blog.name}</a>
                            </h2>
                            <p class="desc-content">${blog.content}</p>
                        </div>
                    </div>
                </div>
                    `;
}

