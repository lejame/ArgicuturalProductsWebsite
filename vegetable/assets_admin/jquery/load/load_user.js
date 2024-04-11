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
        "</li>"
    );
    $(".add-name").replaceWith(userItem2);
  }
  handleLogotButtonClick();
});
function handleLogotButtonClick() {
  $("body").on("click", ".btn-logout", function () {
    localStorage.removeItem("username");
    window.location.href = "index_logined.html";
  });
}
