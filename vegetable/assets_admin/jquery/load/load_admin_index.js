$(document).ready(function () {
  loadData();
  var username = localStorage.getItem("username");
  var email = localStorage.getItem("email");
  if (username !== null) {
    var userItem1 = `<div class="details">
                            <h6>${username}</h6>
                        </div>
                        <ul class="border-top">
                            <li><a href="signin.html"><i class="ri-logout-circle-r-line"></i>Logout</a></li>
                        </ul>`;

    $("#replaces").replaceWith(userItem1);
  } else {
    alert("Lỗi hệ thống!");
    window.location.href = "signin.html";
  }
});

function loadData() {
  $.ajax({
    method: "GET",
    url: "http://localhost:8080/admin",
    headers: {
      "Content-Type": "application/json",
      Authorization: "Bearer " + localStorage.getItem("Token"),
    },
  })
    .done(function (result) {
      uploadDardboard(result.data);
    })
    .fail(function (jqXHR, textStatus, errorThrown) {
      console.error("AJAX request failed: " + textStatus, errorThrown);
    });
}

function uploadDardboard(data) {
  $(".totalcustomer").text(data.totalUser);
  $(".revenueByMonth").text(data.revenueByMonth);
  $(".totalProduct").text(data.totalProduct);
  $(".totalBlog").text(data.totalBlog);
}
