var dataLoaded = false;
$(document).ready(function () {
  if (dataLoaded === false) {
    loadData();
  }
});
function loadData() {
  $.ajax({
    method: "GET",
    url: "http://localhost:8080/notify",
    headers: {
      "Content-Type": "application/json",
      Authorization: "Bearer " + localStorage.getItem("Token"),
    },
  })
    .done(function (result) {
      displayNotify(result.data);
      var data = JSON.stringify(result.data);
      localStorage.setItem("NotifyList", data);
      dataLoaded = true; // Set dataLoaded to true after loading data
    })
    .fail(function (jqXHR, textStatus, errorThrown) {
      console.error("AJAX request failed: " + textStatus, errorThrown);
    });
}
function displayNotify(notify) {
  var htmlItem = "";
  for (var i = 0; i < notify.length; i++) {
    var notifys = notify[i];
    htmlItem += createNotifyHTML(notifys);
  }
  $("#notify_list tbody").empty().append(htmlItem);
}
function createNotifyHTML(notify) {
  return `
            <tr>
                <td>${notify.id}</td>
                <td><img class="tbl-thumb" src="${notify.img}"
                        alt=""></td>
                <td>${notify.title}</td>
                <td>${notify.content}</td>
                <td>${notify.date}</td>
            </tr>`;
}


