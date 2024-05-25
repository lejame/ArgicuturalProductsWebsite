$(document).ready(function () {
  createNotify();
});
function logout() {
  localStorage.removeItem("token");
}
function createNotify() {
  $(".form-create-notify").submit(function (e) {
    e.preventDefault();
    var fileInput1 = document.getElementById("product_main");
    var selectedFile1 = fileInput1.files[0];
    var fileList = [selectedFile1];
    var title = $("#input-title").val();
    var description = $("#input-description").val();
    console.log(fileList);
    const formData = new FormData();
    const data = {
      title: title,
      content: description,
    };
    console.log(data);
    formData.append(
      "data",
      new Blob([JSON.stringify(data)], { type: "application/json" })
    );
    fileList.forEach((item) => {
      formData.append("file", item);
    });
    axios
      .post("http://localhost:8080/notify/admin", formData, {
        headers: {
          "content-type": "multipart/form-data",
          authorization: `Bearer ${localStorage.getItem("Token")}`,
        },
        timeout: 5000,
      })
      .then((result) => {
        if (!result) {
          alert("Lỗi");
        } else {
          alert("Gửi thông báo thành công");  
          location.reload();
        }
      })
      .catch((err) => {
        console.log(err);
      });
  });
}
