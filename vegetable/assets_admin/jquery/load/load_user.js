$(document).ready(function () {
  var username = localStorage.getItem("username");

  if (username !== null) {
    var userItem2 = $(
      '<li class="sidemenu-wrap d-none d-lg-flex">' +
        '<a href="#">' +
        username +
        '<i class="fa fa-caret-down"></i> </a>' +
        '<ul class="dropdown-sidemenu dropdown-hover-2 dropdown-language">' +
        '<li><a class="btn-logout">Logout</a></li>' +
        "</ul>" +
        "</li>" +
        `<li class="minicart-wrap" style="margin-right:10px;">
          <a href="#" class="minicart-btn toolbar-btn">
            <i class="fa-solid fa-bell" style="color: #FFD43B;"></i>
            <span class="notify_number">0</span>
          </a>
          <div class="cart-item-wrapper dropdown-sidemenu dropdown-hover-2 notify">
          </div>
        </li>`
    );

    $(".add-name").replaceWith(userItem2);
  }
  handleLogotButtonClick();
  load_notify();
});
function handleLogotButtonClick() {
  $("body").on("click", ".btn-logout", function () {
    clearLocalStorage();
    window.location.href = "index_logined.html";
  });
}
function clearLocalStorage() {
  localStorage.removeItem("username");
  localStorage.removeItem("Token");
}
function load_notify() {
  $.ajax({
    method: "GET",
    url: "http://localhost:8080/notify",
    headers: {
      "Content-Type": "application/json",
      Authorization: "Bearer " + localStorage.getItem("Token"),
    },
  }).done(function (result) {
    update_notify(result.data);
  });
}

function update_notify(result) {
  var notify_items = result; // Assuming 'result.data' contains an array of notifications
  var notify_item_html = "";

  for (var i = 0; i < notify_items.length; i++) {
    var notify_item = notify_items[i]; // Access each notification item

    notify_item_html += `<div class="single-cart-item">
                    <div class="cart-img">
                        <a><img src="${notify_item.img}" alt="" height="100%" width="100%" /></a> </div>
                    <div class="cart-text">
                        <h5 class="title"><a>${notify_item.title}</a></h5>
                        <div class="cart-text-btn">
                            <div class="cart-qty">
                                <span>${notify_item.content}</span>
                            </div>
                        </div>
                    </div>
                </div>`;
  }
  $(".notify_number").text(notify_items.length);
  $(".notify").empty().append(notify_item_html);
}
