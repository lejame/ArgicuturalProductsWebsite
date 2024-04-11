$(document).ready(function () {
  $(".form-add-blog").submit(function (e) {
    e.preventDefault();
    var fileInput1 = document.getElementById("product_main");
    var selectedFile1 = fileInput1.files[0];

    // Lấy giá trị ngày tháng trong input
    const dateValue = $("#input-date").val();

    // Định dạng lại giá trị ngày tháng thành "dd/mm/yyyy"
    const formattedDate = formatDate(dateValue);

    var blogName = $("#input-blog-name").val();
    var description = $("#input-description").val();
    var author = $("#input-author").val();
    var fileList = [selectedFile1];
    console.log(fileList);
    console.log("formattedDate:", formattedDate);
    console.log("author:", author);
    console.log("blogName:", blogName);
    console.log("description", description);
    // Tạo đối tượng FormData
    const formData = new FormData();
    const newBlog = {
      name: blogName,
      author: author,
      date: formattedDate,
      content: description,
    };
    formData.append(
      "data",
      new Blob([JSON.stringify(newBlog)], { type: "application/json" })
    );

    fileList.forEach((item) => {
      formData.append("file", item);
    });

    axios
      .post("http://localhost:8080/blog", formData, {
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
          alert("Thêm dữ liệu thành công");
          location.reload();
        }
      })
      .catch((err) => {
        console.log(err);
      });
  });
});

// Hàm để định dạng lại giá trị ngày tháng
function formatDate(dateString) {
  const parts = dateString.split("-");
  const day = parts[2];
  const month = parts[1];
  const year = parts[0];
  return `${day}/${month}/${year}`;
}
