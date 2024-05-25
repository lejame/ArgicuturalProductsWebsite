document.addEventListener("DOMContentLoaded", function () {
  var currentUrl = new URLSearchParams(window.location.search);
  var decodedUrl = decodeURIComponent(currentUrl);
  var dataParam = decodedUrl.split("=")[1];
  var base64Data = atob(dataParam);
  console.log(base64Data);
  const jsonObj = JSON.parse(base64Data);
  // Create a new tab in the browser for each item in the array of objects
  console.log(jsonObj);
  var htmlRating = "";
  for (var j = 0; j < 5; j++) {
    htmlRating +=
      j < jsonObj.rate
        ? '<i class="fa fa-star"></i>'
        : '<i class="fa fa-star-o"></i>';
  }
  htmlRating += `<span>${jsonObj.review_number} Reviews</span>`;

  $(".product-name").text(jsonObj.name);
  $(".new-price").text("$"+jsonObj.price);
  $(".preview-oldprice").html("$"+jsonObj.old_price);
  $(".description").text(jsonObj.description);
  $(".product-rate").html(htmlRating);

  $(".images-1").html(
    `<img src="${jsonObj.images.split(",")[0]}" alt="Product">`
  );
  $(".images-2").html(
    `<img src="${jsonObj.images.split(",")[1]}" alt="Product Thumnail">`
  );
});
function submitReivews() {
  $(".comment-form-area").submit(function (e) {
    e.preventDefault();
    // lấy sản phẩm
    var currentUrl = new URLSearchParams(window.location.search);
    var decodedUrl = decodeURIComponent(currentUrl);
    var dataParam = decodedUrl.split("=")[1];
    var base64Data = atob(dataParam);
    const jsonObj = JSON.parse(base64Data);

    var email = $("#email").val();
    var review_content = $("#review-content").val();
    var rate = $(".rating_list .review_info .product-rating").find(".fa-star").length;
    console.log(rate);
    var idProduct = jsonObj.id;

    // lấy ngày hiện tại khi comment
    var currentDate = new Date();
    var day = currentDate.getDate();
    var month = currentDate.getMonth() + 1; // Tháng trong JavaScript được đánh số từ 0 đến 11, nên cần cộng thêm 1.
    var year = currentDate.getFullYear();
    var formattedDate = day + "/" + month + "/" + year;

    if (!email || !review_content) {
      alert("Please fill out all fields");
    } else {
      var formdata = new FormData();
      var newReviews = {
        email: email,
        comment: review_content,
        rate: rate,
        time: formattedDate,
        idProduct: idProduct,
      };
      formdata.append(
        "data",
        new Blob([JSON.stringify(newReviews)], { type: "application/json" })
      );
      axios
        .post("http://localhost:8080/reviews", formdata, {
          headers: {
            authorization: `Bearer ${localStorage.getItem("Token")}`,
          },
          timeout: 5000,
        })
        .then((result) => {
          if (result.data != null) {
            location.reload();
          }
          else{
            alert("Email không tồn tại");
          }
        })
        .catch((err) => {
          console.log(err);
        });
    }
  });
}
function chosseRate() {
  $(".product-rating i").click(function () {
    $(this).addClass("fa-star").removeClass("fa-star-o"); // Add filled star
    $(this).prevAll("i").addClass("fa-star").removeClass("fa-star-o"); // Add filled stars before the clicked star
    $(this).nextAll("i").addClass("fa-star-o").removeClass("fa-star"); // Remove filled stars after the clicked star
  });
}

function loadRevews() {
  // lấy sản phẩm
  var currentUrl = new URLSearchParams(window.location.search);
  var decodedUrl = decodeURIComponent(currentUrl);
  var dataParam = decodedUrl.split("=")[1];
  var base64Data = atob(dataParam);
  const jsonObj = JSON.parse(base64Data);

  $.ajax({
    method: "GET",
    url: "http://localhost:8080/reviews/" + jsonObj.id,
    headers: {
      "Content-Type": "application/json",
      Authorization: "Bearer " + localStorage.getItem("Token"),
    },
  })
    .done(function (result) {
      displayReviews(result.data);
    })
    .fail(function (jqXHR, textStatus, errorThrown) {
      console.error("AJAX request failed: " + textStatus, errorThrown);
    });
}
$(document).ready(function () {
  chosseRate();
  submitReivews();
  loadRevews();
});
function displayReviews(data) {
  var htmlItem = "";
  for (var i = 0; i < data.length; i++) {
    var reivew = data[i];
    htmlItem += createReviewsHTML(reivew);
  }
  $("#connect-2 .product_tab_content .content-in-here")
    .empty()
    .append(htmlItem);
}
function createReviewsHTML(review) {
  var htmlRating = "";
  for (var j = 0; j < 5; j++) {
    htmlRating +=
      j < review.rate
        ? '<i class="fa fa-star"></i>'
        : '<i class="fa fa-star-o"></i>';
  }

  return `<div class="review_address_inner">
                <!-- Start Single Review -->
                <div class="pro_review mb-5">
                    <div class="review_thumb">
                        <img alt="review images" src="assets/images/review/1.jpg">
                    </div>
                    <div class="review_details">
                        <div class="review_info mb-2">
                            <div class="product-rating mb-2" >
                                ${htmlRating}
                            </div>
                            <h5>${review.user_name} - <span> ${review.time}</span></h5>
                        </div>
                        <p>${review.comment}
                        </p>
                    </div>
                </div>
            </div>`;
}
