$(document).ready(function(){
  loadData();
})
function loadData() {
    $.ajax({
      method: "GET",
      url: "http://localhost:8080/admin/transaction",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + localStorage.getItem("Token"),
      },
    })
      .done(function (result) {
        displayTransaction(result.data);
     
      })
      .fail(function (jqXHR, textStatus, errorThrown) {
        console.error("AJAX request failed: " + textStatus, errorThrown);
      });
  }
  function displayTransaction(data){
    var htmlItem = ""
    for(i = 0;i<data.length;i++){
      var dataLoaded = data[i];
      htmlItem += createHTML(dataLoaded);
    }
    $("#recent_order tbody").append(htmlItem);
  }
  function createHTML(data){
      return `<tr>
                <td class="token">${data.orderId}</td>
                <td>${data.payMethod}</td>
                <td>${data.payDate}</td>
                <td>${data.firstName}</td>
                <td>${data.lastName}</td>
                <td>${data.address}</td>
                <td class="paid">${data.town}</td>
                <td>${data.email}</td>
                <td>${data.phone}</td>
                <td>${data.status}</td>
              </tr>`;
  }