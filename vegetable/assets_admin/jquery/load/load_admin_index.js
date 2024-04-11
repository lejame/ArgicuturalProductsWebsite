$(document).ready(function () {
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
      window.location.href="signin.html";
    }
  });