$(document).ready(function () {
  var total = 120;
  var currency = "USD";
  var method = "paypal";
  var description = "123";
  const newPaypal = {
    price: total,
    currency: currency,
    description: description,
    method: method,
    intent: "sale", // sale or authorization (depending on your needs)
  };
  console.log(newPaypal);

  const formData = JSON.stringify(newPaypal); // Convert the object to a JSON string
  console.log(formData);
  $(".btn-place-order").click(function (e) {
    e.preventDefault();
    $.ajax({
      url: "http://localhost:8080/paypal",
      method: "POST",
      data: formData,
      contentType: "application/json", // Set the content type to JSON
    }).done(function (result) {
        location.href = result;
    });
  });
});
